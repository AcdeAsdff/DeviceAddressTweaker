package com.linearity.deviceaddresstweaker.AndroidHooks.android.app;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookActivityThreadClass {

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass = XposedHelpers.findClassIfExists("android.app.ActivityThread",lpparam.classLoader);
        if (hookClass != null){
            XposedBridge.hookAllMethods(hookClass, "handleResumeActivity", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null){
                        java.util.regex.Pattern packageNamePattern = Pattern.compile("\\{[a-z0-9.]{1,60}/");
                        Matcher matcher = packageNamePattern.matcher(param.args[0].toString());
                        String found = param.args[0].toString();
                        if (matcher.find()){
                            found = matcher.group();
                        }
                        LoggerLog(found + " | try onPause--cancelled");
                        param.setResult(true);
                    }
                }
            });
            XposedBridge.hookAllMethods(hookClass, "handlePauseActivity", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null){
                        java.util.regex.Pattern packageNamePattern = Pattern.compile("\\{[a-z0-9.]{1,60}/");
                        Matcher matcher = packageNamePattern.matcher(param.args[0].toString());
                        String found = param.args[0].toString();
                        if (matcher.find()){
                            found = matcher.group();
                        }
                        LoggerLog(found + " | try onPause--cancelled");
                        param.setResult(null);
                    }
                }
            });
        }
    }
}
