package com.linearity.deviceaddresstweaker.AndroidHooks.android.mtp;

import static com.linearity.utils.HookUtils.disableClass;
import static com.linearity.utils.HookUtils.disableClass_random;

import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMTPClass {
    public static boolean HookMTP = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        HookMTP = sharedPreferences.getBoolean("HookMTPClass", true);
        if (HookMTP){
            Class<?> hookClass;
            for (String s:new String[]{
                    "android.mtp_perf.AppFusePerfTest", "android.mtp.MtpStorageManager",
                    "android.mtp.MtpStorage", "android.mtp.MtpServer", "android.mtp.MtpPropertyList",
                    "android.mtp.MtpPropertyGroup", "android.mtp.MtpObjectInfo", "android.mtp.MtpEvent",
                    "android.mtp.MtpDeviceInfo", "android.mtp.MtpDevice", "android.mtp.MtpDatabase",
                    "android.mtp.MtpConstants", "android.mtp.MtpStorageInfo"

            }){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }
        }
    }
}
