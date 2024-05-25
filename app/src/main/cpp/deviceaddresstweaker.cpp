// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("deviceaddresstweaker");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("deviceaddresstweaker")
//      }
//    }
#include <stdarg.h>
#include "sys/system_properties.h"
#include <cstdio>
#include <jni.h>
#include <string>
#include <dlfcn.h>
#include "deviceaddresstweaker.h"
#include "datutils.h"
#include <android/log.h>
#define  TAG    "[linearity-Native]"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
using namespace datutils;
static HookFunType hook_func = nullptr;
//length:62
int (*backup__system_property_get)(const char* __name, char* __value);
int fake__system_property_get(const char* __name, char* __value){
    std::string namestr = std::string(__name);
    if (std::equal(namestr.begin(), namestr.end(),"ro.build.version.sdk")){
        __value[0]='2';
        __value[1]='8';__value[2]='\0';
        return 2;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.product.model")){
        setRandomCharPointer(__value,10);return 10;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.build.date.utc")){
        setRandomCharPointerWithInt(__value,10);return 10;
    }else if(std::equal(namestr.begin(), namestr.end(),"sys.usb.state")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.hardware")){
        setRandomCharPointer(__value,5);return 5;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.product.first_api_level")){
        setRandomCharPointerWithInt(__value,2);return 10;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.build.version.release")){
        setRandomCharPointerWithInt(__value,2);return 10;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.board.platform")){
        setRandomCharPointer(__value,5);return 5;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.product.board")){
        setRandomCharPointer(__value,5);return 5;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.build.version.codename")){
        setRandomCharPointer(__value,5);return 5;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.debuggable")){
        __value[0]='0';__value[1]='\0';return 1;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.serialno")
    || std::equal(namestr.begin(), namestr.end(),"ro.boot.serialno")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.boot.hardware")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.product.device")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.build.tags")){
        setCharPointerFromCharPointer(__value,12,"release-keys");return 12;
    }else if(std::equal(namestr.begin(), namestr.end(),"gsm.sim.state")){
        setCharPointerFromCharPointer(__value,13,"LOADED,ABSENT");return 13;
    }else if(std::equal(namestr.begin(), namestr.end(),"persist.sys.country")){
        setCharPointerFromCharPointer(__value,2,"CN");return 2;
    }else if(std::equal(namestr.begin(), namestr.end(),"persist.sys.language")){
        setCharPointerFromCharPointer(__value,5,"zh-CN");return 5;
    }else if(std::equal(namestr.begin(), namestr.end(),"debug.atrace.tags.enableflags")){
        setCharPointerFromCharPointer(__value,1,"0");return 1;
    }else if(namestr.find("debug.") != -1){
        __value[0]='\0';
        return 0;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.arch")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.hardware.chipname")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.chipname")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.mediatek.platform")){
        setRandomCharPointer(__value,8);return 8;
    }else if(std::equal(namestr.begin(), namestr.end(),"ro.build.version.release_or_codename")){
        setRandomCharPointerWithInt(__value,2);return 10;
    }else if(std::equal(namestr.begin(), namestr.end(),"gsm.network.type")){
        setCharPointerFromCharPointer(__value,3,"LTE");return 3;
    }
    int result = backup__system_property_get(__name,__value);
//    LOGD("%s %s", __name,__value);
    return result;
};
static jstring fake_android_media_MediaDrm_getPropertyString(JNIEnv *env, jobject thiz, jstring jname){
    jstring result = env->NewStringUTF(getRandomCharPointer(12));
    return result;
}
static jbyteArray fake_android_media_MediaDrm_getPropertyByteArray(JNIEnv *env, jobject thiz, jstring jname) {
//    LOGD("called method:android_media_MediaDrm_getPropertyByteArray");
    return getRandomByteArray(env,24);
}
static jobjectArray fake_wechat_getAsyncableJsApis(JNIEnv *env, jobject thiz){
//    jstring a[0];
    LOGD("called fake_wechat_getAsyncableJsApis");
    jstring empty = env->NewStringUTF("");
    jobjectArray ret = env->NewObjectArray(1,env->GetObjectClass(empty), nullptr);
//    env->SetObjectArrayElement(ret, 0, 1, a);
    return ret;
}
static const JNINativeMethod gMethods[] = {
        { "getPropertyByteArray", "(Ljava/lang/String;)[B",(void *)fake_android_media_MediaDrm_getPropertyByteArray },
        { "getPropertyString", "(Ljava/lang/String;)Ljava/lang/String;",(void *)fake_android_media_MediaDrm_getPropertyString },
};
static const JNINativeMethod wechatMethods[] = {//com.tencent.mm.appbrand.commonjni.AppBrandCommonBindingJni
        {"getAsyncableJsApis","()[Ljava/lang/String;",(void *)fake_wechat_getAsyncableJsApis},
};

FILE *(*backup_fopen)(const char *filename, const char *mode);

FILE *fake_fopen(const char *filename, const char *mode) {
//    std::string str(filename);
//    if ((str.find("cpufreq") != -1)||(str.find("/proc") != -1)){return nullptr;}
//    if (strstr(filename, "banned")) return nullptr;
//    LOGD("%s", filename);
    return backup_fopen(filename, mode);
}

jclass (*backup_FindClass)(JNIEnv *env, const char *name);
jclass fake_FindClass(JNIEnv *env, const char *name)
{
    if(!strcmp(name, "dalvik/system/BaseDexClassLoader")) {
        LOGD("findclass:%s", name);
    }
    return backup_FindClass(env, name);
}

jobject (*backup__simple_CallObjectMethod)(JNIEnv *env,jobject jobject1,jmethodID jmethodId);

jobject fake__simple_CallObjectMethod(JNIEnv *env,jobject jobject1,jmethodID jmethodId){
//    jclass cls = env->GetObjectClass(jobject1);
//
//// First get the class object
//    jmethodID mid = env->GetMethodID(cls, "getClass", "()Ljava/lang/Class;");
//    jobject clsObj = env->CallObjectMethod(jobject1, mid);
//
//// Now get the class object's class descriptor
//    cls = env->GetObjectClass(clsObj);
//
//// Find the getName() method on the class object
//    mid = env->GetMethodID(cls, "getName", "()Ljava/lang/String;");
//
//// Call the getName() to get a jstring object back
//    jstring strObj = (jstring)env->CallObjectMethod(clsObj, mid);
//
//// Now get the c string from the java jstring object
//    const char* str = env->GetStringUTFChars(strObj, NULL);
//    LOGD("%s", str);
    return backup__simple_CallObjectMethod(env,jobject1,jmethodId);
};

void on_library_loaded(const char *name, void *handle) {
    // hooks on `libtarget.so`
    if (name == nullptr){ return;}
    LOGD("%s", name);
    if (std::string(name).find("libc.so") != -1) {
//        LOGD("found target");
//        dlclose(handle);
//        void *target = dlsym(handle, "__simple_CallObjectMethod");
//        hook_func(target, (void *) fake__simple_CallObjectMethod, (void **) backup__simple_CallObjectMethod);
    }
}

extern "C" [[gnu::visibility("default")]] [[gnu::used]]
jint JNI_OnLoad(JavaVM *jvm, void*) {
    JNIEnv *env = nullptr;
    jvm->GetEnv((void **)&env, JNI_VERSION_1_6);
    hook_func((void *)env->functions->FindClass, (void *)fake_FindClass, (void **)&backup_FindClass);
    env->RegisterNatives( env->FindClass("android/media/MediaDrm"),gMethods, (sizeof(gMethods)/sizeof(gMethods[0])));
    return JNI_VERSION_1_6;
}

extern "C" [[gnu::visibility("default")]] [[gnu::used]]
NativeOnModuleLoaded native_init(const NativeAPIEntries *entries) {
    hook_func = entries->hook_func;
    // system hooks
    hook_func((void*) fopen, (void*) fake_fopen, (void**) &backup_fopen);
    hook_func((void*)__system_property_get,(void*) fake__system_property_get,(void**) &backup__system_property_get);
    return on_library_loaded;
}
