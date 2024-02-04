package com.linearity.deviceaddresstweaker.AndroidHooks.android.telephony;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.SIM_STATE_UNKNOWN;
import static android.telephony.TelephonyManager.UNINITIALIZED_CARD_ID;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.telecom.PhoneAccountHandle;
<<<<<<< Updated upstream
=======
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.ClosedSubscriberGroupInfo;
import android.telephony.NetworkScan;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.UiccCardInfo;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.gsm.SmsMessage;
>>>>>>> Stashed changes

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
<<<<<<< Updated upstream
=======
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> Stashed changes

public class HookTelephonyClass {
    public static boolean HookTelephony = true;
    public static boolean HookTelephonyManager = true;
    public static boolean HookGSMCellLocation = true;
    public static boolean HookCdmaCellLocation = true;
<<<<<<< Updated upstream

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam) {
=======
    public static boolean HookExactLocation = true;
    public static SmsManager defaultSMSManager;
    public static ArrayList<String> emptyArrListStr = new ArrayList<>();
    public static ArrayList<android.telephony.SmsMessage> emptyArrListSmsMsg = new ArrayList<>();
    public static SmsMessage.SubmitPdu emptySubmitPdu = new SmsMessage.SubmitPdu();
    public static List<CellInfo> emptyArrListCellInfo = new ArrayList<CellInfo>();
    public static List<UiccCardInfo> emptyArrListUiccCardInfo = new ArrayList<UiccCardInfo>();
    public static String[] randomStrArray = new String[]{getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10)};

    public static Map<Integer, List<EmergencyNumber>> emptyEmergencyNumberList = new HashMap();
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookTelephony = sharedPreferences.getBoolean("HookTelephonyClass_HookTelephony", true);
        HookTelephonyManager = sharedPreferences.getBoolean("HookTelephonyClass_HookTelephonyManager", true);
        HookGSM = sharedPreferences.getBoolean("HookTelephonyClass_HookGSM", true);
        HookSmsManager = sharedPreferences.getBoolean("HookTelephonyClass_HookSmsManager", true);
        HookSmsMessage = sharedPreferences.getBoolean("HookTelephonyClass_HookSmsMessage", true);
        HookGSMCellLocation = sharedPreferences.getBoolean("HookTelephonyClass_HookGSMCellLocation", true);
        HookCdmaCellLocation = sharedPreferences.getBoolean("HookTelephonyClass_HookCdmaCellLocation", true);
        HookExactLocation =  sharedPreferences.getBoolean("HookTelephonyClass_HookHookExactLocation", true);

>>>>>>> Stashed changes
        if (HookTelephony){
            if (HookGSMCellLocation){//      android.telephony.gSM.GSMCellLocation.class getLac()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getLac",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getLac()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.gSM.GSMCellLocation.class getCid()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getCid",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getCid()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.gSM.GSMCellLocation.class getPsc()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getPsc",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getPsc()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookCdmaCellLocation){//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLatitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLatitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLatitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLongitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLongitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getSystemId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getSystemId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getSystemId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getNetworkId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getNetworkId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getNetworkId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
<<<<<<< Updated upstream
            if (HookTelephonyManager) {//      android.telephony.TelephonyManager.class getDeviceId()
=======
            if (HookGSM){
                if (HookGSMCellLocation) {
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.telephony.gsm.GsmCellLocation.class.getName(),
                                lpparam.classLoader,
                                "getLac",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return -1;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.telephony.gsm.GsmCellLocation.class.getName(),
                                lpparam.classLoader,
                                "getCid",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return -1;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.telephony.gsm.GsmCellLocation.class.getName(),
                                lpparam.classLoader,
                                "getPsc",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return -1;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                android.telephony.gsm.GsmCellLocation.class,
                                "setStateInvalid",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.GsmCellLocation.class,
                                "setLacAndCid",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.GsmCellLocation.class,
                                "hashCode",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.GsmCellLocation.class,
                                "equals",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.GsmCellLocation.class,
                                "toString",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "[-1,-1,-1]";
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                android.telephony.gsm.GsmCellLocation.class,
                                "equalsHandlesNulls",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.GsmCellLocation.class,
                                "fillInNotifierBundle",
                                new XC_MethodReplacement() {
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
                if (HookSmsManager){
                    if (defaultSMSManager == null){
                        try {
                            Constructor<?> SmsManagerConstructor =  XposedHelpers.findConstructorExact(
                                    android.telephony.SmsManager.class,
                                    Context.class,int.class
                            );
                            defaultSMSManager = (SmsManager) SmsManagerConstructor.newInstance(null, Integer.MIN_VALUE);
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                android.telephony.gsm.SmsManager.class.getName(),
                                lpparam.classLoader,
                                "getDefault",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return defaultSMSManager;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                android.telephony.gsm.SmsManager.class,
                                "sendTextMessage",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    emptyArrListStr.add(getRandomString(20));
                    try {
                        XposedBridge.hookAllMethods(
                                android.telephony.gsm.SmsManager.class,
                                "divideMessage",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return emptyArrListStr;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                android.telephony.gsm.SmsManager.class,
                                "sendMultipartTextMessage",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.SmsManager.class,
                                "sendDataMessage",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.SmsManager.class,
                                "copyMessageToSim",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.SmsManager.class,
                                "deleteMessageFromSim",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.SmsManager.class,
                                "updateMessageOnSim",
                                new XC_MethodReplacement() {
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
                                android.telephony.gsm.SmsManager.class,
                                "getAllMessagesFromSim",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return emptyArrListSmsMsg;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
                if (HookSmsMessage){
                    if (defaultSMSManager == null){
                        try {
                            Constructor<?> SmsManagerConstructor =  XposedHelpers.findConstructorExact(
                                    android.telephony.SmsManager.class,
                                    Context.class,int.class
                            );
                            defaultSMSManager = (SmsManager) SmsManagerConstructor.newInstance(null, Integer.MIN_VALUE);
                        } catch (Exception e) {
                            LoggerLog(e);
                        }
                    }
                    try {
                        XposedHelpers.findAndHookMethod(
                                SmsMessage.SubmitPdu.class.getName(),
                                lpparam.classLoader,
                                "toString",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "SubmitPdu: encodedScAddress = []"
                                                + ", encodedMessage = []";
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "createFromPdu",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return defaultSMSManager;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getTPLayerLengthForPDU",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "calculateLength",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getSubmitPdu",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return emptySubmitPdu;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getServiceCenterAddress",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getOriginatingAddress",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getDisplayOriginatingAddress",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getMessageBody",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getMessageClass",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return SmsMessage.MessageClass.CLASS_3;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getDisplayMessageBody",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getPseudoSubject",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getTimestampMillis",
                                new XC_MethodReplacement() {
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
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "isEmail",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getEmailBody",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getEmailFrom",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getProtocolIdentifier",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "isReplace",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "isCphsMwiMessage",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "isMWIClearMessage",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "isMWISetMessage",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "isMwiDontStore",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getUserData",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getPdu",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getStatusOnSim",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return STATUS_ON_SIM_FREE;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getStatusOnIcc",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return STATUS_ON_ICC_FREE;
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "getIndexOnSim",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getIndexOnIcc",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getStatus",
                                new XC_MethodReplacement() {
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
                        XposedBridge.hookAllMethods(
                                SmsMessage.class,
                                "isStatusReportMessage",
                                new XC_MethodReplacement() {
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
                                SmsMessage.class,
                                "getSmsFacility",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return new SmsMessage();
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookTelephonyManager){
//                [
//                CellInfoLte:{mRegistered=YES mTimeStamp=77829543326034ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=112891660 mPci=103 mTac=28976 mEarfcn=38950 mBands=[41] mBandwidth=20000 mMcc=460 mMnc=00 mAlphaLong=CMCC mAlphaShort=CMCC mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-61 rsrp=-88 rsrq=-6 rssnr=2147483647 cqiTableIndex=2147483647 cqi=2147483647 ta=2147483647 level=3 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }},
//                CellInfoLte:{mRegistered=YES mTimeStamp=36416547640ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=150238464 mPci=438 mTac=28958 mEarfcn=100 mBands=[1] mBandwidth=20000 mMcc=460 mMnc=11 mAlphaLong=CHN-CT mAlphaShort=CT mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-57 rsrp=-87 rsrq=-10 rssnr=2147483647 cqiTableIndex=2147483647 cqi=2147483647 ta=2 level=3 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }},
//                CellInfoLte:{mRegistered=NO mTimeStamp=36416547640ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=2147483647 mPci=352 mTac=2147483647 mEarfcn=100 mBands=[] mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort= mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-69 rsrp=-94 rsrq=-16 rssnr=2147483647 cqiTableIndex=2147483647 cqi=2147483647 ta=2147483647 level=3 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }},
//                CellInfoLte:{mRegistered=NO mTimeStamp=36416547640ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=2147483647 mPci=485 mTac=2147483647 mEarfcn=100 mBands=[] mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort= mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-69 rsrp=-97 rsrq=-19 rssnr=2147483647 cqiTableIndex=2147483647 cqi=2147483647 ta=2147483647 level=2 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}]
                Constructor<?> CellInfoLteConstructor =
                        XposedHelpers.findConstructorExact(
                                android.telephony.CellInfoLte.class
                        );
                Constructor<?> CellSignalStrengthLteConstructor = XposedHelpers.findConstructorExact(
                        android.telephony.CellSignalStrengthLte.class,
                        int.class,int.class,int.class,int.class,
                        int.class,int.class,int.class
                );
                Constructor<?> CellIdentityLteConstructor = XposedHelpers.findConstructorExact(
                        android.telephony.CellIdentityLte.class,
                        int.class,int.class,int.class,
                        int.class,int[].class,int.class,
                        String.class,String.class,String.class,String.class,
                        Collection.class, ClosedSubscriberGroupInfo.class
                );
                try{
                    CellInfoLte cellInfoLte1;
                    {
                        cellInfoLte1 = (CellInfoLte) CellInfoLteConstructor.newInstance();
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellSignalStrengthLte",
                                CellSignalStrengthLteConstructor.newInstance(
                                        random.nextInt(62) - 113,
                                        random.nextInt(97) - 140,
                                        random.nextInt(37) - 34,
                                        random.nextInt(50) - 20,
                                        random.nextInt(5) + 1,
                                        random.nextInt(15),
                                        random.nextInt(1282)
                                )
                        );
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellIdentityLte",
                                CellIdentityLteConstructor.newInstance(
                                        random.nextInt(268435455),
                                        random.nextInt(503),
                                        random.nextInt(65535),
                                        random.nextInt(262143),
                                        new int[]{41},
                                        20000,
                                        "460",
                                        "11",
                                        "CMCC",
                                        "CMCC",
                                        new ArrayList<String>(),
                                        null
                                )
                        );
                        XposedHelpers.setBooleanField(
                                cellInfoLte1,
                                "mRegistered",
                                true
                        );
                        XposedHelpers.setLongField(
                                cellInfoLte1,
                                "mTimeStamp",
                                77829543326034L
                        );
                        emptyArrListCellInfo.add(cellInfoLte1);
                    }
                    {
                        cellInfoLte1 = (CellInfoLte) CellInfoLteConstructor.newInstance();
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellSignalStrengthLte",
                                CellSignalStrengthLteConstructor.newInstance(
                                        random.nextInt(62) - 113,
                                        random.nextInt(97) - 140,
                                        random.nextInt(37) - 34,
                                        random.nextInt(50) - 20,
                                        random.nextInt(5) + 1,
                                        random.nextInt(15),
                                        random.nextInt(1282)
                                )
                        );
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellIdentityLte",
                                CellIdentityLteConstructor.newInstance(
                                        random.nextInt(268435455),
                                        random.nextInt(503),
                                        random.nextInt(65535),
                                        random.nextInt(262143),
                                        new int[]{41},
                                        20000,
                                        "460",
                                        "11",
                                        "CMCC",
                                        "CMCC",
                                        new ArrayList<String>(),
                                        null
                                )
                        );
                        XposedHelpers.setBooleanField(
                                cellInfoLte1,
                                "mRegistered",
                                true
                        );
                        XposedHelpers.setLongField(
                                cellInfoLte1,
                                "mTimeStamp",
                                77829543326034L
                        );
                        emptyArrListCellInfo.add(cellInfoLte1);
                    }
                    {
                        cellInfoLte1 = (CellInfoLte) CellInfoLteConstructor.newInstance();
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellSignalStrengthLte",
                                CellSignalStrengthLteConstructor.newInstance(
                                        random.nextInt(62) - 113,
                                        random.nextInt(97) - 140,
                                        random.nextInt(37) - 34,
                                        random.nextInt(50) - 20,
                                        random.nextInt(5) + 1,
                                        random.nextInt(15),
                                        random.nextInt(1282)
                                )
                        );
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellIdentityLte",
                                CellIdentityLteConstructor.newInstance(
                                        random.nextInt(268435455),
                                        random.nextInt(503),
                                        random.nextInt(65535),
                                        random.nextInt(262143),
                                        new int[]{41},
                                        20000,
                                        "460",
                                        "11",
                                        "CMCC",
                                        "CMCC",
                                        new ArrayList<String>(),
                                        null
                                )
                        );
                        XposedHelpers.setBooleanField(
                                cellInfoLte1,
                                "mRegistered",
                                true
                        );
                        XposedHelpers.setLongField(
                                cellInfoLte1,
                                "mTimeStamp",
                                77829543326034L
                        );
                        emptyArrListCellInfo.add(cellInfoLte1);
                    }
                    {
                        cellInfoLte1 = (CellInfoLte) CellInfoLteConstructor.newInstance();
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellSignalStrengthLte",
                                CellSignalStrengthLteConstructor.newInstance(
                                        random.nextInt(62) - 113,
                                        random.nextInt(97) - 140,
                                        random.nextInt(37) - 34,
                                        random.nextInt(50) - 20,
                                        random.nextInt(5) + 1,
                                        random.nextInt(15),
                                        random.nextInt(1282)
                                )
                        );
                        XposedHelpers.setObjectField(cellInfoLte1,
                                "mCellIdentityLte",
                                CellIdentityLteConstructor.newInstance(
                                        random.nextInt(268435455),
                                        random.nextInt(503),
                                        random.nextInt(65535),
                                        random.nextInt(262143),
                                        new int[]{41},
                                        20000,
                                        "460",
                                        "11",
                                        "CMCC",
                                        "CMCC",
                                        new ArrayList<String>(),
                                        null
                                )
                        );
                        XposedHelpers.setBooleanField(
                                cellInfoLte1,
                                "mRegistered",
                                true
                        );
                        XposedHelpers.setLongField(
                                cellInfoLte1,
                                "mTimeStamp",
                                77829543326034L
                        );
                        emptyArrListCellInfo.add(cellInfoLte1);
                    }
//                    emptyArrListCellInfo.add((CellInfo) CellInfoLteConstructor.newInstance());
                }catch (Exception e){
                    LoggerLog(e);
                }
>>>>>>> Stashed changes
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceId()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getDeviceId(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceId",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceId(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getTypeAllocationCode()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getTypeAllocationCode",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getTypeAllocationCode()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getTypeAllocationCode(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getTypeAllocationCode",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getTypeAllocationCode(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class createForSubscriptionId(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "createForSubscriptionId",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用createForSubscriptionId(int)" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class createForPhoneAccountHandle(PhoneAccountHandle)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "createForPhoneAccountHandle",
                            PhoneAccountHandle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用createForPhoneAccountHandle(PhoneAccountHandle)" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getDeviceSoftwareVersion()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceSoftwareVersion",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceSoftwareVersion()" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getImei()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getImei",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用ggetImei()" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getImei(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getImei",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getImei(int)" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getCellLocation()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getCellLocation",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getCellLocation()" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getNetworkOperatorName()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkOperatorName",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkOperatorName()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getNetworkOperator()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkOperator",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkOperator()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getNetworkSpecifier()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkSpecifier",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkSpecifier()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getNetworkCountryIso()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkCountryIso",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkCountryIso()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getNetworkCountryIso(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkCountryIso",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkCountryIso(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
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
                //      android.telephony.TelephonyManager.class getDataNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDataNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDataNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
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
                //      android.telephony.TelephonyManager.class getVoiceNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getVoiceNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getVoiceNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
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
                //      android.telephony.TelephonyManager.class hasIccCard()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "hasIccCard",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用hasIccCard()" + param.getResult());
                                    param.setResult(false);
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
                //      android.telephony.TelephonyManager.class getSimState()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimState",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //                            //LoggerLog(lpparam.packageName + "调用getSimState()" + param.getResult());
                                    param.setResult(SIM_STATE_UNKNOWN);
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
                //      android.telephony.TelephonyManager.class getSimState(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimState",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimState(int)" + param.getResult());
                                    param.setResult(SIM_STATE_UNKNOWN);
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
                //      android.telephony.TelephonyManager.class getSimOperator()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimOperator",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimOperator()" + param.getResult());
                                    param.setResult(null);
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
                //      android.telephony.TelephonyManager.class getSimOperatorName()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimOperatorName",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimOperatorName()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getSimCountryIso()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimCountryIso",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimCountryIso()" + param.getResult());
                                    param.setResult(getRandomString(20));
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
                //      android.telephony.TelephonyManager.class getCardIdForDefaultEuicc()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getCardIdForDefaultEuicc",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getCardIdForDefaultEuicc()" + param.getResult());
                                    param.setResult(UNINITIALIZED_CARD_ID);
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
                //      android.telephony.TelephonyManager.class getUiccCardsInfo()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getUiccCardsInfo",
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
<<<<<<< Updated upstream
=======
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "getGroupIdLevel1",
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
                            android.telephony.TelephonyManager.class,
                            "getLine1Number",
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
                            android.telephony.TelephonyManager.class,
                            "setLine1NumberForDisplay",
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
                            android.telephony.TelephonyManager.class,
                            "getVoiceMailNumber",
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
                            android.telephony.TelephonyManager.class,
                            "setVoiceMailNumber",
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
                            android.telephony.TelephonyManager.class,
                            "getVisualVoicemailPackageName",
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
                            android.telephony.TelephonyManager.class,
                            "setVisualVoicemailSmsFilterSettings",
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
                            android.telephony.TelephonyManager.class,
                            "sendVisualVoicemailSms",
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
                            android.telephony.TelephonyManager.class,
                            "getVoiceMailAlphaTag",
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
                            android.telephony.TelephonyManager.class,
                            "sendDialerSpecialCode",
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
                            android.telephony.TelephonyManager.class,
                            "getCallState",
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
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "getCallStateForSubscription",
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
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "getDataActivity",
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
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "getDataState",
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
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
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
                            android.telephony.TelephonyManager.class,
                            "isVoiceCapable",
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
                            android.telephony.TelephonyManager.class,
                            "isSmsCapable",
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
                if (HookExactLocation)
                {try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "getAllCellInfo",
//                            new XC_MethodHook() {
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.afterHookedMethod(param);
//                                    LoggerLog(param.getResult());
//                                }
//                            }
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyArrListCellInfo;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }}

                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
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
                            android.telephony.TelephonyManager.class,
                            "getMmsUserAgent",
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
                            android.telephony.TelephonyManager.class,
                            "getMmsUAProfUrl",
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
                            android.telephony.TelephonyManager.class,
                            "iccOpenLogicalChannel",
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
                            android.telephony.TelephonyManager.class,
                            "iccCloseLogicalChannel",
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
                            android.telephony.TelephonyManager.class,
                            "iccTransmitApduLogicalChannel",
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
                            android.telephony.TelephonyManager.class,
                            "iccTransmitApduBasicChannel",
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
                            android.telephony.TelephonyManager.class,
                            "iccExchangeSimIO",
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
                            android.telephony.TelephonyManager.class,
                            "sendEnvelopeWithStatus",
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
                            android.telephony.TelephonyManager.class,
                            "getSubscriptionId",
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
                            android.telephony.TelephonyManager.class,
                            "getSubId",
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
                            android.telephony.TelephonyManager.class,
                            "getPhoneId",
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
                            android.telephony.TelephonyManager.class,
                            "getSlotIndex",
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
                            android.telephony.TelephonyManager.class,
                            "getIccAuthentication",
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
                            android.telephony.TelephonyManager.class,
                            "getForbiddenPlmns",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return randomStrArray;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "setNetworkSelectionModeAutomatic",
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
                            android.telephony.TelephonyManager.class,
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
                            android.telephony.TelephonyManager.class,
                            "setNetworkSelectionModeManual",
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
                            android.telephony.TelephonyManager.class,
                            "getNetworkSelectionMode",
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
                            android.telephony.TelephonyManager.class,
                            "getManualNetworkSelectionPlmn",
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
                            android.telephony.TelephonyManager.class,
                            "setPreferredNetworkTypeToGlobal",
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
                            android.telephony.TelephonyManager.class,
                            "hasCarrierPrivileges",
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
                            android.telephony.TelephonyManager.class,
                            "setOperatorBrandOverride",
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
                            android.telephony.TelephonyManager.class,
                            "setCallComposerStatus",
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
                            android.telephony.TelephonyManager.class,
                            "getCallComposerStatus",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return CALL_COMPOSER_STATUS_ON;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "sendUssdRequest",
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
                            android.telephony.TelephonyManager.class,
                            "isConcurrentVoiceAndDataSupported",
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
                            android.telephony.TelephonyManager.class,
                            "getMobileProvisioningUrl",
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
                            android.telephony.TelephonyManager.class,
                            "setDataEnabled",
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
                            android.telephony.TelephonyManager.class,
                            "isDataEnabled",
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
                            android.telephony.TelephonyManager.class,
                            "isDataRoamingEnabled",
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
                            android.telephony.TelephonyManager.class,
                            "canChangeDtmfToneLength",
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
                            android.telephony.TelephonyManager.class,
                            "isWorldPhone",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;//爱 国 手 机 ,不 是 华 为 我 不 用 :<
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "isTtyModeSupported",
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
                            android.telephony.TelephonyManager.class,
                            "isRttSupported",
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
                            android.telephony.TelephonyManager.class,
                            "isHearingAidCompatibilitySupported",
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
                            android.telephony.TelephonyManager.class,
                            "setSimPowerState",
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
                            android.telephony.TelephonyManager.class,
                            "setSimPowerStateForSlot",
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
                            android.telephony.TelephonyManager.class,
                            "getPhoneAccountHandle",
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
                            android.telephony.TelephonyManager.class,
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
                            android.telephony.TelephonyManager.class,
                            "getVoicemailRingtoneUri",
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
                            android.telephony.TelephonyManager.class,
                            "setVoicemailRingtoneUri",
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
                            android.telephony.TelephonyManager.class,
                            "isVoicemailVibrationEnabled",
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
                            android.telephony.TelephonyManager.class,
                            "setVoicemailVibrationEnabled",
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
                            android.telephony.TelephonyManager.class,
                            "getSimCarrierId",
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
                            android.telephony.TelephonyManager.class,
                            "getSimCarrierIdName",
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
                            android.telephony.TelephonyManager.class,
                            "getSimSpecificCarrierId",
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
                            android.telephony.TelephonyManager.class,
                            "getSimSpecificCarrierIdName",
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
                            android.telephony.TelephonyManager.class,
                            "getCarrierIdFromSimMccMnc",
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
                            android.telephony.TelephonyManager.class,
                            "setDataEnabledForReason",
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
                            android.telephony.TelephonyManager.class,
                            "isDataEnabledForReason",
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
                            android.telephony.TelephonyManager.class,
                            "isManualNetworkSelectionAllowed",
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
                            android.telephony.TelephonyManager.class,
                            "setAlwaysReportSignalStrength",
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
                            android.telephony.TelephonyManager.class,
                            "getSignalStrength",
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
                            android.telephony.TelephonyManager.class,
                            "isDataConnectionAllowed",
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
                            android.telephony.TelephonyManager.class,
                            "isDataCapable",
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
                            android.telephony.TelephonyManager.class,
                            "setCarrierTestOverride",
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
                            android.telephony.TelephonyManager.class,
                            "getEmergencyNumberList",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyEmergencyNumberList;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "isEmergencyNumber",
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
                            android.telephony.TelephonyManager.class,
                            "setPreferredOpportunisticDataSubscription",
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
                            android.telephony.TelephonyManager.class,
                            "getPreferredOpportunisticDataSubscription",
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
                            android.telephony.TelephonyManager.class,
                            "updateAvailableNetworks",
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
                            android.telephony.TelephonyManager.class,
                            "isModemEnabledForSlot",
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
                            android.telephony.TelephonyManager.class,
                            "isMultiSimSupported",
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
                            android.telephony.TelephonyManager.class,
                            "switchMultiSimConfig",
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
                            android.telephony.TelephonyManager.class,
                            "doesSwitchMultiSimConfigTriggerReboot",
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
                            android.telephony.TelephonyManager.class,
                            "getEquivalentHomePlmns",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return emptyArrListStr;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.telephony.TelephonyManager.class,
                            "isRadioInterfaceCapabilitySupported",
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
                            android.telephony.TelephonyManager.class,
                            "registerTelephonyCallback",
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
                            android.telephony.TelephonyManager.class,
                            "unregisterTelephonyCallback",
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
                            android.telephony.TelephonyManager.class,
                            "setSignalStrengthUpdateRequest",
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
                            android.telephony.TelephonyManager.class,
                            "clearSignalStrengthUpdateRequest",
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
                            android.telephony.TelephonyManager.class,
                            "getNetworkSlicingConfiguration",
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

>>>>>>> Stashed changes
            }
        }
    }
}
