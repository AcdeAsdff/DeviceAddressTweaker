package com.linearity.deviceaddresstweaker.AndroidHooks.android.content;

import android.content.pm.PackageManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.res.HookResClass;

import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookContentClass {
    public static boolean HookContent = true;
    public static boolean HookContext = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookContent = sharedPreferences.getBoolean("HookContentClass_HookContent", true);
        HookContext = sharedPreferences.getBoolean("HookContentClass_HookContext", true);
        if (HookContent){
            HookResClass.DoHook(lpparam,procHead,sharedPreferences);
            if (HookContext){
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            android.content.Context.class.getName(),
//                            lpparam.classLoader,
//                            "checkSelfPermission",
//                            String.class,
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return PackageManager.PERMISSION_GRANTED;
//                                }
//                            }
//                    );
//                }catch (Exception e){
//                    LoggerLog(e);
//                }
            }
        }
    }
}
