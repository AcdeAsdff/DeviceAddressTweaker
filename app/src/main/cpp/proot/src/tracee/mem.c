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

#include <sys/ptrace.h> /* ptrace(2), PTRACE_*, */
#include <sys/types.h>  /* pid_t, size_t, */
#include <stdlib.h>     /* NULL, */
#include <stddef.h>     /* offsetof(), */
#include <sys/user.h>   /* struct user*, */
#include <errno.h>      /* errno, */
#include <assert.h>     /* assert(3), */
#include <sys/wait.h>   /* waitpid(2), */
#include <string.h>     /* memcpy(3), */
#include <stdint.h>     /* uint*_t, */
#include <sys/uio.h>    /* process_vm_*, struct iovec, */
#include <unistd.h>     /* sysconf(3), */
#include <sys/mman.h>   /* mmap(2), munmap(2), MAP_*, */

#include "../tracee/mem.h"
#include "../tracee/abi.h"
#include "../syscall/heap.h"
#include "../arch.h"            /* word_t, NO_MISALIGNED_ACCESS */
//#include "../build.h"           /* HAVE_PROCESS_VM,  */
#include "../cli/note.h"

/**
 * Load the word at the given @address, potentially *not* aligned.
 */
static inline word_t load_word(const void *address)
{
#ifdef NO_MISALIGNED_ACCESS
	if (((word_t)address) % sizeof(word_t) == 0)
		return *(word_t *)address;
	else {
		word_t value;
		memcpy(&value, address, sizeof(word_t));
		return value;
	}
#else
	return *(word_t *)address;
#endif
}

/**
 * Store the word with the given @value to the given @address,
 * potentially *not* aligned.
 */
static inline void store_word(void *address, word_t value)
{
#ifdef NO_MISALIGNED_ACCESS
	if (((word_t)address) % sizeof(word_t) == 0)
		*((word_t *)address) = value;
	else
		memcpy(address, &value, sizeof(word_t));
#else
	*((word_t *)address) = value;
#endif
}

/**
 * Copy @size bytes from the buffer @src_tracer to the address
 * @dest_tracee within the memory space of the @tracee process. It
 * returns -errno if an error occured, otherwise 0.
 */
int write_data(const Tracee *tracee, word_t dest_tracee, const void *src_tracer, word_t size)
{
	word_t *src  = (word_t *)src_tracer;
	word_t *dest = (word_t *)dest_tracee;

	long   status;
	word_t word, i, j;
	word_t nb_trailing_bytes;
	word_t nb_full_words;

	uint8_t *last_dest_word;
	uint8_t *last_src_word;

#if defined(HAVE_PROCESS_VM)
	struct iovec local;
	struct iovec remote;

	local.iov_base = src;
	local.iov_len  = size;

	remote.iov_base = dest;
	remote.iov_len  = size;

	status = process_vm_writev(tracee->pid, &local, 1, &remote, 1, 0);
	if ((size_t) status == size)
		return 0;
	/* Fallback to ptrace if something went wrong.  */

#endif /* HAVE_PROCESS_VM */

	nb_trailing_bytes = size % sizeof(word_t);
	nb_full_words     = (size - nb_trailing_bytes) / sizeof(word_t);

	/* Copy one word by one word, except for the last one. */
	for (i = 0; i < nb_full_words; i++) {
		status = ptrace(PTRACE_POKEDATA, tracee->pid, dest + i, load_word(&src[i]));
		if (status < 0) {
			note(tracee, WARNING, SYSTEM, "ptrace(POKEDATA)");
			return -EFAULT;
		}
	}

	if (nb_trailing_bytes == 0)
		return 0;

	/* Copy the bytes in the last word carefully since we have to
	 * overwrite only the relevant ones. */

	word = ptrace(PTRACE_PEEKDATA, tracee->pid, dest + i, NULL);
	if (errno != 0) {
		note(tracee, WARNING, SYSTEM, "ptrace(PEEKDATA)");
		return -EFAULT;
	}

	last_dest_word = (uint8_t *)&word;
	last_src_word  = (uint8_t *)&src[i];

	for (j = 0; j < nb_trailing_bytes; j++)
		last_dest_word[j] = last_src_word[j];

	status = ptrace(PTRACE_POKEDATA, tracee->pid, dest + i, word);
	if (status < 0) {
		note(tracee, WARNING, SYSTEM, "ptrace(POKEDATA)");
		return -EFAULT;
	}

	return 0;
}

/**
 * Gather the @src_tracer_count buffers pointed to by @src_tracer to
 * the address @dest_tracee within the memory space of the @tracee
 * process.  This function returns -errno if an error occured,
 * otherwise 0.
 */
int writev_data(const Tracee *tracee, word_t dest_tracee, const struct iovec *src_tracer, int src_tracer_count)
{
	size_t size;
	int status;
	int i;

#if defined(HAVE_PROCESS_VM)
	struct iovec remote;

	for (i = 0, size = 0; i < src_tracer_count; i++)
		size += src_tracer[i].iov_len;

	remote.iov_base = (word_t *)dest_tracee;
	remote.iov_len  = size;

	status = process_vm_writev(tracee->pid, src_tracer, src_tracer_count, &remote, 1, 0);
	if ((size_t) status == size)
		return 0;
	/* Fallback to iterative-write if something went wrong.  */

#endif /* HAVE_PROCESS_VM */

	for (i = 0, size = 0; i < src_tracer_count; i++) {
		status = write_data(tracee, dest_tracee + size,
				src_tracer[i].iov_base, src_tracer[i].iov_len);
		if (status < 0)
			return status;

		size += src_tracer[i].iov_len;
	}

	return 0;
}

/**
 * Copy @size bytes to the buffer @dest_tracer from the address
 * @src_tracee within the memory space of the @tracee process. It
 * returns -errno if an error occured, otherwise 0.
 */
int read_data(const Tracee *tracee, void *dest_tracer, word_t src_tracee, word_t size)
{
	word_t *src  = (word_t *)src_tracee;
	word_t *dest = (word_t *)dest_tracer;

	word_t nb_trailing_bytes;
	word_t nb_full_words;
	word_t word, i, j;

	uint8_t *last_src_word;
	uint8_t *last_dest_word;

#if defined(HAVE_PROCESS_VM)
	long status;
	struct iovec local;
	struct iovec remote;

	local.iov_base = dest;
	local.iov_len  = size;

	remote.iov_base = src;
	remote.iov_len  = size;

	status = process_vm_readv(tracee->pid, &local, 1, &remote, 1, 0);
	if ((size_t) status == size)
		return 0;
	/* Fallback to ptrace if something went wrong.  */

#endif /* HAVE_PROCESS_VM */

	nb_trailing_bytes = size % sizeof(word_t);
	nb_full_words     = (size - nb_trailing_bytes) / sizeof(word_t);

	/* Copy one word by one word, except for the last one. */
	for (i = 0; i < nb_full_words; i++) {
		word = ptrace(PTRACE_PEEKDATA, tracee->pid, src + i, NULL);
		if (errno != 0) {
			note(tracee, WARNING, SYSTEM, "ptrace(PEEKDATA)");
			return -EFAULT;
		}
		store_word(&dest[i], word);
	}

	if (nb_trailing_bytes == 0)
		return 0;

	/* Copy the bytes from the last word carefully since we have
	 * to not overwrite the bytes lying beyond @dest_tracer. */

	word = ptrace(PTRACE_PEEKDATA, tracee->pid, src + i, NULL);
	if (errno != 0) {
		note(tracee, WARNING, SYSTEM, "ptrace(PEEKDATA)");
		return -EFAULT;
	}

	last_dest_word = (uint8_t *)&dest[i];
	last_src_word  = (uint8_t *)&word;

	for (j = 0; j < nb_trailing_bytes; j++)
		last_dest_word[j] = last_src_word[j];

	return 0;
}

/**
 * Copy to @dest_tracer at most @max_size bytes from the string
 * pointed to by @src_tracee within the memory space of the @tracee
 * process. This function returns -errno on error, otherwise
 * it returns the number in bytes of the string, including the
 * end-of-string terminator.
 */
int read_string(const Tracee *tracee, char *dest_tracer, word_t src_tracee, word_t max_size)
{
	word_t *src  = (word_t *)src_tracee;
	word_t *dest = (word_t *)dest_tracer;

	word_t nb_trailing_bytes;
	word_t nb_full_words;
	word_t word, i, j;

	uint8_t *src_word;
	uint8_t *dest_word;

#if defined(HAVE_PROCESS_VM)
	/* [process_vm] system calls do not check the memory regions
	 * in the remote process until just before doing the
	 * read/write.  Consequently, a partial read/write [1] may
	 * result if one of the remote_iov elements points to an
	 * invalid memory region in the remote process.  No further
	 * reads/writes will be attempted beyond that point.  Keep
	 * this in mind when attempting to read data of unknown length
	 * (such as C strings that are null-terminated) from a remote
	 * process, by avoiding spanning memory pages (typically 4KiB)
	 * in a single remote iovec element.  (Instead, split the
	 * remote read into two remote_iov elements and have them
	 * merge back into a single write local_iov entry.  The first
	 * read entry goes up to the page boundary, while the second
	 * starts on the next page boundary.).
	 *
	 * [1] Partial transfers apply at the granularity of iovec
	 * elements. These system calls won't perform a partial
	 * transfer that splits a single iovec element.
	 *
	 * -- man 2 process_vm_readv
	 */
	long status;
	size_t size;
	size_t offset;
	struct iovec local;
	struct iovec remote;

	static size_t chunk_size = 0;
	static uintptr_t chunk_mask;

	/* A chunk shall not cross a page boundary.  */
	if (chunk_size == 0) {
		chunk_size = sysconf(_SC_PAGE_SIZE);
		chunk_size = (chunk_size > 0 && chunk_size < 1024 ? chunk_size : 1024);
		chunk_mask = ~(chunk_size - 1);
	}

	/* Read the string by chunk.  */
	offset = 0;
	do {
		uintptr_t current_chunk = (src_tracee + offset) & chunk_mask;
		uintptr_t next_chunk    = current_chunk + chunk_size;

		/* Compute the number of bytes available up to the
		 * next chunk or up to max_size.  */
		size = next_chunk - (src_tracee + offset);
		size = (size < max_size - offset ? size : max_size - offset);

		local.iov_base = (uint8_t *)dest + offset;
		local.iov_len  = size;

		remote.iov_base = (uint8_t *)src + offset;
		remote.iov_len  = size;

		status = process_vm_readv(tracee->pid, &local, 1, &remote, 1, 0);
		if ((size_t) status != size)
			goto fallback;

		status = strnlen(local.iov_base, size);
		if ((size_t) status < size) {
			size = offset + status + 1;
			assert(size <= max_size);
			return size;
		}

		offset += size;
	} while (offset < max_size);
	assert(offset == max_size);

	/* Fallback to ptrace if something went wrong.  */
fallback:
#endif /* HAVE_PROCESS_VM */

	nb_trailing_bytes = max_size % sizeof(word_t);
	nb_full_words     = (max_size - nb_trailing_bytes) / sizeof(word_t);

	/* Copy one word by one word, except for the last one. */
	for (i = 0; i < nb_full_words; i++) {
		word = ptrace(PTRACE_PEEKDATA, tracee->pid, src + i, NULL);
		if (errno != 0)
			return -EFAULT;

		store_word(&dest[i], word);

		/* Stop once an end-of-string is detected. */
		src_word = (uint8_t *)&word;
		for (j = 0; j < sizeof(word_t); j++)
			if (src_word[j] == '\0')
				return i * sizeof(word_t) + j + 1;
	}

	/* Copy the bytes from the last word carefully since we have
	 * to not overwrite the bytes lying beyond @dest_tracer. */

	word = ptrace(PTRACE_PEEKDATA, tracee->pid, src + i, NULL);
	if (errno != 0)
		return -EFAULT;

	dest_word = (uint8_t *)&dest[i];
	src_word  = (uint8_t *)&word;

	for (j = 0; j < nb_trailing_bytes; j++) {
		dest_word[j] = src_word[j];
		if (src_word[j] == '\0')
			break;
	}

	return i * sizeof(word_t) + j + 1;
}

/**
 * Return the value of the word at the given @address in the @tracee's
 * memory space.  The caller must test errno to check if an error
 * occured.
 */
word_t peek_word(const Tracee *tracee, word_t address)
{
	word_t result = 0;

#if defined(HAVE_PROCESS_VM)
	int status;
	struct iovec local;
	struct iovec remote;

	local.iov_base = &result;
	local.iov_len  = sizeof_word(tracee);

	remote.iov_base = (void *)address;
	remote.iov_len  = sizeof_word(tracee);

	errno = 0;
	status = process_vm_readv(tracee->pid, &local, 1, &remote, 1, 0);
	if (status > 0)
		return result;
	/* Fallback to ptrace if something went wrong.  */
#endif
	errno = 0;
	result = (word_t) ptrace(PTRACE_PEEKDATA, tracee->pid, address, NULL);

	/* From ptrace(2) manual: "Unfortunately, under Linux,
	 * different variations of this fault will return EIO or
	 * EFAULT more or less arbitrarily."  */
	if (errno == EIO)
		errno = EFAULT;

	/* Use only the 32 LSB when running a 32-bit process on a
	 * 64-bit kernel. */
	if (is_32on64_mode(tracee))
		result &= 0xFFFFFFFF;

	return result;
}

/**
 * Set the word at the given @address in the @tracee's memory space to
 * the given @value.  The caller must test errno to check if an error
 * occured.
 */
void poke_word(const Tracee *tracee, word_t address, word_t value)
{
	word_t tmp;

#if defined(HAVE_PROCESS_VM)
	int status;
	struct iovec local;
	struct iovec remote;

	/* Note: &value points to the 32 LSB on 64-bit little-endian
	 * architecture.  */
	local.iov_base = &value;
	local.iov_len  = sizeof_word(tracee);

	remote.iov_base = (void *)address;
	remote.iov_len  = sizeof_word(tracee);

	errno = 0;
	status = process_vm_writev(tracee->pid, &local, 1, &remote, 1, 0);
	if (status > 0)
		return;
	/* Fallback to ptrace if something went wrong.  */
#endif
	/* Don't overwrite the 32 MSB when running a 32-bit process on
	 * a 64-bit kernel. */
	if (is_32on64_mode(tracee)) {
		errno = 0;
		tmp = (word_t) ptrace(PTRACE_PEEKDATA, tracee->pid, address, NULL);
		if (errno != 0)
			return;

		value |= (tmp & 0xFFFFFFFF00000000ULL);
	}

	errno = 0;
	(void) ptrace(PTRACE_POKEDATA, tracee->pid, address, value);

	/* From ptrace(2) manual: "Unfortunately, under Linux,
	 * different variations of this fault will return EIO or
	 * EFAULT more or less arbitrarily."  */
	if (errno == EIO)
		errno = EFAULT;

	return;
}

/**
 * Allocate @size bytes in the @tracee's memory space.  This function
 * returns the address of the allocated memory in the @tracee's memory
 * space, otherwise 0 if an error occured.
 */
word_t alloc_mem(Tracee *tracee, ssize_t size)
{
	word_t stack_pointer;

	/* This function should be called in sysenter only since the
	 * stack pointer is systematically restored at the end of
	 * sysexit (except for execve, but in this case the stack
	 * pointer should be handled with care since it is used by the
	 * process to retrieve argc, argv, envp, and auxv).  */
	assert(IS_IN_SYSENTER(tracee));

	/* Get the current value of the stack pointer from the tracee's
	 * USER area. */
	stack_pointer = peek_reg(tracee, CURRENT, STACK_POINTER);

	/* Some ABIs specify an amount of bytes after the stack
	 * pointer that shall not be used by anything but the compiler
	 * (for optimization purpose).  */
	if (stack_pointer == peek_reg(tracee, ORIGINAL, STACK_POINTER))
		size += RED_ZONE_SIZE;

	/* Align the stack */
	size = ((size - 1) / STACK_ALIGNMENT + 1) * STACK_ALIGNMENT;

	/* Sanity check. */
	if (   (size > 0 && stack_pointer <= (word_t) size)
	    || (size < 0 && stack_pointer >= ULONG_MAX + size)) {
		note(tracee, WARNING, INTERNAL, "integer under/overflow detected in %s",
			__FUNCTION__);
		return 0;
	}

	/* Remember the stack grows downward. */
	stack_pointer -= size;

	/* Set the new value of the stack pointer in the tracee's USER
	 * area. */
	poke_reg(tracee, STACK_POINTER, stack_pointer);
	return stack_pointer;
}

/**
 * Clear @size bytes at the given @address in the @tracee's memory
 * space.  This function returns -errno if an error occured, otherwise
 * 0.
 */
int clear_mem(const Tracee *tracee, word_t address, size_t size)
{
	int status;
	void *zeros;

	zeros = mmap(NULL, size, PROT_READ, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
	if (zeros == MAP_FAILED)
		return -errno;

	status = write_data(tracee, address, zeros, size);
	munmap(zeros, size);
	return status;
}
