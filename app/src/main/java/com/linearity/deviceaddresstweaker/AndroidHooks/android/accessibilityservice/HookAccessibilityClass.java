package com.linearity.deviceaddresstweaker.AndroidHooks.android.accessibilityservice;

import static com.linearity.utils.HookUtils.disableClass_random;

import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookAccessibilityClass {
    public static final String[] toHook = new String[]{
            "android.accessibilityservice.util.AccessibilityUtils",
            "android.accessibilityservice.FingerprintGestureController",
            "android.accessibilityservice.AccessibilityTrace",
            "android.accessibilityservice.AccessibilityShortcutInfo",
            "android.accessibilityservice.AccessibilityServiceInfo",
            "android.accessibilityservice.AccessibilityService",
            "android.accessibilityservice.AccessibilityGestureEvent",
            "android.accessibilityservice.AccessibilityButtonController",
            "android.accessibilityservice.GestureDescription"
    };
    public static boolean HookAccessibility = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookAccessibility = sharedPreferences.getBoolean("HookAccessibility",true);

        Class<?> hookClass;
        if (HookAccessibility){
            for (String s:toHook){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }
        }
    }
}
