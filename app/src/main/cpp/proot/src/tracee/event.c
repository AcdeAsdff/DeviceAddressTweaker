/* -*- c-set-style: "K&R"; c-basic-offset: 8 -*-
 *
 * This file is part of PRoot.
 *
 * Copyright (C) 2015 STMicroelectronics
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

#include <stdio.h>
#include <sched.h>      /* CLONE_*,  */
#include <sys/types.h>  /* pid_t, */
#include <sys/ptrace.h> /* ptrace(1), PTRACE_*, */
#include <sys/types.h>  /* waitpid(2), */
#include <sys/wait.h>   /* waitpid(2), */
#include <sys/utsname.h> /* uname(2), */
#include <unistd.h>     /* fork(2), chdir(2), getpid(2), */
#include <string.h>     /* strcmp(3), */
#include <errno.h>      /* errno(3), */
    /* bool, true, false, */
#include <assert.h>     /* assert(3), */
#include <stdlib.h>     /* atexit(3), getenv(3), */
#include "../../talloc/talloc.h"     /* talloc_*, */
#include <inttypes.h>   /* PRI*, */
#include <linux/version.h> /* KERNEL_VERSION, */
#include <strings.h>

#include "../tracee/event.h"
#include "../cli/note.h"
#include "../path/path.h"
#include "../path/binding.h"
#include "../syscall/syscall.h"
#include "../syscall/seccomp.h"
#include "../ptrace/wait.h"
#include "../extension/extension.h"
#include "../execve/elf.h"

#include "../attribute.h"
#include "../compat.h"
#include "tracee.h"


/**
 * Start @tracee->exe with the given @argv[].  This function
 * returns -errno if an error occurred, otherwise 0.
 */
int launch_process(Tracee *tracee, char *const *pString)
{
	char *const default_argv[] = { "-sh", NULL };
	long status;
	pid_t pid;

	/* Warn about open file descriptors. They won't be
	 * translated until they are closed. */
	list_open_fd(tracee);

	pid = fork();
	switch(pid) {
	case -1:
		note(tracee, ERROR, SYSTEM, "fork()");
		return -errno;

	case 0: /* child */
		/* Declare myself as ptraceable before executing the
		 * requested program. */
		status = ptrace(PTRACE_TRACEME, 0, NULL, NULL);
		if (status < 0) {
			note(tracee, ERROR, SYSTEM, "ptrace(TRACEME)");
			return -errno;
		}

		/* Synchronize with the tracer's event loop.  Without
		 * this trick the tracer only sees the "return" from
		 * the next execve(2) so PRoot wouldn't handle the
		 * interpreter/runner.  I also verified that strace
		 * does the same thing. */
		kill(getpid(), SIGSTOP);

		/* Improve performance by using seccomp mode 2, unless
		 * this support is explicitly disabled.  */
		if (getenv("PROOT_NO_SECCOMP") == NULL)
			(void) enable_syscall_filtering(tracee);

		/* Now process is ptraced, so the current rootfs is already the
		 * guest rootfs.  Note: Valgrind can't handle execve(2) on
		 * "foreign" binaries (ENOEXEC) but can handle execvp(3) on such
		 * binaries.  */
		execvp(tracee->exe,default_argv);
		return -errno;

	default: /* parent */
		/* We know the pid of the first tracee now.  */
		tracee->pid = pid;
		return 0;
	}

	/* Never reached.  */
	return -ENOSYS;
}

/* Send the KILL signal to all tracees when PRoot has received a fatal
 * signal.  */
static void kill_all_tracees2(int signum, siginfo_t *siginfo UNUSED, void *ucontext UNUSED)
{
	note(NULL, WARNING, INTERNAL, "signal %d received from process %d",
		signum, siginfo->si_pid);
	kill_all_tracees();

	/* Exit immediately for system signals (segmentation fault,
	 * illegal instruction, ...), otherwise exit cleanly through
	 * the event loop.  */
	if (signum != SIGQUIT)
		_exit(EXIT_FAILURE);
}

/**
 * Helper for print_talloc_hierarchy().
 */
static void print_talloc_chunk(const void *ptr, int depth, int max_depth UNUSED,
			int is_ref, void *data UNUSED)
{
	const char *name;
	size_t count;
	size_t size;

	name = talloc_get_name(ptr);
	size = talloc_get_size(ptr);
	count = talloc_reference_count(ptr);

	if (depth == 0)
		return;

	while (depth-- > 1)
		fprintf(stderr, "\t");

	fprintf(stderr, "%-16s ", name);

	if (is_ref)
		fprintf(stderr, "-> %-8p", ptr);
	else {
		fprintf(stderr, "%-8p  %zd bytes  %zd ref'", ptr, size, count);

		if (name[0] == '$') {
			fprintf(stderr, "\t(\"%s\")", (char *)ptr);
		}
		if (name[0] == '@') {
			char **argv;
			int i;

			fprintf(stderr, "\t(");
			for (i = 0, argv = (char **)ptr; argv[i] != NULL; i++)
				fprintf(stderr, "\"%s\", ", argv[i]);
			fprintf(stderr, ")");
		}
		else if (strcmp(name, "Tracee") == 0) {
			fprintf(stderr, "\t(pid = %d, parent = %p)",
				((Tracee *)ptr)->pid, ((Tracee *)ptr)->parent);
		}
		else if (strcmp(name, "Bindings") == 0) {
			Tracee *tracee;

			tracee = TRACEE(ptr);

			if (ptr == tracee->fs->bindings.pending)
				fprintf(stderr, "\t(pending)");
			else if (ptr == tracee->fs->bindings.guest)
				fprintf(stderr, "\t(guest)");
			else if (ptr == tracee->fs->bindings.host)
				fprintf(stderr, "\t(host)");
		}
		else if (strcmp(name, "Binding") == 0) {
			Binding *binding = (Binding *)ptr;
			fprintf(stderr, "\t(%s:%s)", binding->host.path, binding->guest.path);
		}
	}

	fprintf(stderr, "\n");
}

/* Print on stderr the complete talloc hierarchy.  */
static void print_talloc_hierarchy(int signum, siginfo_t *siginfo UNUSED, void *ucontext UNUSED)
{
	switch (signum) {
	case SIGUSR1:
		talloc_report_depth_cb(NULL, 0, 100, print_talloc_chunk, NULL);
		break;

	case SIGUSR2:
		talloc_report_depth_file(NULL, 0, 100, stderr);
		break;

	default:
		break;
	}
}

static int last_exit_status = -1;

/**
 * Check if kernel >= 4.8
 */
static bool is_kernel_4_8(void)
{
	static int version_48 = -1;
	int major = 0;
	int minor = 0;

	if (version_48 != -1)
		return version_48;

	version_48 = false;

	struct utsname utsname;

	if (uname(&utsname) < 0)
		return false;

	sscanf(utsname.release, "%d.%d", &major, &minor);

	if ((major == 4 && minor >= 8) || major > 4)
		version_48 = true;

	return version_48;
}

/**
 * Check if this instance of PRoot can *technically* handle @tracee.
 */
static void check_architecture(Tracee *tracee)
{
	struct utsname utsname;
	ElfHeader elf_header;
	char path[PATH_MAX];
	int status;

	if (tracee->exe == NULL)
		return;

	status = translate_path(tracee, path, AT_FDCWD, tracee->exe, false);
	if (status < 0)
		return;

	status = open_elf(path, &elf_header);
	if (status < 0)
		return;
	close(status);

	if (!IS_CLASS64(elf_header) || sizeof(word_t) == sizeof(uint64_t))
		return;

	note(tracee, ERROR, USER,
		"'%s' is a 64-bit program whereas this version of "
		"%s handles 32-bit programs only", path, tracee->tool_name);

	status = uname(&utsname);
	if (status < 0)
		return;

	if (strcmp(utsname.machine, "x86_64") != 0)
		return;

	note(tracee, INFO, USER,
		"A 64-bit version that supports 32-bit binaries is required");
}

/**
 * Wait then handle any event from any tracee.  This function returns
 * the exit status of the last terminated program.
 */

int event_loop()
{
	struct sigaction signal_action;
	long status;
	int signum;

	/* Kill all tracees when exiting.  */
	status = atexit(kill_all_tracees);
	if (status != 0)
		note(NULL, WARNING, INTERNAL, "atexit() failed");

	/* All signals are blocked when the signal handler is called.
	 * SIGINFO is used to know which process has signaled us and
	 * RESTART is used to restart waitpid(2) seamlessly.  */
	bzero(&signal_action, sizeof(signal_action));
	signal_action.sa_flags = SA_SIGINFO | SA_RESTART;
	status = sigfillset(&signal_action.sa_mask);
	if (status < 0)
		note(NULL, WARNING, SYSTEM, "sigfillset()");

	/* Handle all signals.  */
	for (signum = 0; signum < SIGRTMAX; signum++) {
		switch (signum) {
		case SIGQUIT:
		case SIGILL:
		case SIGABRT:
		case SIGFPE:
		case SIGSEGV:
			/* Kill all tracees on abnormal termination
			 * signals.  This ensures no process is left
			 * untraced.  */
			signal_action.sa_sigaction = kill_all_tracees2;
			break;

		case SIGUSR1:
		case SIGUSR2:
			/* Print on stderr the complete talloc
			 * hierarchy, useful for debug purpose.  */
			signal_action.sa_sigaction = print_talloc_hierarchy;
			break;

		case SIGCHLD:
		case SIGCONT:
		case SIGSTOP:
		case SIGTSTP:
		case SIGTTIN:
		case SIGTTOU:
			/* The default action is OK for these signals,
			 * they are related to tty and job control.  */
			continue;

		default:
			/* Ignore all other signals, including
			 * terminating ones (^C for instance). */
			signal_action.sa_sigaction = (void *)SIG_IGN;
			break;
		}

		status = sigaction(signum, &signal_action, NULL);
		if (status < 0 && errno != EINVAL)
			note(NULL, WARNING, SYSTEM, "sigaction(%d)", signum);
	}

	while (1) {
		int tracee_status;
		Tracee *tracee;
		int signal;
		pid_t pid;

		/* This is the only safe place to free tracees.  */
		free_terminated_tracees();

		/* Wait for the next tracee's stop. */
		pid = waitpid(-1, &tracee_status, __WALL);
		if (pid < 0) {
			if (errno != ECHILD) {
				note(NULL, ERROR, SYSTEM, "waitpid()");
				return EXIT_FAILURE;
			}
			break;
		}

		/* Get information about this tracee. */
		tracee = get_tracee(NULL, pid, true);
		assert(tracee != NULL);

		tracee->running = false;

		VERBOSE(tracee, 6, "vpid %" PRIu64 ": got event %x",
			tracee->vpid, tracee_status);

		status = notify_extensions(tracee, NEW_STATUS, tracee_status, 0);
		if (status != 0)
			continue;

		if (tracee->as_ptracee.ptracer != NULL) {
			bool keep_stopped = handle_ptracee_event(tracee, tracee_status);
			if (keep_stopped)
				continue;
		}

		signal = handle_tracee_event(tracee, tracee_status);
		(void) restart_tracee(tracee, signal);
	}

	return last_exit_status;
}
extern int event_loop_clone()
{
    struct sigaction signal_action;
    long status;
    int signum;

    /* Kill all tracees when exiting.  */
    status = atexit(kill_all_tracees);
    if (status != 0)
        note(NULL, WARNING, INTERNAL, "atexit() failed");

    /* All signals are blocked when the signal handler is called.
     * SIGINFO is used to know which process has signaled us and
     * RESTART is used to restart waitpid(2) seamlessly.  */
    bzero(&signal_action, sizeof(signal_action));
    signal_action.sa_flags = SA_SIGINFO | SA_RESTART;
    status = sigfillset(&signal_action.sa_mask);
    if (status < 0)
        note(NULL, WARNING, SYSTEM, "sigfillset()");

    /* Handle all signals.  */
    for (signum = 0; signum < SIGRTMAX; signum++) {
        switch (signum) {
            case SIGQUIT:
            case SIGILL:
            case SIGABRT:
            case SIGFPE:
            case SIGSEGV:
                /* Kill all tracees on abnormal termination
                 * signals.  This ensures no process is left
                 * untraced.  */
                signal_action.sa_sigaction = kill_all_tracees2;
                break;

            case SIGUSR1:
            case SIGUSR2:
                /* Print on stderr the complete talloc
                 * hierarchy, useful for debug purpose.  */
                signal_action.sa_sigaction = print_talloc_hierarchy;
                break;

            case SIGCHLD:
            case SIGCONT:
            case SIGSTOP:
            case SIGTSTP:
            case SIGTTIN:
            case SIGTTOU:
                /* The default action is OK for these signals,
                 * they are related to tty and job control.  */
                continue;

            default:
                /* Ignore all other signals, including
                 * terminating ones (^C for instance). */
                signal_action.sa_sigaction = (void *)SIG_IGN;
                break;
        }

        status = sigaction(signum, &signal_action, NULL);
        if (status < 0 && errno != EINVAL)
            note(NULL, WARNING, SYSTEM, "sigaction(%d)", signum);
    }

    while (1) {
        int tracee_status;
        Tracee *tracee;
        int signal;
        pid_t pid;

        /* This is the only safe place to free tracees.  */
        free_terminated_tracees();

        /* Wait for the next tracee's stop. */
        pid = waitpid(-1, &tracee_status, __WALL);
        if (pid < 0) {
            if (errno != ECHILD) {
                note(NULL, ERROR, SYSTEM, "waitpid()");
                return EXIT_FAILURE;
            }
            break;
        }

        /* Get information about this tracee. */
        tracee = get_tracee(NULL, pid, true);
        assert(tracee != NULL);

        tracee->running = false;

        VERBOSE(tracee, 6, "vpid %" PRIu64 ": got event %x",
                tracee->vpid, tracee_status);

        status = notify_extensions(tracee, NEW_STATUS, tracee_status, 0);
        if (status != 0)
            continue;

        if (tracee->as_ptracee.ptracer != NULL) {
            bool keep_stopped = handle_ptracee_event(tracee, tracee_status);
            if (keep_stopped)
                continue;
        }

        signal = handle_tracee_event(tracee, tracee_status);
        (void) restart_tracee(tracee, signal);
    }

    return last_exit_status;
}
/**
 * For kernels >= 4.8.0
 * Handle the current event (@tracee_status) of the given @tracee.
 * This function returns the "computed" signal that should be used to
 * restart the given @tracee.
 */
static int handle_tracee_event_kernel_4_8(Tracee *tracee, int tracee_status)
{
	static bool seccomp_detected = false;
	static bool seccomp_enabled = false; /* added for 4.8.0 */
	long status;
	int signal;

	/* Don't overwrite restart_how if it is explicitly set
	 * elsewhere, i.e in the ptrace emulation when single
	 * stepping.  */
	if (tracee->restart_how == 0) {
		/* When seccomp is enabled, all events are restarted in
		 * non-stop mode, but this default choice could be overwritten
		 * later if necessary.  The check against "sysexit_pending"
		 * ensures PTRACE_SYSCALL (used to hit the exit stage under
		 * seccomp) is not cleared due to an event that would happen
		 * before the exit stage, eg. PTRACE_EVENT_EXEC for the exit
		 * stage of execve(2).  */
		if (tracee->seccomp == ENABLED && !tracee->sysexit_pending)
			tracee->restart_how = PTRACE_CONT;
		else
			tracee->restart_how = PTRACE_SYSCALL;
	}

	/* Not a signal-stop by default.  */
	signal = 0;

	if (WIFEXITED(tracee_status)) {
		last_exit_status = WEXITSTATUS(tracee_status);
		VERBOSE(tracee, 1,
			"vpid %" PRIu64 ": exited with status %d",
			tracee->vpid, last_exit_status);
		terminate_tracee(tracee);
	}
	else if (WIFSIGNALED(tracee_status)) {
		check_architecture(tracee);
		VERBOSE(tracee, 1,
			"vpid %" PRIu64 ": terminated with signal %d",
			tracee->vpid, WTERMSIG(tracee_status));
		terminate_tracee(tracee);
	}
	else if (WIFSTOPPED(tracee_status)) {
		/* Don't use WSTOPSIG() to extract the signal
		 * since it clears the PTRACE_EVENT_* bits. */
		signal = (tracee_status & 0xfff00) >> 8;

		switch (signal) {
			static bool deliver_sigtrap = false;

		case SIGTRAP: {
			const unsigned long default_ptrace_options = (
				PTRACE_O_TRACESYSGOOD	|
				PTRACE_O_TRACEFORK	|
				PTRACE_O_TRACEVFORK	|
				PTRACE_O_TRACEVFORKDONE	|
				PTRACE_O_TRACEEXEC	|
				PTRACE_O_TRACECLONE	|
				PTRACE_O_TRACEEXIT);

			/* Distinguish some events from others and
			 * automatically trace each new process with
			 * the same options.
			 *
			 * Note that only the first bare SIGTRAP is
			 * related to the tracing loop, others SIGTRAP
			 * carry tracing information because of
			 * TRACE*FORK/CLONE/EXEC.  */
			if (deliver_sigtrap)
				break;  /* Deliver this signal as-is.  */

			deliver_sigtrap = true;

			/* Try to enable seccomp mode 2...  */
			status = ptrace(PTRACE_SETOPTIONS, tracee->pid, NULL,
					default_ptrace_options | PTRACE_O_TRACESECCOMP);
			if (status < 0) {
				seccomp_enabled = false;
				/* ... otherwise use default options only.  */
				status = ptrace(PTRACE_SETOPTIONS, tracee->pid, NULL,
						default_ptrace_options);
				if (status < 0) {
					note(tracee, ERROR, SYSTEM, "ptrace(PTRACE_SETOPTIONS)");
					exit(EXIT_FAILURE);
				}
			}
			else {
				if (getenv("PROOT_NO_SECCOMP") == NULL)
					seccomp_enabled = true;
			}
		}
			/* Fall through. */
		case SIGTRAP | PTRACE_EVENT_SECCOMP2 << 8:
		case SIGTRAP | PTRACE_EVENT_SECCOMP << 8:

			if (!seccomp_detected && seccomp_enabled) {
				VERBOSE(tracee, 1, "ptrace acceleration (seccomp mode 2) enabled");
				tracee->seccomp = ENABLED;
				seccomp_detected = true;
			}

			if (signal == (SIGTRAP | PTRACE_EVENT_SECCOMP2 << 8) ||
			    signal == (SIGTRAP | PTRACE_EVENT_SECCOMP << 8)) {

				unsigned long flags = 0;
				signal = 0;

				/* Use the common ptrace flow if seccomp was
				 * explicitly disabled for this tracee.  */
				if (tracee->seccomp != ENABLED)
					break;

				status = ptrace(PTRACE_GETEVENTMSG, tracee->pid, NULL, &flags);
				if (status < 0)
					break;

				if ((flags & FILTER_SYSEXIT) == 0) {
					tracee->restart_how = PTRACE_CONT;
					translate_syscall(tracee);

					if (tracee->seccomp == DISABLING)
						tracee->restart_how = PTRACE_SYSCALL;
					break;
				}
			}

			/* Fall through. */
		case SIGTRAP | 0x80:

			signal = 0;

			/* This tracee got signaled then freed during the
			   sysenter stage but the kernel reports the sysexit
			   stage; just discard this spurious tracee/event.  */

			if (tracee->exe == NULL) {
				tracee->restart_how = PTRACE_CONT; /* SYSCALL OR CONT */
				return 0;
			}

			switch (tracee->seccomp) {
			case ENABLED:
				if (IS_IN_SYSENTER(tracee)) {
					/* sysenter: ensure the sysexit
					 * stage will be hit under seccomp.  */
					tracee->restart_how = PTRACE_SYSCALL;
					tracee->sysexit_pending = true;
				}
				else {
					/* sysexit: the next sysenter
					 * will be notified by seccomp.  */
					tracee->restart_how = PTRACE_CONT;
					tracee->sysexit_pending = false;
				}
				/* Fall through.  */
			case DISABLED:
				translate_syscall(tracee);

				/* This syscall has disabled seccomp.  */
				if (tracee->seccomp == DISABLING) {
					tracee->restart_how = PTRACE_SYSCALL;
					tracee->seccomp = DISABLED;
				}

				break;

			case DISABLING:
				/* Seccomp was disabled by the
				 * previous syscall, but its sysenter
				 * stage was already handled.  */
				tracee->seccomp = DISABLED;
				if (IS_IN_SYSENTER(tracee))
					tracee->status = 1;
				break;
			}
			break;

		case SIGTRAP | PTRACE_EVENT_VFORK << 8:
			signal = 0;
			(void) new_child(tracee, CLONE_VFORK);
			break;

		case SIGTRAP | PTRACE_EVENT_FORK  << 8:
		case SIGTRAP | PTRACE_EVENT_CLONE << 8:
			signal = 0;
			(void) new_child(tracee, 0);
			break;

		case SIGTRAP | PTRACE_EVENT_VFORK_DONE << 8:
		case SIGTRAP | PTRACE_EVENT_EXEC  << 8:
		case SIGTRAP | PTRACE_EVENT_EXIT  << 8:
			signal = 0;
			break;

		case SIGSTOP:
			/* Stop this tracee until PRoot has received
			 * the EVENT_*FORK|CLONE notification.  */
			if (tracee->exe == NULL) {
				tracee->sigstop = SIGSTOP_PENDING;
				signal = -1;
			}

			/* For each tracee, the first SIGSTOP
			 * is only used to notify the tracer.  */
			if (tracee->sigstop == SIGSTOP_IGNORED) {
				tracee->sigstop = SIGSTOP_ALLOWED;
				signal = 0;
			}
			break;

		default:
			/* Deliver this signal as-is.  */
			break;
		}
	}

	/* Clear the pending event, if any.  */
	tracee->as_ptracee.event4.proot.pending = false;

	return signal;
}


/**
 * For kernels < 4.8.0
 * Handle the current event (@tracee_status) of the given @tracee.
 * This function returns the "computed" signal that should be used to
 * restart the given @tracee.
 */
int handle_tracee_event(Tracee *tracee, int tracee_status)
{
	static bool seccomp_detected = false;
	long status;
	int signal;

	if (is_kernel_4_8())
		return handle_tracee_event_kernel_4_8(tracee, tracee_status);
	/* Don't overwrite restart_how if it is explicitly set
	 * elsewhere, i.e in the ptrace emulation when single
	 * stepping.  */
	if (tracee->restart_how == 0) {
		/* When seccomp is enabled, all events are restarted in
		 * non-stop mode, but this default choice could be overwritten
		 * later if necessary.  The check against "sysexit_pending"
		 * ensures PTRACE_SYSCALL (used to hit the exit stage under
		 * seccomp) is not cleared due to an event that would happen
		 * before the exit stage, eg. PTRACE_EVENT_EXEC for the exit
		 * stage of execve(2).  */
		if (tracee->seccomp == ENABLED && !tracee->sysexit_pending)
			tracee->restart_how = PTRACE_CONT;
		else
			tracee->restart_how = PTRACE_SYSCALL;
	}

	/* Not a signal-stop by default.  */
	signal = 0;

	if (WIFEXITED(tracee_status)) {
		last_exit_status = WEXITSTATUS(tracee_status);
		VERBOSE(tracee, 1,
			"vpid %" PRIu64 ": exited with status %d",
			tracee->vpid, last_exit_status);
		terminate_tracee(tracee);
	}
	else if (WIFSIGNALED(tracee_status)) {
		check_architecture(tracee);
		VERBOSE(tracee, 1,
			"vpid %" PRIu64 ": terminated with signal %d",
			tracee->vpid, WTERMSIG(tracee_status));
		terminate_tracee(tracee);
	}
	else if (WIFSTOPPED(tracee_status)) {
		/* Don't use WSTOPSIG() to extract the signal
		 * since it clears the PTRACE_EVENT_* bits. */
		signal = (tracee_status & 0xfff00) >> 8;

		switch (signal) {
			static bool deliver_sigtrap = false;

		case SIGTRAP: {
			const unsigned long default_ptrace_options = (
				PTRACE_O_TRACESYSGOOD	|
				PTRACE_O_TRACEFORK	|
				PTRACE_O_TRACEVFORK	|
				PTRACE_O_TRACEVFORKDONE	|
				PTRACE_O_TRACEEXEC	|
				PTRACE_O_TRACECLONE	|
				PTRACE_O_TRACEEXIT);

			/* Distinguish some events from others and
			 * automatically trace each new process with
			 * the same options.
			 *
			 * Note that only the first bare SIGTRAP is
			 * related to the tracing loop, others SIGTRAP
			 * carry tracing information because of
			 * TRACE*FORK/CLONE/EXEC.  */
			if (deliver_sigtrap)
				break;  /* Deliver this signal as-is.  */

			deliver_sigtrap = true;

			/* Try to enable seccomp mode 2...  */
			status = ptrace(PTRACE_SETOPTIONS, tracee->pid, NULL,
					default_ptrace_options | PTRACE_O_TRACESECCOMP);
			if (status < 0) {
				/* ... otherwise use default options only.  */
				status = ptrace(PTRACE_SETOPTIONS, tracee->pid, NULL,
						default_ptrace_options);
				if (status < 0) {
					note(tracee, ERROR, SYSTEM, "ptrace(PTRACE_SETOPTIONS)");
					exit(EXIT_FAILURE);
				}
			}
		}

			/* Fall through. */
		case SIGTRAP | 0x80:
			signal = 0;

			/* This tracee got signaled then freed during the
			   sysenter stage but the kernel reports the sysexit
			   stage; just discard this spurious tracee/event.  */
			if (tracee->exe == NULL) {
				tracee->restart_how = PTRACE_CONT; /* SYSCALL OR CONT */
				return 0;
			}

			switch (tracee->seccomp) {
			case ENABLED:
				if (IS_IN_SYSENTER(tracee)) {
					/* sysenter: ensure the sysexit
					 * stage will be hit under seccomp.  */
					tracee->restart_how = PTRACE_SYSCALL;
					tracee->sysexit_pending = true;
				}
				else {
					/* sysexit: the next sysenter
					 * will be notified by seccomp.  */
					tracee->restart_how = PTRACE_CONT;
					tracee->sysexit_pending = false;
				}
				/* Fall through.  */
			case DISABLED:
				translate_syscall(tracee);

				/* This syscall has disabled seccomp.  */
				if (tracee->seccomp == DISABLING) {
					tracee->restart_how = PTRACE_SYSCALL;
					tracee->seccomp = DISABLED;
				}

				break;

			case DISABLING:
				/* Seccomp was disabled by the
				 * previous syscall, but its sysenter
				 * stage was already handled.  */
				tracee->seccomp = DISABLED;
				if (IS_IN_SYSENTER(tracee))
					tracee->status = 1;
				break;
			}
			break;

		case SIGTRAP | PTRACE_EVENT_SECCOMP2 << 8:
		case SIGTRAP | PTRACE_EVENT_SECCOMP << 8: {
			unsigned long flags = 0;

			signal = 0;

			if (!seccomp_detected) {
				VERBOSE(tracee, 1, "ptrace acceleration (seccomp mode 2) enabled");
				tracee->seccomp = ENABLED;
				seccomp_detected = true;
			}

			/* Use the common ptrace flow if seccomp was
			 * explicitely disabled for this tracee.  */
			if (tracee->seccomp != ENABLED)
				break;

			status = ptrace(PTRACE_GETEVENTMSG, tracee->pid, NULL, &flags);
			if (status < 0)
				break;

			/* Use the common ptrace flow when
			 * sysexit has to be handled.  */
			if ((flags & FILTER_SYSEXIT) != 0) {
				tracee->restart_how = PTRACE_SYSCALL;
				break;
			}

			/* Otherwise, handle the sysenter
			 * stage right now.  */
			tracee->restart_how = PTRACE_CONT;
			translate_syscall(tracee);

			/* This syscall has disabled seccomp, so move
			 * the ptrace flow back to the common path to
			 * ensure its sysexit will be handled.  */
			if (tracee->seccomp == DISABLING)
				tracee->restart_how = PTRACE_SYSCALL;
			break;
		}

		case SIGTRAP | PTRACE_EVENT_VFORK << 8:
			signal = 0;
			(void) new_child(tracee, CLONE_VFORK);
			break;

		case SIGTRAP | PTRACE_EVENT_FORK  << 8:
		case SIGTRAP | PTRACE_EVENT_CLONE << 8:
			signal = 0;
			(void) new_child(tracee, 0);
			break;

		case SIGTRAP | PTRACE_EVENT_VFORK_DONE << 8:
		case SIGTRAP | PTRACE_EVENT_EXEC  << 8:
		case SIGTRAP | PTRACE_EVENT_EXIT  << 8:
			signal = 0;
			break;

		case SIGSTOP:
			/* Stop this tracee until PRoot has received
			 * the EVENT_*FORK|CLONE notification.  */
			if (tracee->exe == NULL) {
				tracee->sigstop = SIGSTOP_PENDING;
				signal = -1;
			}

			/* For each tracee, the first SIGSTOP
			 * is only used to notify the tracer.  */
			if (tracee->sigstop == SIGSTOP_IGNORED) {
				tracee->sigstop = SIGSTOP_ALLOWED;
				signal = 0;
			}
			break;

		default:
			/* Deliver this signal as-is.  */
			break;
		}
	}

	/* Clear the pending event, if any.  */
	tracee->as_ptracee.event4.proot.pending = false;

	return signal;
}


/**
 * Restart the given @tracee with the specified @signal.  This
 * function returns false if the tracee was not restarted (error or
 * put in the "waiting for ptracee" state), otherwise true.
 */
bool restart_tracee(Tracee *tracee, int signal)
{
	int status;

	/* Put in the "stopped"/"waiting for ptracee" state?.  */
	if (tracee->as_ptracer.wait_pid != 0 || signal == -1)
		return false;

	/* Restart the tracee and stop it at the next instruction, or
	 * at the next entry or exit of a system call. */
	status = ptrace(tracee->restart_how, tracee->pid, NULL, signal);
	if (status < 0)
		return false; /* The process likely died in a syscall.  */

	VERBOSE(tracee, 6, "vpid %" PRIu64 ": restarted using %d, signal %d",
		tracee->vpid, tracee->restart_how, signal);

	tracee->restart_how = 0;
	tracee->running = true;

	return true;
}
