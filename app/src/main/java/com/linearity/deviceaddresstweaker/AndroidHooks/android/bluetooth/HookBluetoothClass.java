package com.linearity.deviceaddresstweaker.AndroidHooks.android.bluetooth;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;

import android.bluetooth.BluetoothAdapter;

import de.robv.android.xposed.XC_MethodHook;
import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookBluetoothClass {
    public static boolean HookBluetooth = true;
    public static boolean HookBluetoothAdapter = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookBluetooth = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetooth", true);
        HookBluetoothAdapter = sharedPreferences.getBoolean("HookBluetoothClass_HookBluetoothAdapter", true);
        if (HookBluetooth){
            if (HookBluetoothAdapter) {
                //      BluetoothAdapter.class getAddress()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.bluetooth.BluetoothAdapter.class.getName(),
                            lpparam.classLoader,
                            "getAddress",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getAddress()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
//      BluetoothAdapter.class getName()
                try {
                    XposedHelpers.findAndHookMethod(
                            BluetoothAdapter.class.getName(),
                            lpparam.classLoader,
                            "getName",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getName()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
