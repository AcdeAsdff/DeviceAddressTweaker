package com.linearity.deviceaddresstweaker.AndroidHooks.android.drm;

import static com.linearity.utils.HookUtils.disableClass_random;

import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookDrmClass {
    public static boolean HookDrm = true;

    public static final String[] toHook = new String[]{
            "android.drm.ProcessedData", "android.drm.DrmUtils", "android.drm.DrmSupportInfo",
            "android.drm.DrmStore", "android.drm.DrmRights", "android.drm.DrmOutputStream",
            "android.drm.DrmManagerClient", "android.drm.DrmInfoStatus", "android.drm.DrmInfoRequest",
            "android.drm.DrmInfoEvent", "android.drm.DrmInfo", "android.drm.DrmEvent",
            "android.drm.DrmErrorEvent", "android.drm.DrmConvertedStatus"
    };

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookDrm = sharedPreferences.getBoolean("HookDrm",true);

        Class<?> hookClass;
        if (HookDrm){
            for (String s:toHook){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }
        }
    }
}
