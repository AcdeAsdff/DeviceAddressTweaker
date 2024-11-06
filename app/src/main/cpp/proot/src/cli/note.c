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

#include <errno.h>  /* errno, */
#include <string.h> /* strerror(3), */
#include <stdarg.h> /* va_*, */
#include <stdio.h>  /* vfprintf(3), */
#include <limits.h> /* INT_MAX, */

#include "../cli/note.h"
#include "../tracee/tracee.h"
#include "../../proot-note-logger.h"

int global_verbose_level;
const char *global_tool_name;

/**
 * Print @message to the standard error stream according to its
 * @severity and @origin.
 */
void note(const Tracee *tracee, Severity severity, Origin origin, const char *message, ...)
{
	const char *tool_name;
	va_list extra_params;
	int verbose_level;

	if (tracee == NULL) {
		verbose_level = global_verbose_level;
		tool_name     = global_tool_name ?: "";
	}
	else {
		verbose_level = tracee->verbose;
		tool_name     = tracee->tool_name;
	}

	if (verbose_level < 0 && severity != ERROR)
		return;

	switch (severity) {
	case WARNING:
//        NOTE_LOGD("%s warning: ", tool_name);

//            NOTE_LOGD("warning: ");
		break;

	case ERROR:
//        NOTE_LOGD("error: ");
		break;

	case INFO:
	default:
//        NOTE_LOGD("info: ");
		break;
	}

	if (origin == TALLOC){
//        NOTE_LOGD("talloc: ");
    }

	va_start(extra_params, message);
    NOTE_LOGD(message, extra_params);
	va_end(extra_params);

	switch (origin) {
	case SYSTEM:
        NOTE_LOGD(": ");
		perror(NULL);
		break;

	case TALLOC:
		break;

	case INTERNAL:
	case USER:
	default:
        NOTE_LOGD("\n");
		break;
	}

	return;
}

