package com.linearity.deviceaddresstweaker.JavaHooks.java.lang;

import java.io.File;
import java.util.Arrays;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

public class HookLang {
    public static boolean HookLang = true;
    public static boolean HookRuntime = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
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
                                        LoggerLog(lpparam.packageName + " Runtime.class exec args" + Arrays.toString(args));
                                        if (!command.contains(lpparam.packageName)) {
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
                    LoggerLog(e);
                }
        }
        }
    }
}
