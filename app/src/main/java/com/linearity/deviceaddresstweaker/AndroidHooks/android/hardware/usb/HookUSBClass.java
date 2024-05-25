package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.usb;

import static com.linearity.utils.HookUtils.disableClass;
import static com.linearity.utils.HookUtils.disableClass_random;
import static com.linearity.utils.HookUtils.disableMethod;
import static com.linearity.utils.HookUtils.listenClass;

import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookUSBClass {
    public static boolean HookUSB = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        HookUSB = sharedPreferences.getBoolean("HookHardwareClass_usb_HookUSBClass", true);
        if (HookUSB){
            Class<?> hookClass;
            for (String s:new String[]{
                    "android.hardware.usb.UsbRequest.java",
                    "android.hardware.usb.UsbPortStatus.java",
                    "android.hardware.usb.UsbPort.java",
                    "android.hardware.usb.UsbManager.java",
                    "android.hardware.usb.UsbInterface.java",
                    "android.hardware.usb.UsbEndpoint.java",
                    "android.hardware.usb.UsbDeviceConnection.java",
                    "android.hardware.usb.UsbDevice.java",
                    "android.hardware.usb.UsbConstants.java",
                    "android.hardware.usb.UsbAccessory.java",
                    "android.hardware.usb.ParcelableUsbPort.java",
                    "android.hardware.usb.DeviceFilter.java",
                    "android.hardware.usb.AccessoryFilter.java",
                    "android.hardware.usb.UsbConfiguration.java",
            }){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }

//            hookClass = XposedHelpers.findClassIfExists("android.hardware.usb.UsbManager",lpparam.classLoader);
//            if (hookClass != null){
//                listenClass(hookClass);
//            }
        }
    }
}
