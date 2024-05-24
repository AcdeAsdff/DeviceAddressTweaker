package com.linearity.deviceaddresstweaker.AndroidHooks.android.os;

import android.os.Build;
import android.os.Environment;
import android.system.StructStatVfs;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.FakeProcInfoGenerator.infoMap;
import static com.linearity.utils.FakeProcInfoGenerator.random;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnLongMAX;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import com.linearity.utils.HookUtils;
import com.linearity.utils.ReturnReplacements;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;

public class HookOsClass {
    public static final boolean logSysProperties = false;
    public static StructStatVfs emptyStructStatVfs =
            new StructStatVfs(
                    4096L,//Math.abs(4096L + random.nextInt(4097))
                    4096L,//Math.abs(4096L + random.nextInt(4097))
                    Math.abs(28050939L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(12633283L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(12633283L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(13511677L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(12666051L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(12666051L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(64517L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(1030L + random.nextInt(Integer.MAX_VALUE)),
                    Math.abs(255L + random.nextInt(256)));
    public static boolean HookOs = true;
    public static boolean HookBuild = true;
    public static boolean HookEnvironment = true;
    public static boolean HookStatFs = true;
    public static boolean HookPowerManager = true;
    public static boolean HookSystemProperties = true;

    public static String[] foolCpuAll = new String[]{
            "x86",
            "x86_64",
            "arm64-v8a",
            "armeabi-v7a",
    };
    public static String[] foolCpu;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) {
        Class<?> hookClass;
        HookOs = sharedPreferences.getBoolean("HookOsClass_HookOs", true);
        HookBuild = sharedPreferences.getBoolean("HookOsClass_HookBuild", true);
        HookEnvironment = sharedPreferences.getBoolean("HookOsClass_HookEnvironment", true);
        HookStatFs = sharedPreferences.getBoolean("HookOsClass_HookStatFs", true);
        HookPowerManager = sharedPreferences.getBoolean("HookOsClass_HookPowerManager", true);
        HookSystemProperties = sharedPreferences.getBoolean("HookOsClass_HookSystemProperties",true);

                ArrayList<String> temp = new ArrayList<>();
        String UrCpu = (String) XposedHelpers.getStaticObjectField(Build.class, "CPU_ABI");
        for (String check:foolCpuAll){
            if (!check.equals(UrCpu)){
                temp.add(check);
            }
        }
        foolCpu = temp.toArray(new String[0]);
        if (HookOs){
            if (HookBuild) {
                hookClass = XposedHelpers.findClassIfExists(android.os.Build.class.getName(),
                        lpparam.classLoader);
                //      (StaticObjectField) android.os.Build.class MODEL|BRAND|BOARD|DEVICE|DISPLAY
//        android.os.Build.VERSION.class SDK
                if (hookClass != null){
                    try {
                        {
                            XposedHelpers.setStaticObjectField(hookClass, "SERIAL", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "MODEL", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "BRAND", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "BOARD", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "DEVICE", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "DISPLAY", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "BOOTLOADER", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "CPU_ABI2", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "FINGERPRINT", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "HARDWARE", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "MANUFACTURER", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "PRODUCT", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "RADIO", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "TAGS", ReturnReplacements.getRandomString(20));
                            XposedHelpers.setStaticObjectField(hookClass, "TIME", 1);
                            XposedHelpers.setStaticObjectField(hookClass, "TYPE", ReturnReplacements.getRandomString(20));
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getSerial",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(50);
                                        }
                                    }
                            );
                            XposedBridge.hookAllMethods(hookClass,
                                    "getString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return infoMap.get((String) param.args[0]);
                                        }
                                    }
                            );
                            XposedBridge.hookAllMethods(hookClass,"getRadioVersion",returnNull);
                            XposedBridge.hookAllMethods(hookClass,"getLong",returnLongMAX);
                        }
                        hookClass = XposedHelpers.findClassIfExists(Build.VERSION.class.getName(),lpparam.classLoader);
                        if (hookClass != null){
                            try {
//                    XposedHelpers.setStaticObjectField(hookClass, "SDK", "R");
                                XposedHelpers.setStaticObjectField(hookClass, "RELEASE", ReturnReplacements.getRandomString(20));
                                XposedHelpers.setStaticObjectField(hookClass, "CODENAME", ReturnReplacements.getRandomString(20));
                                XposedHelpers.setStaticObjectField(hookClass, "INCREMENTAL", ReturnReplacements.getRandomString(20));
//                    XposedHelpers.setStaticObjectField(hookClass, "SDK_INT", 30);
                            }catch (Exception e){
                                LoggerLog(e);
                            }
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }//not finished
            if (HookEnvironment) {
                hookClass = XposedHelpers.findClassIfExists(
                        android.os.Environment.class.getName(),
                        lpparam.classLoader);
                if (hookClass != null){
                    try{
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getRootDirectory",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new File(ReturnReplacements.getRandomString(50));
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getStorageDirectory",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new File(ReturnReplacements.getRandomString(50));
                                        }
                                    }
                            );
                        }
                        //getDataDirectory
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getExternalStorageDirectory",
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.afterHookedMethod(param);
                                            String result = ((File) param.getResult()).getAbsolutePath();
                                            if (!result.endsWith( "/")){result += "/";}
                                            result += "Android/data/" + lpparam.packageName + "/";
                                            param.setResult(new File(result));
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "hasInterestingFiles",
                                    File.class, returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isInterestingFile",
                                    File.class, returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getExternalStoragePublicDirectory",
                                    String.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new File(ReturnReplacements.getRandomString(30));
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getExternalStorageState",
                                    File.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(6);
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getStorageState",
                                    File.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(6);
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getExternalStorageState",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Environment.MEDIA_MOUNTED;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageRemovable", returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageRemovable",
                                    File.class, returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageEmulated", returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageEmulated",
                                    File.class, returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageLegacy", returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageLegacy",
                                    File.class, returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageManager", returnFalse
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isExternalStorageManager",
                                    File.class, returnFalse
                            );
                        }
                        //getDirectory
                        //getDirectoryPath
                    }catch (Exception e){LoggerLog(e);}
                }
            }//not finished
            if (HookStatFs){
                hookClass = XposedHelpers.findClassIfExists(
                        android.os.StatFs.class.getName(),
                        lpparam.classLoader);
                 if (hookClass != null){
                    XposedBridge.hookAllMethods(hookClass,
                            "doStat",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return generateStructStatVfs();
                                }
                            }
                    );
                }
            }//not finished
            if (HookPowerManager){
                hookClass = XposedHelpers.findClassIfExists(android.os.PowerManager.class.getName(),lpparam.classLoader);
                if (hookClass != null) {
                    try {
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getBrightnessConstraint", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "userActivity", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "goToSleep", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "wakeUp", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "nap", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "dream", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "boostScreenBrightness", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isWakeLockLevelSupported", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isScreenOn", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isInteractive", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isRebootingUserspaceSupportedImpl", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "reboot", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "rebootSafeMode", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "areAutoPowerSaveModesEnabled", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isPowerSaveMode", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setPowerSaveModeEnabled", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getBatteryDischargePrediction",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Duration.ZERO;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isBatteryDischargePredictionPersonalized", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getLocationPowerSaveMode", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getSoundTriggerPowerSaveMode", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isDeviceIdleMode", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isDeviceLightIdleMode", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isLightDeviceIdleMode", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isLowPowerStandbySupported", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setLowPowerStandbyActiveDuringMaintenance", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "forceLowPowerStandbyActive", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setLowPowerStandbyPolicy", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isExemptFromLowPowerStandby", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isAllowedInLowPowerStandby", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isIgnoringBatteryOptimizations", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "shutdown", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isSustainedPerformanceModeSupported", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getCurrentThermalStatus", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getThermalHeadroom",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Float.MAX_VALUE;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setDozeAfterScreenOff", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isAmbientDisplayAvailable", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "suppressAmbientDisplay", ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isAmbientDisplaySuppressedForToken", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isAmbientDisplaySuppressed", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isAmbientDisplaySuppressedForTokenByApp", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getLastShutdownReason", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getLastSleepReason", ReturnReplacements.returnIntegerMAX
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "forceSuspend", ReturnReplacements.returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "forceSuspend", ReturnReplacements.returnTrue
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookSystemProperties){
                hookClass = XposedHelpers.findClassIfExists("android.os.SystemProperties",lpparam.classLoader);
                if (hookClass != null){
                    HashSet<String> out = new HashSet<>();
                    XposedBridge.hookAllMethods(hookClass, "native_get", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (infoMap.containsKey((String) param.args[0])){
                                param.setResult((infoMap.get((String) param.args[0])));return;
                            }
                            if (param.args[0].equals("debug.force_rtl")){param.setResult("false");return;}
                            if (param.args[0].equals("cache_key.package_info")){param.setResult(String.valueOf(random.nextLong()));return;}
                            if (((String)param.args[0]).startsWith("ro.gfx.driver")){param.setResult("");return;}
//                            param.setResult("0");
                            if (logSysProperties){
                                StringBuilder sb = new StringBuilder();
                                for (Object o : param.args) {
                                    sb.append("|").append(o.toString());
                                }
                                String toShow = "native_find  " + sb + " " + param.getResult();
                                if (out.contains(toShow)) {
                                    return;
                                }
                                out.add(toShow);
                                LoggerLog(toShow);
                            }
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "native_get_long", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (infoMap.containsKey((String) param.args[0])){
                                param.setResult(Long.parseLong(infoMap.get((String) param.args[0])));return;
                            }
//                            param.setResult(0L);
                            if (logSysProperties){
                                StringBuilder sb = new StringBuilder();
                                for (Object o : param.args) {
                                    sb.append("|").append(o.toString());
                                }
                                String toShow = "native_find  " + sb + " " + param.getResult();
                                if (out.contains(toShow)) {
                                    return;
                                }
                                out.add(toShow);
                                LoggerLog(toShow);
                            }
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "native_find", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (infoMap.containsKey((String) param.args[0])){
                                param.setResult(Long.parseLong(infoMap.get((String) param.args[0])));return;
                            }
//                            param.setResult(0L);
                            if (logSysProperties){
                                StringBuilder sb = new StringBuilder();
                                for (Object o : param.args) {
                                    sb.append("|").append(o.toString());
                                }
                                String toShow = "native_find  " + sb + " " + param.getResult();
                                if (out.contains(toShow)) {
                                    return;
                                }
                                out.add(toShow);
                                LoggerLog(toShow);
                            }
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "native_get_boolean", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (infoMap.containsKey((String) param.args[0])){
                                param.setResult(Boolean.parseBoolean(infoMap.get((String) param.args[0])));return;
                            }
//                            param.setResult(0);
                            if (logSysProperties){
                                StringBuilder sb = new StringBuilder();
                                for (Object o : param.args) {
                                    sb.append("|").append(o.toString());
                                }
                                String toShow = "native_find  " + sb + " " + param.getResult();
                                if (out.contains(toShow)) {
                                    return;
                                }
                                out.add(toShow);
                                LoggerLog(toShow);
                            }
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "native_get_int", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (infoMap.containsKey((String) param.args[0])){
                                param.setResult(Integer.parseInt(infoMap.get((String) param.args[0])));return;
                            }
//                            param.setResult(0);
                            if (logSysProperties){
                                StringBuilder sb = new StringBuilder();
                                for (Object o : param.args) {
                                    sb.append("|").append(o.toString());
                                }
                                String toShow = "native_find  " + sb + " " + param.getResult();
                                if (out.contains(toShow)) {
                                    return;
                                }
                                out.add(toShow);
                                LoggerLog(toShow);
                            }
                        }
                    });
                }
            }
        }
    }

    public static StructStatVfs generateStructStatVfs(){
        return new StructStatVfs(
                4096L,//Math.abs(4096L + random.nextInt(4097))
                4096L,//Math.abs(4096L + random.nextInt(4097))
                Math.abs(28050939L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(12633283L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(12633283L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(13511677L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(12666051L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(12666051L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(64517L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(1030L + random.nextInt(Integer.MAX_VALUE)),
                Math.abs(255L + random.nextInt(256)));
    }
}
