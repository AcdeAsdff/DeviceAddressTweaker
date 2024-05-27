package com.linearity.deviceaddresstweaker.AndroidHooks.android.bluetooth;

import static com.linearity.utils.HookUtils.disableClass_random;
import static com.linearity.utils.HookUtils.disableMethod_random;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.returnCantUseArrayList;
import static com.linearity.utils.ReturnReplacements.returnIntegerRandom;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.bluetooth.BluetoothAdapter;

import android.content.SharedPreferences;

import java.lang.reflect.Method;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookBluetoothClass {
    public static boolean HookBluetooth = true;
    public static boolean HookBluetoothAdapter = true;

    public static final String[] allClasses = new String[]{
            "android.bluetooth.le.AdvertiseCallback",
            "android.bluetooth.le.AdvertiseData",
            "android.bluetooth.le.AdvertiseSettings",
            "android.bluetooth.le.AdvertisingSet",
            "android.bluetooth.le.AdvertisingSetCallback",
            "android.bluetooth.le.AdvertisingSetParameters",
            "android.bluetooth.le.BluetoothLeAdvertiser",
            "android.bluetooth.le.BluetoothLeScanner",
            "android.bluetooth.le.PeriodicAdvertisingParameters",
            "android.bluetooth.le.ScanCallback",
            "android.bluetooth.le.ScanFilter",
            "android.bluetooth.le.ScanRecord",
            "android.bluetooth.le.ScanResult",
            "android.bluetooth.le.ScanSettings",
            "android.bluetooth.BluetoothA2dp",
            "android.bluetooth.BluetoothAdapter",
            "android.bluetooth.BluetoothAssignedNumbers",
            "android.bluetooth.BluetoothClass",
            "android.bluetooth.BluetoothDevice",
            "android.bluetooth.BluetoothGatt",
            "android.bluetooth.BluetoothGattCallback",
            "android.bluetooth.BluetoothGattCharacteristic",
            "android.bluetooth.BluetoothGattDescriptor",
            "android.bluetooth.BluetoothGattServer",
            "android.bluetooth.BluetoothGattServerCallback",
            "android.bluetooth.BluetoothGattService",
            "android.bluetooth.BluetoothHeadset",
            "android.bluetooth.BluetoothHealth",
            "android.bluetooth.BluetoothHealthAppConfiguration",
            "android.bluetooth.BluetoothHealthCallback",
            "android.bluetooth.BluetoothHearingAid",
            "android.bluetooth.BluetoothHidDevice",
            "android.bluetooth.BluetoothHidDeviceAppQosSettings",
            "android.bluetooth.BluetoothHidDeviceAppSdpSettings",
            "android.bluetooth.BluetoothLeAudio",
            "android.bluetooth.BluetoothProfile",
            "android.bluetooth.BluetoothServerSocket",
            "android.bluetooth.BluetoothSocket",
            "android.bluetooth.BluetoothStatusCodes",
    };
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookBluetooth = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetooth", true);
        HookBluetoothAdapter = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetoothAdapter", true);
        if (HookBluetooth){
            for (String s:allClasses){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }
            hookClass = XposedHelpers.findClassIfExists("android.bluetooth.BluetoothManager",lpparam.classLoader);
            if (hookClass != null){//not finished
                XposedBridge.hookAllMethods(hookClass,"getConnectionState",returnIntegerRandom);
                XposedBridge.hookAllMethods(hookClass,"getConnectedDevices",returnCantUseArrayList);
                XposedBridge.hookAllMethods(hookClass,"getDevicesMatchingConnectionStates",returnCantUseArrayList);
            }
//            if (HookBluetoothAdapter) {
//                hookClass = XposedHelpers.findClassIfExists(BluetoothAdapter.class.getName(),lpparam.classLoader);
//                if (hookClass != null){
//                    try {
//                        //      BluetoothAdapter.class getAddress()
//                        {
//                            findAndHookMethodIfExists(hookClass, "getAddress", returnRandomStr20);
//
//                        }
////      BluetoothAdapter.class getName()
//                        {
//                            findAndHookMethodIfExists(hookClass,
//                                    "getName", returnRandomStr20
//                            );
//
//                        }
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                }
//            }
        }
    }
}
