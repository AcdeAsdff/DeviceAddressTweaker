package com.linearity.deviceaddresstweaker.JavaHooks.java.lang;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.FakeProcInfoGenerator.emptyProcess;
import static com.linearity.utils.FakeSystemProperties.propertiesMap;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.LoggerUtils.showObjectFields;
import static com.linearity.utils.ReturnReplacements.getRandomString;

import com.linearity.deviceaddresstweaker.JavaHooks.java.lang.reflect.HookReflect;
import com.linearity.utils.FakeProcInfoGenerator;

public class HookLang {
    public static boolean HookLang = true;
    public static boolean HookRuntime = true;
    public static boolean HookThrowable = true;
    public static boolean HookObject = true;
    public static boolean HookClass = true;
    public static boolean Listen2Throwable = false;
    public static boolean HookStackTraceElement = true;
    public static boolean HookSystem = true;
//    public static Throwable emptyThrowable = new LinearityException();
//    public static Throwable[] emptyThrowables = new Throwable[]{emptyThrowable};
//    public static StackTraceElement emptyStackTrace = new StackTraceElement("[linearity]","[emptyST]","[noFileName]",114514);
//    public static StackTraceElement[] emptyStackTraces = new StackTraceElement[]{emptyStackTrace};
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookLang = sharedPreferences.getBoolean("HookLang_HookLang", true);
        HookSystem = sharedPreferences.getBoolean("HookLang_HookSystem", true);
        HookRuntime = sharedPreferences.getBoolean("HookLang_HookRuntime", true);
        HookThrowable = sharedPreferences.getBoolean("HookLang_HookThrowable", true);
        HookObject = sharedPreferences.getBoolean("HookLang_HookObject", true);
        HookClass = sharedPreferences.getBoolean("HookLang_HookClass", true);
        HookStackTraceElement = sharedPreferences.getBoolean("HookLang_HookStackTraceElement",true);
        Listen2Throwable = sharedPreferences.getBoolean("HookLang_Listen2Throwable", false);
        Class<?> hookClass;
        if (HookLang){
            if (HookSystem){
                hookClass = XposedHelpers.findClassIfExists(System.class.getName(),lpparam.classLoader);
                if(hookClass != null){
                    XposedBridge.hookAllMethods(hookClass, "getProperty", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            String prop = (String) param.args[0];
                            if (prop==null){return;}
                            if (prop.equals("java.library.path")) {
                                return;
                            }
                            if (propertiesMap.containsKey(prop)){
                                param.setResult(propertiesMap.get(prop));return;
                            }
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            String prop = (String) param.args[0];
                            if (!propertiesMap.containsKey(prop)){
                                if (param.getResult() != null){
                                    LoggerLog("propertiesMap.put(\""+prop+"\",\""+param.getResult()+"\");");
                                }else {
                                    LoggerLog("propertiesMap.put(\""+prop+"\",null);");
                                }
                            }
                        }
                    });
//                    Properties sysProps = (Properties) XposedHelpers.getStaticObjectField(hookClass,"props");
//                    for (String name:sysProps.stringPropertyNames()){
//                        if (propertiesMap.containsKey(name)){
//                            sysProps.setProperty(name,propertiesMap.get(name));
//                            continue;
//                        }
//                        LoggerLog("propertiesMap.put(\""+name+"\",\""+sysProps.getProperty(name)+"\");");
////                        LoggerLog(name + " | " + sysProps.getProperty(name));
//                    }
                }
            }
            if (HookRuntime){//      Runtime.class exec
                hookClass = XposedHelpers.findClassIfExists(
                        Runtime.class.getName(),
                        lpparam.classLoader);
                if (hookClass != null){
                    try {
                        XposedBridge.hookAllMethods(hookClass,
                                "exec",
                                new XC_MethodHook(114514) {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        String replaceToCommand = "getprop none";
                                        //LoggerLog(lpparam.packageName + "调用Runtime.class exec()" + param.getResult());
                                        Object[] args = param.args;
//                                        LoggerLog(lpparam.packageName + "Runtime.class exec args" + Arrays.toString(args));
                                        Process process;
                                        String command;
                                        if (args[0] instanceof String[]){
                                            command  = Arrays.toString((String[])args[0]);
                                        }else {
                                            command = (String) args[0];
                                        }
                                        if (command.contains("getprop")){
                                            param.setResult(FakeProcInfoGenerator.INSTANCE.generate(null));
                                            return;
                                        }
//                                        if (!Objects.equals(command, "sh")){
//                                            LoggerLog("[linearity-ShellListener]",new Exception("executing:"+ command));
//                                        }
                                        param.setResult(emptyProcess);
                                    }

//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        Process result = (Process) param.getResult();
//                                        LoggerLog(result.getOutputStream().getClass().getTypeName());
//                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
        }//not finished
            if (HookThrowable){
                try {
                    {
                        XposedBridge.hookAllMethods(
                                Throwable.class,
                                "toString",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        String result = (String) param.getResult();
                                        if (result.contains("posed") || result.contains("LSPHooker")) {
                                            result = "android.os.Handler";
                                        }
                                        param.setResult(result);
                                    }
                                }
                        );
                    }
                    {
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
                    }
                    {
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
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            else if(Listen2Throwable){
                try {
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
                    {
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
                    }
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
                hookClass = XposedHelpers.findClassIfExists(Class.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getName",
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            String result = (String) param.getResult();
                                            if (result.contains("posed") || result.contains("LSPHooker")) {
                                                result = "android.os.Handler";
                                            }
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getNameNative",
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            String result = (String) param.getResult();
                                            if (result.contains("posed") || result.contains("LSPHooker")) {
                                                result = "android.os.Handler";
                                            }
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookStackTraceElement){
                hookClass = XposedHelpers.findClassIfExists(StackTraceElement.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                     try {
                         {
                             XposedBridge.hookAllMethods(
                                     hookClass,
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
                         }
                         {
                             XposedBridge.hookAllMethods(
                                     hookClass,
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
                         }
                         {
                             XposedBridge.hookAllMethods(
                                     hookClass,
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
                         }
                     }catch (Exception e){LoggerLog(e);}
                }

            }//md,跟美团拼了 https://tech.meituan.com/2018/02/02/android-anti-hooking.html
            HookReflect.DoHook(lpparam,procHead,sharedPreferences);
        }
    }


}
