package com.linearity.deviceaddresstweaker.AndroidHooks.android.telecom;

import static com.linearity.utils.HookUtils.disableClass_random;

import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTelecomClass {
    public static boolean HookTelecom = true;
    public static final String[] toHook = new String[]{
            "android.telecom.package-info", "android.telecom.Voicemail",
            "android.telecom.VideoProfile", "android.telecom.VideoCallbackServant",
            "android.telecom.VideoCallImpl", "android.telecom.TimedEvent",
            "android.telecom.TelecomManager", "android.telecom.TelecomAnalytics",
            "android.telecom.StatusHints", "android.telecom.Response",
            "android.telecom.RemoteConnectionService", "android.telecom.RemoteConnectionManager",
            "android.telecom.RemoteConnection", "android.telecom.RemoteConference",
            "android.telecom.PhoneAccountSuggestionService",
            "android.telecom.PhoneAccountSuggestion", "android.telecom.PhoneAccountHandle",
            "android.telecom.Phone", "android.telecom.ParcelableRttCall",
            "android.telecom.ParcelableConnection", "android.telecom.ParcelableConference",
            "android.telecom.ParcelableCallAnalytics", "android.telecom.ParcelableCall",
            "android.telecom.Logging.TimedEvent", "android.telecom.Logging.SessionManager",
            "android.telecom.Logging.Session", "android.telecom.Logging.Runnable",
            "android.telecom.Logging.EventManager", "android.telecom.Log",
            "android.telecom.InCallService", "android.telecom.InCallAdapter",
            "android.telecom.GatewayInfo", "android.telecom.DisconnectCause",
            "android.telecom.DefaultDialerManager", "android.telecom.ConnectionServiceAdapterServant",
            "android.telecom.ConnectionServiceAdapter", "android.telecom.ConnectionService",
            "android.telecom.ConnectionRequest", "android.telecom.Connection",
            "android.telecom.Conferenceable", "android.telecom.Conference",
            "android.telecom.CallerInfoAsyncQuery", "android.telecom.CallerInfo",
            "android.telecom.CallbackRecord", "android.telecom.CallScreeningService",
            "android.telecom.CallRedirectionService", "android.telecom.CallDiagnostics",
            "android.telecom.CallDiagnosticService", "android.telecom.CallAudioState",
            "android.telecom.Call", "android.telecom.BluetoothCallQualityReport",
            "android.telecom.AuthenticatorService", "android.telecom.AudioState",
            "android.telecom.PhoneAccount"
    };

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookTelecom = sharedPreferences.getBoolean("HookTelecom",true);

        Class<?> hookClass;
        if (HookTelecom){
            for (String s:toHook){
                hookClass = XposedHelpers.findClassIfExists(s,lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                }
            }
        }
    }
}
