package com.linearity.deviceaddresstweaker.TIM;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.*;
import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.XModuleResources;
import android.content.res.XResForwarder;
import android.content.res.XResources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.telephony.NetworkScan;
import android.telephony.UiccCardInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;
import com.linearity.deviceaddresstweaker.R;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTIMClass {
    public static boolean HookTIM = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) {
        if (HookTIM) {
            try {
                //tim or st. else(give it a try)
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
                            "isAppOnForeground",
                            Context.class) != null) {
                        XposedBridge.hookAllMethods(
                                XposedHelpers.findClass("moai.core.utilities.appstate.AppStatuses",lpparam.classLoader),
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                }
                        );
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "moai.core.utilities.appstate.AppStatuses",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.qqmail.utilities.AppStatusUtil",
                            lpparam.classLoader,
                            "isAppOnBackGround") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.BaseSmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.av.smallscreen.SmallScreenService",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aljz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "iue",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "kvv",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "aktz",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    if (XposedHelpers.findMethodExactIfExists(
                            "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                            lpparam.classLoader,
                            "isAppOnForeground") != null) {
                        XposedHelpers.findAndHookMethod(
                                "com.tencent.mobileqq.pluginsdk.PluginProxyActivity",
                                lpparam.classLoader,
                                "isAppOnForeground",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {

                    try {
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
                    } catch (Exception e) {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("argh",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "a",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        if (param.getResult() != null && param.getResult().getClass().equals(hookClass))
                                        {
                                            XposedHelpers.setObjectField(param.getResult(),
                                                    "gl",
                                                    new ColorDrawable(Color.parseColor("#8039C5BB")
                                                    ));
                                        }
                                    }
                                });
                        XposedBridge.hookAllMethods(hookClass,
                                "b",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        if (param.getResult() != null && param.getResult().getClass().equals(hookClass))
                                        {
                                            XposedHelpers.setObjectField(param.getResult(),
                                                    "gl",
                                                    new ColorDrawable(Color.parseColor("#8039C5BB")
                                                    ));
                                        }
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("eipc.EIPCClient",lpparam.classLoader);
                    if (hookClass != null){
                        for (Method m:hookClass.getDeclaredMethods()){
                            if (m.getReturnType().equals(Void.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.ark.open.ArkAppMgr",lpparam.classLoader);
                    if (hookClass != null){
                        XposedHelpers.findAndHookMethod(hookClass,
                                "compareVersionString",
                                String.class,
                                String.class,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("angt",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "uj", new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                return true;
                            }
                        });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("ajka",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "g", new XC_MethodReplacement() {
                            @Override
                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                return true;
                            }
                        });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.Pandora.util.BackgroundUtil",lpparam.classLoader);
                    if (hookClass != null){
                        for (Method m:hookClass.getDeclaredMethods()){
                            Class<?> returnType = m.getReturnType();
                            if (returnType.equals(Void.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }else if (returnType.equals(Boolean.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.mobileqq.msf.core.c.k",lpparam.classLoader);
                    if (hookClass != null){
                        for (Method m:hookClass.getDeclaredMethods()){
                            Class<?> returnType = m.getReturnType();
                            if (returnType.equals(Void.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }else if (returnType.equals(Boolean.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.qphone.base.util.QLog",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "writeLogToFile",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                        XposedBridge.hookAllMethods(hookClass,
                                "addLogItem",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                        XposedHelpers.findAndHookMethod(hookClass,
                                "isColorLevel",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return true;
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("ayvc",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "cr",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
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
                                        result.addState(new int[0],gradientDrawable);
                                        param.setResult(result);
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("aicd",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass,
                                "aoM",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> hookClass = XposedHelpers.findClassIfExists("com.tencent.biz.pubaccount.readinjoy.engine.KandianMergeManager",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllConstructors(hookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return param.thisObject;
                                    }
                                });
                        for (Method m:hookClass.getDeclaredMethods()){
                            if (m.getReturnType().equals(Integer.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                });
                            }else if (m.getReturnType().equals(Long.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0L;
                                    }
                                });
                            }else if (m.getReturnType().equals(Boolean.TYPE)){
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                            }else {
                                XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
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
//            xres.hookLayout(
//                    pkg,
//                    "layout",
//
//            )
            XResForwarder Color_Miku = modRes.fwd(R.color.miku);
            XResForwarder Color_Teal700 = modRes.fwd(R.color.teal_700);
            XResForwarder Color_Miku_a80 = modRes.fwd(R.color.miku_alpha80);
            {
                xres.setReplacement(pkg, "color", "al3", Color_Miku_a80);
                xres.setReplacement(pkg, "drawable", "je", modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(pkg, "drawable", "gxb", modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0235b1, modRes.fwd(R.color.C86C7C5));
                xres.setReplacement(0x7f0235ad, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f023525, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f023528, modRes.fwd(R.drawable.skin_header_bar_bg_min_tweaked));
                xres.setReplacement(0x7f0234f0, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_disabled_tweaked));
                xres.setReplacement(0x7f0234f1, modRes.fwd(R.drawable.skin_common_btn_small_blue_local_albums_pressed_tweaked));
                xres.setReplacement(0x7f0234d4, modRes.fwd(R.drawable.skin_background_local_albums_tweaked));
                xres.setReplacement(0x7f02341d, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f0233d4, modRes.fwd(R.color.miku_alpha60));
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
                xres.setReplacement(0x7f023650, modRes.fwd(R.drawable.skin_tips_newmessage_tweaked));
                xres.setReplacement(0x7f023656, modRes.fwd(R.drawable.skin_tips_newmessage_ninetynine_tweaked));
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
                xres.setReplacement(0x7f02351f, modRes.fwd(R.color.miku_alpha60));
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
                xres.setReplacement(0x7f0206e6, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f02341b, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341c, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341d, modRes.fwd(R.color.miku_alpha80));
                xres.setReplacement(0x7f02341e, modRes.fwd(R.color.miku_alpha60));//input color 1
                xres.setReplacement(0x7f02341f, modRes.fwd(R.color.miku_alpha60));//input color 2
                xres.setReplacement(0x7f02341f, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f070100, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f070511, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f020d0e, modRes.fwd(R.drawable.icon_bubble));
                xres.setReplacement(0x7f020d10, modRes.fwd(R.drawable.icon_bubble_left));
                xres.setReplacement(0x7f020d11, modRes.fwd(R.drawable.icon_bubble_left_pressed));
                xres.setReplacement(0x7f020d13, modRes.fwd(R.drawable.icon_bubble_no_arrow_dark_default));
                xres.setReplacement(0x7f020d14, modRes.fwd(R.drawable.icon_bubble_no_arrow_dark_pressed));
                xres.setReplacement(0x7f020d15, modRes.fwd(R.drawable.icon_bubble_pressed));
                xres.setReplacement(0x7f0222db, modRes.fwd(R.drawable.aeq));
                xres.setReplacement(0x7f07085e, modRes.fwd(R.color.black));//Ur chat text color
//                xres.setReplacement(0x7f07085a, modRes.fwd(R.color.miku_alpha80));//and others
                xres.setReplacement(0x7f07085d, modRes.fwd(R.color.blue));//Ur link color
                xres.setReplacement(0x7f07085c, modRes.fwd(R.color.blue));//and others
                xres.setReplacement(0x7f0234bc, modRes.fwd(R.color.miku_alpha60));//skin_aio_user_bubble_nor
                xres.setReplacement(0x7f0234bd, modRes.fwd(R.color.miku_alphaa0));//skin_aio_user_bubble_pressed
                xres.setReplacement(0x7f02340b, modRes.fwd(R.color.miku_alpha60));//skin_aio_friend_bubble_nor
                xres.setReplacement(0x7f02340c, modRes.fwd(R.color.miku_alphaa0));//skin_aio_friend_bubble_pressed
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
                xres.setReplacement(0x7f020867, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f020868, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f07096f, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f0708a6, modRes.fwd(R.color.miku_alpha60));
//                xres.setReplacement(0x7f0708a7, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f0709c1, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f0709c2, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f0709c3, modRes.fwd(R.color.miku_alpha60));
                xres.setReplacement(0x7f0709c4, modRes.fwd(R.color.miku_alpha60));

                xres.setReplacement(0x7f020a03, modRes.fwd(R.color.teal_200_alpha80));
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
            xres.setReplacement(pkg,"color", "emoview_aio_guide_bg_color", Color_Miku_a80);
            xres.setReplacement(pkg,"color", "emoview_aio_guide_stroke_color", Color_Miku_a80);
            xres.setReplacement(pkg,"color", "emoview_aio_emoji_bkg", Color_Miku_a80);
            xres.setReplacement(pkg,"color", "emoview_aio_bottom_tab_bkg", Color_Miku_a80);
//            xres.setReplacement(pkg,"color", "card_color_white", 0X8039C5BB);
            xres.setReplacement(pkg,"color", "skin_black", modRes.fwd(R.color.teal_700));//settings text color
            xres.setReplacement(pkg,"color", "album_list_mask_color", Color_Miku);
            xres.setReplacement(pkg,"color", "xi", modRes.fwd(R.color.teal_200));
            xres.setReplacement(pkg,"color", "ahl", Color_Miku);
            xres.setReplacement(pkg,"color", "ahb", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_unite_search_text_black_000000_6991b8", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_gray3", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_gray2_item", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_black_item", Color_Miku);//unknown text color
            xres.setReplacement(pkg,"color", "skin_color_title_immersive_bar", Color_Miku_a80);
            xres.setReplacement(pkg,"color", "skin_notification", Color_Miku_a80);
            xres.setReplacement(pkg,"color", "aj3", Color_Miku_a80);
            xres.setReplacement(pkg,"drawable", "euc", modRes.fwd(R.drawable.p));
//            xres.setReplacement(pkg,"drawable", "h03", modRes.fwd(R.drawable.p));
//            xres.setReplacement(pkg,"drawable", "skin_header_bar_bg", modRes.fwd(R.drawable.p));

            xres.setReplacement(pkg,"layout", "cod", modRes.fwd(R.layout.cod));
            xres.setReplacement(pkg,"layout", "coe", modRes.fwd(R.layout.coe));
//            xres.setReplacement(pkg,"color", "transparent", Color_Miku);
//            xres.setReplacement(pkg,"drawable", "bg_maillist_addaccount", modRes.fwd(R.drawable.bg_maillist_addaccount));

            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
//            try{xres.hookLayout(pkg,
//                    "layout",
//                    "ik",
//                    new XC_LayoutInflated() {
//                        @SuppressLint("ResourceAsColor")
//                        @Override
//                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            View view = liparam.view.findViewById(liparam.res.getIdentifier("recent_chat_list", "id", pkg));
//                            view.setBackgroundColor(R.color.C_8039C5BB);
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("content", "id", pkg));
//
//                            if (view != null){
//                                view.setBackgroundColor(R.color.C_8039C5BB);
//                            }
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("jq6", "id", pkg));
//                            if (view != null){
//                                view.setBackgroundColor(R.color.C_8039C5BB);
//                            }
//                            view = liparam.view.findViewById(liparam.res.getIdentifier("j2j", "id", pkg));
//                            if (view != null){
//                                view.setBackgroundColor(R.color.C_8039C5BB);
//                            }
//                        }
//                    });
//            }catch(Exception e){
//                LoggerLog(e);
//            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
//            try{xres.hookLayout(pkg,
//                    "layout",
//                    "cod",
//                    new XC_LayoutInflated() {
//                        @Override
//                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
////                            LoggerLog(liparam.view.getParent());
////                            liparam.view.setVisibility(View.INVISIBLE);
////                            liparam.view.setBackground(null);
////                            liparam.view.setDrawingCacheBackgroundColor(Color.parseColor("#6039c5bb"));
//                            liparam.res
////                            View view = liparam.view.findViewById(liparam.res.getIdentifier("nk4", "id", pkg));
////                            view.setBackground(null);
////                            view.setAlpha(1);
//                        }
//                    });
//            }catch(Exception e){
//                LoggerLog(e);
//            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
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
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
                    "layout",
                    "bx4",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("iji", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
                    "layout",
                    "bla",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("search_box", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }catch(Exception e){
                LoggerLog(e);
            }
            try{xres.hookLayout(pkg,
                    "layout",
                    "b1x",
                    new XC_LayoutInflated() {
                        @Override
                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                            View view = liparam.view.findViewById(liparam.res.getIdentifier("m7r", "id", pkg));
                            view.setBackgroundColor(Color.parseColor("#6039c5bb"));

                        }
                    });
            }catch(Exception e){
                LoggerLog(e);
            }

//            try{xres.hookLayout(pkg,
//                    "layout",
//                    "ik",
//                    new XC_LayoutInflated() {
//                        @Override
//                        public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
//                            View view = liparam.view.findViewById(liparam.res.getIdentifier("d7a", "id", pkg));
//                            view.setVisibility(View.INVISIBLE);
//
//                        }
//                    });
//            }catch(Exception e){
//                LoggerLog(e);
//            }
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

}
