package com.linearity.deviceaddresstweaker.TIM;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.*;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.app.PendingIntent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.NetworkScan;
import android.telephony.UiccCardInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTIMClass {
    public static boolean HookTIM = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        if (HookTIM){
            //tim or st. else(give it a try)
            try {
                if (XposedHelpers.findMethodExactIfExists(
                        "moai.core.utilities.appstate.AppStatuses",
                        lpparam.classLoader,
                        "isAppOnForeground",
                        Context.class) != null){
                    XposedHelpers.findAndHookMethod(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
<<<<<<< Updated upstream
                            "isAppOnForeground",
                            Context.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
=======
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "moai.core.utilities.appstate.AppStatuses",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null){
                        XposedHelpers.findAndHookMethod(
                                "moai.core.utilities.appstate.AppStatuses",
                                lpparam.classLoader,
                                "isAppOnBackGround",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.qqmail.utilities.AppStatusUtil",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null){
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.qqmail.utilities.AppStatusUtil",
                                lpparam.classLoader,
                                "isAppOnBackGround",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.BaseSmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.av.smallscreen.BaseSmallScreenService",
                                lpparam.classLoader,
                                "isAppOnForeground",

                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.SmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.av.smallscreen.SmallScreenService",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "aljz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "aljz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "aktz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "iue",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "iue",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "kvv",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "kvv",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "aktz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                            lpparam.classLoader,
                            "isAppOnForeground") != null){
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
>>>>>>> Stashed changes
                                }
                            }
                    );
                }
            }catch (Exception e){
                LoggerLog(e);
            }
            try {
                try{
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.common.app.AppInterface", lpparam.classLoader);
                    if (tencentAppInterface != null) {

                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch (Exception e){
                    LoggerLog(e);
                }
<<<<<<< Updated upstream
                try {
=======
                try{
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.android.tpush.common.AppInfos", lpparam.classLoader);
                    if (tencentAppInterface != null) {

                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch(Exception e){
                    LoggerLog(e);
                }
                try{
>>>>>>> Stashed changes
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("mqq.app.Foreground", lpparam.classLoader);
                    if (tencentAppInterface != null) {

                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                }catch (Exception e){
                    LoggerLog(e);
                }
                try{
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("mqq.app.BaseActivity", lpparam.classLoader);
                    if (tencentAppInterface != null) {

                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "checkSelfPermission",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return PERMISSION_GRANTED;
                                    }
                                }
                        );
                    }
                }catch (Exception e){
                    LoggerLog(e);
                }
                if (false){
                    try {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.msf.core.i", lpparam.classLoader);

                        if (tencentAppInterface != null) {
                            LoggerLog("Class Found!");
//                        for (Method i: tencentAppInterface.getMethods())
//                        {
//                            LoggerLog(i.toString());
//                        }
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "a",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.beforeHookedMethod(param);
                                            if (param.args.length != 0){
                                                LoggerLog("--------------start---------------------");
                                                for (Object i : param.args) {
                                                    if (i != null) {
                                                        if (i instanceof String) {
                                                            LoggerLog("[linearity]param:" + i);
                                                        } else {
                                                            LoggerLog("[linearity]param:" + i.toString());
                                                        }
                                                    } else {
                                                        LoggerLog("[linearity]param:null");
                                                    }
                                                }

                                                LoggerLog("--------------end---------------------");
                                            }
                                        }
                                    }
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
//            if (false){
//                try {
//                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.utils.DeviceInfoUtil$1", lpparam.classLoader);
//
//                    if (tencentAppInterface != null) {
//                        LoggerLog("Class Found!");
//                        for (Method i: tencentAppInterface.getMethods())
//                        {
//                            LoggerLog(i.toString());
//                        }
//                    }
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
//            }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.ContactsMonitor", lpparam.classLoader);
                    if (tencentAppInterface != null) {
//                    Class<?> CursorWrapperInnerClass = XposedHelpers.findClass("android.content.ContentResolver$CursorWrapperInner", lpparam.classLoader);
                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "query",
                                new XC_MethodReplacement(114514) {
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
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.DeviceInfoMonitor", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getDeviceId",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getImei",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getLine1Number",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getMeid",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getModel",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSerialByField",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSerialByMethod",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSimOperator",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSimSerialNumber",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSubscriberId",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getUiccCardsInfo",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new ArrayList<UiccCardInfo>();
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }

                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.NetworkMonitor", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getAddress",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getBSSID",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getDataNetworkType",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return NETWORK_TYPE_LTE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getHardwareAddress",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return byteArray114514;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getMacAddress",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(20);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getNetworkInterfaces",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Collections.enumeration(List.of());
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getNetworkType",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return NETWORK_TYPE_LTE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getNetworkTypeName",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "LTE";
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSSID",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(30);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getScanResults",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new ArrayList<>();
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSubtype",
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
                                    tencentAppInterface,
                                    "getType",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ConnectivityManager.TYPE_MOBILE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getTypeName",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "MOBILE";
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "hasTransport",
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.NetworkMonitor", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getConnectionInfo",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return fakeWifiInfo;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getLastKnownLocation",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return fakeLocation;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getServiceState",
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
                                    tencentAppInterface,
                                    "listen",
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
                                    tencentAppInterface,
                                    "requestCellInfoUpdate",
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
                                    tencentAppInterface,
                                    "requestLocationUpdates",
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
                            NetworkScan networkScan = (NetworkScan) XposedHelpers.findConstructorExact(
                                    "android.telephony.NetworkScan",
                                    lpparam.classLoader,
                                    int.class,int.class
                            ).newInstance(Integer.MAX_VALUE, Integer.MAX_VALUE);
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "requestNetworkScan",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return networkScan;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "requestSingleUpdate",
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
                                    tencentAppInterface,
                                    "startDiscovery",
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
                                    tencentAppInterface,
                                    "startLeScan",
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
                                    tencentAppInterface,
                                    "startScan",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args.length == 4){
                                                if (param.args[3] instanceof PendingIntent){
                                                    return Integer.MAX_VALUE;
                                                }
                                            }
                                            return null;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.common.config.AppSetting", lpparam.classLoader);
                    //not finished!
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "Ns",//ipv6,useless now
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
                                    tencentAppInterface,
                                    "getChannelId",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(10);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getLC",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(10);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getDeviceInfo",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(20);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }//not finished!
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("aqfo", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getBSSID",
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
                                    tencentAppInterface,
                                    "getCpuFrequency",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 0x7fffffffffffffffL;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getCpuMaxFreq",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 0x7fffffffffffffffL;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getCpuNumber",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Integer.MIN_VALUE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getCpuType",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(100);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
//                    try {
//                        XposedBridge.hookAllMethods(
//                                tencentAppInterface,
//                                "getDesity",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return random.nextFloat();
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getIMSI",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(20);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getMacAddress",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            LoggerLog("[linearity]MacAddr:" + param.getResult());
                                            param.setResult(getRandomString(20));
                                        }
                                    }
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return getRandomString(20);
//                                    }
//                                }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getOsVersion",
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
                                    tencentAppInterface,
                                    "getPerfLevel",
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
                                    tencentAppInterface,
                                    "getRomMemroy",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new long[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSDCardMemory",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new long[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSystemAvaialbeMemory",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 1024L;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getSystemAvaialbeMemory",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 16384L;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getTotalInternalMemorySize",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 16384L;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("cooperation.qzone.QZoneCrashHandler", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "appendLog",
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
                                    tencentAppInterface,
                                    "getLastCrashInf",
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
                                    tencentAppInterface,
                                    "saveLastCrashInf",
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
                                    tencentAppInterface,
                                    "getCpuNumber",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Integer.MIN_VALUE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("cooperation.qzone.CrashGuard", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getCrashMaxCount",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 1;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "onAppLaunch",
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
                                    tencentAppInterface,
                                    "onException",
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
                                    tencentAppInterface,
                                    "getCpuNumber",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Integer.MIN_VALUE;
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("acnq", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "accept",
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
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("anoc", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "b",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return new StringBuilder(getRandomString(10));
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "c",
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
                                    tencentAppInterface,
                                    "d",
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
                                    tencentAppInterface,
                                    "getCrashExtraMessage",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return getRandomString(20);
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "getCrashExtraMessage",
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
                                    tencentAppInterface,
                                    "onCrashHandleStart",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "0";
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "onCrashHandleSaving",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "0";
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "onCrashHandleEnd",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "0";
                                        }
                                    }
                            );
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.b", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "a",
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
                                    tencentAppInterface,
                                    "b",
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
                                    tencentAppInterface,
                                    "c",
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
                                    tencentAppInterface,
                                    "g",
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
                                    tencentAppInterface,
                                    "e",
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
                                    tencentAppInterface,
                                    "d",
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
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                try {
//                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.pb.getbusiinfo.BusinessInfoCheckUpdate$AppInfo", lpparam.classLoader);
//                    if (tencentAppInterface != null) {
//                        try {
//                            XposedHelpers.findAndHookConstructor(
//                                    tencentAppInterface,
//                                    new XC_MethodHook(114514) {
//                                        @Override
//                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable
//                                        {
////                                            super.afterHookedMethod(param);
//                                            LoggerLog("[linearity]-------------------------------------------");
////                                            solveTIMPBField("__fieldMap__", tencentAppInterface, lpparam, param.thisObject);
//                                            setTIMPBField("appset", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("buffer", tencentAppInterface, lpparam, param.thisObject, "");
//                                            setTIMPBField("exposure_max", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("extend", tencentAppInterface, lpparam, param.thisObject, "");
//                                            setTIMPBField("iNewFlag", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("icon_flag", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("icon_type", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("icon_url", tencentAppInterface, lpparam, param.thisObject, "");
//                                            setTIMPBField("id_list", tencentAppInterface, lpparam, param.thisObject, new ArrayList<>());
//                                            setTIMPBField("mission_level", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("missions", tencentAppInterface, lpparam, param.thisObject, new ArrayList<>());
//                                            setTIMPBField("modify_ts", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("num", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("path", tencentAppInterface, lpparam, param.thisObject, "");
//                                            setTIMPBField("preload_ts", tencentAppInterface, lpparam, param.thisObject, 0L);
//                                            setTIMPBField("push_red_ts", tencentAppInterface, lpparam, param.thisObject, 0);
////                                            solveTIMPBField("red_display_info", tencentAppInterface, lpparam, param.thisObject);
//                                            setTIMPBField("type", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("uiAppId", tencentAppInterface, lpparam, param.thisObject, 0);
//                                            setTIMPBField("use_cache", tencentAppInterface, lpparam, param.thisObject, false);
//                                            LoggerLog("[linearity]-------------------------------------------");
//                                        }
//                                    }
//                            );
//
//                        } catch (Exception e) {
//                            LoggerLog(e);
//                        }
//                    }
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
            }catch (Exception e){
                LoggerLog(e);
            }
        }
    }


    public static void solveTIMPBField(String fieldName, Class<?> targClass, XC_LoadPackage.LoadPackageParam lpparam, Object thisObj){
        //com/tencent/mobileqq/pb/PBInt32Field
//        Class<?> PBInt32Field = XposedHelpers.findClass("com.tencent.mobileqq.pb.PBInt32Field", lpparam.classLoader);
        Field field = XposedHelpers.findField(targClass, fieldName);
        Object target = XposedHelpers.getObjectField(thisObj, fieldName);
//        Class<?> fieldType = field.getType();

        try {
            LoggerLog(field.toString() + " "
//                    + field.getType() + " "
                    + field.getName() + " "
                    + XposedHelpers.getObjectField(target, "value"));
        } catch (Exception e) {
            LoggerLog(e);
        }
    }
    public static void setTIMPBField(String fieldName, Class<?> targClass, XC_LoadPackage.LoadPackageParam lpparam, Object thisObj, Object value){
        try {
            Object target = XposedHelpers.getObjectField(thisObj, fieldName);
            XposedHelpers.setObjectField(target, "value", value);
            XposedHelpers.setObjectField(thisObj, fieldName, target);
        } catch (Exception e) {
            LoggerLog(e);
        }
    }
}
