//
// Created by MOKO on 2024/5/22.
//


#ifndef DEVICEADDRESSTWEAKER_DEVICEADDRESSTWEALER_H
#define DEVICEADDRESSTWEAKER_DEVICEADDRESSTWEALER_H
#include <stdint.h>
typedef int (*HookFunType)(void *func, void *replace, void **backup);

typedef int (*UnhookFunType)(void *func);

typedef void (*NativeOnModuleLoaded)(const char *name, void *handle);

typedef struct {
    uint32_t version;
    HookFunType hook_func;
    UnhookFunType unhook_func;
} NativeAPIEntries;

typedef NativeOnModuleLoaded (*NativeInit)(const NativeAPIEntries *entries);

#endif //DEVICEADDRESSTWEAKER_DEVICEADDRESSTWEALER_H



