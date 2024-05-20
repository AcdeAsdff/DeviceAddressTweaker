package com.linearity.deviceaddresstweaker.AndroidHooks.android.content;

import static com.linearity.utils.HookUtils.disableMethod;

import android.content.ClipboardManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.res.HookResClass;

import android.content.SharedPreferences;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookContentClass {
    public static boolean HookContent = true;
    public static boolean HookContext = true;
    public static boolean HookClipboardManager = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookContent = sharedPreferences.getBoolean("HookContentClass_HookContent", true);
        HookContext = sharedPreferences.getBoolean("HookContentClass_HookContext", true);
        HookClipboardManager = sharedPreferences.getBoolean("HookContentClass_HookClipboardManager", true);

        Class<?> hookClass;
        if (HookContent){
            HookResClass.DoHook(lpparam,procHead,sharedPreferences);
            if (HookContext){
//                try {
//                    findAndHookMethodIfExists(
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
            if (HookClipboardManager){
                hookClass = XposedHelpers.findClassIfExists(ClipboardManager.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    for (Method m:hookClass.getDeclaredMethods()){
                        if (m.getName().equals("setPrimaryClip")){continue;}
                        disableMethod(m, hookClass);//now all of U cannot steal my clipboard
                    }
                }
            }
        }
    }
}
