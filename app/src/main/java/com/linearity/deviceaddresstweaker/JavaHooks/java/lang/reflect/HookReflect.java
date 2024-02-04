package com.linearity.deviceaddresstweaker.JavaHooks.java.lang.reflect;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import java.lang.reflect.Modifier;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookReflect {

    public static boolean HookReflect = true;
    public static boolean HookModifier = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookReflect = sharedPreferences.getBoolean("HookLang_HookReflect_HookReflect", true);
        HookModifier = sharedPreferences.getBoolean("HookLang_HookReflect_HookModifier", true);
        if (HookReflect){
            if(HookModifier){

                try {
                    XposedBridge.hookAllMethods(
                            Modifier.class,
                            "isNative",
                            new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
