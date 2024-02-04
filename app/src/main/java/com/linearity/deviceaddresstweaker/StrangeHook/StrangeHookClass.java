package com.linearity.deviceaddresstweaker.StrangeHook;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class StrangeHookClass {
    public static boolean HookSecClass = false;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        if (HookSecClass){
            if (!lpparam.processName.split(":")[0].contains("com.jingcai.apps")){return;}
            try {
                XposedHelpers.findAndHookMethod(
                        Runtime.class.getName(),
                        lpparam.classLoader,
                        "exec",
                        String.class,
                        String[].class,
                        File.class,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                LoggerLog(lpparam.packageName + "调用Runtime.class exec()" + param.getResult());
                                Object[] args = param.args;
                                ////LoggerLog(lpparam.packageName + "Runtime.class exec args" + Arrays.toString(args));
                                Process process = null;
                                String command = String.valueOf(args[0]);
                                if (command.contains("getprop")) {
                                    if (command.equals("getprop debug.sf.hw")) {
                                        process = Runtime.getRuntime().exec("getprop debug.sf.hw", null, null);
                                        param.setResult(process);
                                    }
                                } else if (command.contains("ls")) {
                                    if (!command.equals("ls -l /system/bin/getprop")) {
                                        Process process1 = Runtime.getRuntime().exec("ls -l /system/bin/getprop", null, null);
                                        param.setResult(process1);
                                    }
                                    ////LoggerLog(lpparam.packageName + "Runtime.class exec args set to " + "ls -l /system/bin/getprop");
                                } else if (command.contains("cat")) {
                                    if (!command.equals("cat /proc")) {
                                        Process process1 = Runtime.getRuntime().exec("cat /proc", null, null);
                                        param.setResult(process1);
                                    }
                                } else {
                                    //LoggerLog(lpparam.packageName + " Runtime.class exec args" + Arrays.toString(args));
                                    if (!command.contains(lpparam.processName.split(":")[0])) {
                                        param.setResult(process);
                                    }
                                    ////LoggerLog(lpparam.packageName + "exec process set null");
                                }
//                            if (!String.valueOf(args[0]).contains("logcat")){
//                                param.setResult(null);
//                            }
                            }
                        }
                );
            } catch (Exception e) {
                //LoggerLog(e);
            }
            try{
                XposedHelpers.findAndHookMethod(
                        "a.auu.a",
                        lpparam.classLoader,
                        "c", String.class,
                        new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                String tryResult = SecCourseStrDecodeResult((String) param.args[0]);
//                                if (tryResult.equals("检测到该应用在hook环境中运行")){LoggerLog("being detected!");}
//                                if (tryResult.equals("errorCannotDecode")){
                                    LoggerLog(param.args[0]);
                                    LoggerLog(param.getResult());
                                    LoggerLog("---------------------");
//                                }

                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedHelpers.findAndHookMethod(
//                        Runtime.class,
//                        "nativeLoad",
//                        String.class, ClassLoader.class, Class.class,
//                        new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                                LoggerLog("----------");
//                                LoggerLog(param.args[0]);
//                                LoggerLog("----------");
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass("com.netease.nis.wrapper.MyApplication",
//                                lpparam.classLoader),
//                        "g",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                return true;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass(
//                                "com.netease.nis.wrapper.MyApplication",
//                                lpparam.classLoader),
//                        "l",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                return true;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass(
//                                "com.netease.nis.wrapper.MyJni",
//                                lpparam.classLoader),
//                        "ip",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                return null;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass(
//                                "com.netease.nis.wrapper.MyJni",
//                                lpparam.classLoader),
//                        "cl",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                return lpparam.classLoader;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass("com.netease.nis.wrapper.MyJni",
//                                lpparam.classLoader),
//                        "cp",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                return null;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
            try{
                XposedBridge.hookAllMethods(
                        XposedHelpers.findClass("com.netease.nis.wrapper.MyJni",
                                lpparam.classLoader),
                        "getEnvInfo",
                        new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                return getRandomString(10);
                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}
            try {
                XposedBridge.hookAllMethods(
                        Runtime.class,
                        "nativeExit",
                        new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                LoggerLog("exit Called " + lpparam.processName);
                                return null;
                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}

            try{
                XposedBridge.hookAllMethods(
                        XposedHelpers.findClass("com.netease.nis.wrapper.NEDialog",
                                lpparam.classLoader),
                        "exitApp",
                        new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                return true;
                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}


//            try{
//                XposedHelpers.findAndHookMethod(
//                        "com.netease.nis.wrapper.m",
//                        lpparam.classLoader,
//                        "a",
//                        Context.class, String.class,
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                System.load("/data/data/com.jingcai.apps.qualitydev/app_lib/libnesec.so");
//                                return null;
//                            }//                        }
////                        new XC_MethodHook(114514) {
////                            @Override
////                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                                super.beforeHookedMethod(param);
////                                if (((String) param.args[1]).contains("libnesec.so")){
////                                    LoggerLog("Loaded Library Replaced");
////                                    ((String) param.args[1]).replace("libnesec.so","libnesectweaked.so");
////                                }
////                                else if (((String) param.args[2]).equals("nesec")){
////                                    LoggerLog("Loaded Library Replaced");
////                                    param.args[1] = "nesectweaked";
////                                }
////                                else {
////                                    LoggerLog("Loaded Library:");
////                                    LoggerLog(param.args[2]);
////                                }
////                            }
////                        }
//                );
//            }catch (Exception e){LoggerLog(e);}

            try{
                XposedHelpers.findAndHookMethod(
                        Runtime.class,
                        "load0",

                        Class.class,String.class,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (((String) param.args[1]).contains("libnesec.so")){
                                    LoggerLog("Loaded Library Replaced");
                                    ((String) param.args[1]).replace("libnesec.so","libnesectweaked.so");
                                }
                                else if (((String) param.args[1]).equals("nesec")){
                                    LoggerLog("Loaded Library Replaced");
                                    param.args[1] = "nesectweaked";
                                }
                                else {
                                    LoggerLog("Loaded Library:");
                                    LoggerLog(param.args[1]);
                                }
                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}

            try{
                XposedHelpers.findAndHookMethod(
                        Runtime.class,
                        "nativeLoad",

                        String.class,ClassLoader.class,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (((String) param.args[0]).contains("libnesec.so")){
                                    LoggerLog("Loaded Library Replaced");
                                    ((String) param.args[0]).replace("libnesec.so","libnesectweaked.so");
                                }
                                else if (((String) param.args[0]).equals("nesec")){
                                    LoggerLog("Loaded Library Replaced");
                                    param.args[0] = "nesectweaked";
                                }
                                else {
                                    LoggerLog("Loaded Library:");
                                    LoggerLog(param.args[0]);
                                }
                            }
                        }
                );
            }catch (Exception e){LoggerLog(e);}

//            try{
//                XposedHelpers.findAndHookMethod(
//                        Runtime.class,
//                        "nativeLoad",
//
//                        String.class,ClassLoader.class,Class.class,
//                        new XC_MethodHook(114514) {
//                            @Override
//                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                super.beforeHookedMethod(param);
//                                if (((String) param.args[0]).contains("libnesec.so")){
//                                    LoggerLog("Loaded Library Replaced");
//                                    ((String) param.args[0]).replace("libnesec.so","libnesectweaked.so");
//                                }
//                                else if (((String) param.args[0]).equals("nesec")){
//                                    LoggerLog("Loaded Library Replaced");
//                                    param.args[0] = "nesectweaked";
//                                }
//                                else {
//                                    LoggerLog("Loaded Library:");
//                                    LoggerLog(param.args[0]);
//                                }
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
//            try{
//                XposedBridge.hookAllMethods(
//                        XposedHelpers.findClass("com.netease.nis.wrapper.MyApplication",
//                                lpparam.classLoader),
//                        "attachBaseContext",
//                        new XC_MethodReplacement() {
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
////                                DalvikBridgeFunc bridge = gDvmJni.useCheckJni ? dvmCheckCallJNIMethod : dvmCallJNIMethod;
////                                ALOGI("invoke native method %s, addr:%p", method->name, method->insns);
//                                System.load("/data/user/0/com.jingcai.apps.qualitydev/app_lib/libnesec.so");
//                                return null;
//                            }
//                        }
//                );
//            }catch (Exception e){LoggerLog(e);}
            if (false) {
                LoggerLog( "[linearity]HookToastReady");
                XposedHelpers.findAndHookMethod(XposedHelpers.findClass("android.widget.Toast", lpparam.classLoader), "makeText", Context.class, CharSequence.class, int.class, new XC_MethodHook(114514) {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        LoggerLog("[linearity]BeforeToastHook: " + param.args[0] + " " + param.args[1] + " " + param.args[2] + " ");
                        super.beforeHookedMethod(param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        LoggerLog("[linearity]AfterToastHook: " + param.getResult());
                        LoggerLog("Dump Stack: " + "---------------start----------------");
                        Throwable ex = new Throwable();
                        StackTraceElement[] stackElements = ex.getStackTrace();
                        if (stackElements != null) {
                            for (int i = 0; i < stackElements.length; i++) {

                                LoggerLog("Dump Stack" + i + ": " + stackElements[i].getClassName()
                                        + "----" + stackElements[i].getFileName()
                                        + "----" + stackElements[i].getLineNumber()
                                        + "----" + stackElements[i].getMethodName());
                            }
                        }
                        LoggerLog("Dump Stack: " + "---------------over----------------");
//                        Context context = ((Application) param.args[0]).getApplicationContext();
//                            Toast.makeText(context, "FinishHookToast", Toast.LENGTH_LONG).show();
                        super.afterHookedMethod(param);
                    }
                });
//                    LoggerLog("[linearity]HookSignReady");
//

            }
        }
    }

    public static String SecCourseStrDecodeResult(String input){
        switch (input){
            default:return "errorCannotDecode";
            case "ORcVFREWFw==": {
                return "wrapper";
            }
            case "LQoZSw8WESsEBwBPHQw9SwMXAAMVKxdaKBgyFT4JHQYABwwhCw==":return "com.netease.nis.wrapper.MyApplication";
            case "Kx0AFwAQEREWAwwVEA0RVQ==":return "extract_switch_0";
            case "PhcbEwgXADw6BxIIBwYmOkU=":return "provider_switch_1";
            case "PQ0RCQ0sCScIHRE+Qw==":return "shell_limit_0";
            case "PREBBw==":return "stub";
            case "fw==":return "1";
            case "FQsRAAUwCiARBgoNIA0rCRgpAAYLLQ0pRQMgDSsJGCkABgstDTgMDBoRKwFO":return "[needControlShellLaunch] bShellLaunchLimited:";
            case "bggnDQQfCQ0LAF8=":return " mShellCnt:";
            case "FQQAEQAQDQwEBwAiHAs6AAwRPFMHKwIdC01TEysXBwwOHV8=":return "[attachBaseContext] begin, version:";
            case "eUtBS1IsUX5U":return "7.5.3_401";
            case "bggkFw4FDCoABjYWGhEtDU4=":return " mProviderSwitch:";
            case "FQsRAAU2HToXFQYVIAoTRRYrBBYBCx0ABAIHXw==":return "[needExtractSo] bNeedExtact:";
            case "DDAzNzEnOh0yPTEiOw==":return "BUGRPT_SWITCH";
            case "IQs=":return "on";
            case "IAAHAAI=":return "nesec";
            case "YRUGCgJcFisJEkoMEhU9":return "/proc/self/maps";
            case "YRYNFhUWCGEHHQtOEhU+OgQXDhAAPRY=":return "/system/bin/app_process";
            case "LxcZU1VeE3YE":return "arm64-v8a";
            case "IgwaDgQBXw==":return "linker:";
            case "bgQGBglJ":return " arch:";
            case "Nl1C":return "x86";
            case "HRwHEQQeSyIKFQEtGgc8BAYcQQ==":return "System.loadLibrary ";
            case "bhYBBgIWFj0=":return " success";
            case "YRUGCgJc":return "/proc/";
            case "YQYZAQ0aCys=":return "/cmdline";
            case "qMb0g9T4gMbVnMrElt/aguDNhO/NJgobDob9yqvH94HZ3o3x9ZzE7Q==":return "检测到该应用在hook环境中运行";
            case "Kx0XABEHDCELTg==":return "exception:";
            case "FQkbBAUuRToMGQBb":return "[load] time:";
            case "OgQEEQAD":return "taptap";
            case "IAoANRMcBy8RHQoP":return "notProbation";
            case "FQQAEQAQDQwEBwAiHAs6AAwRPFMAIAFYRRUaCCtf":return "[attachBaseContext] end, time:";
            case "KQAAIwgWCSo2NyEEAAZuFxEUFBoXK0UaBAwWX2sWVBEYAwB0QAc=":return "getFieldSCDesc require name:%s type:%s";
            case "LQkVFhJT":return "class ";
            case "JwsAABMVBC0AVA==":return "interface ";
            case "FQ==":return "[";
            case "JwsA":return "int";
            case "KAkbBBU=":return "float";
            case "IgoaAg==":return "long";
            case "FQoaJhMWBDoAKUUEHQFiRQAMDBZf":return "[onCreate] end, time:";
        }
    }
}
