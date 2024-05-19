package com.linearity.deviceaddresstweaker.AndroidHooks.android.net.wifi;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.fakeWifiInfo;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnIntegerMAX;
import static com.linearity.utils.ReturnReplacements.returnIntegerMIN;
import static com.linearity.utils.ReturnReplacements.returnIntegerOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnLongOne;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.NetworkSpecifier;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

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
    public static String wifiNetworkSpecifierStr = "[, " +
            "SSID Match pattern=PatternMatcher{LITERAL: 114514}, " +
            "BSSID Match pattern=Pair{01:01:04:05:01:04 01:01:04:05:01:04}, " +
            "SSID=\"114514\", " +
            "BSSID=null, " +
            "band=2147483647]";
    public static boolean HookWifi = true;
    public static boolean HookWifiInfo = true;
    public static boolean HookWifiManager = false;
    public static boolean HookWifiNetworkSpecifier = true;
    public static boolean HookWifiNetworkSuggestion = true;
    public static boolean HookWpsInfo = true;
    public static boolean HookWifiEnterpriseConfig = true;
    public static boolean HookWifiConfiguration = true;
    public static boolean HookSupplicantState = true;
    public static boolean HookSoftApConfiguration = true;
    public static boolean HookScanResult = true;
    public static boolean HookWifiLocks = false;
    public static ByteBuffer emptyByteBuffer = ByteBuffer.allocate(0);

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookWifi = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifi", true);
        HookWifiInfo = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiInfo", true);
        HookWifiManager = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiManager", false);
        HookWifiNetworkSpecifier = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiNetworkSpecifier", true);
        HookWifiNetworkSuggestion = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiNetworkSuggestion", true);
        HookWpsInfo = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWpsInfo", true);
        HookWifiEnterpriseConfig = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiEnterpriseConfig", true);
        HookWifiConfiguration = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiConfiguration", true);
        HookSupplicantState = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookSupplicantState", true);
        HookSoftApConfiguration = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookSoftApConfiguration", true);
        HookScanResult = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookScanResult", true);
        HookWifiLocks = sharedPreferences.getBoolean("HookNetClass_HookWifiClass_HookWifiLocks", false);
        if (HookWifi){
            if (HookWifiInfo) {
                hookClass = XposedHelpers.findClassIfExists(android.net.wifi.WifiInfo.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getBSSID",returnRandomStr20
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSSID",returnRandomStr20
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRssi",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getWifiStandard", returnIntegerOne
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getLinkSpeed",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getTxLinkSpeedMbps",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getMaxSupportedTxLinkSpeedMbps",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRxLinkSpeedMbps",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getMaxSupportedRxLinkSpeedMbps",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getFrequency",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getMacAddress",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return "01:01:04:05:01:04";
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getPasspointFqdn", returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getPasspointProviderFriendlyName", returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSubscriptionId",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getNetworkId",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSupplicantState",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return SupplicantState.COMPLETED;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getIpAddress",returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getHiddenSSID", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getDetailedStateOf",
                                    SupplicantState.class, new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return NetworkInfo.DetailedState.CONNECTED;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return "empty";
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "describeContents",returnIntegerZero
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "equals",
                                    Object.class, returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "hashCode", returnIntegerMIN
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getApplicableRedactions",returnLongOne
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getCurrentSecurityType",returnIntegerZero
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookWifiManager){
                //addNetworkPrivileged
                try {
                    hookClass = XposedHelpers.findClassIfExists(WifiManager.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        for (Method i: hookClass.getDeclaredMethods()){
                            if (Modifier.isAbstract(i.getModifiers())){continue;}
                            if (int.class.equals(i.getReturnType())){
                                if (i.getName().equals("getWifiState")){
                                    XposedBridge.hookMethod(i, new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return WifiManager.WIFI_STATE_ENABLED;
                                        }
                                    });
                                    continue;
                                }
                                XposedBridge.hookMethod(i, returnIntegerMAX);
                            }
                            else if (boolean.class.equals(i.getReturnType())){
                                if (i.getName().contains("equal")){
                                    XposedBridge.hookMethod(i, returnFalse);continue;
                                }
                                XposedBridge.hookMethod(i, returnTrue);
                            }
                            else if (void.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, returnNull);
                            }
                            else if (List.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return new ArrayList<>();
                                    }
                                });
                            }
                            else if (WifiInfo.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return fakeWifiInfo;
                                    }
                                });
                            }
                            else if (DhcpInfo.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, returnNull);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists(WifiManager.AddNetworkResult.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        for (Method i : hookClass.getDeclaredMethods()) {
                            if (int.class.equals(i.getReturnType())) {
                                if (i.getName().equals("getWifiState")) {
                                    XposedBridge.hookMethod(i, new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return WifiManager.WIFI_STATE_ENABLED;
                                        }
                                    });
                                    continue;
                                }
                                XposedBridge.hookMethod(i, returnIntegerMAX);
                            }
                            else if (void.class.equals(i.getReturnType())) {
                                XposedBridge.hookMethod(i, returnNull);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists(WifiManager.LocalOnlyHotspotCallback.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        for (Method i: hookClass.getDeclaredMethods()){
                            if (void.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, returnNull);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists(WifiManager.LocalOnlyHotspotReservation.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        for (Method i: hookClass.getDeclaredMethods()){
                            if (void.class.equals(i.getReturnType())){
                                XposedBridge.hookMethod(i, returnNull);
                            }
                        }
                    }
                    if (HookWifiLocks){
                        hookClass = XposedHelpers.findClassIfExists(WifiManager.MulticastLock.class.getName(),lpparam.classLoader);
                        if (hookClass != null){
                            for (Method i : hookClass.getDeclaredMethods()) {
                                if (void.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnNull);
                                    continue;
                                }
                                if (boolean.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnTrue);
                                }
                                else if (String.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnRandomStr20);
                                }
                            }
                        }
                        hookClass = XposedHelpers.findClassIfExists(WifiManager.WifiLock.class.getName(),lpparam.classLoader);
                        if (hookClass != null){
                            for (Method i : hookClass.getDeclaredMethods()) {
                                if (void.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnNull);
                                }
                                else if (boolean.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnTrue);
                                }
                                if (String.class.equals(i.getReturnType())) {
                                    XposedBridge.hookMethod(i, returnRandomStr20);
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    LoggerLog(e);
                }
            }
            if (HookWifiNetworkSpecifier) {
                hookClass = XposedHelpers.findClassIfExists(android.net.wifi.WifiNetworkSpecifier.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getBand", returnIntegerMAX
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "describeContents", returnIntegerZero
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "hashCode", returnIntegerMIN
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "equals",
                                    Object.class, returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return wifiNetworkSpecifierStr;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "canBeSatisfiedBy",
                                    NetworkSpecifier.class, returnTrue
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "redact", returnNull
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookWifiNetworkSuggestion){
                hookClass = XposedHelpers.findClassIfExists(android.net.wifi.WifiNetworkSuggestion.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "describeContents",returnIntegerZero
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "hashCode", returnIntegerMIN
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "equals",
                                    Object.class, returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return wifiNetworkSuggestionStr;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getBssid",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return HookNetClass.macAddress114514;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isCredentialSharedWithUser", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isAppInteractionRequired", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isEnhancedOpen", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isHiddenSsid", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isInitialAutojoinEnabled", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isUserInteractionRequired", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getPasspointConfig", returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getPriority",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return Integer.MAX_VALUE;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getSsid",returnRandomStr20
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isUntrusted", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isCarrierMerged", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getEnterpriseConfig", returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getPassphrase", returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getPriorityGroup",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return Integer.MAX_VALUE;
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getSubscriptionId",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return Integer.MAX_VALUE;
                                        }
                                    }
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookWpsInfo) {
                hookClass =XposedHelpers.findClassIfExists(android.net.wifi.WpsInfo.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        findAndHookMethodIfExists(
                                hookClass,
                                "toString",
                                new XC_MethodReplacement(114514) {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return wpsInfoStr;
                                    }
                                }
                        );
                    }catch (Exception e){LoggerLog(e);}
                } 
            }
        }
    }

}
