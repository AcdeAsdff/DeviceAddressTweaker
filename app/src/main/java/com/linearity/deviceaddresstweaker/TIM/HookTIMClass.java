package com.linearity.deviceaddresstweaker.TIM;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.fakeLocation;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.fakeWifiInfo;
import static com.linearity.utils.FakeClass.java.util.EmptyArrays.EMPTY_BYTE_ARRAY;
import static com.linearity.utils.FakeClass.java.util.EmptyArrays.EMPTY_INT_ARRAY;
import static com.linearity.utils.FakeInfo.FakeProcInfoGenerator.random;
import static com.linearity.utils.HookUtils.disableClass;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.getRandomString;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnTrue;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.content.res.XModuleResources;
import android.content.res.XResForwarder;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.telephony.NetworkScan;
import android.telephony.UiccCardInfo;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;
import com.linearity.deviceaddresstweaker.R;
import com.linearity.deviceaddresstweaker.TIM.TIMUtils.QQConcurrentHashMap;
import com.linearity.utils.HookUtils;
import com.linearity.utils.HookerThread;
import com.linearity.utils.ReturnReplacements;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTIMClass {
    public static boolean HookTIM = true;

    public static final String[] blockMsgHead = new String[]{
            "谨防兼职刷单、游戏交易、投资荐股、色情招嫖、仿冒公检法及客服人员的网络骗局，如有资金往来请谨慎操作"
    };
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) {

        if (HookTIM) {
            try {

//                if (!lpparam.processName.equals("com.tencent.tim:peak"))
                {
                    HookerThread hook1 = new HookerThread(lpparam.classLoader,
                            true,
                            HookerThread.TIMHookedPackagesPart1,
                            HookerThread.TIMHookedPackagesPart3
                    );
                    hook1.run();
                    HookerThread hook2 = new HookerThread(lpparam.classLoader,
                            false,
                            HookerThread.TIMHookedPackagesPart4
                    );
                    hook2.run();

                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.smtt.sdk.TbsLogReport",lpparam.classLoader);
                    if (hookClass != null){
                        disableClass(hookClass);
                    }
                }
                //tim or st. else(give it a try)
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("moai.core.utilities.appstate.AppStatuses",lpparam.classLoader);
                    if (hookClass != null) {
                        XposedBridge.hookAllMethods(
                                hookClass,
                                "isAppOnForeground",returnTrue);
                    }
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "moai.core.utilities.appstate.AppStatuses",
                                lpparam.classLoader,
                                "isAppOnBackGround",returnFalse
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "com.tencent.qqmail.utilities.AppStatusUtil",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "com.tencent.qqmail.utilities.AppStatusUtil",
                                lpparam.classLoader,
                                "isAppOnBackGround",returnFalse
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.BaseSmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "com.tencent.av.smallscreen.BaseSmallScreenService",
                                lpparam.classLoader,
                                "isAppOnForeground",returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.SmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "com.tencent.av.smallscreen.SmallScreenService",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aljz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "aljz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "aktz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "iue",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "iue",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "kvv",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "kvv",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "aktz",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        HookUtils.findAndHookMethodIfExists(
                                "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                                lpparam.classLoader,
                                "isAppOnForeground",returnTrue);
                    }
                }
                {
                    Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.common.app.AppInterface", lpparam.classLoader);
                    if (tencentAppInterface != null) {

                        XposedBridge.hookAllMethods(
                                tencentAppInterface,
                                "isAppOnForeground",
                                returnTrue
                        );
                    }
                }
                {

                    try {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.android.tpush.common.AppInfos", lpparam.classLoader);
                        if (tencentAppInterface != null) {

                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "isAppOnForeground", ReturnReplacements.returnTrue
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("mqq.app.Foreground", lpparam.classLoader);
                        if (tencentAppInterface != null) {

                            XposedBridge.hookAllMethods(
                                    tencentAppInterface,
                                    "isAppOnForeground", ReturnReplacements.returnTrue
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
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
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
//                    try {
//                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.ContactsMonitor", lpparam.classLoader);
//                        if (tencentAppInterface != null) {
////                    Class<?> CursorWrapperInnerClass = XposedHelpers.findClass("android.content.ContentResolver$CursorWrapperInner", lpparam.classLoader);
//                            XposedBridge.hookAllMethods(
//                                    tencentAppInterface,
//                                    "query",
//                                    new XC_MethodHook() {
//                                        @Override
//                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                            super.beforeHookedMethod(param);
//                                            LoggerLog("-----");
//                                            for (Object o:param.args){
//                                                LoggerLog(o);
//                                            }
//                                        }
//                                    }
//                            );
//                        }
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
                    try {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.monitor.DeviceInfoMonitor", lpparam.classLoader);
                        if (tencentAppInterface != null) {
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getDeviceId", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getImei", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getLine1Number", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getMeid", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getModel", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getSerialByField", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getSerialByMethod", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getSimOperator", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getSimSerialNumber", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getString", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getSubscriberId", ReturnReplacements.returnRandomStr20
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
                                        "getAddress", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getBSSID", ReturnReplacements.returnRandomStr20
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
                                        "getMacAddress", ReturnReplacements.returnRandomStr20
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
                                        "getSSID", ReturnReplacements.returnRandomStr20
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
                                        "getServiceState", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "listen", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "requestCellInfoUpdate", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "requestLocationUpdates", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                NetworkScan networkScan = (NetworkScan) XposedHelpers.findConstructorExact(
                                        "android.telephony.NetworkScan",
                                        lpparam.classLoader,
                                        int.class, int.class
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
                                        "requestSingleUpdate", ReturnReplacements.returnNull
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
                                                if (param.args.length == 4) {
                                                    if (param.args[3] instanceof PendingIntent) {
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
                                                return getRandomString(random.nextInt(10)+5);
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
                                                return getRandomString(random.nextInt(10)+5);
                                            }
                                        }
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getDeviceInfo", ReturnReplacements.returnRandomStr20
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
                                        "getBSSID", ReturnReplacements.returnNull
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
                                        "getIMSI", ReturnReplacements.returnRandomStr20
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
                                                return new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
                                                return new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
                    {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("acnq", lpparam.classLoader);
                        if (tencentAppInterface != null) {
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "accept", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                        }
                    }
                    {
                        Class<?> tencentAppInterface = XposedHelpers.findClassIfExists("anoc", lpparam.classLoader);
                        if (tencentAppInterface != null) {
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "b",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return new StringBuilder(getRandomString(random.nextInt(10)+5));
                                            }
                                        }
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "c", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "d", ReturnReplacements.returnNull
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getCrashExtraMessage", ReturnReplacements.returnRandomStr20
                                );
                            } catch (Exception e) {
                                LoggerLog(e);
                            }
                            try {
                                XposedBridge.hookAllMethods(
                                        tencentAppInterface,
                                        "getCrashExtraMessage", ReturnReplacements.returnNull
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
//                                            setTIMPBField("/../path", tencentAppInterface, lpparam, param.thisObject, "");
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
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.ark.open.ArkAppMgr",lpparam.classLoader);
                    if (hookClass != null){
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "compareVersionString",
                                String.class,
                                String.class,returnIntegerZero);
                    }
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("angt",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "uj", returnTrue);
                    }
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("ajka",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "g", returnTrue);
                    }
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("wis",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "getPriority",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        int index = (int) param.args[0];
                                        SparseArray<Integer> bt = (SparseArray<Integer>) XposedHelpers.getObjectField(param.thisObject,"bt");
                                        if (bt.contains(index)){
                                            return bt.get(index);
                                        }
                                        return 0;
                                    }
                                });
                    }
                }
//                {
//                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.qphone.base.util.QLog",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllMethods(hookClass,
//                                "writeLogToFile",returnTrue);
//                        XposedBridge.hookAllMethods(hookClass,
//                                "addLogItem",returnNull);
//                        HookUtils.findAndHookMethodIfExists(hookClass,
//                                "isColorLevel",returnTrue);
//                    }
//                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("ayvc",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "cr",returnNull);
                    }
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("aszn",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "H",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        StateListDrawable drawable = ((StateListDrawable)param.getResult());
                                        GradientDrawable gradientDrawable = ((GradientDrawable)drawable.getStateDrawable(0));
                                        gradientDrawable.setColor(Color.parseColor("#4019A59B"));
                                        StateListDrawable result = new StateListDrawable();
                                        result.addState(drawable.getStateSet(0),gradientDrawable);
                                        gradientDrawable = (GradientDrawable) drawable.getStateDrawable(1);
                                        gradientDrawable.setColor(Color.parseColor("#4039C5BB"));
                                        result.addState(EMPTY_INT_ARRAY,gradientDrawable);
                                        param.setResult(result);
                                    }
                                });
                    }
                }
                {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("aicd",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "aoM",returnFalse);
                    }
                }
//                try {
//                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.biz.pubaccount.readinjoy.engine.KandianDailyManager",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllConstructors(hookClass,
//                                new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return param.thisObject;
//                                    }
//                                });
//                    }
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
//                try {
//                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.biz.pubaccount.readinjoy.engine.KandianSubscribeManager",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllConstructors(hookClass,
//                                new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return param.thisObject;
//                                    }
//                                });
//                    }
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                Class<?> hookClass;

                {
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.component.media.image.ImageManager",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "getImage", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                String str = param.args[0].toString();
                                if (str.startsWith("https://pgdt.gtimg.cn/gdt/0/")){
                                    param.setResult(null);
                                    return;
                                }
                                if (str.equals("https://qzonestyle.gtimg.cn/qzone/space_item/cover/org/11/118859/1080x1080.jpg#kp=1")){
                                    param.args[0] = "https://r.photo.store.qq.com/psc?/V11QrRTi19Bsp" +
                                            "T/bqQfVz5yrrGYSXMvKr.cqazr0dVG.NUQG05R0G8XaZmIArsYlJygK" +
                                            "A80d9y0g7PBYcpKDKz1W*q7QHIVvjXu.YW5YutefCvEwh.yCo1FS5k!/o";//got it!
                                    return;
                                }
//                                Field f = XposedHelpers.findFieldIfExists(param.args[0].getClass(),"url");
//                                if (f != null){
//                                    str = (String) f.get(param.args[0]);
//                                }
//                                LoggerLog(new Exception(str));
////                                showObjectFields(param.args[0],"    ");
                            }
                        });
                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("agqm",lpparam.classLoader);
                    if (hookClass != null){
                        for (Method m:hookClass.getDeclaredMethods()){
                            if (m.getName().equals("a") && m.getReturnType().equals(boolean.class)){
                                XposedBridge.hookMethod(m, ReturnReplacements.returnTrue);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists("awgy",lpparam.classLoader);
                    if (hookClass != null){
                        for (Method m:hookClass.getDeclaredMethods()){
                            if (m.getReturnType().equals(boolean.class)){
                                XposedBridge.hookMethod(m, ReturnReplacements.returnFalse);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists("acxv",lpparam.classLoader);
                    if (hookClass != null){
                        XposedHelpers.setStaticObjectField(hookClass,"jr",new FilterHashmap(
                                "qzone_weather","official_location","readinjoy_anti_cheating",
                                "qzone_weather","readinjoy_feed_ad_distance","nearby_readinjoy",
                                "qq_story_water_mark","qq_weather","vfuchong_bus_card",
                                "readinjoy_position","extend_friend"));
                    }

                    hookClass = XposedHelpers.findClassIfExists("anea",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "onReceive", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (param.args != null && param.args[0] == null){
                                    param.setResult(null);
                                }
                            }
                        });
                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.ditto.shell.DittoUIEngine",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,"updateLayoutFile", ReturnReplacements.returnNull);
//                        XposedBridge.hookAllMethods(hookClass, "loadJsonObject", new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                                JSONObject jsonObject = ((JSONObject)param.getResult());
//                                for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
//                                    String s = it.next();
//                                    Object o = jsonObject.get(s);
//                                    if (o instanceof JSONArray) {
//                                        for (int i = 0; i < ((JSONArray) o).length(); i++) {
//                                            LoggerLog("ArrayItem:" + ((JSONArray) o).get(i));
//                                        }
//                                    }
//                                    else{
//                                        LoggerLog(s + ":" + o);
//                                    }
//                                }
//                                LoggerLog(new Exception(param.args[0].toString() + " | " + param.args[1].toString()));
//                            }
//                        });
                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.core.Utils",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,"isAppOnForeground", ReturnReplacements.returnTrue);
                        XposedBridge.hookAllMethods(hookClass,"isMainProcAndForeground", ReturnReplacements.returnTrue);
                        XposedBridge.hookAllMethods(hookClass, "convertAndUpdateConfig", ReturnReplacements.returnTrue);
                        XposedBridge.hookAllMethods(hookClass,"isNeedReport", ReturnReplacements.returnFalse);
                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.api.PandoraEx",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "updateConfig", ReturnReplacements.returnTrue);
                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.qmethod.pandoraex.core.ActivityDetector",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "init", ReturnReplacements.returnNull);
                        XposedBridge.hookAllMethods(hookClass, "onStarted", ReturnReplacements.returnNull);
                        XposedBridge.hookAllMethods(hookClass, "onResumed", ReturnReplacements.returnNull);
                        XposedBridge.hookAllMethods(hookClass, "updateActivityRecord", ReturnReplacements.returnNull);
                        XposedBridge.hookAllMethods(hookClass, "getTopActivityName", ReturnReplacements.returnNull);
                        XposedBridge.hookAllMethods(hookClass, "getRecentSceneArray", ReturnReplacements.returnNull);
                    }
//                    hookClass = XposedHelpers.findClassIfExists("android.app.SharedPreferencesImpl",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllMethods(hookClass, "getLong", new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        Throwable th = param.getThrowable();
//                                        SharedPreferences sp = (SharedPreferences)param.thisObject;
//                                        if (th != null){
//                                            LoggerLog((String) param.args[0]+" "+Integer.parseInt(param.args[1].toString()));
//                                            LoggerLog(th);
//                                            long result = sp.getInt((String) param.args[0],Integer.parseInt(param.args[1].toString()));
//                                            param.setResult(result);
//                                            sp.edit().putLong((String) param.args[0],result).apply();
//                                        }
//                                    }
//                                }
//                        );
//                    }
                }
                {

                    String[] toClear = new String[]{
                            "com.tencent.template.public","620","游戏分享",
                            "com.tencent.new_element.invite","32","新奇物种邀请",
                            "com.tencent.bot.groupbot","113","私人管家",
                            "com.tencent.gxhServiceIntelligentTip","57","服务号机制化消息",
                            "com.tencent.wezone.share","140","小世界沉浸式feed分享",
                            "com.tencent.gamecenter.gameshare","820","游戏中心-游戏分享",
                            "com.tencent.qianbao","166","QQ支付-钱包Ark消息",
//                            "com.tencent.group.poster","71","群频道-群帖子",
//                            "com.tencent.template.qqfavorite.share","46","QQ 收藏",
                            "com.tencent.troopsharecard","23","推荐群聊",
                            "com.tencent.imagetextbot","205","机器人大图文链接",
//                            "com.tencent.qzone.albumInvite","134","QQ空间",
//                                        "com.tencent.creategroupmsg","29","创建群成功",
//                                        "com.tencent.structmsg","151","结构化消息",
//                                        "com.tencent.groupphoto","40","群相册",
//                                        "com.tencent.qzone.video","31","QQ空间视频分享",

                    };
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.ark.ArkEnvironmentManager",lpparam.classLoader);
                    if (hookClass != null) {
                        XposedHelpers.findAndHookMethod(hookClass, "getAppConfigSharedPreferences", new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                SharedPreferences.Editor spEdit = ((SharedPreferences) param.getResult()).edit();
                                for (int i=0;i< toClear.length;i+=3){
                                    spEdit.putString(toClear[i],"{\"ver\":" + toClear[i+1] + ",\"name\":\"" + toClear[i+2] + "\",\"icon\":\"\",\"entry\":0,\"flag\":0,\"type\":0,\"urlWhitelist\":{\"res\":[],\"nav\":[]},\"fwdViews\":[\"*\"]}");
                                }
                                spEdit.apply();
                            }
                        });
                    }


//                    hookClass = XposedHelpers.finm
                }
                {

                    for (String s:new String[]{
                            "KanDianVideoUploadBrocast","KandianVideoUploadService",
                            "ReadInJoyDeliverUGCActivity","ReadInJoyDeliverVideoActivity",
                            "ReadInJoyDraftboxFragment","ReadInJoyPrivacyListFragment",
                            "ReadInJoyPrivacyListView","ReadInJoyTopicSelectionFragment",
                            "ReadInJoyUgcSearchTopicFragment","ReadInJoyVideoSearchTagFragment",
                            "ReadInJoyVideoTagSelectionFragment",
                    }){
                        hookClass = XposedHelpers.findClassIfExists("com.tencent.biz.pubaccount.readinjoy.ugc." + s, lpparam.classLoader);
                        if (hookClass != null && !Modifier.isAbstract(hookClass.getModifiers())){
                            for (Method m : hookClass.getDeclaredMethods()) {
                                HookUtils.disableMethod(m,hookClass);
                            }
                        }
                    }
                    for (String s:new String[]{
                            "AvatarItemView","AvatarPendantManager","FriendCloneSettingFragment",
                            "LzmaUtils","PendantInfo","PobingDecoder","QuickUpdateIPCModule",
                            "ThemeChangeBroadcastReceiver","VasKeyValue","VasQuickUpdateEngine",
                            "VasQuickUpdateManager","ZanBannerView"
                    }){
                        hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.vas." + s, lpparam.classLoader);
                        if (hookClass != null && !Modifier.isAbstract(hookClass.getModifiers())){
                            for (Method m : hookClass.getDeclaredMethods()) {
                                HookUtils.disableMethod(m,hookClass);
                            }
                        }
                    }
//                    hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.mini.apkg.BaseLibManager",lpparam.classLoader);
//                    if (hookClass != null){
//                        for (Method m:hookClass.getDeclaredMethods()){
//                            disableMethod(m);
//                        }
//                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("aqmv",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (param.args[0] == null){param.args[0] = "http://127.39.0.1";}
                            }
                        });
                        XposedBridge.hookAllMethods(hookClass,"setUrl",new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (param.args[0] == null){param.args[0] = "http://127.39.0.1";}
                            }
                        });
                    }
                }
//                {
//                    hookClass = XposedHelpers.findClassIfExists("dov.com.qq.im.QIMCameraCaptureActivity",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllMethods(
//                                hookClass, "onCreate", new XC_MethodHook() {
//                                    @Override
//                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.beforeHookedMethod(param);
//                                        LoggerLog(new Exception("not an exception"));
//                                    }
//
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        if (param.getThrowable() != null){
//                                            LoggerLog(param.getThrowable());
//                                        }else {
//                                            LoggerLog("no throwable");
//                                        }
//                                    }
//                                }
//                        );
//                    }
//                    hookClass = XposedHelpers.findClassIfExists("axhd",lpparam.classLoader);
//                    if(hookClass != null){
//                        for (String s:new String[]{
//                                "d","i","w","e"
//                        }){
//                            XposedBridge.hookAllMethods(hookClass, s, new XC_MethodHook() {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
//                                    LoggerLog(new Exception(s + " " + param.args[0] + " " + param.args[1]));
//                                }
//                            });
//                        }
//                    }
//                }

                {

                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(Color.parseColor("#a039C5BB"));
                    XC_MethodReplacement setViewBackground0039C5BB = new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            ((View)param.thisObject).setBackgroundColor(Color.parseColor("#0039C5BB"));
                            return null;
                        }
                    };
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.biz.pubaccount.readinjoy.view.pullrefresh.SkinPullRefreshHeader",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                int color = Color.parseColor("#0039C5BB");
                                XposedHelpers.setIntField(param.thisObject,"baH",color);
                                ((View)param.thisObject).setBackgroundColor(color);
                            }
                        });
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgRes", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgDrawable", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgColor", setViewBackground0039C5BB);
                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.activity.contacts.pullrefresh.ContactRefreshHeader",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgRes", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgDrawable", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgColor", setViewBackground0039C5BB);
                        XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                int color = Color.parseColor("#0039C5BB");
                                ((View)param.thisObject).setBackgroundColor(color);
                            }
                        });
                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.widget.PullRefreshHeader",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgRes", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgDrawable", setViewBackground0039C5BB);
                        XposedBridge.hookAllMethods(hookClass, "setHeaderBgColor", setViewBackground0039C5BB);
                        XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                int color = Color.parseColor("#0039C5BB");
                                ((View)param.thisObject).setBackgroundColor(color);
                            }
                        });

                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.widget.immersive.SystemBarCompact",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "setStatusBarDrawable", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                GradientDrawable gradientDrawable = new GradientDrawable();
                                gradientDrawable.setColor(Color.parseColor("#a039C5BB"));
                                param.args[0] = gradientDrawable;
                            }
                        });
                        XposedBridge.hookAllMethods(hookClass, "setStatusBarColor", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                param.args[0] = Color.parseColor("#a039C5BB");
                            }
                        });
                        XposedBridge.hookAllMethods(hookClass, "setStatusDrawable", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                param.args[0] = gradientDrawable;
                            }
                        });
                        XposedBridge.hookAllMethods(hookClass, "setStatusColor", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                param.args[0] = Color.parseColor("#a039C5BB");
                            }
                        });
                    }
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.activity.emogroupstore.EmoticonGroupStoreFragment",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "bVi", new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                View view = (View) XposedHelpers.callMethod(param.thisObject,"getTitleBarView");
//                                view.setBackgroundDrawable(gradientDrawable);
                                view.setBackgroundColor(Color.parseColor("#FF39C5BB"));
                            }
                        });
                    }

                    hookClass = XposedHelpers.findClassIfExists("argh",lpparam.classLoader);
                    if (hookClass != null) {
                        XposedBridge.hookAllMethods(hookClass,
                                "a",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        Object result = param.getResult();
                                        if (result == null ){return;}
                                        if ((XposedHelpers.findFieldIfExists(result.getClass(),"gl") == null)){return;}
                                        if (!(XposedHelpers.getObjectField(result,"gl") instanceof Drawable)){return;}
                                        XposedHelpers.setObjectField(result,
                                                "gl",
                                                new ColorDrawable(Color.parseColor("#8039C5BB")
                                                ));
                                    }
                                });
                        XposedBridge.hookAllMethods(hookClass,
                                "b",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        Object result = param.getResult();
                                        if (result == null ){return;}
                                        if ((XposedHelpers.findFieldIfExists(result.getClass(),"gl") == null)){return;}
                                        if (!(XposedHelpers.getObjectField(result,"gl") instanceof Drawable)){return;}
                                        XposedHelpers.setObjectField(result,
                                                "gl",
                                                new ColorDrawable(Color.parseColor("#8039C5BB")
                                                ));
                                    }
                                });
                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.smtt.sdk.TbsDownloader",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,"a",returnFalse);
                    }
                }
                {
                    hookClass = XposedHelpers.findClassIfExists("com.tencent.imcore.message.QQMessageFacade$Message",lpparam.classLoader);
                    if (hookClass != null){
                        hookClass = XposedHelpers.findClassIfExists("com.tencent.imcore.message.QQMessageFacade",lpparam.classLoader);
                        if (hookClass != null){
                            XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    XposedHelpers.setObjectField(param.thisObject,"gO",new QQConcurrentHashMap<String,Object>(1017, 0, 1200));
                                }
                            });
                            XposedHelpers.findAndHookMethod(hookClass, "a", String.class, int.class, boolean.class, new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    modifyMessage(param.getResult());
                                }
                            });
                        }
//                        hookClass = XposedHelpers.findClassIfExists("aqhj",lpparam.classLoader);
//                        if (hookClass != null){
//
//                            XposedBridge.hookAllMethods(hookClass, "b", new XC_MethodHook() {
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.afterHookedMethod(param);
//                                    if (param.args.length >= 5){
//                                        showObjectFields(param.args[4],"4    ");
//                                        showObjectFields(param.args[3],"3    ");
//                                        showObjectFields(param.args[2],"2    ");
//                                    }
////                                    modifyMessage(param.getResult());
//                                }
//                            });
//                        }
                        hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.data.ChatMessage",lpparam.classLoader);
                        if (hookClass != null){
                            XposedBridge.hookAllMethods(hookClass, "parse", new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    modifyMessage(param.thisObject);
                                }
                            });
                        }
                    }
//                    hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.activity.recent.RecentBaseData",lpparam.classLoader);
//                    if (hookClass != null){
//                        XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                                if (param.args.length >= 4){
//
//                                    showObjectFields(param.args[4],"    ");
//                                }
//                            }
//                        });
//                    }
                }

            } catch (Exception e) {
                LoggerLog(e);
            }
        }
    }


    public static void DoColor(DeviceAddressTweaker instance){
        if (instance.resparam == null){return;}
        if (!Objects.equals(instance.resparam.packageName, "com.tencent.tim")){return;}
        XResources xres = instance.resparam.res;
        String pkg = instance.resparam.packageName;
        XModuleResources modRes = XModuleResources.createInstance(instance.modulePath, xres);
        try{
//            hookLayoutIfExists(xres,
//                    pkg,
//                    "layout",
//
//            )
            XResForwarder Color_Miku = modRes.fwd(R.color.miku);
            XResForwarder Color_Teal700 = modRes.fwd(R.color.teal_700);
            XResForwarder Color_Miku_a60 = modRes.fwd(R.color.miku_alpha60);
            XResForwarder Color_Miku_a80 = modRes.fwd(R.color.miku_alpha80);
            XResForwarder Color_Miku_aa0 = modRes.fwd(R.color.miku_alphaa0);
            XResForwarder Color_Pink = modRes.fwd(R.color.ideal_pink);
            XResForwarder Color_Pink_2 = modRes.fwd(R.color.ideal_pink_2);
            {
                setReplacementIfExists(xres,pkg, "color", "al3", Color_Miku_a80);
                setReplacementIfExists(xres,pkg, "drawable", "je", modRes.fwd(R.color.C86C7C5));
                setReplacementIfExists(xres,pkg, "drawable", "gxb", modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0235b1, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f0235ad, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023525, Color_Miku_a60);
                xres.setReplacement(0x7f023528, modRes.fwd(R.drawable.skin_header_bar_bg_min_tweaked));
                xres.setReplacement(0x7f0234f0, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_disabled_tweaked));
                xres.setReplacement(0x7f0234f1, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_pressed_tweaked));
                xres.setReplacement(0x7f0234d4, modRes.fwd(R.drawable.skin_background_local_albums_tweaked));
                xres.setReplacement(0x7f02341d, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0233d4, Color_Miku_a60);
                xres.setReplacement(0x7f0233d5, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0233d6, modRes.fwd(R.color.miku_alphaa0));
                xres.setReplacement(0x7f0709a6, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0709b1, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f0708b4, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f070897, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f070868, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f070848, modRes.fwd(R.color.skin_black));
                xres.setReplacement(0x7f070088, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f070613, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0234d6, modRes.fwd(R.drawable.skin_bottom_bar_background_theme_version2_tweaked));
                xres.setReplacement(0x7f02102c, modRes.fwd(R.drawable.di4));
                xres.setReplacement(0x7f0234d5, modRes.fwd(R.drawable.skin_background_theme_version2_tweaked));
                xres.setReplacement(0x7f020575, modRes.fwd(R.color.teal_200_alpha80));
                xres.setReplacement(0x7f0208e5, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0208e1, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f0208e2, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f0208df, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f100175, modRes.fwd(R.drawable.e0));
                xres.setReplacement(0x7f07096b, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f0235c4, modRes.fwd(R.color.miku_alphaa0));
                xres.setReplacement(0x7f0235c6, modRes.fwd(R.color.miku_alphaa0));
                xres.setReplacement(0x7f0235c0, modRes.fwd(R.color.miku_alphaa0));
                xres.setReplacement(0x7f070100, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02249e, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f070042, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0702f1, modRes.fwd(R.color.teal_200_alpha80));
                xres.setReplacement(0x7f07038a, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0234d3, modRes.fwd(R.drawable.skin_background_tweaked));
                xres.setReplacement(0x7f0234df, modRes.fwd(R.drawable.skin_chat_background_tweaked));
                xres.setReplacement(0x7f0234e0, modRes.fwd(R.drawable.skin_chat_background_preview_tweaked));
                xres.setReplacement(0x7f023450, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7F023526, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f022a70, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7F0235CF, modRes.fwd(R.drawable.bg_texture_by_pixiv_userid_1599876));
                xres.setReplacement(0x7F023129, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023613, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f023614, modRes.fwd(R.color.miku_trans_30));
                xres.setReplacement(0x7f023609, modRes.fwd(R.color.miku_alpha80));//(search bar edge color)
                xres.setReplacement(0x7f0220a8, modRes.fwd(R.drawable.eos));
                xres.setReplacement(0x7f02341e, modRes.fwd(R.color.transparent));//skin_aio_input_bar_bg_theme_version2_tweaked
                xres.setReplacement(0x7f02340f, modRes.fwd(R.drawable.skin_aio_friend_bubble_struct_msg_nor_tweaked));
                xres.setReplacement(0x7f023410, modRes.fwd(R.drawable.skin_aio_friend_bubble_struct_msg_pressed_tweaked));
                xres.setReplacement(0x7f020241, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f020243, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f020242, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f0708d8, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f0708d7, modRes.fwd(R.color.teal_custom_1));
                xres.setReplacement(2130849853, modRes.fwd(R.drawable.qzone_skin_home_panel_list_divide));
                xres.setReplacement(2130847063, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(2130849660, modRes.fwd(R.drawable.qzone_selector_feed_music_bar_tweaked));
                xres.setReplacement(0x7f07075e, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f07026d, modRes.fwd(R.color.teal_custom_1));
                xres.setReplacement(0x7f023608, modRes.fwd(R.drawable._bg_texture__by_pixiv_uid_14270992_2));
                xres.setReplacement(0x7f02351f, Color_Miku_a60);
                xres.setReplacement(0x7f023522, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f021325, modRes.fwd(R.drawable.profile_bg_by_pixiv_procrastinator_userid_83351375));
                xres.setReplacement(0x7f020574, modRes.fwd(R.drawable.bg_texture_by_fenghu));
                xres.setReplacement(0x7f020575, modRes.fwd(R.drawable.bg_texture_by_pixiv_uid_14270992));
                xres.setReplacement(0x7f0206dc, modRes.fwd(R.drawable.bg_texture_by_fenghu_2));
                xres.setReplacement(0x7f02110e, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f02008c, modRes.fwd(R.drawable.p));
                xres.setReplacement(0x7f100377, modRes.fwd(R.style.action_sheet_layout_style));
                xres.setReplacement(0x7f02110e, modRes.fwd(R.drawable.hco));
                xres.setReplacement(0x7f023627, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023628, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02361e, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02361f, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023618, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02361a, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023622, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02362d, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02362e, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023620, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f023621, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f023629, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f02362a, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f02361b, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f02361d, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f023623, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f02362f, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f023630, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f0206e6, Color_Miku_a60);
                xres.setReplacement(0x7f02341b, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341c, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341d, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341e, Color_Miku_a60);//input color 1
                xres.setReplacement(0x7f02341f, Color_Miku_a60);//input color 2
                xres.setReplacement(0x7f02341f, Color_Miku_a60);
                xres.setReplacement(0x7f070100, Color_Miku_a60);
                xres.setReplacement(0x7f070511, Color_Miku_a60);
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble", modRes.fwd(R.drawable.icon_bubble));
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble_left", modRes.fwd(R.drawable.icon_bubble_left));
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble_left_pressed", modRes.fwd(R.drawable.icon_bubble_left_pressed));
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble_no_arrow_dark_default", modRes.fwd(R.drawable.icon_bubble_no_arrow_dark_default));
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble_no_arrow_dark_pressed", modRes.fwd(R.drawable.icon_bubble_no_arrow_dark_pressed));
                setReplacementIfExists(xres,pkg,"drawable","icon_bubble_pressed", modRes.fwd(R.drawable.icon_bubble_pressed));
                xres.setReplacement(0x7f0222db, modRes.fwd(R.drawable.aeq));
                xres.setReplacement(0x7f07085e, modRes.fwd(R.color.black));//Ur chat text color
//                xres.setReplacement(0x7f07085a, modRes.fwd(R.color.miku_alpha80));//and others
                xres.setReplacement(0x7f07085d, modRes.fwd(R.color.blue));//Ur link color
                xres.setReplacement(0x7f07085c, modRes.fwd(R.color.blue));//and others
                setReplacementIfExists(xres,pkg,"drawable","skin_aio_user_bubble_nor", Color_Miku_a60);
                setReplacementIfExists(xres,pkg,"drawable","skin_aio_user_bubble_pressed", modRes.fwd(R.color.miku_alphaa0));
                setReplacementIfExists(xres,pkg,"drawable","skin_aio_friend_bubble_nor", Color_Miku_a60);
                setReplacementIfExists(xres,pkg,"drawable","skin_aio_friend_bubble_pressed", modRes.fwd(R.color.miku_alphaa0));
                xres.setReplacement(0x7f070745, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f070748, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f07074b, modRes.fwd(R.color.teal_200_alpha80));
                xres.setReplacement(0x7f07074c, modRes.fwd(R.color.teal_700));
                xres.setReplacement(0x7f07074d, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f07074e, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f07074f, modRes.fwd(R.color.black));
                xres.setReplacement(0x7f070750, modRes.fwd(R.color.teal_200));
                xres.setReplacement(0x7f070751, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f070752, modRes.fwd(R.color.black));
                xres.setReplacement(0x7f070753, modRes.fwd(R.color.miku));
                xres.setReplacement(0x7f070754, modRes.fwd(R.color.teal_custom_2));
                xres.setReplacement(0x7f070755, modRes.fwd(R.color.teal_200));
                xres.setReplacement(0x7f070756, modRes.fwd(R.color.teal_200_alpha80));
                xres.setReplacement(0x7f070764, modRes.fwd(R.color.teal_200_alpha80));
                xres.setReplacement(0x7f0236dc, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f020867, Color_Miku_a60);
                xres.setReplacement(0x7f020868, Color_Miku_a60);
                xres.setReplacement(0x7f07096f, Color_Miku_a60);
                xres.setReplacement(0x7f0708a6, Color_Miku_a60);
//                xres.setReplacement(0x7f0708a7, Color_Miku_a60);
                xres.setReplacement(0x7f0709c1, Color_Miku_a60);
                xres.setReplacement(0x7f0709c2, Color_Miku_a60);
                xres.setReplacement(0x7f0709c3, Color_Miku_a60);
                xres.setReplacement(0x7f0709c4, Color_Miku_a60);

                xres.setReplacement(0x7f020a03, modRes.fwd(R.color.teal_200_alpha80));
            }

//            xres.setReplacement(0x7f022bc4,modRes.fwd(R.drawable.acdeshead));
//            xres.setReplacement(0x7f022e79,modRes.fwd(R.drawable.acdeshead));

//            xres.setReplacement(0x7f0235d1,modRes.fwd(R.color.teal_200));
//            setReplacementIfExists(xres,pkg,"color","lf", Color_Miku);
//            setReplacementIfExists(xres,pkg,"color","mj", Color_Miku);
//            setReplacementIfExists(xres,pkg,"color","alb", Color_Miku);
            setReplacementIfExists(xres,pkg,"color","ajr", Color_Teal700);

//            setReplacementIfExists(xres,pkg,"color","windowBackground", Color_Miku);
//            LoggerLog(pkg);
            setReplacementIfExists(xres,pkg,"color", "emoview_aio_guide_bg_color", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "emoview_aio_guide_stroke_color", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "emoview_aio_emoji_bkg", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "emoview_aio_bottom_tab_bkg", Color_Miku_a80);
//            setReplacementIfExists(xres,pkg,"color", "card_color_white", 0X8039C5BB);
            setReplacementIfExists(xres,pkg,"color", "skin_black", modRes.fwd(R.color.teal_700));//settings text color
            setReplacementIfExists(xres,pkg,"color", "album_list_mask_color", Color_Miku);
            {
                setReplacementIfExists(xres, pkg, "drawable", "hev", Color_Miku_aa0);
                //👆 background of 👇
                //Windows QQ 已登录     口 口
                setReplacementIfExists(xres, pkg, "color", "ana", Color_Miku_aa0);
                //👆 background of 👇
                //手机静音              (  o)
            }
            setReplacementIfExists(xres,pkg,"color", "xi", modRes.fwd(R.color.teal_200));
            setReplacementIfExists(xres,pkg,"color", "ahl", Color_Miku);
            setReplacementIfExists(xres,pkg,"color", "action_sheet_button_red", Color_Pink);
            setReplacementIfExists(xres,pkg,"color", "ar", Color_Pink);
            setReplacementIfExists(xres,pkg,"color", "b1", Color_Pink);
            setReplacementIfExists(xres,pkg,"color", "b2", Color_Pink);
            setReplacementIfExists(xres,pkg,"color", "bottle_topbar_unread_count_bg", Color_Pink);
            setReplacementIfExists(xres,pkg,"color", "calendar_red", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "f2", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "hz", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "dialog_btn_text_red", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "editor_text_color_red", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "red", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "s_unread_red", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "a_l", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"color", "jg", Color_Pink_2);
            setReplacementIfExists(xres,pkg,"drawable", "skin_tips_newmessage", modRes.fwd(R.drawable.pink_dot));
            setReplacementIfExists(xres,pkg,"drawable", "skin_tips_newmessage_ninetynine", modRes.fwd(R.drawable.skin_tips_newmessage_ninetynine_tweaked));
            setReplacementIfExists(xres,pkg,"drawable", "common_list_overscoll_top_bg", Color_Miku_a60);
            setReplacementIfExists(xres,pkg,"color", "ahb", Color_Miku);//unknown text color
            setReplacementIfExists(xres,pkg,"color", "skin_unite_search_text_black_000000_6991b8", Color_Miku);//unknown text color
            setReplacementIfExists(xres,pkg,"color", "skin_gray3", Color_Miku);//unknown text color
            setReplacementIfExists(xres,pkg,"color", "skin_gray2_item", Color_Miku);//unknown text color
            setReplacementIfExists(xres,pkg,"color", "skin_black_item", Color_Miku);//unknown text color
            setReplacementIfExists(xres,pkg,"color", "skin_color_title_immersive_bar", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "skin_notification", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "black_trans_50", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"drawable", "a97", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"drawable", "a98", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"drawable", "b42", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"drawable", "b47", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "aj3", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "x0", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"color", "el", Color_Miku_aa0);

            setReplacementIfExists(xres,pkg,"drawable", "skin_group_list_sub_entry_theme_version2", Color_Miku_a80);
            setReplacementIfExists(xres,pkg,"drawable", "k4", Color_Miku_a80);

            setReplacementIfExists(xres,pkg,"drawable", "euc", modRes.fwd(R.drawable.p));
//            setReplacementIfExists(xres,pkg,"drawable", "h03", modRes.fwd(R.drawable.p));
//            setReplacementIfExists(xres,pkg,"drawable", "skin_header_bar_bg", modRes.fwd(R.drawable.p));

            setReplacementIfExists(xres,pkg,"layout", "cod", modRes.fwd(R.layout.cod));
            setReplacementIfExists(xres,pkg,"layout", "coe", modRes.fwd(R.layout.coe));

            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "ach",
                    new XC_LayoutInflated() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            liparam.view.setBackgroundColor(R.color.miku_alpha80);

                            TextView tv = (TextView) liparam.view.findViewById(liparam.res.getIdentifier("f13", "id", pkg));
                            tv.setBackgroundColor(Color.parseColor("#39c5bb"));
                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
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
            }
            {hookLayoutIfExists(xres,pkg,
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
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "cmn",
                    new XC_LayoutInflated() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            liparam.view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("nb8", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
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
            }
            {hookLayoutIfExists(xres,pkg,
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
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "coe",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("jta", "id", pkg));
//                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            view = liparam.view.findViewById(liparam.res.getIdentifier("bmt", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "action_sheet_cancel_button",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            liparam.view.setBackgroundColor(Color.parseColor("#39c5bb"));
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("jta", "id", pkg));
//                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));
                            view = liparam.view.findViewById(liparam.res.getIdentifier("bmt", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "bx4",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("iji", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "bla",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("search_box", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }
            {hookLayoutIfExists(xres,pkg,
                    "layout",
                    "b1x",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("m7r", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }
            hookLayoutIfExists(xres,pkg,
                    "layout",
                    "bc2",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("cxs", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#8039c5bb"));
                            View view1 = liparam.view.findViewById(liparam.res.getIdentifier("ahs", "id", pkg));
                            view1.setBackgroundColor(Color.parseColor("#8039c5bb"));
                            View view2 = liparam.view.findViewById(liparam.res.getIdentifier("fsk", "id", pkg));
                            view2.setBackgroundColor(Color.parseColor("#8039c5bb"));
                            View view3 = liparam.view.findViewById(liparam.res.getIdentifier("kdv", "id", pkg));
                            view3.setBackgroundColor(Color.parseColor("#6039c5bb"));
                        }
                    });
            hookLayoutIfExists(xres,pkg,
                    "layout",
                    "bc4",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            LinearLayout linearLayout = liparam.view.findViewById(liparam.res.getIdentifier("buw", "id", pkg));
                            linearLayout.setBackgroundColor(Color.parseColor("#8039c5bb"));
                            for (int i=0;i<linearLayout.getChildCount();i++){
                                View child = linearLayout.getChildAt(i);
                                if (child instanceof LinearLayout){
                                    child.setBackgroundColor(Color.parseColor("#8039c5bb"));
                                }
                            }
                        }
                    });
        }catch(Exception e){
            LoggerLog(e);
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
    

    public static final boolean logResourceNotFound = false;
    public static void setReplacementIfExists(XResources xres,String pkg,String type,String name,Object replacement){
        if (xres.getIdentifier(name,type,pkg) != 0){
            xres.setReplacement(pkg,type,name,replacement);
        }else if (logResourceNotFound){
            LoggerLog("[linearity-ResourceNotFound]",pkg + "|" + type + "|" + name);
        }
    }
    public static void hookLayoutIfExists(XResources xres,String pkg,String type,String name,XC_LayoutInflated hooker){
        if (xres.getIdentifier(name,type,pkg) != 0){
            xres.hookLayout(pkg, type, name,hooker);
        }
    }

    public static void modifyMessage(Object message){
        if (XposedHelpers.findFieldIfExists(message.getClass(),"msg") != null){
            String msg = (String) XposedHelpers.getObjectField(message, "msg");
            if (msg != null) {
                for (String s : blockMsgHead) {
                    if (msg.startsWith(s)) {
                        XposedHelpers.setObjectField(message, "msg", "");
                        break;
                    }
                }
            }
        }
        if (XposedHelpers.findFieldIfExists(message.getClass(),"emoRecentMsg") != null){
            CharSequence emoRecentMsgCharSeq = (CharSequence) XposedHelpers.getObjectField(message, "emoRecentMsg");
            if (emoRecentMsgCharSeq != null) {
                String emoRecentMsg = emoRecentMsgCharSeq.toString();
                for (String s : blockMsgHead) {
                    if (emoRecentMsg.startsWith(s)) {
                        XposedHelpers.setObjectField(message, "emoRecentMsg", "");
                        XposedHelpers.setObjectField(message, "msgData", EMPTY_BYTE_ARRAY);
                        break;
                    }
                }
            }
        }
        if (XposedHelpers.findFieldIfExists(message.getClass(),"extStr") != null){
            String extStr = (String) XposedHelpers.getObjectField(message, "extStr");
            if (extStr != null) {
                for (String s:blockMsgHead){
                    if(extStr.contains(s)){
                        XposedHelpers.setObjectField(message, "extStr", "{\"unite_gray_tips_align\":\"center\",\"uint64_busi_type\":\"1\",\"uint64_busi_id\":\"1\",\"bytes_content\":\"<gtip/>\"}");
                    }
                }
            }
        }
        if (XposedHelpers.findFieldIfExists(message.getClass(), "lastMsg") != null) {
            Object lastMsg = XposedHelpers.getObjectField(message, "lastMsg");
            if (lastMsg != null) {
                if (XposedHelpers.findFieldIfExists(lastMsg.getClass(), "tipParam") != null) {
                    Object tipParam = XposedHelpers.getObjectField(lastMsg, "tipParam");
                    if (tipParam != null) {
                        if (XposedHelpers.findFieldIfExists(tipParam.getClass(), "wording") != null) {
                            String wording = (String) XposedHelpers.getObjectField(tipParam, "wording");
                            if (wording != null) {
                                for (String s:blockMsgHead){
                                    if (wording.contains(s)) {
                                        XposedHelpers.setObjectField(tipParam, "wording", "");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (XposedHelpers.findFieldIfExists(message.getClass(),"mExJsonObject") != null){
            JSONObject mExJsonObject = (JSONObject) XposedHelpers.getObjectField(message, "mExJsonObject");
            if (mExJsonObject != null) {
                String toStr = mExJsonObject.toString();
                for (String s : blockMsgHead) {
                    if (toStr.contains(s)) {
                        List<String> strs = new LinkedList<>();
                        for (Iterator<String> it = mExJsonObject.keys(); it.hasNext(); ) {
                            String toRemove = it.next();
                            strs.add(toRemove);
                        }
                        for (String toRemove : strs) {
                            mExJsonObject.remove(toRemove);
                        }
                        break;
                    }
                }
            }
        }
    }
}
