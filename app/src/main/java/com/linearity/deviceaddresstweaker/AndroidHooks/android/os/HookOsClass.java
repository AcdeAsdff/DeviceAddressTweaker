package com.linearity.deviceaddresstweaker.AndroidHooks.android.os;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;

import android.os.Build;
import android.os.Environment;
import android.system.StructStatVfs;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

import java.io.File;
<<<<<<< Updated upstream
=======
import java.time.Duration;
import java.util.ArrayList;
>>>>>>> Stashed changes

public class HookOsClass {
    public static StructStatVfs emptyStructStatVfs =
            new StructStatVfs(
                    4096L,
                    4096L,
                    28050939L,
                    12633283L,
                    12633283L,
                    13511677L,
                    12666051L,
                    12633283L,
                    64517L,
                    1030L,
                    255L);
    public static boolean HookOs = true;
    public static boolean HookBuild = true;
    public static boolean HookEnvironment = true;
    public static boolean HookStatFs = true;
<<<<<<< Updated upstream

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam) {
=======
    public static boolean HookPowerManager = true;

    public static String[] foolCpuAll = new String[]{
            "x86",
            "x86_64",
            "arm64-v8a",
            "armeabi-v7a",
    };
    public static String[] foolCpu;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) {
        HookOs = sharedPreferences.getBoolean("HookOsClass_HookOs", true);
        HookBuild = sharedPreferences.getBoolean("HookOsClass_HookBuild", true);
        HookEnvironment = sharedPreferences.getBoolean("HookOsClass_HookEnvironment", true);
        HookStatFs = sharedPreferences.getBoolean("HookOsClass_HookStatFs", true);
        HookPowerManager = sharedPreferences.getBoolean("HookOsClass_HookPowerManager", true);

                ArrayList<String> temp = new ArrayList<>();
        String UrCpu = (String) XposedHelpers.getStaticObjectField(Build.class, "CPU_ABI");
        for (String check:foolCpuAll){
            if (!check.equals(UrCpu)){
                temp.add(check);
            }
        }
        foolCpu = temp.toArray(new String[0]);
>>>>>>> Stashed changes
        if (HookOs){
            if (HookBuild) {
                //      (StaticObjectField) android.os.Build.class MODEL|BRAND|BOARD|DEVICE|DISPLAY
//        android.os.Build.VERSION.class SDK
                try {
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "SERIAL", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "DISPLAY", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "BOOTLOADER", getRandomString(20));
<<<<<<< Updated upstream
//                    XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", getRandomString(20));
=======
//                    XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", foolCpu[random.nextInt(foolCpu.length)]);
//                    XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", "x86_32");
>>>>>>> Stashed changes
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI2", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "FINGERPRINT", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "HARDWARE", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "RADIO", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "TAGS", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "TIME", 1);
                    XposedHelpers.setStaticObjectField(android.os.Build.class, "TYPE", getRandomString(20));
//                    XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "SDK", "R");
                    XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "RELEASE", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "CODENAME", getRandomString(20));
                    XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "INCREMENTAL", getRandomString(20));
//                    XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "SDK_INT", 30);
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Build.class.getName(),
                            lpparam.classLoader,
                            "getRadioVersion",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getRadioVersion()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Build.class.getName(),
                            lpparam.classLoader,
                            "getSerial",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(50);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
            if (HookEnvironment){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getRootDirectory",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return new File(getRandomString(50));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getStorageDirectory",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return new File(getRandomString(50));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //getDataDirectory
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getExternalStorageDirectory",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.afterHookedMethod(param);
                                    String result = ((File) param.getResult()).getAbsolutePath();
                                    result = checkReplaceFile(result,lpparam);
                                    param.setResult(new File(result));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "hasInterestingFiles",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isInterestingFile",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getExternalStoragePublicDirectory",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return new File(getRandomString(30));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getExternalStorageState",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(6);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getStorageState",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(6);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getExternalStorageState",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Environment.MEDIA_MOUNTED;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageRemovable",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageRemovable",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageEmulated",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageEmulated",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageLegacy",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageLegacy",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageManager",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "isExternalStorageManager",
                            File.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //getDirectory
                //getDirectoryPath
            }//not finished
            if (HookStatFs){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.StatFs.class.getName(),
                            lpparam.classLoader,
                            "doStat",
                            String.class,
//                            new XC_MethodHook(114514) {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
//                                    param.args[0] = "/data";
//                                }
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.afterHookedMethod(param);
//                                    LoggerLog(((StructStatVfs)param.getResult()).toString());
//                                }
//                            }
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return emptyStructStatVfs;
                                    return emptyStructStatVfs;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
            if (HookPowerManager){
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getBrightnessConstraint",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "userActivity",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "goToSleep",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "wakeUp",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "nap",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "dream",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "boostScreenBrightness",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isWakeLockLevelSupported",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isScreenOn",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isInteractive",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isRebootingUserspaceSupportedImpl",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "reboot",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "rebootSafeMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "areAutoPowerSaveModesEnabled",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isPowerSaveMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "setPowerSaveModeEnabled",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getBatteryDischargePrediction",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Duration.ZERO;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isBatteryDischargePredictionPersonalized",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getLocationPowerSaveMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getSoundTriggerPowerSaveMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isDeviceIdleMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isDeviceLightIdleMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isLightDeviceIdleMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isLowPowerStandbySupported",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "setLowPowerStandbyActiveDuringMaintenance",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "forceLowPowerStandbyActive",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "setLowPowerStandbyPolicy",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isExemptFromLowPowerStandby",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isAllowedInLowPowerStandby",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isIgnoringBatteryOptimizations",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "shutdown",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isSustainedPerformanceModeSupported",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getCurrentThermalStatus",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getThermalHeadroom",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Float.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "setDozeAfterScreenOff",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isAmbientDisplayAvailable",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "suppressAmbientDisplay",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isAmbientDisplaySuppressedForToken",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isAmbientDisplaySuppressed",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "isAmbientDisplaySuppressedForTokenByApp",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getLastShutdownReason",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "getLastSleepReason",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "forceSuspend",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.os.PowerManager.class,
                            "forceSuspend",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
