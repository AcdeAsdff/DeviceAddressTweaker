package com.linearity.deviceaddresstweaker.AndroidHooks.android.content;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookSharedPreferencesClass {
    public static boolean HookSharedPreferences = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookSharedPreferences = sharedPreferences.getBoolean("HookContentClass_HookSharedPreferences", true);

        if (HookSharedPreferences){
            hookClass = XposedHelpers.findClassIfExists(android.content.SharedPreferences.class.getName(),lpparam.classLoader);
            if (hookClass != null){
                try{
                    XposedBridge.hookAllMethods(
                            android.content.SharedPreferences.class,
                            "getBoolean",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String query = (String) param.args[0];
//                                LoggerLog("----------------");
//                                LoggerLog(query);
//                                LoggerLog("----------------");
                                    String queryLowerCase = query.toLowerCase();
                                    if (query == null){return;}
                                    if (queryLowerCase.contains("on") || queryLowerCase.contains("is")){
                                        if (queryLowerCase.contains("foreground")){
                                            param.setResult(true);
                                            return;
                                        }
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }

    }
}
