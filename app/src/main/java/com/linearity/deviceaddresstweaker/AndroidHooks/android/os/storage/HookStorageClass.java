package com.linearity.deviceaddresstweaker.AndroidHooks.android.os.storage;

import android.content.SharedPreferences;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookStorageClass {

    public static boolean HookStorage = true;
    public static boolean HookStorageManager = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) {
        if (HookStorage){
            if (HookStorageManager){

            }
        }
    }
}
