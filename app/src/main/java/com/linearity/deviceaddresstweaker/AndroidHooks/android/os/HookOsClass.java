package com.linearity.deviceaddresstweaker.AndroidHooks.android.os;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;

import android.os.Build;
import android.os.Environment;
import android.system.StructStatVfs;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

import java.io.File;

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

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam) {
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
//                    XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", getRandomString(20));
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
                try {
                    XposedHelpers.findAndHookMethod(
                            android.os.Environment.class.getName(),
                            lpparam.classLoader,
                            "getDataDirectory",
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
            }
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
            }
        }
    }
}
