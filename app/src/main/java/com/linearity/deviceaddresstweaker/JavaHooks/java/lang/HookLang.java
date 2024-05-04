package com.linearity.deviceaddresstweaker.JavaHooks.java.lang;

import java.io.File;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;
import com.linearity.deviceaddresstweaker.JavaHooks.java.lang.reflect.HookReflect;

public class HookLang {
    public static boolean HookLang = true;
    public static boolean HookRuntime = true;
    public static boolean HookThrowable = true;
    public static boolean HookObject = true;
    public static boolean HookClass = true;
    public static boolean Listen2Throwable = false;
    public static boolean HookStackTraceElement = true;
//    public static Throwable emptyThrowable = new LinearityException();
//    public static Throwable[] emptyThrowables = new Throwable[]{emptyThrowable};
//    public static StackTraceElement emptyStackTrace = new StackTraceElement("[linearity]","[emptyST]","[noFileName]",114514);
//    public static StackTraceElement[] emptyStackTraces = new StackTraceElement[]{emptyStackTrace};
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookLang = sharedPreferences.getBoolean("HookLang_HookLang", true);
        HookRuntime = sharedPreferences.getBoolean("HookLang_HookRuntime", true);
        HookThrowable = sharedPreferences.getBoolean("HookLang_HookThrowable", true);
        HookObject = sharedPreferences.getBoolean("HookLang_HookObject", true);
        HookClass = sharedPreferences.getBoolean("HookLang_HookClass", true);
        HookStackTraceElement = sharedPreferences.getBoolean("HookLang_HookStackTraceElement",true);
        Listen2Throwable = sharedPreferences.getBoolean("HookLang_Listen2Throwable", false);
        if (HookLang){
            if (HookRuntime){//      Runtime.class exec
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
                                    //LoggerLog(lpparam.packageName + "调用Runtime.class exec()" + param.getResult());
                                    Object[] args = param.args;
                                    //LoggerLog(lpparam.packageName + "Runtime.class exec args" + Arrays.toString(args));
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
                                        //LoggerLog(lpparam.packageName + "Runtime.class exec args set to " + "ls -l /system/bin/getprop");
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
                                        //LoggerLog(lpparam.packageName + "exec process set null");
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
        }//not finished
            if (HookThrowable){
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "toString",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "readObject",
                            new XC_MethodReplacement() {
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
                            Throwable.class,
                            "writeObject",
                            new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            else if(Listen2Throwable){
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "getMessage",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "getLocalizedMessage",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "getCause",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "initCause",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "toString",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "fillInStackTrace",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "getStackTrace",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "setStackTrace",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "addSuppressed",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Throwable.class,
                            "getSuppressed",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(lpparam.processName + " called method " + param.method.getName());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookObject){
                try {
                    XposedBridge.hookAllMethods(
                            Object.class,
                            "toString",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }

            }
            if (HookClass){
                try {
                    XposedBridge.hookAllMethods(
                            Class.class,
                            "getName",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            Class.class,
                            "getNameNative",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookStackTraceElement){
                try {
                    XposedBridge.hookAllMethods(
                            StackTraceElement.class,
                            "getClassName",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            StackTraceElement.class,
                            "getMethodName",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            StackTraceElement.class,
                            "toString",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String result = (String) param.getResult();
                                    if(result.contains("posed")||result.contains("LSPHooker")){
                                        result = "android.os.Handler";
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                
            }//md,跟美团拼了 https://tech.meituan.com/2018/02/02/android-anti-hooking.html
            HookReflect.DoHook(lpparam,procHead,sharedPreferences);
        }
    }
}
