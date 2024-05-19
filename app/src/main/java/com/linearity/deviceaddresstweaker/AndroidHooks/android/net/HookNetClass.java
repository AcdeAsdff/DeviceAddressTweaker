package com.linearity.deviceaddresstweaker.AndroidHooks.android.net;

import static android.net.ConnectivityDiagnosticsManager.DataStallReport.DETECTION_METHOD_DNS_EVENTS;
import static android.net.MacAddress.TYPE_BROADCAST;
import static android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;
import static android.net.NetworkCapabilities.TRANSPORT_ETHERNET;
import static android.net.NetworkCapabilities.TRANSPORT_USB;
import static android.net.NetworkCapabilities.TRANSPORT_VPN;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI_AWARE;

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
import android.os.PersistableBundle;
import android.telephony.TelephonyManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.wifi.HookWifiClass;
import com.linearity.utils.HookUtils;
import com.linearity.utils.ReturnReplacements;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.net.SocketFactory;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.ReturnReplacements.returnByteArr114514;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.LoggerUtils.LoggerLog;
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
        public static boolean HookInetAddress = true;
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
        public static boolean HookDnsResolver = false;
        public static boolean HookDhcpInfo = false;
        public static ProxyInfo proxyInfo = null;
        public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
            Class<?> hookClass;
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
                proxyInfo = ProxyInfo.buildDirectProxy(ReturnReplacements.getRandomString(20),Integer.MIN_VALUE);
        //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        //                ipPrefix = new IpPrefix(emptyInetAddress, 127);
        //            }
            } catch (Exception e) {
                LoggerLog(e);
            }
        
        
            if (HookNet){
                HookWifiClass.DoHook(lpparam,procHead,sharedPreferences);//working here
                if (HookCaptivePortal){
                    hookClass = XposedHelpers.findClassIfExists(android.net.CaptivePortal.class.getName(),
                            lpparam.classLoader);
                     if (hookClass != null){
                         try {
                             {
                                 HookUtils.findAndHookMethodIfExists(hookClass,
                                         "describeContents", ReturnReplacements.returnIntegerZero
                                 );
                             }
                             {
                                 HookUtils.findAndHookMethodIfExists(hookClass,
                                         "ignoreNetwork", ReturnReplacements.returnNull
                                 );
                             }
                             {
                                 HookUtils.findAndHookMethodIfExists(hookClass,
                                         "reportCaptivePortalDismissed", ReturnReplacements.returnNull
                                 );
                             }
                         }catch (Exception e){LoggerLog(e);}
                     }
                }
                if (HookConnectivityDiagnosticsManager){
                    hookClass = XposedHelpers.findClassIfExists(ConnectivityDiagnosticsManager.DataStallReport.class.getName(), lpparam.classLoader);
                    if (hookClass != null){
                        LinkProperties linkProperties = new LinkProperties();
                        NetworkCapabilities networkCapabilities = new NetworkCapabilities();
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDetectionMethod",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return DETECTION_METHOD_DNS_EVENTS;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return false;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MIN_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return 0;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getNetwork",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return fakeNetwork;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getReportTimestamp",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return 1L;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getLinkProperties",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return linkProperties;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getNetworkCapabilities",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return networkCapabilities;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getStallDetails",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return PersistableBundle.EMPTY;
                                            }
                                        }
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                        hookClass = XposedHelpers.findClassIfExists(ConnectivityDiagnosticsManager.ConnectivityReport.class.getName(), lpparam.classLoader);
                        if (hookClass != null){
                            try {
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "equals",
                                            Object.class,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "hashCode",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return Integer.MIN_VALUE;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "describeContents",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getNetwork",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return fakeNetwork;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getReportTimestamp",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 1L;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getLinkProperties",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return linkProperties;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getNetworkCapabilities",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return networkCapabilities;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getAdditionalInfo",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return PersistableBundle.EMPTY;
                                                }
                                            }
                                    );
                                }
                            }catch (Exception e){LoggerLog(e);}
                        }
                    }
                }
                if (HookConnectivityManager){
                    hookClass = XposedHelpers.findClassIfExists(ConnectivityManager.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getNetworkPreference", ReturnReplacements.returnIntegerOne
                                );
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
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getActiveNetworkInfo",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return networkInfo;
                                                }
                                            }
                                    );
                                }
                                {
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
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getAllNetworkInfo",
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return allNetworkInfo;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getLinkProperties",
                                            Network.class,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return linkProperties;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getNetworkCapabilities",
                                            Network.class,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return networkCapabilities;
                                                }
                                            }
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getBackgroundDataSetting", ReturnReplacements.returnTrue
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "addDefaultNetworkActiveListener",
                                            ConnectivityManager.OnNetworkActiveListener.class, ReturnReplacements.returnNull
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "removeDefaultNetworkActiveListener",
                                            ConnectivityManager.OnNetworkActiveListener.class, ReturnReplacements.returnNull
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "isDefaultNetworkActive", ReturnReplacements.returnTrue
                                    );
                                }
                                {
                                    HookUtils.findAndHookMethodIfExists(hookClass,
                                            "getDefaultProxy", ReturnReplacements.returnNull
                                    );
                                }
                            }
        //                getActiveNetwork()
        //                getAllNetworks()//Hook Network
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getNetworkWatchlistConfigHash", ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getConnectionOwnerUid",
                                        int.class, InetSocketAddress.class,InetSocketAddress.class, ReturnReplacements.returnIntegerMAX
                                );
                            }
                        }catch (Exception e){
                            LoggerLog(e);
                        }
                    }  
        
                }//maybe not finished
                //Credentials
                //DhcpInfo
                //DnsResolver
                //EthernetNetworkSpecifier
                if (HookIkev2VpnProfile) {
                    hookClass = XposedHelpers.findClassIfExists(
                            android.net.Ikev2VpnProfile.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getServerAddr",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "1.1.4.5.1.4.19.19.81.0";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getUserIdentity",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "1.1.4.5.1.4.19.19.81.0";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getPresharedKey", returnByteArr114514
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getServerRootCaCert",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return null;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getUsername", ReturnReplacements.returnRandomStr20
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getPassword", ReturnReplacements.returnRandomStr20
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getRsaPrivateKey",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return null;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getUserCert",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return null;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getProxyInfo",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return null;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getAllowedAlgorithms",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return emptyStringList;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isBypassable",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return ReturnReplacements.random.nextBoolean();
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isMetered",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return ReturnReplacements.random.nextBoolean();
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getMaxMtu",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return 0;
                                            }
                                        }
                                );
                            }
        //                     {
        //                        findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MIN_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return false;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "certificateFromPemString",
                                        String.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return null;
                                            }
                                        }
                                );
                            }
                            //Builder
                        }catch (Exception e){
                            LoggerLog(e);
                        }
                    }
                }
                //IpConfiguration
                if (HookIpPrefix){
                    hookClass = XposedHelpers.findClassIfExists(
                            IpPrefix.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode", ReturnReplacements.returnIntegerMIN
                                );
                            }
                            {
                                InetAddress finalEmptyInetAddress = emptyInetAddress;
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getAddress",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return finalEmptyInetAddress;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getRawAddress", returnByteArr114514
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getPrefixLength",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return 127;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents", ReturnReplacements.returnIntegerZero
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                    //toString:requires api33,but my phone is 32 and i dont want to use virtual env.
                }
                //IpSecAlgorithm
                //IpSecManager
                if (HookIpSecTransform){
                    hookClass = XposedHelpers.findClassIfExists(
                            IpSecTransform.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                         try {
                             {
                                 HookUtils.findAndHookMethodIfExists(hookClass,
                                         "toString", ReturnReplacements.returnRandomStr20
                                 );
                             }
                             {
                                 HookUtils.findAndHookMethodIfExists(hookClass,
                                         "equals",
                                         Object.class,returnFalse
                                 );
                             }
                         }catch (Exception e){LoggerLog(e);}
                    }
                }
                if (HookLinkAddress){
                    hookClass = XposedHelpers.findClassIfExists(
                            LinkAddress.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "LinkAddresses: []";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode", ReturnReplacements.returnIntegerMIN
                                );
                            }
                            //getAddress
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getFlags", ReturnReplacements.returnIntegerOne
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getScope", ReturnReplacements.returnIntegerOne
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents", ReturnReplacements.returnIntegerZero
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                if (HookLinkProperties) {
                    hookClass = XposedHelpers.findClassIfExists(
                            LinkProperties.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getLinkAddresses",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return emptyLinkAddressList;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setLinkAddresses",
                                        Collection.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setDnsServers",
                                        Collection.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDnsServers",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return emptyInetAddressList;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isPrivateDnsActive", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setDhcpServerAddress",
                                        Inet4Address.class, ReturnReplacements.returnNull
                                );
                            }
                            //getDhcpServerAddress
                            //getPrivateDnsServerName
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setDomains",
                                        String.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDomains", ReturnReplacements.returnRandomStr20
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setMtu",
                                        int.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getMtu", ReturnReplacements.returnIntegerMIN
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getRoutes",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return emptyRouteInfoList;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setHttpProxy",
                                        ProxyInfo.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getHttpProxy",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return fakeProxyInfo;
                                            }
                                        }
                                );
                            }
                            //getNat64Prefix
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setNat64Prefix",
                                        IpPrefix.class, ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "clear", ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents", ReturnReplacements.returnIntegerZero
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return LinkPropertiesStr;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isWakeOnLanSupported", returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode", ReturnReplacements.returnIntegerMIN
                                );
                            }
        
        
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                //LocalServerSocket
                //LocalSocket
                if (HookLocalSocketAddress){
                    hookClass = XposedHelpers.findClassIfExists(
                            LocalSocketAddress.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getName", ReturnReplacements.returnRandomStr20
                                );
                            }
        //                 {
        //                    findAndHookMethodIfExists(
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
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                if (HookMacAddress) {
        
                    hookClass = XposedHelpers.findClassIfExists(
                            MacAddress.class.getName(),
                            lpparam.classLoader);
                         if (hookClass != null){
                             try {
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "getAddressType",
                                             new XC_MethodReplacement(114514) {
                                                 @Override
                                                 protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                     return TYPE_BROADCAST;
                                                 }
                                             }
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "isLocallyAssigned", ReturnReplacements.returnTrue
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "toByteArray",returnByteArr114514
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "toString",
                                             new XC_MethodReplacement(114514) {
                                                 @Override
                                                 protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                     return macAddr114514;
                                                 }
                                             }
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "toOuiString",
                                             new XC_MethodReplacement(114514) {
                                                 @Override
                                                 protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                     return macAddrOui114;
                                                 }
                                             }
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "hashCode", ReturnReplacements.returnIntegerMIN
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "equals",
                                             Object.class,returnFalse
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "describeContents", ReturnReplacements.returnIntegerZero
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "matches",
                                             MacAddress.class,MacAddress.class,returnFalse
                                     );
                                 }
                                 {
                                     HookUtils.findAndHookMethodIfExists(hookClass,
                                             "getLinkLocalIpv6FromEui48Mac", ReturnReplacements.returnNull
                                     );
                                 }
                             }catch (Exception e){
                                 LoggerLog(e);
                             }
                         }
        
        
                }
                //MailTo
                if (HookNetwork){
                    hookClass = XposedHelpers.findClassIfExists(
                            Network.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        try{
                            InetAddress finalEmptyInetAddress2 = emptyInetAddress;
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getAllByName",
                                        String.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return inetAddresses;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getByName",
                                        String.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return finalEmptyInetAddress2;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getSocketFactory",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return defaultSocketFactory;
                                            }
                                        }
                                );
                            }
                            //openConnection
                            //bindSocket
                            //fromNetworkHandle
                            //getNetworkHandle
                            //...}}
                        }catch (Exception e){LoggerLog(e);}
                    }
                if (HookNetworkCapabilities){
                    hookClass = XposedHelpers.findClassIfExists(android.net.NetworkCapabilities.class.getName(),
                                                        lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            //                 {
                            //                    findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getCapabilities",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return netCapIntArray;
                                            }
                                        }
                                );
                            }
                            //                 {
                            //                    findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hasCapability",
                                        int.class, ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hasTransport",
                                        int.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                switch ((int) param.args[0]) {
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
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getOwnerUid",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getLinkUpstreamBandwidthKbps",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getLinkDownstreamBandwidthKbps",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getSignalStrength",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return NetworkCap2Str;
                                            }
                                        }
                                );
                            }
                        }catch (Exception e){
                            LoggerLog(e);
                        }
                    }
            }
                if (HookNetworkInfo){
                    hookClass = XposedHelpers.findClassIfExists(
                            NetworkInfo.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try{
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getType", ReturnReplacements.returnIntegerZero
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getSubtype",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return TelephonyManager.NETWORK_TYPE_LTE;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getTypeName",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "MOBILE";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getSubtypeName",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "LTE";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isConnectedOrConnecting", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isConnected", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isAvailable", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isFailover", returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isRoaming", returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getState",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return NetworkInfo.State.CONNECTED;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDetailedState",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return NetworkInfo.DetailedState.CONNECTED;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getReason", ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getExtraInfo", ReturnReplacements.returnNull
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return NetworkInfo2Str;
                                            }
                                        }
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }  
                if (HookNetworkRequest){
                    hookClass = XposedHelpers.findClassIfExists(
                            NetworkRequest.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents", ReturnReplacements.returnIntegerZero
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hasCapability",
                                        int.class, ReturnReplacements.returnTrue
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "canBeSatisfiedBy",
                                        NetworkCapabilities.class, ReturnReplacements.returnTrue
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hasTransport",
                                        int.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                switch ((int) param.args[0]) {
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

                            }
                            //getNetworkSpecifier
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return NetworkReq2Str;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode", ReturnReplacements.returnIntegerMIN
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getCapabilities",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return netCapIntArray;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getTransportTypes",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return transportTypes;
                                            }
                                        }
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                //NetworkSpecifier(abstract and no callable method)
                //ParseException
                if (HookPlatformVpnProfile){
                    hookClass = XposedHelpers.findClassIfExists(
                            PlatformVpnProfile.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        try {


                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getTypeString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "Unknown VPN profile type";
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getType",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MAX_VALUE;
                                            }
                                        }
                                );
                            }
                            //                     {
                            //                        findAndHookMethodIfExists(
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
                            //                     {
                            //                        findAndHookMethodIfExists(
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
                            //                    }}
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                if (HookProxy){
                    hookClass = XposedHelpers.findClassIfExists(
                            Proxy.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        {
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDefaultPort", ReturnReplacements.returnIntegerMIN
                                );

                            }
                            //                 {
                            //                    findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getHost",
                                        Context.class,
                                        new XC_MethodHook(114514) {
                                            @Override
                                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                super.beforeHookedMethod(param);
                                                Context context = (Context) param.args[0];
                                                if (!Objects.equals(context.getPackageName(), lpparam.packageName)) {
                                                    param.setResult(null);
                                                }
                                            }
                                        }
                                );

                            }
                            //getPort}}
                        }
                    }
                }
                if (HookProxyInfo){
                    hookClass = XposedHelpers.findClassIfExists(
                            ProxyInfo.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try{
                            {
                                XposedBridge.hookAllMethods(
                                        hookClass,
                                        "buildDirectProxy", ReturnReplacements.returnNull
                                );

                            }
                            {
                                XposedBridge.hookAllMethods(
                                        hookClass,
                                        "buildPacProxy", ReturnReplacements.returnNull
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class, returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "describeContents", ReturnReplacements.returnIntegerZero
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode", ReturnReplacements.returnIntegerMIN
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getHost", ReturnReplacements.returnRandomStr20
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getPort", ReturnReplacements.returnIntegerMIN
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getExclusionList",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return emptyStringArray;
                                            }
                                        }
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isValid", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getPacFileUrl",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Uri.EMPTY;
                                            }
                                        }
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                if (HookRouteInfo){
                    hookClass = XposedHelpers.findClassIfExists(
                            RouteInfo.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        try{
                            InetAddress finalEmptyInetAddress1 = emptyInetAddress;
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getDestination",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return ipPrefix;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getGateway",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return finalEmptyInetAddress1;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getInterface", ReturnReplacements.returnNull
                                );

                            }
                            //                 {
                            //                    findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isDefaultRoute",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return ReturnReplacements.random.nextBoolean();
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hasGateway",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return ReturnReplacements.random.nextBoolean();
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "matches",
                                        InetAddress.class, returnFalse
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "::/0 -> null 114514 mtu 0";
                                            }
                                        }
                                );

                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                //SocketKeepalive
                //SSLCertificateSocketFactory
                //SSLSessionCache
                //StaticIpConfiguration,do we really need this?
                if (HookTelephonyNetworkSpecifier){
                    hookClass = XposedHelpers.findClassIfExists(
                            TelephonyNetworkSpecifier.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        try{
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getSubscriptionId",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return -Integer.MAX_VALUE;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "hashCode",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return Integer.MIN_VALUE;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "equals",
                                        Object.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return false;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "toString",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return "TelephonyNetworkSpecifier [mSubId = 0x80000000]";
                                            }
                                        }
                                );

                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
                //trafficStats,maybe u want to listen to this (with a listener).
                if (HookUri){
                    hookClass = XposedHelpers.findClassIfExists(
                            android.net.Uri.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        try{
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "fromFile",
                                    File.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            File file = (File) param.args[0];
                                            String path = file.getAbsolutePath();
                                            path = checkReplaceFile(path, lpparam);
                                            if (!checkBannedInFile(path, lpparam)) {
                                                param.args[0] = new File("/");
                                                return;
                                            }
                                            param.args[0] = new File(path);
                                        }
                                    }
                            );

                        }catch (Exception e){LoggerLog(e);}
                    }
                }//not finished
                if (HookVpnManager){
                    hookClass = XposedHelpers.findClassIfExists(
                            VpnManager.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null) {
                        try{
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "getIntentForConfirmation",
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return VPNEstablishIntent;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "provisionVpnProfile",
                                        PlatformVpnProfile.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return VPNEstablishIntent;
                                            }
                                        }
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "deleteProvisionedVpnProfile", ReturnReplacements.returnNull
                                );

                            }
                            //                     {
                            //                        findAndHookMethodIfExists(
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
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "startProvisionedVpnProfile", ReturnReplacements.returnNull
                                );

                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "stopProvisionedVpnProfile", ReturnReplacements.returnNull
                                );

                            }
                            //                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            //                        String VpnProfileStateStr = "{State: DISCONNECTED, SessionId: 114514, Always-on: false, Lockdown: false}";
                            //                        VpnProfileState fakeVpnProfileState =
                            //                                new VpnProfileState(
                            //                                        VpnProfileState.STATE_DISCONNECTED,
                            //                                        getRandomString(20),
                            //                                        false,
                            //                                        false);
                            //                         {
                            //                            findAndHookMethodIfExists(
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
                        }catch (Exception e){LoggerLog(e);}
                    }
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
        //                        findAndHookMethodIfExists(
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
        //                     {
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
                    hookClass = XposedHelpers.findClassIfExists(VpnService.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        try{
                            //                 {
                            //                    findAndHookMethodIfExists(
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
                            {
                                XposedBridge.hookAllMethods(
                                        hookClass,
                                        "protect", ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "addAddress",
                                        InetAddress.class, int.class, ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "removeAddress",
                                        InetAddress.class, int.class, ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "setUnderlyingNetworks",
                                        Network[].class, ReturnReplacements.returnTrue
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isAlwaysOn", returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "isLockdownEnabled", returnFalse
                                );
                            }
                            {
                                HookUtils.findAndHookMethodIfExists(hookClass,
                                        "onBind",
                                        Intent.class, ReturnReplacements.returnNull
                                );
                            }
                        }catch (Exception e){LoggerLog(e);}
                    }
                }
            }
        }
        }
}
