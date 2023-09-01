package com.linearity.deviceaddresstweaker.AndroidHooks.android.os.storage;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookStorageClass {

    public static boolean HookStorage = true;
    public static boolean HookStorageManager = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam) {
        if (HookStorage){
            if (HookStorageManager){

            }
        }
    }
}
