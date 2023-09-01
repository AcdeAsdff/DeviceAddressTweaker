package com.linearity.deviceaddresstweaker.AndroidHooks.android.content.pm;

import android.content.pm.PackageManager;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

public class HookPmClass {
    public static boolean HookPm = true;
    public static boolean HookPackageManager = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
        if (HookPm){
            if (HookPackageManager){
                try {
                    XposedHelpers.setStaticIntField(PackageManager.class,"PERMISSION_DENIED",PackageManager.PERMISSION_GRANTED);
                }catch (Exception e){
                    LoggerLog(e);
                }
            }

        }
    }
}
