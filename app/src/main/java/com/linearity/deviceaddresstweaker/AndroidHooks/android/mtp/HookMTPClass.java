package com.linearity.deviceaddresstweaker.AndroidHooks.android.mtp;

import static com.linearity.utils.HookUtils.disableClass;

import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMTPClass {
    public static boolean HookMTP = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookMTP = sharedPreferences.getBoolean("HookMTPClass", true);
        if (HookMTP){
            Class<?> hookClass;
            for (String s:new String[]{
                    "android.mtp.MtpConstants",
                    "android.mtp.MtpDevice",
                    "android.mtp.MtpDeviceInfo",
                    "android.mtp.MtpEvent",
                    "android.mtp.MtpObjectInfo",
                    "android.mtp.MtpStorageInfo"
            }){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass(hookClass);
                }
            }
        }
    }
}
