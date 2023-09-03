package com.linearity.deviceaddresstweaker.TIM;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.*;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XModuleResources;
import android.content.res.XResForwarder;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.RecordingCanvas;
import android.net.ConnectivityManager;
import android.telephony.NetworkScan;
import android.telephony.UiccCardInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.linearity.deviceaddresstweaker.ColorUtils;
import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;
import com.linearity.deviceaddresstweaker.R;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTIMClass{
    public static boolean HookTIM = true;
    public static boolean HookTIMColor = true;
    public static int C_FF30C0B0 = ColorUtils.parseColor("#30C0B0");
    public static int C_00856b = ColorUtils.parseColor("#00856b");
    static Method setXListCacheColorHint;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
        if (HookTIM){
            try {
                if (XposedHelpers.findMethodExactIfExists(
                        "moai.core.utilities.appstate.AppStatuses",
                        lpparam.classLoader,
                        "isAppOnForeground",
                        Context.class) != null){
                    XposedHelpers.findAndHookMethod(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
                            "isAppOnForeground",
                            Context.class,
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
                try {
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
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.mini.apkg.NavigationBarInfo", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookMethod(
                                    tencentAppInterface,
                                    "updateInfo",
                                    JSONObject.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            JSONObject json = (JSONObject) param.args[0];
                                            json.remove("navigationBarBackgroundColor");
                                            json.put("navigationBarBackgroundColor", "#39C5BB");
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
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qqmini.sdk.launcher.model.NavigationBarInfo", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookMethod(
                                    tencentAppInterface,
                                    "updateInfo",
                                    JSONObject.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            JSONObject json = (JSONObject) param.args[0];
                                            json.remove("navigationBarBackgroundColor");
                                            json.put("navigationBarBackgroundColor", "#39C5BB");
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
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.microapp.apkg.q", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookMethod(
                                    tencentAppInterface,
                                    "a",
                                    JSONObject.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            JSONObject json = (JSONObject) param.args[0];
                                            json.remove("navigationBarBackgroundColor");
                                            json.put("navigationBarBackgroundColor", "#39C5BB");
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
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.mini.ui.NavigationBar", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookConstructor(tencentAppInterface, Context.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                        XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                        XposedHelpers.setObjectField(param.thisObject, "mBackGroundColor", ColorUtils.parseColor("#39C5BB"));
                                        View view = (View) XposedHelpers.getObjectField(param.thisObject,"mContainer");
                                        view.setBackgroundColor(ColorUtils.parseColor("#39C5BB"));
                                            Method method = XposedHelpers.findMethodExact(
                                                    tencentAppInterface,
                                                    "setNavBackgroundColor",
                                                    int.class
                                            );
                                            method.invoke(param.thisObject, ColorUtils.parseColor("#39C5BB"));
                                        }
                                    });
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.mobileqq.microapp.ui.NavigationBar", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            Class<?> arg = XposedHelpers.findClass("com.tencent.mobileqq.microapp.appbrand.a",lpparam.classLoader);

                            try {
                                XposedHelpers.findAndHookConstructor(tencentAppInterface, arg, Context.class,
                                        new XC_MethodHook() {
                                            @Override
                                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                                super.afterHookedMethod(param);
//                                            XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                                XposedHelpers.setObjectField(param.thisObject, "g", ColorUtils.parseColor("#39C5BB"));
                                                Method method = XposedHelpers.findMethodExact(
                                                        tencentAppInterface,
                                                        "a",
                                                        int.class
                                                );
                                                method.invoke(param.thisObject, ColorUtils.parseColor("#39C5BB"));
                                            }
                                        });
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedHelpers.findAndHookMethod(tencentAppInterface, "d",
                                        new XC_MethodHook() {
                                            @Override
                                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                                super.afterHookedMethod(param);
                                                View view = (View) XposedHelpers.getObjectField(param.thisObject, "w");
                                                view.setBackgroundColor(C_FF30C0B0);
                                                view = (View) XposedHelpers.getObjectField(param.thisObject, "k");
                                                view.setBackgroundColor(C_FF30C0B0);
                                            }
                                        });
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedHelpers.findAndHookMethod(tencentAppInterface, "e",
                                        new XC_MethodHook() {
                                            @Override
                                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                                super.afterHookedMethod(param);
                                                View view = (View) XposedHelpers.getObjectField(param.thisObject, "w");
                                                view.setBackgroundColor(C_FF30C0B0);
                                                view = (View) XposedHelpers.getObjectField(param.thisObject, "k");
                                                view.setBackgroundColor(C_FF30C0B0);
                                            }
                                        });
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.widget.AbsListView", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookMethod(tencentAppInterface, "setCacheColorHint",int.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
//                                            XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                            param.args[0] = Color.argb(0x60,0x39,0xc5,0xbb);
                                        }
                                    });
                            tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.widget.AbsListView$f", lpparam.classLoader);
                            XposedHelpers.findAndHookMethod(tencentAppInterface, "setCacheColorHint",int.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
//                                            XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                            param.args[0] = Color.argb(0x60,0x39,0xc5,0xbb);
                                        }
                                    });
                            tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.widget.ListView", lpparam.classLoader);
                            XposedHelpers.findAndHookMethod(tencentAppInterface, "setCacheColorHint",int.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
//                                            XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                            param.args[0] = Color.argb(0x60,0x39,0xc5,0xbb);
                                        }
                                    });
                            setXListCacheColorHint = XposedHelpers.findMethodExact(tencentAppInterface,"setCacheColorHint",int.class);
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qqmini.sdk.widget.NavigationBar", lpparam.classLoader);
                    if (tencentAppInterface != null) {
                        try {
                            XposedHelpers.findAndHookConstructor(tencentAppInterface, Context.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            XposedHelpers.setObjectField(param.thisObject, "mBarStyle", "custom");
                                            XposedHelpers.setObjectField(param.thisObject, "mBackGroundColor", ColorUtils.parseColor("#39C5BB"));
                                            View view = (View) XposedHelpers.getObjectField(param.thisObject,"mContainer");
                                            view.setBackgroundColor(ColorUtils.parseColor("#39C5BB"));
                                            Method method = XposedHelpers.findMethodExact(
                                                    tencentAppInterface,
                                                    "setNavBackgroundColor",
                                                    int.class
                                            );
                                            method.invoke(param.thisObject, ColorUtils.parseColor("#39C5BB"));
                                        }
                                    });
                            if (HookTIMColor){
//                                try {
//                                    XposedHelpers.findAndHookMethod(
//                                            android.graphics.Color.class.getName(),
//                                            lpparam.classLoader,
//                                            "parseColor",
//                                            String.class,
//                                            new XC_MethodHook(114514) {
//                                                @Override
//                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                                    super.beforeHookedMethod(param);
//                                                    if(Objects.equals((String) param.args[0], "#F8F8F8")
//                                                    || Objects.equals((String) param.args[0], "#fafafa")){
//                                                        param.args[0] = "#39C5BB";
//                                                    }
//                                                }
//                                            }
//                                    );
//                                } catch (Exception e) {
//                                    LoggerLog(e);
//                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.graphics.Paint.class.getName(),
                                            lpparam.classLoader,
                                            "setColor",
                                            int.class,
                                            new XC_MethodHook(114514) {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
//                                                    param.args[0] = Color.argb(0x80,0x39,0xc5,0xbb);
                                                }
                                            }
                                    );
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.view.View.class.getName(),
                                            lpparam.classLoader,
                                            "setBackgroundColor",
                                            int.class,
                                            new XC_MethodHook(114514) {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    if ((int)param.args[0] == 637534208
                                                        ||(int)param.args[0] == C_FF30C0B0
                                                        ||(int)param.args[0] == C_00856b
//                                                        ||(int)param.args[0] == -460552
//                                                        ||(int)param.args[0] == -16777216
//                                                        ||(int)param.args[0] == -3158065
//                                                        ||(int)param.args[0] == -854793
//                                                            ||(int)param.args[0] == -1644826
//                                                            ||(int)param.args[0] == -1315339
//                                                            ||(int)param.args[0] == -657670
//                                                            ||(int)param.args[0] == -3355444
//                                                            ||(int)param.args[0] == 1308622847
//                                                            ||(int)param.args[0] == 1996488704
//                                                            ||(int)param.args[0] == 855836698
//                                                            ||(int)param.args[0] == -12991045
//                                                            ||(int)param.args[0] == -12627531
//                                                            ||(int)param.args[0] == 1040187392
//                                                            ||(int)param.args[0] == 1711276032
//                                                            ||(int)param.args[0] == 436207616
//                                                            ||(int)param.args[0] == 0
                                                    ){
                                                        return;
                                                    }
                                                    param.args[0] = Color.argb(80,0x39,0xc5,0xbb);
                                                }
                                            }
                                    );
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.view.View.class.getName(),
                                            lpparam.classLoader,
                                            "setDrawingCacheBackgroundColor",
                                            int.class,
                                            new XC_MethodHook(114514) {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    param.args[0] = Color.argb(80,0x39,0xc5,0xbb);
                                                }
                                            }
                                    );
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    Class<?> targClass = XposedHelpers.findClass(RecordingCanvas.class.getName(),lpparam.classLoader);
                                    XposedHelpers.setStaticIntField(targClass,"MAX_BITMAP_SIZE",1024*1024*1024);
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.view.View.class.getName(),
                                            lpparam.classLoader,
                                            "setBackgroundResource",
                                            int.class,
                                            new XC_MethodHook(114514) {
                                                @SuppressLint("ResourceType")
                                                @Override
                                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.afterHookedMethod(param);
//                                                    if (
//                                                            ((int)param.args[0] == 0)
//                                                            ||((int)param.args[0] == 2130839642)//menu:message item
//                                                            ||((int)param.args[0] == 2130839770)//drawable/cjb,cjb.png,an orange png,i have no idea
//                                                            ||((int)param.args[0] == 2130839769)//drawable/cja,cja.png,a red png,i have no idea
//                                                            ||((int)param.args[0] == 2130848635)//icon used but i don't know where
//                                                            ||((int)param.args[0] == 2130839282)//hh.xml, some 99000000 like,i have no idea
//                                                            ||((int)param.args[0] == 2131166090)//a kind of gray (alpha not max)
//
//                                                            ||((int)param.args[0] == 2130851109)//skin_header_bar_bg,tweaked
//                                                            ||((int)param.args[0] == 2130839643)//top_back_left_selector,tweaked
//                                                            ||((int)param.args[0] == 2130851762)//jn.xml,skin_list_item_xxxxxx_theme_version2.png,tweaked
//                                                            ||((int)param.args[0] == 2130850896)//skin_aio_panel_icon_bg,tweaked
//                                                            ||((int)param.args[0] == 2130851110)//gvl.png,tweaked
//                                                            ||((int)param.args[0] == 2130848368)//fu1.png,tweaked
//                                                            ||((int)param.args[0] == 2130851279)//skin_panel_background.png,tweaked
//                                                            ||((int)param.args[0] == 2130838900)//bg_texture,tweaked
//                                                            ||((int)param.args[0] == 2130839682)//common_strip_setting_bg.xml,tweaked
//                                                            ||((int)param.args[0] == 2130850089)//gii.png,tweaked
//                                                            ||((int)param.args[0] == 2130838901)//bg_texture_theme_version2.xml,tweaked
//                                                            ||((int)param.args[0] == 2130851347)//skin_searchbar_input_theme_version2.png,tweaked
//                                                            ||((int)param.args[0] == 2130851348)//skin_searchbar_input_theme_version2_top.png,tweaked
//                                                            ||((int)param.args[0] == 2130851337)//h03.png,tweaked
//                                                            ||((int)param.args[0] == 2130851245)//skin_list_item_normal_theme_version2,tweaked
//                                                            ||((int)param.args[0] == 2130845864)//eos.png,tweaked
//                                                            ||((int)param.args[0] == 2130851412)//new message gray
//                                                            ||((int)param.args[0] == 2130850846)//skin_aio_input_bar_bg_theme_version2,tweaked
//                                                            ||((int)param.args[0] == 2130838295)
//                                                            ||((int)param.args[0] == 2130838215)
//                                                            ||((int)param.args[0] == 2130850833)
//                                                            ||((int)param.args[0] == 2130843456)
//                                                            ||((int)param.args[0] == 2130843460)
//                                                            ||((int)param.args[0] == 2130838084)
//                                                            ||((int)param.args[0] == 2130838085)
//                                                            ||((int)param.args[0] == 2130851408)
//                                                            ||((int)param.args[0] == 2130840623)
//                                                            ||((int)param.args[0] == 2130842600)
//                                                            ||((int)param.args[0] == 2131167650)
//                                                            ||((int)param.args[0] == 2130839072)
//                                                            ||((int)param.args[0] == 2130838460)
//                                                            ||((int)param.args[0] == 2130838065)
//                                                            ||((int)param.args[0] == 2130846512)
//                                                            ||((int)param.args[0] == 2130846527)
//                                                            ||((int)param.args[0] == 2130846518)
//                                                            ||((int)param.args[0] == 2130846532)
//                                                            ||((int)param.args[0] == 2130838428)
//                                                            ||((int)param.args[0] == 2130849918)
//                                                            ||((int)param.args[0] == 2130849853)
//                                                            ||((int)param.args[0] == 2130850015)
//                                                            ||((int)param.args[0] == 2130847063)
//                                                            ||((int)param.args[0] == 2130849660)
//                                                            ||((int)param.args[0] == 2130850018)
//                                                            ||((int)param.args[0] == 2130849650)
//                                                            ||((int)param.args[0] == 2130849657)
//                                                            ||((int)param.args[0] == 2130848777)
//
//                                                    ){
//                                                        return;
//                                                    }
                                                    if (
                                                            ((int)param.args[0] == 2130839768)||//drawable/cj_,cj_.png,a gray png,i have no idea
                                                            ((int)param.args[0] == 17170445)

                                                    ){
                                                        ((View)param.thisObject).setBackgroundResource(0);
                                                        ((View)param.thisObject).setBackgroundColor(C_FF30C0B0);
                                                        return;
                                                    }
//                                                    if (((int)param.args[0] == 2130851109)){
//                                                        ((View)param.thisObject).setBackgroundColor(C_FF30C0B0);
//                                                        return;
//                                                    }
//                                                    LoggerLog(param.args[0]);
//                                                    ((View)param.thisObject).setBackgroundResource(0);
//                                                    param.args[0] = C_FF30C0B0;

                                                }
                                            }
                                    );
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.graphics.Color.class.getName(),
                                            lpparam.classLoader,
                                            "parseColor",
                                            String.class,
                                            new XC_MethodHook(114514) {
                                                @SuppressLint("ResourceType")
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.afterHookedMethod(param);
                                                    String str = (String) param.args[0];
//                                                    param.args[0] = "#39c5bb";
                                                    if (str.equals("#FFFFFF")
                                                            ||str.equals("#ffffff")
                                                            ||str.equals("#FF988A")
                                                            ||str.equals("#fafafa")
                                                    ){
                                                        param.args[0] = "#39c5bb";
                                                        return;
                                                    }
//                                                    param.args[0] = "#39c5bb";

                                                }
                                            }
                                    );
                                } catch (Exception e) {
                                    LoggerLog(e);
                                }
                                try {
                                    XposedHelpers.findAndHookMethod(
                                            android.graphics.drawable.GradientDrawable.class.getName(),
                                            lpparam.classLoader,
                                            "setColor",
                                            ColorStateList.class,
                                            new XC_MethodHook(114514) {
                                                @SuppressLint("ResourceType")
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    ColorStateList colorStateList = (ColorStateList) param.args[0];
                                                    LoggerLog(colorStateList.toString());
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

    public static void DoColor(DeviceAddressTweaker instance){
        if (instance.resparam == null){return;}
        if (!Objects.equals(instance.resparam.packageName, "com.tencent.tim")){return;}
        XResources xres = instance.resparam.res;
        String pkg = instance.resparam.packageName;
        XModuleResources modRes = XModuleResources.createInstance(instance.modulePath, xres);
        try {
//            xres.hookLayout(
//                    pkg,
//                    "layout",
//
//            )
            XResForwarder Color_Miku = modRes.fwd(R.color.miku);
            XResForwarder Color_Teal700 = modRes.fwd(R.color.teal_700);
            {
                xres.setReplacement(pkg, "color", "al3", Color_Miku);
                xres.setReplacement(pkg, "drawable", "je", modRes.fwd(R.drawable.je));
                xres.setReplacement(pkg, "drawable", "gxb", modRes.fwd(R.drawable.gxb));
                xres.setReplacement(0x7f0235b1, modRes.fwd(R.drawable.skin_list_item_pressed_theme_version2));
                xres.setReplacement(0x7f0235ad, modRes.fwd(R.drawable.skin_list_item_normal_theme_version2));
                xres.setReplacement(0x7f023525, modRes.fwd(R.drawable.skin_header_bar_bg_tweaked));
                xres.setReplacement(0x7f023528, modRes.fwd(R.drawable.skin_header_bar_bg_min_tweaked));
                xres.setReplacement(0x7f0234f0, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_disabled_tweaked));
                xres.setReplacement(0x7f0234f1, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_pressed_tweaked));
                xres.setReplacement(0x7f0234d4, modRes.fwd(R.drawable.skin_background_local_albums_tweaked));
                xres.setReplacement(0x7f02341d, modRes.fwd(R.drawable.skin_aio_input_bar_bg_local_albums_tweaked));
                xres.setReplacement(0x7f0233d4, modRes.fwd(R.drawable.skin_aio_album_send_button_disabled_tweaked));
                xres.setReplacement(0x7f0233d5, modRes.fwd(R.drawable.skin_aio_album_send_button_normal_tweaked));
                xres.setReplacement(0x7f0233d6, modRes.fwd(R.drawable.skin_aio_album_send_button_pressed_tweaked));
                xres.setReplacement(0x7f0709a6, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0709b1, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f0708b4, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f070897, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f070868, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f070848, modRes.fwd(R.color.skin_black));
                xres.setReplacement(0x7f070088, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f070613, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0234d6, modRes.fwd(R.drawable.skin_bottom_bar_background_theme_version2_tweaked));
                xres.setReplacement(0x7f02102c, modRes.fwd(R.drawable.di4));
                xres.setReplacement(0x7f0234d5, modRes.fwd(R.drawable.skin_background_theme_version2_tweaked));
                xres.setReplacement(0x7f020575, modRes.fwd(R.color.teal_200));
                xres.setReplacement(0x7f0208e5, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0208e1, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f0208e2, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f0208df, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f100175, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f023650, modRes.fwd(R.drawable.skin_tips_newmessage_tweaked));
                xres.setReplacement(0x7f023656, modRes.fwd(R.drawable.skin_tips_newmessage_ninetynine_tweaked));
                xres.setReplacement(0x7f07096b, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0235c4, modRes.fwd(R.drawable.b4p));
                xres.setReplacement(0x7f0235c6, modRes.fwd(R.drawable.b4p));
                xres.setReplacement(0x7f0235c0, modRes.fwd(R.drawable.b4p));
                xres.setReplacement(0x7f070100, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02249e, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f070042, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0702f1, modRes.fwd(R.color.teal_200));
                xres.setReplacement(0x7f07038a, modRes.fwd(R.color.teal_700));
                xres.setReplacement(0x7f0234d3, modRes.fwd(R.drawable.skin_background_tweaked));
                xres.setReplacement(0x7f0234df, modRes.fwd(R.drawable.skin_chat_background_tweaked));
                xres.setReplacement(0x7f0234e0, modRes.fwd(R.drawable.skin_chat_background_preview_tweaked));
                xres.setReplacement(0x7f023450, modRes.fwd(R.drawable.skin_aio_panel_icon_bg_tweaked));
                xres.setReplacement(0x7F023526, modRes.fwd(R.drawable.gvl));
                xres.setReplacement(0x7f022a70, modRes.fwd(R.drawable.fu1));
                xres.setReplacement(0x7F0235CF, modRes.fwd(R.drawable.skin_panel_background_tweaked));
                xres.setReplacement(0x7f023618, modRes.fwd(R.drawable.skin_setting_strip_bg_pressed_tweaked));
                xres.setReplacement(0x7f02361a, modRes.fwd(R.drawable.skin_setting_strip_bg_pressed_theme_version2_9_tweaked));
                xres.setReplacement(0x7f02361b, modRes.fwd(R.drawable.skin_setting_strip_bg_unpressed_tweaked));
                xres.setReplacement(0x7f02361d, modRes.fwd(R.drawable.skin_setting_strip_bg_unpressed_theme_version2_9_tweaked));
                xres.setReplacement(0x7F023129, modRes.fwd(R.drawable.gii));
                xres.setReplacement(0x7f023613, modRes.fwd(R.drawable.skin_searchbar_input_theme_version2_tweaked));
                xres.setReplacement(0x7f023614, modRes.fwd(R.drawable.skin_searchbar_input_theme_version2_top_tweaked));
                xres.setReplacement(0x7f023609, modRes.fwd(R.drawable.h03));//(search bar edge color)
                xres.setReplacement(0x7f0220a8, modRes.fwd(R.drawable.eos));
                xres.setReplacement(0x7f02341e, modRes.fwd(R.drawable.skin_aio_input_bar_bg_theme_version2_tweaked));
                xres.setReplacement(0x7f02340f, modRes.fwd(R.drawable.skin_aio_friend_bubble_struct_msg_nor_tweaked));
                xres.setReplacement(0x7f023410, modRes.fwd(R.drawable.skin_aio_friend_bubble_struct_msg_pressed_tweaked));
                xres.setReplacement(0x7f020241, modRes.fwd(R.drawable.aio_emjio_delete_disable));
                xres.setReplacement(0x7f020243, modRes.fwd(R.drawable.aio_emjio_delete_pressed));
                xres.setReplacement(0x7f020242, modRes.fwd(R.drawable.aio_emjio_delete_normal));
                xres.setReplacement(0x7f0708d8, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f0708d7, modRes.fwd(R.color.teal_custom_1));
                xres.setReplacement(2130849853, modRes.fwd(R.drawable.qzone_skin_home_panel_list_divide));
                xres.setReplacement(2130847063, modRes.fwd(R.drawable.qq_title_immersive_bar_tweaked));
                xres.setReplacement(2130849660, modRes.fwd(R.drawable.qzone_selector_feed_music_bar_tweaked));
                xres.setReplacement(0x7f07075e, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f07026d, modRes.fwd(R.color.teal_custom_1));
                xres.setReplacement(0x7f023608, modRes.fwd(R.drawable.skin_searchbar_bg_theme_version2));
                xres.setReplacement(0x7f02351f, modRes.fwd(R.drawable.skin_group_list_item_normal_theme_version2_9));
                xres.setReplacement(0x7f023522, modRes.fwd(R.drawable.skin_group_list_item_pressed_theme_version2_9));
                xres.setReplacement(0x7f021325, modRes.fwd(R.drawable.profile_bg));
            }

//            xres.setReplacement(0x7f022bc4,modRes.fwd(R.drawable.acdeshead));
//            xres.setReplacement(0x7f022e79,modRes.fwd(R.drawable.acdeshead));

//            xres.setReplacement(0x7f0235d1,modRes.fwd(R.color.teal_200));
//            xres.setReplacement(pkg,"color","lf", Color_Miku);
//            xres.setReplacement(pkg,"color","mj", Color_Miku);
//            xres.setReplacement(pkg,"color","alb", Color_Miku);
            xres.setReplacement(pkg,"color","ajr", Color_Teal700);

//            xres.setReplacement(pkg,"color","windowBackground", Color_Miku);
//            LoggerLog(pkg);
            xres.setReplacement(pkg,"color", "emoview_aio_guide_bg_color", Color_Miku);
//            xres.setReplacement(pkg,"color", "card_color_white", 0XFF39C5BB);
            xres.setReplacement(pkg,"color", "skin_black", modRes.fwd(R.color.teal_700));//settings text color
            xres.setReplacement(pkg,"color", "album_list_mask_color", Color_Miku);
            xres.setReplacement(pkg,"color", "xi", modRes.fwd(R.color.teal_200));
            xres.setReplacement(pkg,"color", "ahl", Color_Miku);
            xres.setReplacement(pkg,"color", "ahb", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_unite_search_text_black_000000_6991b8", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_gray3", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_gray2_item", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_black_item", Color_Miku);//unknown text color
//            xres.setReplacement(pkg,"color", "transparent", Color_Miku);
//            xres.setReplacement(pkg,"drawable", "bg_maillist_addaccount", modRes.fwd(R.drawable.bg_maillist_addaccount));

            try {xres.hookLayout(pkg,
                    "layout",
                    "ach",
                    new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                    liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));

                        TextView tv = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("f13", "id", pkg));
                        tv.setBackgroundColor(Color.parseColor("#39c5bb"));
                }
            });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "acg",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));

                            TextView tv = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("#808080", "textColor", pkg));
                            tv.setBackgroundColor(Color.parseColor("#39c5bb"));
                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "a",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            TextView tv = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("k8r", "id", pkg));
                            tv.setBackgroundColor(Color.parseColor("#39c5bb"));
                            tv = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("k8y", "id", pkg));
                            tv.setBackgroundColor(Color.parseColor("#39c5bb"));
                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "cmn",
                    new XC_LayoutInflated() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("nb8", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#39c5bb"));
                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
//            try {xres.hookLayout(pkg,
//                    "layout",
//                    "ik",
//                    new XC_LayoutInflated() {
//                        @SuppressLint("ResourceAsColor")
//                        @Override
//                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            View view = liparam.view.findViewById(liparam.res.getIdentifier("recent_chat_list", "id", pkg));
//                            view.setBackgroundColor(Color.parseColor("#FF39c5bb"));
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("content", "id", pkg));
//
//                            if (view != null){
//                                view.setBackgroundColor(Color.parseColor("#FF39c5bb"));
//                            }
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("jq6", "id", pkg));
//                            if (view != null){
//                                view.setBackgroundColor(Color.parseColor("#FF39c5bb"));
//                            }
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("j2j", "id", pkg));
//                            if (view != null){
//                                view.setBackgroundColor(Color.parseColor("#FF39c5bb"));
//                            }
//                        }
//                    });
//            } catch (Exception e) {
//                LoggerLog(e);
//            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "b5b",
                    new XC_LayoutInflated() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("gvq", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#39c5bb"));
                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "coh",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("ach", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#3039c5bb"));
                            if (view instanceof ViewGroup){
//                                LoggerLog("114514");
                                for (int i=0;i<((ViewGroup) view).getChildCount();i++){
                                    View view1 = ((ViewGroup) view).getChildAt(i);
                                    if (view1 instanceof LinearLayout){
//                                        LoggerLog("1145141919");
                                        view1.setBackground(null);
                                        view1.setBackgroundColor(Color.parseColor("#6039c5bb"));
                                        break;
                                    }
                                }
                            }
                            view = liparam.view.findViewById(liparam.res.getIdentifier("mi_", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            view = liparam.view.findViewById(liparam.res.getIdentifier("af8", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            view = liparam.view.findViewById(liparam.res.getIdentifier("mij", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("nal", "id", pkg));
//                            if (view != null && setXListCacheColorHint != null){
//                                setXListCacheColorHint.invoke(view, Color.argb(0x80,0x39,0xc5,0xbb));
//                            }
//                            view.setVisibility(View.INVISIBLE);
//                            view.setDrawingCacheBackgroundColor(Color.argb(0x80,0x39,0xc5,0xbb));
//                            (view).setCacheColorHint(Color.parseColor("#6039c5bb"));
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("center_horizontal", "gravity", pkg));
//                            view.setBackgroundColor(Color.parseColor("#39c5bb"));

                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "cod",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            LoggerLog(liparam.view.getParent());
//                            liparam.view.setVisibility(View.INVISIBLE);
//                            liparam.view.setBackground(null);
//                            liparam.view.setDrawingCacheBackgroundColor(Color.parseColor("#6039c5bb"));
                            liparam.view.setAlpha(0.4F);
//                            View view = liparam.view.findViewById(liparam.res.getIdentifier("nk4", "id", pkg));
//                            view.setBackground(null);
//                            view.setAlpha(1);
                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
            try {xres.hookLayout(pkg,
                    "layout",
                    "coe",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("jta", "id", pkg));
                            view.setAlpha(0.4F);
//                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            view = liparam.view.findViewById(liparam.res.getIdentifier("bmt", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            } catch (Exception e) {
                LoggerLog(e);
            }
//            try {xres.hookLayout(pkg,
//                    "layout",
//                    "cmp",
//                    new XC_LayoutInflated() {
//                        @SuppressLint("ResourceAsColor")
//                        @Override
//                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            View view = liparam.view.findViewById(liparam.res.getIdentifier("cme", "id", pkg));
//                            view.setBackgroundColor(Color.parseColor("#109050"));
//                        }
//                    });
//            } catch (Exception e) {
//                LoggerLog(e);
//            }

        } catch (Exception e) {
            LoggerLog(e);
        }
    }
}
