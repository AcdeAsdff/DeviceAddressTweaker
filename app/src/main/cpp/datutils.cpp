//
// Created by MOKO on 2024/5/23.
//

#include <android/log.h>
#include "deviceaddresstweaker.h"
#include <dlfcn.h>
#include <string>
#include <jni.h>
#include <cstdio>
#include "sys/system_properties.h"
#include "datutils.h"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)

//JNIEnv *env = nullptr;
const char* fromChar = "1234567890zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";

namespace datutils {
    char* getRandomCharPointer(int length){
        char* result = (char *)(malloc(sizeof(char) * (length+1)));
        for (int i=0;i<length;i++){
            result[i] = fromChar[abs(rand())%62];
        }result[length]='\0';

        return result;
    }

    void setRandomCharPointer(char* charPointer, int length){
        for (int i=0;i<length;i++){
            charPointer[i] = fromChar[abs(rand())%62];
        }charPointer[length]='\0';

    }

    void setCharPointerFromCharPointer(char* charPointer, int length, const char* fromCharPointer){
        for (int i=0;i<length;i++){
            charPointer[i] = fromCharPointer[i];
        }charPointer[length]='\0';

    }
    void setRandomCharPointerWithInt(char* charPointer, int length){
        for (int i=0;i<length;i++){
            charPointer[i] = fromChar[abs(rand())%10];
        }charPointer[length]='\0';

    }

    jbyteArray getRandomByteArray(JNIEnv *env,int size){
        jbyte a[size];
        srand(time(NULL));
        for (int i=0;i<size;i++){
            a[i]=(char)rand();
        }
        jbyteArray ret = env->NewByteArray(size);
        env->SetByteArrayRegion (ret, 0, size, a);
        return ret;
    }
} // datutils