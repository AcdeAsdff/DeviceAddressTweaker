package com.linearity.deviceaddresstweaker.AndroidHooks.android.location;

import android.location.Location;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

import de.robv.android.xposed.XC_MethodHook;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookLocationClass {
    public static boolean HookLocation = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookLocation = sharedPreferences.getBoolean("HookLocationClass_HookLocation", true);
        if (HookLocation){
            //      Location.class getLatitude()
            try {
                XposedHelpers.findAndHookMethod(
                        Location.class.getName(),
                        lpparam.classLoader,
                        "getLatitude",
                        new XC_MethodHook(114514) {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //LoggerLog(lpparam.packageName + "调用Location.class getLatitude()" + param.getResult());
                                param.setResult(0.);
                            }
                        }
                );
            } catch (Exception e) {
                LoggerLog(e);
            }
//      Location.class getLongitude()
            try {
                XposedHelpers.findAndHookMethod(
                        Location.class.getName(),
                        lpparam.classLoader,
                        "getLongitude",
                        new XC_MethodHook(114514) {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //LoggerLog(lpparam.packageName + "调用Location.class getLongitude()" + param.getResult());
                                param.setResult(0.);
                            }
                        }
                );
            } catch (Exception e) {
                LoggerLog(e);
            }
        }
        ////      LocationManager.class getLastKnownLocation()
//        try {
//            XposedHelpers.findAndHookMethod(
//                    LocationManager.class.getName(),
//                    lpparam.classLoader,
//                    "getLastKnownLocation",
//                    new XC_MethodHook(114514) {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(lpparam.packageName + "调用LocationManager.class getLastKnownLocation()" + param.getResult());
//                            param.setResult(new Location("0"));
//                        }
//                    }
//            );
//        }catch (Exception e){
//            LoggerLog(e);
//        }
    }
}
