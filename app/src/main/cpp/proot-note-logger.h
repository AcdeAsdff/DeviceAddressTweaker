//
// Created by MOKO on 2024/5/28.
//

#ifndef DEVICEADDRESSTWEAKER_PROOT_NOTE_LOGGER_H
#define DEVICEADDRESSTWEAKER_PROOT_NOTE_LOGGER_H
#include "android/log.h"
#define NOTE_TAG "[linearity-proot-note]"
#define  NOTE_LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,NOTE_TAG,__VA_ARGS__)
#endif //DEVICEADDRESSTWEAKER_PROOT_NOTE_LOGGER_H
