package com.linearity.deviceaddresstweaker.Wechat;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

import android.app.Application;
import android.content.Context;

import de.robv.android.xposed.XC_MethodHook;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookWechatClass {

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        try{
            if (lpparam.processName.split(":")[0].contains("tencent.mm")) {
                Class<?> dumpClass = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.h0", lpparam.classLoader);
//                LoggerLog(Arrays.toString(dumpClass.getMethods()));
//                    Class<?> fclass2 = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.g", lpparam.classLoader);
                XposedBridge.hookAllMethods(dumpClass, "a",
//                            Context.class, fclass2, String.class, boolean.class,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                LoggerLog("[linearity]BeforeSignHook: " + param.args[0] + " " + param.args[1] + " " + param.args[2] + " ");
                                param.setResult(true);
                            }
                        });

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
                            Context context = ((Application) param.args[0]).getApplicationContext();
//                            Toast.makeText(context, "FinishHookToast", Toast.LENGTH_LONG).show();
                            super.afterHookedMethod(param);
                        }
                    });
//                    LoggerLog("[linearity]HookSignReady");
//

                }
            }
        }catch (Exception e){
            LoggerLog(e);
        }
    }
}
