package com.linearity.deviceaddresstweaker.AndroidHooks.android.content;

import static com.linearity.utils.HookUtils.disableMethod;
import static com.linearity.utils.HookUtils.disableMethod_random;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.getRandomString;

import android.content.ClipboardManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.pm.HookPmClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.res.HookResClass;

import android.content.ContextWrapper;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookContentClass {
    public static boolean HookContent = true;
    public static boolean HookContext = true;
    public static boolean HookContextWrapper = true;
    public static boolean HookClipboardManager = true;
    public static boolean HookIntentFilter = true;

    public static final String[] bannedIntentHead = new String[]{
            "android.",//go fuck yourself
//            "android.net",
//            "android.intent.action.MEDIA","android.security","android.hardware",
//            "android.intent.action.BATTERY_CHANGED","android.intent.action.PROXY_CHANGE","android.intent.action.PROXY_CHANGE",
//            "android.intent.action.SCREEN","android.intent.action.PACKAGE","android.intent.action.TIME"
//            ,"android.intent.action.DATE","android.intent.action.PHONE","android.intent.action.NEW_OUTGOING_CALL",
//            "android.intent.action"
    };
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookContent = sharedPreferences.getBoolean("HookContentClass_HookContent", true);
        HookContext = sharedPreferences.getBoolean("HookContentClass_HookContext", true);
        HookClipboardManager = sharedPreferences.getBoolean("HookContentClass_HookClipboardManager", true);
        HookIntentFilter = sharedPreferences.getBoolean("HookContentClass_HookIntentFilter",true);
        HookContextWrapper = sharedPreferences.getBoolean("HookContentClass_HookContextWrapper",true);

        Class<?> hookClass;
        if (HookContent){
            HookResClass.DoHook(lpparam,procHead,sharedPreferences);
            if (HookContext){
//                try {
//                    findAndHookMethodIfExists(
//                            android.content.Context.class.getName(),
//                            lpparam.classLoader,
//                            "checkSelfPermission",
//                            String.class,
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return PackageManager.PERMISSION_GRANTED;
//                                }
//                            }
//                    );
//                }catch (Exception e){
//                    LoggerLog(e);
//                }
            }
            if (HookContextWrapper){
                hookClass = XposedHelpers.findClassIfExists(ContextWrapper.class.getName(),lpparam.classLoader);
                if(hookClass != null){
                    XposedBridge.hookAllMethods(hookClass, "registerReceiver", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (param.args[1]==null){return;}
                            Collection<String> mActions = (Collection<String>) XposedHelpers.getObjectField(param.args[1],"mActions");
                            removeIntentForFilter(mActions);
                        }
                    });
                }
            }
            if (HookClipboardManager){
                hookClass = XposedHelpers.findClassIfExists(ClipboardManager.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    for (Method m:hookClass.getDeclaredMethods()){
                        if (m.getName().equals("setPrimaryClip")){continue;}
                        disableMethod_random(m, hookClass);//now all of U cannot steal my clipboard
                    }
                }
            }
            if (HookIntentFilter){
                hookClass = XposedHelpers.findClassIfExists(IntentFilter.class.getName(),lpparam.classLoader);
                if (hookClass != null){//not finished
                    XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            if (param.args.length == 0){return;}
                            if (param.args[0] instanceof String){
                                String toAdd = (String) param.args[0];
                                if (toAdd==null){return;}
                                for (String checkHead:bannedIntentHead){
                                    if (toAdd.startsWith(checkHead)){
                                        param.args[0] = getRandomString(40);return;
                                    }
                                }
                            }
//                            Collection<String> mActions = (Collection<String>) XposedHelpers.getObjectField(param.thisObject,"mActions");
//                            removeIntentForFilter(mActions);
                        }
                    });
                    XposedBridge.hookAllMethods(hookClass, "addAction", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            String toAdd = (String) param.args[0];
                            if (toAdd==null){return;}
                            for (String checkHead:bannedIntentHead){
                                if (toAdd.startsWith(checkHead)){
                                    param.setResult(null);
                                    return;
                                }
                            }
//                            LoggerLog("Adding action for IntentFilter:" + toAdd);

                        }
                    });
                }
            }
            HookPmClass.DoHook(lpparam, procHead, sharedPreferences);
        }
    }


    public static void removeIntentForFilter(Collection<String> mActions){
//        for (String action:mActions){
//            LoggerLog("found Action:"+action);
//        }
        mActions.removeIf(s -> {
            for (String checkHead:bannedIntentHead){
                if (s.startsWith(checkHead)){
                    return true;
                }
            }
            return false;
        });
    }
}
