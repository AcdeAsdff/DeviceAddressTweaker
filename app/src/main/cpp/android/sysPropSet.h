//
// Created by MOKO on 2024/5/26.
//
#define  SYSPROPSET_TAG    "[linearity-Native-sysPropSet]"
#define  LOGD_SYSPROP(...)  __android_log_print(ANDROID_LOG_DEBUG,SYSPROPSET_TAG,__VA_ARGS__)
#ifndef DEVICEADDRESSTWEAKER_SYSPROPSET_H
#define DEVICEADDRESSTWEAKER_SYSPROPSET_H

#include <jni.h>
#include "nativehelper/ScopedUtfChars.h"
jstring fake_SystemProperties_getSS(JNIEnv* env, jclass clazz, jstring keyJ,
                                    jstring defJ);
void fake_SystemProperties_add_change_callback(JNIEnv *env, jobject clazz);
void fake_SystemProperties_report_sysprop_change(JNIEnv *env, jobject clazz);
void fake_SystemProperties_set(JNIEnv *env, jobject clazz, jstring keyJ,
                               jstring valJ);
static const JNINativeMethod sysProps_Methods[] = {

    { "native_get",
      "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
      (void*) fake_SystemProperties_getSS },
    { "native_set", "(Ljava/lang/String;Ljava/lang/String;)V",
      (void*) fake_SystemProperties_set },
    { "native_add_change_callback", "()V",
      (void*) fake_SystemProperties_add_change_callback },
    { "native_report_sysprop_change", "()V",
      (void*) fake_SystemProperties_report_sysprop_change },

//    { "native_get_int", "(Ljava/lang/String;I)I",
//      (void*) fake_SystemProperties_get_integral<jint> },
//    { "native_get_long", "(Ljava/lang/String;J)J",
//      (void*) fake_SystemProperties_get_integral<jlong> },
//    { "native_get_boolean", "(Ljava/lang/String;Z)Z",
//      (void*) fake_SystemProperties_get_boolean },
//    { "native_find",
//      "(Ljava/lang/String;)J",
//      (void*) fake_SystemProperties_find },
//    { "native_get",
//      "(J)Ljava/lang/String;",
//      (void*) fake_SystemProperties_getH },
//    { "native_get_int", "(JI)I",
//      (void*) fake_SystemProperties_get_integralH<jint> },
//    { "native_get_long", "(JJ)J",
//      (void*) fake_SystemProperties_get_integralH<jlong> },
//    { "native_get_boolean", "(JZ)Z",
//      (void*) fake_SystemProperties_get_booleanH },

};
#endif //DEVICEADDRESSTWEAKER_SYSPROPSET_H
