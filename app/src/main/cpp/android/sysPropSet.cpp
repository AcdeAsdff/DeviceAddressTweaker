//
// Created by MOKO on 2024/5/26.
//
#include <jni.h>
#include "nativehelper/JNIHelp.h"
#include "nativehelper/ScopedPrimitiveArray.h"
#include "nativehelper/ScopedUtfChars.h"
#include "sys/system_properties.h"
#include <utility>
#include <optional>
#include "sysPropSet.h"
#include "../datutils.h"
#include "android/log.h"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define  TAG    "[linearity-Native-sysPropSet]"
template<typename Functor>
void ReadProperty(const prop_info* prop, Functor&& functor)
{
#if defined(__BIONIC__)
    auto thunk = [](void* cookie,
                    const char* /*name*/,
                    const char* value,
                    uint32_t /*serial*/) {
        std::forward<Functor>(*static_cast<Functor*>(cookie))(value);
    };
    __system_property_read_callback(prop, thunk, &functor);
#else
    LOG(FATAL) << "fast property access supported only on device";
#endif
}
template<typename Functor>
void ReadProperty(JNIEnv* env, jstring keyJ, Functor&& functor)
{
    ScopedUtfChars key(env, keyJ);
    if (!key.c_str()) {
        return;
    }
#if defined(__BIONIC__)
    const prop_info* prop = __system_property_find(key.c_str());
    if (!prop) {
        return;
    }
    ReadProperty(prop, std::forward<Functor>(functor));
#else
    std::forward<Functor>(functor)(
        android::base::GetProperty(key.c_str(), "").c_str());
#endif
}
jstring fake_SystemProperties_getSS(JNIEnv* env, jclass clazz, jstring keyJ,
                                    jstring defJ)
{
    jstring ret = defJ;
    ReadProperty(env, keyJ, [&](const char* value) {
        if (value[0]) {
            ret = env->NewStringUTF(value);
        }
    });
    if (ret == nullptr && !env->ExceptionCheck()) {
        ret = env->NewStringUTF("");  // Legacy behavior
    }
//    const char* ch1 = env->GetStringUTFChars(keyJ, datutils::toJavaBoolean(true));
//    const char* ch2 = env->GetStringUTFChars(defJ, datutils::toJavaBoolean(true));
//    const char* ch3 = env->GetStringUTFChars(ret, datutils::toJavaBoolean(true));
//    LOGD("%s %s %s",ch1,ch2,ch3);
    return ret;
}
void fake_SystemProperties_add_change_callback(JNIEnv *env, jobject clazz){ return;}
void fake_SystemProperties_report_sysprop_change(JNIEnv *env, jobject clazz){ return;}
void fake_SystemProperties_set(JNIEnv *env, jobject clazz, jstring keyJ,jstring valJ){ return;}

