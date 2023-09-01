package com.linearity.deviceaddresstweaker.AndroidHooks.android.net.wifi;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.net.MacAddress;
import android.net.NetworkInfo;
import android.net.NetworkSpecifier;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiNetworkSpecifier;
import android.net.wifi.WifiNetworkSuggestion;
import android.net.wifi.WpsInfo;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

public class HookWifiClass {
    public static String wpsInfoStr = "setup: 4\nBSSID: null\npin: null";
    public static String wifiNetworkSuggestionStr =
            "[ SSID=\"114514\", " +
            "BSSID=01:01:04:05:01:04, " +
            "FQDN=null, " +
            "SecurityParams= open, " +
            "isAppInteractionRequired=false, " +
            "isUserInteractionRequired=false, " +
            "isCredentialSharedWithUser=false, " +
            "isInitialAutoJoinEnabled=true, " +
            "isUnTrusted=false, " +
            "isOemPaid=false, " +
            "isOemPrivate=false, " +
            "isCarrierMerged=false, " +
            "isHiddenSsid=false, " +
            "priorityGroup=2147483647, " +
            "subscriptionId=2147483647, " +
            "carrierId=2147483647, " +
            "priority=2147483647, " +
            "meteredness=0 ]";
    public static String wifiNetworkSpecifierStr = "" +
            "[, " +
            "SSID Match pattern=PatternMatcher{LITERAL: 114514}, " +
            "BSSID Match pattern=Pair{01:01:04:05:01:04 01:01:04:05:01:04}, " +
            "SSID=\"114514\", " +
            "BSSID=null, " +
            "band=2147483647]";
    public static List<WifiConfiguration> wifiConfigurationList = new ArrayList<>();
    public static boolean HookWifi = true;
    public static boolean HookWifiInfo = true;
    public static boolean HookWifiNetworkSpecifier = true;
    public static boolean HookWifiNetworkSuggestion = true;
    public static boolean HookWpsInfo = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){

        if (HookWifi){
            if (HookWifiInfo) {
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getBSSID",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getSSID",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getRssi",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getWifiStandard",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getLinkSpeed",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getTxLinkSpeedMbps",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getMaxSupportedTxLinkSpeedMbps",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getRxLinkSpeedMbps",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getMaxSupportedRxLinkSpeedMbps",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getFrequency",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getMacAddress",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "01:01:04:05:01:04";
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getPasspointFqdn",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getPasspointProviderFriendlyName",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getSubscriptionId",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getNetworkId",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getSupplicantState",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return SupplicantState.COMPLETED;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getIpAddress",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getHiddenSSID",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getDetailedStateOf",
                            SupplicantState.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDetailedStateOf()" + param.getResult());
                                    param.setResult(NetworkInfo.DetailedState.CONNECTED);
                                }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "empty";
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiInfo.class.getName(),
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
                            android.net.wifi.WifiInfo.class.getName(),
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
                            android.net.wifi.WifiInfo.class.getName(),
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getApplicableRedactions",
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
                            android.net.wifi.WifiInfo.class.getName(),
                            lpparam.classLoader,
                            "getCurrentSecurityType",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return WifiInfo.SECURITY_TYPE_OPEN;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            //working...
            if (HookWifiNetworkSpecifier) {
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
                            lpparam.classLoader,
                            "getBand",
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
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
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
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
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
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
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
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return wifiNetworkSpecifierStr;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
                            lpparam.classLoader,
                            "canBeSatisfiedBy",
                            NetworkSpecifier.class,
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
                            android.net.wifi.WifiNetworkSpecifier.class.getName(),
                            lpparam.classLoader,
                            "redact",
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
            if (HookWifiNetworkSuggestion){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return wifiNetworkSuggestionStr;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getBssid",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return HookNetClass.macAddress114514;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isCredentialSharedWithUser",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isAppInteractionRequired",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isEnhancedOpen",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isHiddenSsid",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isInitialAutojoinEnabled",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isUserInteractionRequired",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getPasspointConfig",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getPriority",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getSsid",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isUntrusted",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "isCarrierMerged",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getEnterpriseConfig",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getPassphrase",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getPriorityGroup",
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
                            android.net.wifi.WifiNetworkSuggestion.class.getName(),
                            lpparam.classLoader,
                            "getSubscriptionId",
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
            }
            if (HookWpsInfo) {
                try {
                    XposedHelpers.findAndHookMethod(
                            android.net.wifi.WpsInfo.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return wpsInfoStr;
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
