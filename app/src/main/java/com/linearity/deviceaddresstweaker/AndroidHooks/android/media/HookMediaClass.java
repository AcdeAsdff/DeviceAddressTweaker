package com.linearity.deviceaddresstweaker.AndroidHooks.android.media;

import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;
import android.media.MediaDrm;

import com.linearity.utils.ReturnReplacements;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMediaClass {
    public static boolean HookMedia = true;
    public static boolean HookMediaDRM = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookMedia = sharedPreferences.getBoolean("HookMediaClass",true);
        HookMediaDRM = sharedPreferences.getBoolean("HookMediaClass_HookMediaDRM",true);

        Class<?> hookClass;
        if (HookMedia){
            if (HookMediaDRM){
                hookClass = XposedHelpers.findClassIfExists(MediaDrm.class.getName(),lpparam.classLoader);
                if (hookClass != null){//not finished
//                    XposedBridge.hookAllMethods(hookClass, "getPropertyByteArray", new XC_MethodReplacement() {
//                        @Override
//                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                            LoggerLog("caught method!");
//                            return byteArray114514;
//                        }
//                    });//goto native
                }
            }
        }

    }
}
