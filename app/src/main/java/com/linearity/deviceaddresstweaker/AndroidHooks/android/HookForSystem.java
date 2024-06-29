package com.linearity.deviceaddresstweaker.AndroidHooks.android;

import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.LoggerUtils.showArgs;

import android.content.SharedPreferences;
import android.os.Bundle;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookForSystem {

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        LoggerLog("caught system");
        Class<?> hookClass = XposedHelpers.findClassIfExists("android.os.ServiceManager",lpparam.classLoader);
        if (hookClass != null){
            LoggerLog("serviceManager found!");
            XposedBridge.hookAllMethods(hookClass, "addService", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Class<?> hookClass;
                    LoggerLog("[linearity-addService]",param.args[0].toString() + "|" + param.args[1]);
                    if (param.args[0].equals("settings")){
                        hookClass = XposedHelpers.findClassIfExists("com.android.providers.settings.SettingsProvider",lpparam.classLoader);
                        if (hookClass != null){
                            LoggerLog("found provider");
                            XposedBridge.hookAllMethods(hookClass, "call", new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(param.args[0] + "|" + param.args[1] + "|" + param.args[2]);
                                }
                            });
                        }
                    }
                }
            });
        }
//        hookClass = XposedHelpers.findClassIfExists("com.android.providers.settings.SettingsProvider",lpparam.classLoader);
//        if (hookClass != null){
//            LoggerLog("found provider");
//            XposedBridge.hookAllMethods(hookClass, "call", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    LoggerLog(param.args[0] + "|" + param.args[1] + "|" + param.args[2]);
//                }
//            });
//        }
    }
}
