package com.linearity.deviceaddresstweaker.AndroidHooks.android.telephony;

import static android.telephony.SmsManager.STATUS_ON_ICC_FREE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.SIM_STATE_UNKNOWN;
import static android.telephony.TelephonyManager.UNINITIALIZED_CARD_ID;
import static android.telephony.gsm.SmsManager.STATUS_ON_SIM_FREE;

import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.getRandomString;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.ClosedSubscriberGroupInfo;
import android.telephony.NetworkScan;
import android.telephony.SmsManager;
import android.telephony.UiccCardInfo;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.gsm.SmsMessage;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.ReturnReplacements.returnByteArr114514;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnIntegerMAX;
import static com.linearity.utils.ReturnReplacements.returnIntegerMIN;
import static com.linearity.utils.ReturnReplacements.returnIntegerNegativeOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.random;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HookTelephonyClass {
    public static boolean HookTelephony = true;
    public static boolean HookTelephonyManager = true;
    public static boolean HookGSMCellLocation = true;
    public static boolean HookCdmaCellLocation = true;

    public static boolean HookExactLocation = true;
    public static SmsManager defaultSMSManager;
    public static ArrayList<String> emptyArrListStr = new ArrayList<>();
    public static ArrayList<android.telephony.SmsMessage> emptyArrListSmsMsg = new ArrayList<>();
    public static SmsMessage.SubmitPdu emptySubmitPdu = new SmsMessage.SubmitPdu();
    public static List<CellInfo> emptyArrListCellInfo = new ArrayList<CellInfo>();
    public static List<UiccCardInfo> emptyArrListUiccCardInfo = new ArrayList<UiccCardInfo>();
    public static boolean HookGSM = false;
    public static boolean HookSmsManager = false;
    public static boolean HookSmsMessage = false;
    public static String[] randomStrArray = new String[]{getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10),getRandomString(10)};

    public static Map<Integer, List<EmergencyNumber>> emptyEmergencyNumberList = new HashMap();
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookTelephony = sharedPreferences.getBoolean("HookTelephonyClass_HookTelephony", true);
        HookTelephonyManager = sharedPreferences.getBoolean("HookTelephonyClass_HookTelephonyManager", true);
        HookGSM = sharedPreferences.getBoolean("HookTelephonyClass_HookGSM", true);
        HookSmsManager = sharedPreferences.getBoolean("HookTelephonyClass_HookSmsManager", true);
        HookSmsMessage = sharedPreferences.getBoolean("HookTelephonyClass_HookSmsMessage", true);
        HookGSMCellLocation = sharedPreferences.getBoolean("HookTelephonyClass_HookGSMCellLocation", true);
        HookCdmaCellLocation = sharedPreferences.getBoolean("HookTelephonyClass_HookCdmaCellLocation", true);
        HookExactLocation =  sharedPreferences.getBoolean("HookTelephonyClass_HookHookExactLocation", true);

        if (HookTelephony){
            if (HookGSMCellLocation){
                hookClass = XposedHelpers.findClassIfExists(
                        android.telephony.gsm.GsmCellLocation.class.getName(),
                        lpparam.classLoader);
                if (hookClass != null){
                    try {//      android.telephony.gSM.GSMCellLocation.class getLac()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getLac", returnIntegerNegativeOne
                            );
                        }
//      android.telephony.gSM.GSMCellLocation.class getCid()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getCid", returnIntegerNegativeOne
                            );
                        }
//      android.telephony.gSM.GSMCellLocation.class getPsc()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getPsc", returnIntegerNegativeOne
                            );
                        }

                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "setStateInvalid",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "setLacAndCid",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "hashCode",returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "equals",returnFalse
                            );
                        }
                        {
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
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "equalsHandlesNulls",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.telephony.gsm.GsmCellLocation.class,
                                    "fillInNotifierBundle",returnIntegerMIN
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookCdmaCellLocation){
                hookClass = XposedHelpers.findClassIfExists(
                        android.telephony.cdma.CdmaCellLocation.class.getName(),
                        lpparam.classLoader);

                if (hookClass != null){
                    try
                    {
                        {//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getBaseStationId", returnIntegerNegativeOne
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLatitude()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getBaseStationLatitude", returnIntegerMAX
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getBaseStationLongitude", returnIntegerMAX
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getBaseStationLongitude", returnIntegerMAX
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getBaseStationId", returnIntegerNegativeOne
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getSystemId()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getSystemId", returnIntegerNegativeOne
                                );
                            }
//      android.telephony.cdma.CdmaCellLocation.class getNetworkId()
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getNetworkId", returnIntegerNegativeOne
                                );
                            }
                        }
                    }
                    catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookGSM){
                if (HookSmsManager){
                    if (defaultSMSManager == null){
                         {
                            Constructor<?> SmsManagerConstructor =  XposedHelpers.findConstructorExact(
                                    android.telephony.SmsManager.class,
                                    Context.class,int.class
                            );
                            try {
                                defaultSMSManager = (SmsManager) SmsManagerConstructor.newInstance(null, Integer.MIN_VALUE);
                            }catch (Exception e){
                                LoggerLog(e);
                            }
                        }
                    }
                    hookClass = XposedHelpers.findClassIfExists(
                            android.telephony.gsm.SmsManager.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){
                        emptyArrListStr.add(getRandomString(20));
                        findAndHookMethodIfExists(hookClass,
                                "getDefault",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return defaultSMSManager;
                                    }
                                }
                        );
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "sendTextMessage",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "divideMessage",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return emptyArrListStr;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "sendMultipartTextMessage",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "sendDataMessage",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "copyMessageToSim",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return true;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "deleteMessageFromSim",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return true;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "updateMessageOnSim",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return true;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getAllMessagesFromSim",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return emptyArrListSmsMsg;
                                        }
                                    }
                            );
                        }
                    }

                }
                if (HookSmsMessage){
                     if ((hookClass = XposedHelpers.findClassIfExists(
                             SmsMessage.SubmitPdu.class.getName(),
                             lpparam.classLoader))!=null){
                        findAndHookMethodIfExists(hookClass,
                                "toString",
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "SubmitPdu: encodedScAddress = []"
                                                + ", encodedMessage = []";
                                    }
                                }
                        );
                    }
                    if ((hookClass = XposedHelpers.findClassIfExists(SmsMessage.class.getName(),lpparam.classLoader)) != null) {
                        try {
                            {
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
                            }
                            {
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
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        SmsMessage.class,
                                        "calculateLength",
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
                                            }
                                        }
                                );
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        SmsMessage.class,
                                        "getUserData", returnByteArr114514
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        SmsMessage.class,
                                        "getPdu", returnByteArr114514
                                );
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                        }catch (Exception e){LoggerLog(e);}
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
                 hookClass = XposedHelpers.findClassIfExists(
                         android.telephony.TelephonyManager.class.getName(),
                         lpparam.classLoader);
                if (hookClass != null){
                    try{
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getDeviceId(int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getTypeAllocationCode()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getTypeAllocationCode(int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class createForSubscriptionId(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "createForSubscriptionId",
                                    int.class,returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class createForPhoneAccountHandle(PhoneAccountHandle)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "createForPhoneAccountHandle",
                                    PhoneAccountHandle.class,returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class getDeviceSoftwareVersion()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getDeviceSoftwareVersion",returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class getImei()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getImei",returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class getImei(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getImei",
                                    int.class,returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class getCellLocation()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getCellLocation",returnNull
                            );
                        }
                        //      android.telephony.TelephonyManager.class getNetworkOperatorName()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getNetworkOperatorName",returnRandomStr20
                            );
                        }
                        //      android.telephony.TelephonyManager.class getNetworkOperator()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getNetworkOperator",returnRandomStr20
                            );
                        }
                        //      android.telephony.TelephonyManager.class getNetworkSpecifier()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getNetworkCounIso()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getNetworkCountryIso",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) {
                                            //LoggerLog(lpparam.packageName + "调用getNetworkCounIso()" + param.getResult());
                                            param.setResult(getRandomString(20));
                                        }

                                        //                        @Override
                                        //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        //                            //LoggerLog(getMethodStack());
                                        //                            super.afterHookedMethod(param);
                                        //                        }
                                    }
                            );
                        }
                        //      android.telephony.TelephonyManager.class getNetworkCounIso(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getNetworkCountryIso",
                                    int.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) {
                                            //LoggerLog(lpparam.packageName + "调用getNetworkCounIso(int)" + param.getResult());
                                            param.setResult(getRandomString(20));
                                        }

                                        //                        @Override
                                        //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        //                            //LoggerLog(getMethodStack());
                                        //                            super.afterHookedMethod(param);
                                        //                        }
                                    }
                            );
                        }
                        //      android.telephony.TelephonyManager.class getNetworkType()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getDataNetworkType()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getVoiceNetworkType()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class hasIccCard()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getSimState()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getSimState(int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getSimOperator()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getSimOperatorName()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getSimCounIso()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSimCountryIso",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) {
                                            //LoggerLog(lpparam.packageName + "调用getSimCounIso()" + param.getResult());
                                            param.setResult(getRandomString(20));
                                        }

                                        //                        @Override
                                        //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        //                            //LoggerLog(getMethodStack());
                                        //                            super.afterHookedMethod(param);
                                        //                        }
                                    }
                            );
                        }
                        //      android.telephony.TelephonyManager.class getCardIdForDefaultEuicc()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
                        //      android.telephony.TelephonyManager.class getUiccCardsInfo()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getUiccCardsInfo", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getGroupIdLevel1", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getLine1Number", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setLine1NumberForDisplay", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getVoiceMailNumber", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setVoiceMailNumber", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getVisualVoicemailPackageName", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setVisualVoicemailSmsFilterSettings", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "sendVisualVoicemailSms", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getVoiceMailAlphaTag", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "sendDialerSpecialCode", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getCallState",returnIntegerZero
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getCallStateForSubscription",returnIntegerZero
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getDataActivity",returnIntegerZero
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getDataState",returnIntegerZero
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "listen", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isVoiceCapable", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isSmsCapable", returnTrue
                            );
                        }
                        if (HookExactLocation) {
                            {
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
                            }
                        }

                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "requestCellInfoUpdate", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getMmsUserAgent",returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getMmsUAProfUrl",returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "iccOpenLogicalChannel", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "iccCloseLogicalChannel", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "iccTransmitApduLogicalChannel", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "iccTransmitApduBasicChannel", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "iccExchangeSimIO", returnByteArr114514
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "sendEnvelopeWithStatus", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSubscriptionId", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSubId", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getPhoneId", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSlotIndex", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getIccAuthentication", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getForbiddenPlmns",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return randomStrArray;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setNetworkSelectionModeAutomatic", returnNull
                            );
                        }
                        {
                            NetworkScan networkScan = (NetworkScan) XposedHelpers.findConstructorExact(
                                    "android.telephony.NetworkScan",
                                    lpparam.classLoader,
                                    int.class, int.class
                            ).newInstance(Integer.MAX_VALUE, Integer.MAX_VALUE);
                            XposedBridge.hookAllMethods(hookClass,
                                    "requestNetworkScan",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return networkScan;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setNetworkSelectionModeManual", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getNetworkSelectionMode", returnIntegerOne
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getManualNetworkSelectionPlmn", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setPreferredNetworkTypeToGlobal", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "hasCarrierPrivileges", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setOperatorBrandOverride", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setCallComposerStatus", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getCallComposerStatus", returnIntegerOne
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "sendUssdRequest", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isConcurrentVoiceAndDataSupported", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getMobileProvisioningUrl", returnRandomStr20
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setDataEnabled", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isDataEnabled", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isDataRoamingEnabled", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "canChangeDtmfToneLength", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isWorldPhone", returnFalse//爱 国 手 机 ,不 是 华 为 我 不 用 :<
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isTtyModeSupported", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isRttSupported", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isHearingAidCompatibilitySupported", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setSimPowerState", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setSimPowerStateForSlot", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getPhoneAccountHandle", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getServiceState", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getVoicemailRingtoneUri", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setVoicemailRingtoneUri", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isVoicemailVibrationEnabled", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setVoicemailVibrationEnabled", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSimCarrierId", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSimCarrierIdName", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSimSpecificCarrierId", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSimSpecificCarrierIdName", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getCarrierIdFromSimMccMnc", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setDataEnabledForReason", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isDataEnabledForReason", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isManualNetworkSelectionAllowed", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setAlwaysReportSignalStrength", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getSignalStrength", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isDataConnectionAllowed", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isDataCapable", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setCarrierTestOverride", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getEmergencyNumberList",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return emptyEmergencyNumberList;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isEmergencyNumber", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setPreferredOpportunisticDataSubscription", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getPreferredOpportunisticDataSubscription", returnIntegerMIN
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "updateAvailableNetworks", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isModemEnabledForSlot", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isMultiSimSupported", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "switchMultiSimConfig", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "doesSwitchMultiSimConfigTriggerReboot", returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getEquivalentHomePlmns",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return emptyArrListStr;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "isRadioInterfaceCapabilitySupported", returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "registerTelephonyCallback", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "unregisterTelephonyCallback", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "setSignalStrengthUpdateRequest", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "clearSignalStrengthUpdateRequest", returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getNetworkSlicingConfiguration", returnNull
                            );
                        }
                    }catch (Exception e){
                        LoggerLog(e);
                    }
                }
            }
        }
    }
}
