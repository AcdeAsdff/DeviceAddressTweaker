package com.linearity.utils;

import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;

import android.accounts.Account;
import android.os.Bundle;

import com.linearity.utils.FakeClass.java.util.CantUseArrayList;
import com.linearity.utils.FakeClass.java.util.CantUseEnumeration;
import com.linearity.utils.FakeClass.java.util.CantUseHashmap;
import com.linearity.utils.FakeClass.java.util.CantUseVector;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import de.robv.android.xposed.XC_MethodReplacement;

public class ReturnReplacements {
    public static final XC_MethodReplacement returnNull = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return null;
        }
    };
    public static final XC_MethodReplacement returnSelf = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return param.thisObject;
        }
    };
    public static final XC_MethodReplacement returnFalse = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return false;
        }
    };
    public static final XC_MethodReplacement returnTrue = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return true;
        }
    };
    public static final XC_MethodReplacement returnIntegerZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0;
        }
    };
    public static final XC_MethodReplacement returnIntegerNegativeOne = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 1;
        }
    };
    public static final XC_MethodReplacement returnByteArr114514 = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return byteArray114514;
        }
    };
    public static final XC_MethodReplacement returnIntegerOne = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 1;
        }
    };
    public static final XC_MethodReplacement returnStringOne = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return "1";
        }
    };
    public static final XC_MethodReplacement returnIntegerMIN = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return -2147483648;
        }
    };
    public static final XC_MethodReplacement returnIntegerMAX = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 2147483647;
        }
    };
    public static final XC_MethodReplacement returnShortZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return (short)0;
        }
    };
    public static final XC_MethodReplacement returnCharZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return '\0';
        }
    };
    public static final XC_MethodReplacement returnByteZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return (byte)0;
        }
    };
    public static final XC_MethodReplacement returnFloatZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0.f;
        }
    };
    public static final XC_MethodReplacement returnDoubleZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0.;
        }
    };
    public static final XC_MethodReplacement returnLongZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0L;
        }
    };
    public static final XC_MethodReplacement returnLongOne = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 1L;
        }
    };
    public static final XC_MethodReplacement returnLongMIN = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0x8000000000000000L;
        }
    };
    public static final XC_MethodReplacement returnLongMAX = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return 0x7fffffffffffffffL;
        }
    };
    public static final XC_MethodReplacement returnRandomStr20 = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return getRandomString(20);
        }
    };
    public static final Bundle EmptyBundle = new Bundle();
    public static final XC_MethodReplacement returnEmptyBundle = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return EmptyBundle;
        }
    };
    public static final Map<String, Object> EmptyMap_String_Object = new HashMap<>();
    public static final XC_MethodReplacement returnEmptyMap_String_Object = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) {
            return EmptyMap_String_Object;
        }
    };
    public static final XC_MethodReplacement returnCantUseHashMap = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return CantUseHashmap.INSTANCE;
        }
    };

    public static final XC_MethodReplacement returnCantUseArrayList = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return CantUseArrayList.INSTANCE;
        }
    };

    public static final XC_MethodReplacement returnRandomUUID = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return UUID.randomUUID();
        }
    };

    public static final XC_MethodReplacement returnCantUseVector = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return new CantUseVector<>();
        }
    };

    public static final XC_MethodReplacement returnCantUseEnumeration = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return CantUseEnumeration.INSTANCE;
        }
    };

    public static final Random random = new Random();

    public static String getRandomString(int length){
//        int minLength = length/2;
//        int exactLength = random.nextInt(length - minLength) + minLength + 1;
        String str="abpqmnoEFGHIJrstRSTUVWujkl56YKLX234ZvwxyzABCDcdefghi01MNOPQ789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomHexUpper(int length){
//        int minLength = length/2;
//        int exactLength = random.nextInt(length - minLength) + minLength + 1;
        String str="0123456789ABCDEF";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomHexlower(int length){
//        int minLength = length/2;
//        int exactLength = random.nextInt(length - minLength) + minLength + 1;
        String str="0123456789abcdef";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
