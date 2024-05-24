package com.linearity.deviceaddresstweaker.AndroidHooks.android.view;

import static com.linearity.utils.ReturnReplacements.getRandomString;
import static com.linearity.utils.ReturnReplacements.random;
import static com.linearity.utils.ReturnReplacements.returnIntegerRandom;
import static com.linearity.utils.ReturnReplacements.returnLongRandom;
import static com.linearity.utils.ReturnReplacements.returnLongZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;

import android.content.SharedPreferences;
import android.hardware.display.DeviceProductInfo;
import android.view.Display;

import java.lang.reflect.InvocationTargetException;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookViewClass {
    public static final DeviceProductInfo fakeDeviceProductInfo = new DeviceProductInfo(
            getRandomString(20),getRandomString(20),getRandomString(20),
            random.nextInt(2035),
            random.nextInt(3939)
    );
    public static Object fakeManufactureDate;
    public static boolean HookView = true;
    public static boolean HookDisplay = true;

    public static final XC_MethodHook randomizer = new XC_MethodHook() {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
            param.setResult(((int)param.getResult())+random.nextInt(20)*(random.nextBoolean()?-1:1));
        }
    };

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        HookView = sharedPreferences.getBoolean("HookView",true);
        HookDisplay = sharedPreferences.getBoolean("HookView_HookDisplay",true);


        Class<?> hookClass;
        if (HookView){
            if (HookDisplay){
                //not finished
                hookClass = XposedHelpers.findClassIfExists(Display.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    XposedBridge.hookAllMethods(hookClass, "getSize", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "getRectSize", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "getCurrentSizeRange", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "getWidth", randomizer);
                    XposedBridge.hookAllMethods(hookClass, "getHeight", randomizer);
                    XposedBridge.hookAllMethods(hookClass, "getRotation", returnIntegerRandom);
                    XposedBridge.hookAllMethods(hookClass, "getOrientation", returnIntegerRandom);
                    XposedBridge.hookAllMethods(hookClass, "getMaximumSizeDimension", randomizer);
                    XposedBridge.hookAllMethods(hookClass,"getPresentationDeadlineNanos",returnLongRandom);
                    XposedBridge.hookAllMethods(hookClass,"getAppVsyncOffsetNanos",returnLongRandom);
                    fakeManufactureDate = XposedHelpers.findConstructorExact("android.view.Display$ManufactureDate",lpparam.classLoader,Integer.class,Integer.class).newInstance(random.nextInt(3939),random.nextInt(3939));
                    XposedHelpers.setObjectField(fakeDeviceProductInfo,"mManufactureDate",fakeDeviceProductInfo);
                    XposedBridge.hookAllMethods(hookClass, "getDeviceProductInfo", new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            return fakeDeviceProductInfo;
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "getMetrics", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "getRealSize", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "getRealMetrics", returnNull);
                    XposedBridge.hookAllMethods(hookClass, "toString", returnRandomStr20);


                }

            }
        }
    }


}
