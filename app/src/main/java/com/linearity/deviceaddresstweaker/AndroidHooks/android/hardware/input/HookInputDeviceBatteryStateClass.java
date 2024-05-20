package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.input;

import static com.linearity.utils.HookUtils.disableMethod;

import android.content.SharedPreferences;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookInputDeviceBatteryStateClass {
    public static boolean HookInputDeviceBatteryState = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookInputDeviceBatteryState = sharedPreferences.getBoolean("HookHardwareClass_input_HookInputDeviceBatteryState", true);
        if (HookInputDeviceBatteryState){
            Class<?> hookClass;
            hookClass = XposedHelpers.findClassIfExists("android.hardware.input.InputDeviceBatteryState",lpparam.classLoader);
            if (hookClass != null){
                for (Method m:hookClass.getDeclaredMethods()){
                    disableMethod(m,hookClass);
                }
            }
        }
    }
}
