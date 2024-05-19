package com.linearity.utils;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class HookUtils {
    public static XC_MethodHook.Unhook findAndHookMethodIfExists(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback){
        Class<?> hookClass = XposedHelpers.findClassIfExists(className,classLoader);
        if (hookClass == null){LoggerLog("cannot find class: " + className);return null;}
        return findAndHookMethodIfExists(hookClass,methodName,parameterTypesAndCallback);
    }

    public static XC_MethodHook.Unhook findAndHookMethodIfExists(Class<?> clazz, String methodName, Object... parameterTypesAndCallback){
        if (parameterTypesAndCallback.length == 0 || !(parameterTypesAndCallback[parameterTypesAndCallback.length-1] instanceof XC_MethodHook))
            throw new IllegalArgumentException("no callback defined");
        XC_MethodHook callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length-1];
        Method m = XposedHelpers.findMethodExactIfExists(clazz, methodName, parameterTypesAndCallback);
        if (m == null){
            LoggerLog("cannot find method : " + methodName + "(for class : " + clazz.getName() + ")");
//            LoggerLog(new Exception("cannot find method : " + methodName + "(for class : " + clazz.getName() + ")"));
            return null;
        }
        return XposedBridge.hookMethod(m, callback);
    }
}
