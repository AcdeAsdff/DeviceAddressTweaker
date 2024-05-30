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
#include <sys/socket.h>
#include <asm/unistd.h>
#include <linux/netlink.h>
#include <ifaddrs.h>
#include "android/nativehelper/ScopedUtfChars.h"
#include "android/sysPropSet.h"
#include "media/NdkMediaDrm.h"
#include "Dobby-master/include/dobby.h"
#include "Utils.h"
#include "proot/src/tracee/tracee.h"
#include "proot/src/tracee/event.h"
#include <arpa/inet.h>
#include "/proot/src/cli/cli.h"

#define  TAG    "[linearity-Native]"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
using namespace datutils;
static HookFunType hook_func = nullptr;
uint8_t nop[4] = {0xD5,0x3,0x20,0x1F};
uint8_t * nop_ptr = nop;

extern "C" int initPRoot(int argc, char *const argv[]);
extern "C" int event_loop_clone();

bool SHOW_FOPEN = false;
bool SHOW_SYSPROP_GET = false;
bool SHOW_CANCELLED = false;
bool SHOW_FAKE_IP_INFO = false;
int (*backup__system_property_get)(const char* __name, char* __value);
int fake__system_property_get(const char* __name, char* __value){
    if (__name == nullptr){return 0;}
    std::string namestr = std::string(__name);
    if (strStartWith(__name,"ro.")){
        if (strStartWith(__name,"ro.build")){
            if (strEqual(__name,"ro.build.version.sdk")){
                setRandomCharPointerWithInt(__value,2);
                __value[0]= '0' + abs(rand()%2)+1;
                __value[1]= '0' + abs(rand()%10);
                __value[2]='\0';
                return 2;
            }else if(strEqual(__name,"ro.build.date.utc")){
                setRandomCharPointerWithInt(__value,10);return 10;
            }else if(strEqual(__name,"ro.build.version.release")){
                setRandomCharPointerWithInt(__value,2);return 10;
            }else if(strEqual(__name,"ro.build.version.codename")){
                setRandomCharPointer(__value,5);return 5;
            }else if(strEqual(__name,"ro.build.tags")){
                setCharPointerFromCharPointer(__value,12,"release-keys");return 12;
            }else if(strEqual(__name,"ro.build.version.release_or_codename")){
                setRandomCharPointerWithInt(__value,2);return 10;
            }
        }
        if (strStartWith(__name,"ro.product")) {
            if(strEqual(__name,"ro.product.model")){
                setRandomCharPointer(__value,10);return 10;
            }else if(strEqual(__name,"ro.product.first_api_level")){
                setRandomCharPointerWithInt(__value,2);return 10;
            }else if(strEqual(__name,"ro.product.board")){
                setRandomCharPointer(__value,5);return 5;
            }else if(strEqual(__name,"ro.product.device")){
                setRandomCharPointer(__value,8);return 8;
            }
        }
        if(strEqual(__name,"ro.hardware")){
            setRandomCharPointer(__value,5);return 5;
        }else if(strEqual(__name,"ro.board.platform")){
            setRandomCharPointer(__value,5);return 5;
        }else if(strEndWith(__name,".debuggable")){
            __value[0]='0';__value[1]='\0';return 1;
        }else if(strEndWith(__name,".serialno")){
            setRandomCharPointer(__value,8);return 8;
        }else if(strEqual(__name,"ro.boot.hardware")){
            setRandomCharPointer(__value,8);return 8;
        }else if(strEqual(__name,"ro.arch")){
            setRandomCharPointer(__value,8);return 8;
        }else if(strEndWith(__name,".chipname")){
            setRandomCharPointer(__value,8);return 8;
        }else if(strEqual(__name,"ro.mediatek.platform")){
            setRandomCharPointer(__value,8);return 8;
        }
    }
    if(strEqual(__name,"sys.usb.state")){
        setRandomCharPointer(__value,8);return 8;
    }else if(strEqual(__name,"gsm.sim.state")){
        setCharPointerFromCharPointer(__value,13,"LOADED,ABSENT");return 13;
    }else if(strEqual(__name,"persist.sys.country")){
        setCharPointerFromCharPointer(__value,2,"CN");return 2;
    }else if(strEqual(__name,"persist.sys.language")){
        setCharPointerFromCharPointer(__value,5,"zh-CN");return 5;
    }else if(strEqual(__name,"debug.atrace.tags.enableflags")){
        setCharPointerFromCharPointer(__value,1,"0");return 1;
    }else if(strStartWith(__name,"debug.")){
        __value[0]='\0';
        return 0;
    }else if(strEqual(__name,"gsm.network.type")){
        setCharPointerFromCharPointer(__value,3,"LTE");return 3;
    }
    int result = backup__system_property_get(__name,__value);
    if(SHOW_SYSPROP_GET){
        LOGD("[linearity-system_property_get]%s %s %d", __name, __value, result);
    }
    return result;
};
static jstring fake_android_media_MediaDrm_getPropertyString(JNIEnv *env, jobject thiz, jstring jname){
    jstring result = env->NewStringUTF(getRandomCharPointer(12));
    return result;
}
static jbyteArray fake_android_media_MediaDrm_getPropertyByteArray(JNIEnv *env, jobject thiz, jstring jname) {
    return getRandomByteArray(env,32);
}

static const JNINativeMethod gMethods[] = {
        { "getPropertyByteArray", "(Ljava/lang/String;)[B",(void *)fake_android_media_MediaDrm_getPropertyByteArray },
        { "getPropertyString", "(Ljava/lang/String;)Ljava/lang/String;",(void *)fake_android_media_MediaDrm_getPropertyString },
};

ssize_t (*backup_recvfrom)(int fd, void* const buf , size_t len, int flags, struct sockaddr* src_addr, socklen_t* addr_len);
ssize_t fake_recvfrom(int fd, void* const buf , size_t len, int flags, struct sockaddr* src_addr, socklen_t* addr_len){
    ssize_t result = backup_recvfrom(fd,buf, len, flags,src_addr, addr_len);
    auto* hdr = reinterpret_cast<nlmsghdr*>(buf);

//    LOGD("called recvfrom");
//    return 0;
    return result;
}


FILE *(*backup_fopen)(const char *filename, const char *mode);
FILE *replace_fopen_proc(const char *filename,const char *childFilename, const char *mode){
    std::string pathstr("/proc/");
    pathstr.append(std::to_string(getpid())).append("/").append(childFilename);
    const char* result = pathstr.c_str();
    if(SHOW_CANCELLED){ LOGD("[fopen-cancelled]%s -> %s", filename, result); }
    return backup_fopen(result, mode);
};
FILE *(*backup_popen)(const char *filename, const char *mode);
void* (*backup_dlsym)(void* __handle, const char* __symbol);
void* fake_dlsym(void* __handle, const char* __symbol){
    LOGD("[dlsym]%s",__symbol);
//    if (strcmp(__symbol,"pthread_create")==0){
//        void* fake_pthread_create = calloc(4096,sizeof(char));
//        return fake_pthread_create;
//    }
    return backup_dlsym(__handle,__symbol);
}
FILE *fake_popen(const char *command, const char *mode) {
    if (command== nullptr){return nullptr;}
    std::string str(command);
    if ((str.find("stat")==0)){
        return backup_popen("stat /",mode);
    }
    if ((str.find("cat")==0) ||str.find("ip")==0){
        std::string pathstr("ls /proc/");
        pathstr.append(std::to_string(getpid()));
        const char* replace = pathstr.c_str();
        if (SHOW_CANCELLED){LOGD("[popen-cancelled]%s -> %s", command, replace);}
        return backup_popen(replace,mode);
    }
    LOGD("[popen]%s", command);
    return backup_popen(command, mode);
}
FILE *fake_fopen(const char *filename, const char *mode) {
    if (filename== nullptr){
        return nullptr;
    }
    if (strStartWith(filename,"/system/fonts")
    || strEqual(filename,"/system/etc/fonts.xml")
    ) {
        return backup_fopen(filename,mode);
    }
    if (strEndWith(filename,"/su")){// endswith"/su"
        if (SHOW_CANCELLED) {
            LOGD("[fopen-cancelled]%s -> nullptr", filename);
        }
        return nullptr;
    }
    if (strStartWith(filename,"/proc")){
        if (strEndWith(filename,"/smaps")){
            return replace_fopen_proc(filename,"smaps",mode);
        }
        if (strEndWith(filename,"/maps")){
            return replace_fopen_proc(filename,"maps",mode);
        }
        if (strEndWith(filename,"/stat")){
            return replace_fopen_proc(filename,"stat",mode);
        }
        if (strEndWith(filename,"/status")){
            return replace_fopen_proc(filename,"status",mode);
        }
        if (strEndWith(filename,"/cmdline")){
            return replace_fopen_proc(filename,"cmdline",mode);
        }
        if (strEndWith(filename,"/auxv")){
            return replace_fopen_proc(filename,"auxv",mode);
        }
        if (strEndWith(filename,"/net/tcp")){
            return replace_fopen_proc(filename,"net/tcp",mode);
        }
        if (strEndWith(filename,"/net/route")){
            return replace_fopen_proc(filename,"net/route",mode);
        }
        return replace_fopen_proc(filename,"cmdline",mode);
    }
    if (
        strStartWith(filename,"/sys/devices")
        || strStartWith(filename,"/product/overlay/")
        || strStartWith(filename,"/system/etc")
        || strEqual(filename,"/dev/urandom")
        || strStartWith(filename,"/data/vendor/gpu/")
        || strStartWith(filename,"/data/misc/gpu/")
        || strStartWith(filename,"/sys/class/kgsl/kgsl-3d0/gpu_model")
    ){
        return replace_fopen_proc(filename,"cmdline",mode);
    }
    if(SHOW_FOPEN){
        LOGD("[fopen]%s", filename);
    }

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
    return backup__simple_CallObjectMethod(env,jobject1,jmethodId);
};

media_status_t (*backup__AMediaDrm_getPropertyByteArray)(AMediaDrm *mObj,
                                              const char *propertyName, AMediaDrmByteArray *propertyValue);

media_status_t fake__AMediaDrm_getPropertyByteArray(AMediaDrm *mObj,
                                                         const char *propertyName, AMediaDrmByteArray *propertyValue){
    propertyValue->ptr = (uint8_t*)(malloc(16 * sizeof(uint8_t)));
    propertyValue->length = 1;
    return AMEDIA_OK;
}
int (*backup_getifaddrs)(ifaddrs** out);

int fake_getifaddrs(ifaddrs** out){
    if (out== nullptr){return -1;}
    int result = backup_getifaddrs(out);
    if (result == 0){
        char addr[128];
        __attribute__((unused)) struct ifi_info {
            char    ifi_name[16];	/* interface name, null-terminated */
            short   ifi_index;			/* interface index */
            short   ifi_flags;			/* IFF_xxx constants from  */
            struct sockaddr  *ifi_addr;	/* primary address */
            struct sockaddr  *ifi_brdaddr;/* broadcast address */
            struct ifi_info  *ifi_next;	/* next of these structures */
        } *p;

        srand(time(NULL));
        //stole code
        for(;out!= nullptr;) {

            if ((*out)->ifa_addr != nullptr){
                (*out)->ifa_addr->sa_family = rand();
                if(((*out)->ifa_addr)->sa_family!=AF_INET6) {
                    (((struct sockaddr_in *) ((*out)->ifa_addr))->sin_addr).s_addr = abs(random());
                    inet_ntop(AF_INET, &(((struct sockaddr_in *) ((*out)->ifa_addr))->sin_addr), addr,
                              sizeof(addr));
                }
                else {
                    for (int q=0;q<16;q++){
                        (((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q] = abs(rand())%256;
                    }
                    for (int q=1;q<=8;q++){
                        (((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr16[q-1] =
                                (((__be16)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*2-1])<<16)
                                +((__be16)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*2-2]);
                    }
                    for (int q=1;q<=4;q++){
                        (((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr32[q-1] =
                                (((__be32)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*4-1])<<24)
                                +(((__be32)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*4-2])<<16)
                                +(((__be32)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*4-3])<<8)
                                +(((__be32)(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr).in6_u.u6_addr8[q*4-4]));
                    }
                    inet_ntop(AF_INET6, &(((struct sockaddr_in6 *) ((*out)->ifa_addr))->sin6_addr),addr, sizeof(addr));
                }
            }
            if ((*out)->ifa_netmask != nullptr){
                (*out)->ifa_netmask->sa_family = rand();
            }
            if (SHOW_FAKE_IP_INFO){ LOGD("%s\t", addr); }
            printf("\n+++++++++++++++++++++++++++++++++++++++++++\n");
            if ((*out)->ifa_next == nullptr){break;}
            out=&(*out)->ifa_next;
        }
    }
//    LOGD("getifaddrs:%d",result);
    return result;
}

void on_library_loaded(const char *name, void *handle) {

    if (name == nullptr){ return;}
    // hooks on `libtarget.so`
    void* target = nullptr;
    std::string namestr(name);
    LOGD("[libraryLoad]%s", name);
    //↓for tv.danmaku.bili(7.13.0)
    // If you want to use,you may need to change relativeAddr(find them with IDA,just search where it has called pthread_create)
    if (namestr.find("libmsaoaidsec.so") != -1) {
        DWORD targAbs;
//        targAbs = getAbsoluteAddress(name,0x1A2B8);
//        DobbyCodePatch((void*)targAbs,nop_ptr,4);
        targAbs = getAbsoluteAddress(name,0x1B8B4);
        DobbyCodePatch((void*)targAbs,nop_ptr,4);
        targAbs = getAbsoluteAddress(name,0x1A858);
        DobbyCodePatch((void*)targAbs,nop_ptr,4);

    }else if (namestr.find("libmediandk.so") != -1) {
        target = dlsym(handle, "AMediaDrm_getPropertyByteArray");
        hook_func(target, (void *) fake__AMediaDrm_getPropertyByteArray, (void **) backup__AMediaDrm_getPropertyByteArray);
    }

//    target = dlsym(handle, "getifaddrs");
//    if (target==0){ return;}
//    hook_func(target, (void *) fake_getifaddrs, (void **) backup_getifaddrs);

}

extern "C" [[gnu::visibility("default")]] [[gnu::used]]
jint JNI_OnLoad(JavaVM *jvm, void*) {
    JNIEnv *env = nullptr;
    jvm->GetEnv((void **)&env, JNI_VERSION_1_6);
    hook_func((void *)env->functions->FindClass, (void *)fake_FindClass, (void **)&backup_FindClass);
    env->RegisterNatives( env->FindClass("android/media/MediaDrm"),gMethods, (sizeof(gMethods)/sizeof(gMethods[0])));
    env->RegisterNatives( env->FindClass("android/os/SystemProperties"),sysProps_Methods, (sizeof(sysProps_Methods)/sizeof(sysProps_Methods[0])));
    return JNI_VERSION_1_6;
}

extern "C" [[gnu::visibility("default")]] [[gnu::used]]
NativeOnModuleLoaded native_init(const NativeAPIEntries *entries) {
    hook_func = entries->hook_func;
    // system hooks
    hook_func((void*) fopen, (void*) fake_fopen, (void**) &backup_fopen);
    hook_func((void*) popen, (void*) fake_popen, (void**) &backup_popen);
//    hook_func((void*)recvfrom,(void*) fake_recvfrom,(void**) &backup_recvfrom);
    hook_func((void*)__system_property_get,(void*) fake__system_property_get,(void**) &backup__system_property_get);
    hook_func((void*)getifaddrs, (void *) fake_getifaddrs, (void **) &backup_getifaddrs);

//    char* const argv[] = { (char* const)"",(char* const)"" };
//    initPRoot ( 0 , argv ) ;//failed
//    exit();
//    event_loop_clone();
    return on_library_loaded;
}
