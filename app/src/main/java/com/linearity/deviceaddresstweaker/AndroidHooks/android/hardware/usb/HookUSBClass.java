package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.usb;

import static com.linearity.utils.HookUtils.disableClass;
import static com.linearity.utils.HookUtils.disableMethod;
import static com.linearity.utils.HookUtils.listenClass;

import android.content.SharedPreferences;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookUSBClass {
    public static boolean HookUSB = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookUSB = sharedPreferences.getBoolean("HookHardwareClass_usb_HookUSBClass", true);
        if (HookUSB){
            Class<?> hookClass;
            for (String s:new String[]{
                    "android.hardware.usb.UsbAccessory",
                    "android.hardware.usb.UsbConfiguration",
                    "android.hardware.usb.UsbConstants",
                    "android.hardware.usb.UsbDevice",
                    "android.hardware.usb.UsbDeviceConnection",
                    "android.hardware.usb.UsbEndpoint",
                    "android.hardware.usb.UsbInterface",
                    "android.hardware.usb.UsbManager",
                    "android.hardware.usb.UsbRequest",
            }){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass(hookClass);
                }
            }

//            hookClass = XposedHelpers.findClassIfExists("android.hardware.usb.UsbManager",lpparam.classLoader);
//            if (hookClass != null){
//                listenClass(hookClass);
//            }
        }
    }
}
