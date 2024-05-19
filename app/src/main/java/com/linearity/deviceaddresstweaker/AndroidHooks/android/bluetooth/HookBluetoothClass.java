package com.linearity.deviceaddresstweaker.AndroidHooks.android.bluetooth;

import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.bluetooth.BluetoothAdapter;

import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookBluetoothClass {
    public static boolean HookBluetooth = true;
    public static boolean HookBluetoothAdapter = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookBluetooth = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetooth", true);
        HookBluetoothAdapter = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetoothAdapter", true);
        if (HookBluetooth){
            if (HookBluetoothAdapter) {
                hookClass = XposedHelpers.findClassIfExists(BluetoothAdapter.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        //      BluetoothAdapter.class getAddress()
                        {
                            findAndHookMethodIfExists(hookClass, "getAddress", returnRandomStr20);

                        }
//      BluetoothAdapter.class getName()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getName", returnRandomStr20
                            );

                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
        }
    }
}
