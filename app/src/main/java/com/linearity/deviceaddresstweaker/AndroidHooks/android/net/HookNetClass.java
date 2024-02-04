package com.linearity.deviceaddresstweaker.AndroidHooks.android.net;

import static android.net.ConnectivityDiagnosticsManager.DataStallReport.DETECTION_METHOD_DNS_EVENTS;
import static android.net.LocalSocketAddress.Namespace.FILESYSTEM;
import static android.net.MacAddress.TYPE_BROADCAST;
import static android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;
import static android.net.NetworkCapabilities.TRANSPORT_ETHERNET;
import static android.net.NetworkCapabilities.TRANSPORT_USB;
import static android.net.NetworkCapabilities.TRANSPORT_VPN;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI_AWARE;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.IpPrefix;
import android.net.IpSecTransform;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.LocalSocketAddress;
import android.net.MacAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.PlatformVpnProfile;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.net.RouteInfo;
import android.net.TelephonyNetworkSpecifier;
import android.net.Uri;
import android.net.VpnManager;
import android.net.VpnService;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.telephony.TelephonyManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.wifi.HookWifiClass;
import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.crypto.Mac;
import javax.net.SocketFactory;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedInFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

public class HookNetClass {

    public static Intent VPNEstablishIntent =
            new Intent().
                    setClassName("com.android.vpndialogs","com.android.vpndialogs.ConfirmDialog");
    public static String NetworkReq2Str =
            "[ NONE id=0, " +
                    "[ Transports: CELLULAR " +
                    "Capabilities: NOT_METERED" +
                    "&INTERNET" +
                    "&NOT_RESTRICTED&TRUSTED" +
                    "&NOT_VPN&VALIDATED" +
                    "&NOT_ROAMING" +
                    "&FOREGROUND" +
                    "&NOT_CONGESTED" +
                    "&NOT_SUSPENDED" +
                    "&NOT_VCN_MANAGED " +
                    "LinkUpBandwidth>=1145141919810Kbps " +
                    "LinkDnBandwidth>=1145141919810Kbps " +
                    "TransportInfo: <114514> " +
                    "SignalStrength: -1145141919810]";
    public static String NetworkCap2Str =
            "[ Transports: CELLULAR " +
                    "Capabilities: " +
                    "NOT_METERED&INTERNET" +
                    "&NOT_RESTRICTED" +
                    "&TRUSTED" +
                    "&NOT_VPN" +
                    "&VALIDATED" +
                    "&NOT_ROAMING&FOREGROUND" +
                    "&NOT_CONGESTED" +
                    "&NOT_SUSPENDED" +
                    "&NOT_VCN_MANAGED " +
                    "LinkUpBandwidth>=1145141919810Kbps " +
                    "LinkDnBandwidth>=1145141919810Kbps " +
                    "TransportInfo: <114514> " +
                    "SignalStrength: -1145141919810]";
    public static String NetworkInfo2Str = "" +
            "[type: MOBILE[LTE], " +
            "state: CONNECTED/CONNECTED, " +
            "reason: (unspecified), " +
            "extra: , " +
            "failover: false, " +
            "available: true, " +
            "roaming: false]";
    public static int[] intArray114514 = new int[]{1,1,4,5,1,4};
    public static int[] EnterpriseIds = new int[]{1,2,3,4,5};
    public static int[] netCapIntArray = new int[]{
            0,1,2,3,4,
            5,6,7,8,9,
            10,11,12,13,14,
            15,16,
//            17,
            18,19,20,
//            21,
            23,
//            25,29,32,
            33,34,35
    };
    public static int[] transportTypes = new int[]{TRANSPORT_CELLULAR};
    public static SocketFactory defaultSocketFactory = SocketFactory.getDefault();
    public static InetAddress[] inetAddresses = new InetAddress[0];
    public static byte[] byteArray114514 = new byte[]{1,1,4,5,1,4};
    public static String macAddr114514 = "01:01:04:05:01:04";
    public static String macAddrOui114 = "01:01:04";
    public static List<LinkAddress> emptyLinkAddressList = new ArrayList<>();
    public static List<InetAddress> emptyInetAddressList = new ArrayList<>();
    public static List<RouteInfo> emptyRouteInfoList = new ArrayList<>();
    public static ProxyInfo fakeProxyInfo = ProxyInfo.buildDirectProxy("114514.1919810.jp",Integer.MAX_VALUE);
    public static MacAddress macAddress114514 = MacAddress.fromBytes(byteArray114514);

    public static String LinkPropertiesStr = "{LinkAddresses: [ ] DnsAddresses: [ ] Domains: null MTU: 0 Routes: [ ]}";
    public static String[] emptyStringArray = new String[0];
    public static List<String> emptyStringList = new ArrayList<>();

    public static boolean HookNet = true;
    public static boolean HookNetworkCapabilities = true;
    public static boolean HookNetworkInfo = true;
    public static boolean HookConnectivityManager = true;
    public static boolean HookPlatformVpnProfile = true;
    public static boolean HookVpnManager = true;
<<<<<<< Updated upstream
//    public static boolean HookVpnProfileState = true;
=======
    public static boolean HookInetAddress = true;
>>>>>>> Stashed changes
    public static boolean HookVpnService = true;
    public static boolean HookTelephonyNetworkSpecifier = true;
    public static boolean HookRouteInfo = true;
    public static boolean HookProxyInfo = true;
    public static boolean HookProxy = true;
    public static boolean HookNetworkRequest = true;
    public static boolean HookNetwork = true;
    public static boolean HookMacAddress = true;
    public static boolean HookLocalSocketAddress = true;
    public static boolean HookLinkProperties = true;
    public static boolean HookLinkAddress = true;
    public static boolean HookIpSecTransform = true;
    public static boolean HookIpPrefix = true;
    public static boolean HookIkev2VpnProfile = true;
    public static boolean HookConnectivityDiagnosticsManager = true;
    public static boolean HookCaptivePortal = true;
    public static boolean HookUri = true;
<<<<<<< Updated upstream
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
=======
    public static boolean HookDnsResolver = false;
    public static boolean HookDhcpInfo = false;
    public static ProxyInfo proxyInfo = null;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        {
            HookNet = sharedPreferences.getBoolean("HookNetClass_HookNet", true);
            HookNetworkCapabilities = sharedPreferences.getBoolean("HookNetClass_HookNetworkCapabilities", true);
            HookNetworkInfo = sharedPreferences.getBoolean("HookNetClass_HookNetworkInfo", true);
            HookConnectivityManager = sharedPreferences.getBoolean("HookNetClass_HookConnectivityManager", true);
            HookPlatformVpnProfile = sharedPreferences.getBoolean("HookNetClass_HookPlatformVpnProfile", true);
            HookVpnManager = sharedPreferences.getBoolean("HookNetClass_HookVpnManager", true);
            HookInetAddress = sharedPreferences.getBoolean("HookNetClass_HookInetAddress", true);
            HookVpnService = sharedPreferences.getBoolean("HookNetClass_HookVpnService", true);
            HookTelephonyNetworkSpecifier = sharedPreferences.getBoolean("HookNetClass_HookTelephonyNetworkSpecifier", true);
            HookRouteInfo = sharedPreferences.getBoolean("HookNetClass_HookRouteInfo", true);
            HookProxyInfo = sharedPreferences.getBoolean("HookNetClass_HookProxyInfo", true);
            HookProxy = sharedPreferences.getBoolean("HookNetClass_HookProxy", true);
            HookNetworkRequest = sharedPreferences.getBoolean("HookNetClass_HookNetworkRequest", true);
            HookNetwork = sharedPreferences.getBoolean("HookNetClass_HookNetwork", true);
            HookMacAddress = sharedPreferences.getBoolean("HookNetClass_HookMacAddress", true);
            HookLocalSocketAddress = sharedPreferences.getBoolean("HookNetClass_HookLocalSocketAddress", true);
            HookLinkProperties = sharedPreferences.getBoolean("HookNetClass_HookLinkProperties", true);
            HookLinkAddress = sharedPreferences.getBoolean("HookNetClass_HookLinkAddress", true);
            HookIpSecTransform = sharedPreferences.getBoolean("HookNetClass_HookIpSecTransform", true);
            HookIpPrefix = sharedPreferences.getBoolean("HookNetClass_HookIpPrefix", true);
            HookIkev2VpnProfile = sharedPreferences.getBoolean("HookNetClass_HookIkev2VpnProfile", true);
            HookConnectivityDiagnosticsManager = sharedPreferences.getBoolean("HookNetClass_HookConnectivityDiagnosticsManager", true);
            HookCaptivePortal = sharedPreferences.getBoolean("HookNetClass_HookCaptivePortal", true);
            HookUri = sharedPreferences.getBoolean("HookNetClass_HookUri", true);
            HookDnsResolver = sharedPreferences.getBoolean("HookNetClass_HookDnsResolver", false);
            HookDhcpInfo = sharedPreferences.getBoolean("HookNetClass_HookDhcpInfo", false);
        }
>>>>>>> Stashed changes
        InetAddress emptyInetAddress = null;
        RouteInfo emptyRouteInfo = null;
        ProxyInfo proxyInfo = null;
        IpPrefix ipPrefix = null;
        Network fakeNetwork = null;
        try {

            Constructor<?> InetAddressConstructor = XposedHelpers.findConstructorExact(
                    InetAddress.class.getName(),
                    lpparam.classLoader);
            emptyInetAddress = (InetAddress) InetAddressConstructor.newInstance();
            proxyInfo = ProxyInfo.buildDirectProxy(getRandomString(20),Integer.MIN_VALUE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                ipPrefix = new IpPrefix(emptyInetAddress, 127);
//            }
        } catch (Exception e) {
            LoggerLog(e);
        }


        if (HookNet){
            HookWifiClass.DoHook(lpparam,procHead,sharedPreferences);//working here
            if (HookCaptivePortal){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.CaptivePortal.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.CaptivePortal.class.getName(),
                            lpparam.classLoader,
                            "ignoreNetwork",
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
                    XposedHelpers.findAndHookMethod(
                            android.net.CaptivePortal.class.getName(),
                            lpparam.classLoader,
                            "reportCaptivePortalDismissed",
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
            if (HookConnectivityDiagnosticsManager){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    LinkProperties linkProperties = new LinkProperties();
                    NetworkCapabilities networkCapabilities = new NetworkCapabilities();
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getDetectionMethod",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return DETECTION_METHOD_DNS_EVENTS;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "hashCode",
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "describeContents",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getNetwork",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return fakeNetwork;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getReportTimestamp",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 1L;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getLinkProperties",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return linkProperties;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getNetworkCapabilities",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return networkCapabilities;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.DataStallReport.class.getName(),
                                lpparam.classLoader,
                                "getStallDetails",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return PersistableBundle.EMPTY;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }

                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "hashCode",
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "describeContents",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "getNetwork",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return fakeNetwork;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "getReportTimestamp",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 1L;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "getLinkProperties",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return linkProperties;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "getNetworkCapabilities",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return networkCapabilities;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(),
                                lpparam.classLoader,
                                "getAdditionalInfo",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return PersistableBundle.EMPTY;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookConnectivityManager){
                try {
                    XposedHelpers.findAndHookMethod(
                            ConnectivityManager.class.getName(),
                            lpparam.classLoader,
                            "isNetworkTypeValid",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    int type = (int) param.args[0];
                                    switch(type){
                                        case ConnectivityManager.TYPE_WIMAX:
                                        case ConnectivityManager.TYPE_ETHERNET:
                                        case ConnectivityManager.TYPE_BLUETOOTH:
                                        case ConnectivityManager.TYPE_DUMMY:
                                        case ConnectivityManager.TYPE_VPN:
                                            return false;
                                        default:return true;
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ConnectivityManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkPreference",
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

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    ProxyInfo proxyInfo = new ProxyInfo(null);
                    LinkProperties linkProperties = new LinkProperties();
                    NetworkCapabilities networkCapabilities = new NetworkCapabilities();
                    NetworkInfo networkInfo =
                            new NetworkInfo(
                                    ConnectivityManager.TYPE_MOBILE,
                                    TelephonyManager.NETWORK_TYPE_LTE,
                                    "MOBILE",
                                    "LTE");
                    NetworkInfo[] allNetworkInfo = new NetworkInfo[]{networkInfo};
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getActiveNetworkInfo",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return networkInfo;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                ConnectivityManager.class,
                                "getNetworkInfo",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return networkInfo;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getAllNetworkInfo",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return allNetworkInfo;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getLinkProperties",
                                Network.class,
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return linkProperties;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getNetworkCapabilities",
                                Network.class,
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return networkCapabilities;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getBackgroundDataSetting",
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "addDefaultNetworkActiveListener",
                                ConnectivityManager.OnNetworkActiveListener.class,
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "removeDefaultNetworkActiveListener",
                                ConnectivityManager.OnNetworkActiveListener.class,
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "isDefaultNetworkActive",
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
                        XposedHelpers.findAndHookMethod(
                                ConnectivityManager.class.getName(),
                                lpparam.classLoader,
                                "getDefaultProxy",
//                                new XC_MethodHook(114514) {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                                        super.afterHookedMethod(param);
//                                        LoggerLog(param.getResult().toString());
//                                    }
//                                }
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return finalProxyInfo;
                                        return null;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
//                getActiveNetwork()
//                getAllNetworks()//Hook Network
                try {
                    XposedHelpers.findAndHookMethod(
                            ConnectivityManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkWatchlistConfigHash",
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
                    XposedHelpers.findAndHookMethod(
                            ConnectivityManager.class.getName(),
                            lpparam.classLoader,
                            "getConnectionOwnerUid",
                            int.class, InetSocketAddress.class,InetSocketAddress.class,
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

            }//maybe not finished
            //Credentials
            //DhcpInfo
            //DnsResolver
            //EthernetNetworkSpecifier
            if (HookIkev2VpnProfile){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getServerAddr",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "1.1.4.5.1.4.19.19.81.0";
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getUserIdentity",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "1.1.4.5.1.4.19.19.81.0";
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getPresharedKey",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getServerRootCaCert",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getUsername",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getPassword",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getRsaPrivateKey",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getUserCert",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getProxyInfo",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getAllowedAlgorithms",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return emptyStringList;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "isBypassable",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return DeviceAddressTweaker.random.nextBoolean();
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "isMetered",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return DeviceAddressTweaker.random.nextBoolean();
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getMaxMtu",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                android.net.Ikev2VpnProfile.class.getName(),
//                                lpparam.classLoader,
//                                "getIkeTunnelConnectionParams",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return null;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "hashCode",
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                        XposedHelpers.findAndHookMethod(
                                android.net.Ikev2VpnProfile.class.getName(),
                                lpparam.classLoader,
                                "certificateFromPemString",
                                String.class,
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
                    //Builder
                }
            }
            //IpConfiguration
            if (HookIpPrefix){
                try {
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
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
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
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
                    InetAddress finalEmptyInetAddress = emptyInetAddress;
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "getAddress",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return finalEmptyInetAddress;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "getRawAddress",
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
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "getPrefixLength",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 127;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            IpPrefix.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //toString:requires api33,but my phone is 32 and i dont want to use virtual env.
            }
            //IpSecAlgorithm
            //IpSecManager
            if (HookIpSecTransform){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    try {
                        XposedHelpers.findAndHookMethod(
                                IpSecTransform.class.getName(),
                                lpparam.classLoader,
                                "toString",
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
                        XposedHelpers.findAndHookMethod(
                                IpSecTransform.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                }
            }
            if (HookLinkAddress){
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "LinkAddresses: []";
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
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
                //getAddress
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "getFlags",
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
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "getScope",
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
                    XposedHelpers.findAndHookMethod(
                            LinkAddress.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookLinkProperties){
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getLinkAddresses",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyLinkAddressList;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setLinkAddresses",
                            Collection.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setDnsServers",
                            Collection.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getDnsServers",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyInetAddressList;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "isPrivateDnsActive",
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setDhcpServerAddress",
                            Inet4Address.class,
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
                //getDhcpServerAddress
                //getPrivateDnsServerName
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setDomains",
                            String.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getDomains",
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setMtu",
                            int.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getMtu",
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getRoutes",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyRouteInfoList;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setHttpProxy",
                            ProxyInfo.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "getHttpProxy",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return fakeProxyInfo;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //getNat64Prefix
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "setNat64Prefix",
                            IpPrefix.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "clear",
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return LinkPropertiesStr;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "isWakeOnLanSupported",
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
                    XposedHelpers.findAndHookMethod(
                            LinkProperties.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
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
            //LocalServerSocket
            //LocalSocket
            if (HookLocalSocketAddress){
                try {
                    XposedHelpers.findAndHookMethod(
                            LocalSocketAddress.class.getName(),
                            lpparam.classLoader,
                            "getName",
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
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            android.net.LocalSocketAddress.class.getName(),
//                            lpparam.classLoader,
//                            "getNamespace",
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return FILESYSTEM;
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
            }
            if (HookMacAddress) {

                    try {
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "getAddressType",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return TYPE_BROADCAST;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "isLocallyAssigned",
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
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "toByteArray",
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
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "toString",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return macAddr114514;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "toOuiString",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return macAddrOui114;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "hashCode",
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
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "describeContents",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return 0;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "matches",
                                MacAddress.class,MacAddress.class,
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
                        XposedHelpers.findAndHookMethod(
                                MacAddress.class.getName(),
                                lpparam.classLoader,
                                "getLinkLocalIpv6FromEui48Mac",
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
            //MailTo
            if (HookNetwork){
                InetAddress finalEmptyInetAddress2 = emptyInetAddress;
                try {
                    XposedHelpers.findAndHookMethod(
                            Network.class.getName(),
                            lpparam.classLoader,
                            "getAllByName",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return inetAddresses;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            Network.class.getName(),
                            lpparam.classLoader,
                            "getByName",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return finalEmptyInetAddress2;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            Network.class.getName(),
                            lpparam.classLoader,
                            "getSocketFactory",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return defaultSocketFactory;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //openConnection
                //bindSocket
                //fromNetworkHandle
                //getNetworkHandle
                //...
            }
            if (HookNetworkCapabilities){
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            android.net.NetworkCapabilities.class.getName(),
//                            lpparam.classLoader,
//                            "getEnterpriseIds",
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return EnterpriseIds;
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "getCapabilities",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return netCapIntArray;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            android.net.NetworkCapabilities.class.getName(),
//                            lpparam.classLoader,
//                            "hasEnterpriseId",
//                            int.class,
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return true;
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "hasCapability",
                            int.class,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "hasTransport",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    switch((int) param.args[0]){
                                        case TRANSPORT_ETHERNET:
                                        case TRANSPORT_WIFI_AWARE:
                                        case TRANSPORT_WIFI:
                                        case TRANSPORT_USB:
                                        case TRANSPORT_VPN:
                                        case TRANSPORT_BLUETOOTH:
                                            return false;
                                        default: {
                                            return true;
                                        }
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "getOwnerUid",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "getLinkUpstreamBandwidthKbps",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "getLinkDownstreamBandwidthKbps",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "getSignalStrength",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkCapabilities.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return NetworkCap2Str;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }

            }
            if (HookNetworkInfo){
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getSubtype",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return TelephonyManager.NETWORK_TYPE_LTE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getSubtypeName",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "isConnectedOrConnecting",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "isConnected",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "isAvailable",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "isFailover",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "isRoaming",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getState",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return NetworkInfo.State.CONNECTED;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getDetailedState",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return NetworkInfo.DetailedState.CONNECTED;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getReason",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "getExtraInfo",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return NetworkInfo2Str;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookNetworkRequest){
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return -Integer.MAX_VALUE;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "hasCapability",
                            int.class,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "canBeSatisfiedBy",
                            NetworkCapabilities.class,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "hasTransport",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    switch((int) param.args[0]){
                                        case TRANSPORT_ETHERNET:
                                        case TRANSPORT_WIFI_AWARE:
                                        case TRANSPORT_WIFI:
                                        case TRANSPORT_USB:
                                        case TRANSPORT_VPN:
                                        case TRANSPORT_BLUETOOTH:
                                            return false;
                                        default: {
                                            return true;
                                        }
                                    }
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                //getNetworkSpecifier
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return NetworkReq2Str;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
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
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
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
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "getCapabilities",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return netCapIntArray;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader,
                            "getTransportTypes",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return transportTypes;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            //NetworkSpecifier(abstract and no callable method)
            //ParseException
            if (HookPlatformVpnProfile){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    try {
                        XposedHelpers.findAndHookMethod(
                                PlatformVpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getTypeString",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "Unknown VPN profile type";
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                PlatformVpnProfile.class.getName(),
                                lpparam.classLoader,
                                "getType",
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
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                PlatformVpnProfile.class.getName(),
//                                lpparam.classLoader,
//                                "areLocalRoutesExcluded",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return true;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                PlatformVpnProfile.class.getName(),
//                                lpparam.classLoader,
//                                "isInternetValidationRequired",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return false;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
                }
            }
            if (HookProxy){
                try {
                    XposedHelpers.findAndHookMethod(
                            Proxy.class.getName(),
                            lpparam.classLoader,
                            "getDefaultPort",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return -Integer.MAX_VALUE;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            android.net.Proxy.class.getName(),
//                            lpparam.classLoader,
//                            "getDefaultHost",
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return null;
//                                }
//                            }
//                    );
//
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                try {
                    XposedHelpers.findAndHookMethod(
                            Proxy.class.getName(),
                            lpparam.classLoader,
                            "getHost",
                            Context.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Context context = (Context) param.args[0];
                                    if (!Objects.equals(context.getPackageName(), lpparam.packageName)){
                                        param.setResult(null);
                                    }
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                //getPort
            }
            if (HookProxyInfo){
                try {
                    XposedBridge.hookAllMethods(
                            ProxyInfo.class,
                            "buildDirectProxy",
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
                            ProxyInfo.class,
                            "buildPacProxy",
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
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "describeContents",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MIN_VALUE;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "getHost",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "getPort",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MIN_VALUE;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "getExclusionList",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyStringArray;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "isValid",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader,
                            "getPacFileUrl",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Uri.EMPTY;
                                }
                            }
                    );
                }  catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookRouteInfo){
                IpPrefix finalIpPrefix = ipPrefix;
                InetAddress finalEmptyInetAddress1 = emptyInetAddress;
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "getDestination",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return finalIpPrefix;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "getGateway",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return finalEmptyInetAddress1;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "getInterface",
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
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            RouteInfo.class.getName(),
//                            lpparam.classLoader,
//                            "getType",
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return RouteInfo.RTN_UNICAST;
//                                }
//                            }
//                    );
//
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "isDefaultRoute",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return DeviceAddressTweaker.random.nextBoolean();
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "hasGateway",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return DeviceAddressTweaker.random.nextBoolean();
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "matches",
                            InetAddress.class,
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
                    XposedHelpers.findAndHookMethod(
                            RouteInfo.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "::/0 -> null 114514 mtu 0";
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            //SocketKeepalive
            //SSLCertificateSocketFactory
            //SSLSessionCache
            //StaticIpConfiguration,do we really need this?
            if (HookTelephonyNetworkSpecifier){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    try {
                        XposedHelpers.findAndHookMethod(
                                TelephonyNetworkSpecifier.class.getName(),
                                lpparam.classLoader,
                                "getSubscriptionId",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return -Integer.MAX_VALUE;
                                    }
                                }
                        );

                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                TelephonyNetworkSpecifier.class.getName(),
                                lpparam.classLoader,
                                "hashCode",
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
                        XposedHelpers.findAndHookMethod(
                                TelephonyNetworkSpecifier.class.getName(),
                                lpparam.classLoader,
                                "equals",
                                Object.class,
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
                        XposedHelpers.findAndHookMethod(
                                TelephonyNetworkSpecifier.class.getName(),
                                lpparam.classLoader,
                                "toString",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "TelephonyNetworkSpecifier [mSubId = 0x80000000]";
                                    }
                                }
                        );

                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            //trafficStats,maybe u want to listen to this (with a listener).
            if (HookUri){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.Uri.class.getName(),
                            lpparam.classLoader,
                            "fromFile",
                            File.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if(param.args[0] == null){return;}
                                    File file = (File) param.args[0];
                                    String path = file.getAbsolutePath();
                                    path = checkReplaceFile(path, lpparam);
                                    if (!checkBannedInFile(path,lpparam)){
                                        param.args[0] = new File("/");
                                        return;
                                    }
                                    param.args[0] = new File(path);
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
            if (HookVpnManager){
                try {
                    XposedHelpers.findAndHookMethod(
                            VpnManager.class.getName(),
                            lpparam.classLoader,
                            "getIntentForConfirmation",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return VPNEstablishIntent;
                                }
                            }
                    );

            } catch (Exception e) {
                LoggerLog(e);
            }
                try {
                    XposedHelpers.findAndHookMethod(
                            VpnManager.class.getName(),
                            lpparam.classLoader,
                            "provisionVpnProfile",
                            PlatformVpnProfile.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return VPNEstablishIntent;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            VpnManager.class.getName(),
                            lpparam.classLoader,
                            "deleteProvisionedVpnProfile",
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
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnManager.class.getName(),
//                                lpparam.classLoader,
//                                "startProvisionedVpnProfileSession",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return getRandomString(20);
//                                    }
//                                }
//                        );
//
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
                try {
                    XposedHelpers.findAndHookMethod(
                            VpnManager.class.getName(),
                            lpparam.classLoader,
                            "startProvisionedVpnProfile",
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
                    XposedHelpers.findAndHookMethod(
                            VpnManager.class.getName(),
                            lpparam.classLoader,
                            "stopProvisionedVpnProfile",
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
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                        String VpnProfileStateStr = "{State: DISCONNECTED, SessionId: 114514, Always-on: false, Lockdown: false}";
//                        VpnProfileState fakeVpnProfileState =
//                                new VpnProfileState(
//                                        VpnProfileState.STATE_DISCONNECTED,
//                                        getRandomString(20),
//                                        false,
//                                        false);
//                        try {
//                            XposedHelpers.findAndHookMethod(
//                                    VpnManager.class.getName(),
//                                    lpparam.classLoader,
//                                    "getProvisionedVpnProfileState",
//                                    new XC_MethodReplacement(114514) {
//                                        @Override
//                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                            return fakeVpnProfileState;
//                                        }
//                                    }
//                            );
//
//                        } catch (Exception e) {
//                            LoggerLog(e);
//                        }
//                    }

            }
//            if (HookVpnProfileState){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    String VpnProfileStateStr = "{State: DISCONNECTED, SessionId: 114514, Always-on: false, Lockdown: false}";
//                    VpnProfileState fakeVpnProfileState =
//                            new VpnProfileState(
//                                    VpnProfileState.STATE_DISCONNECTED,
//                                    getRandomString(20),
//                                    false,
//                                    false);
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "getState",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return VpnProfileState.STATE_DISCONNECTED;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "getSessionId",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return getRandomString(20);
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "isAlwaysOn",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return false;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "isLockdownEnabled",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return false;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "toString",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return VpnProfileStateStr;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "equals",
//                                Object.class,
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return false;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.findAndHookMethod(
//                                VpnProfileState.class.getName(),
//                                lpparam.classLoader,
//                                "hashCode",
//                                new XC_MethodReplacement(114514) {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        return Integer.MAX_VALUE;
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                    try {
//                        XposedHelpers.setStaticObjectField(
//                                VpnProfileState.class,
//                                "CREATOR",
//                                new Parcelable.Creator<VpnProfileState>() {
//                                    public VpnProfileState createFromParcel(Parcel in) {
//                                        return fakeVpnProfileState;
//                                    }
//                                    public VpnProfileState[] newArray(int size) {
//                                        return new VpnProfileState[]{fakeVpnProfileState};
//                                    }
//                                }
//                        );
//                    } catch (Exception e) {
//                        LoggerLog(e);
//                    }
//                }
//            }
            if (HookVpnService){
//                try {
//                    XposedHelpers.findAndHookMethod(
//                            VpnService.class.getName(),
//                            lpparam.classLoader,
//                            "prepare",
//                            new XC_MethodReplacement(114514) {
//                                @Override
//                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    return VPNEstablishIntent;
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
                try {
                    XposedBridge.hookAllMethods(
                            VpnService.class,
                            "protect",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "addAddress",
                            InetAddress.class,int.class,
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
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "removeAddress",
                            InetAddress.class,int.class,
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
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "setUnderlyingNetworks",
                            Network[].class,
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
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "isAlwaysOn",
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
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "isLockdownEnabled",
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
                    XposedHelpers.findAndHookMethod(
                            VpnService.class.getName(),
                            lpparam.classLoader,
                            "onBind",
                            Intent.class,
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
        }
    }
}
