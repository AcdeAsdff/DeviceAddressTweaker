package com.linearity.deviceaddresstweaker;

import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.location.Location;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.accounts.HookAccountClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.app.HookAppClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.bluetooth.HookBluetoothClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.HookContentClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.HookHardwareClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.location.HookLocationClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.os.HookOsClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.provider.HookProviderClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.telephony.HookTelephonyClass;
import com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO;
import com.linearity.deviceaddresstweaker.JavaHooks.java.lang.HookLang;
import com.linearity.deviceaddresstweaker.JavaHooks.java.net.HookJavaNetClass;
import com.linearity.deviceaddresstweaker.TIM.HookTIMClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class DeviceAddressTweaker implements IXposedHookLoadPackage {
    public static boolean useLogger = true;

    public static Bundle EmptyBundle = new Bundle();
    public static Parcelable.Creator<AccountAuthenticatorResponse> CREATOR_AccountAuthenticatorResponse =  new Parcelable.Creator<AccountAuthenticatorResponse>() {
        public AccountAuthenticatorResponse createFromParcel(Parcel source) {
            return new AccountAuthenticatorResponse(source);
        }

        public AccountAuthenticatorResponse[] newArray(int size) {
            return EmptyAccountAuthenticatorResponse;
        }
    };
    public static WifiInfo fakeWifiInfo =
            new WifiInfo.Builder()
                    .setBssid(getRandomString(20))
                    .setSsid(byteArray114514)
                    .setCurrentSecurityType(WifiConfiguration.SECURITY_TYPE_OPEN)
                    .build();
    public static Location fakeLocation = new Location("jerk");
    public static AccountAuthenticatorResponse[] EmptyAccountAuthenticatorResponse = new AccountAuthenticatorResponse[0];
    public static Intent EmptyIntent = new Intent();
    public static Map<String, Integer> EmptyMap_String_Integer = new HashMap<>();
    public static Map<String, Object> EmptyMap_String_Object = new HashMap<>();
    public static Map<Account, Integer> EmptyMap_Account_Integer = new HashMap<>();
    public static Future2Task<Account[]> EmptyFuture2TaskOfAccountArray = new Future2Task<Account[]>() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return true;
        }

        @Override
        public boolean isCancelled() {
            return true;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Account[] getResult() throws AuthenticatorException, IOException, OperationCanceledException {
            return EmptyAccountArray;
        }

        @Override
        public Account[] getResult(long timeout, TimeUnit unit) throws AuthenticatorException, IOException, OperationCanceledException {
            return EmptyAccountArray;
        }
    };
    public static Future2Task<Boolean> EmptyFuture2TaskOBoolean = new Future2Task<Boolean>() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return true;
        }

        @Override
        public boolean isCancelled() {
            return true;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Boolean getResult() throws AuthenticatorException, IOException, OperationCanceledException {
            return true;
        }

        @Override
        public Boolean getResult(long timeout, TimeUnit unit) throws AuthenticatorException, IOException, OperationCanceledException {
            return true;
        }
    };
    public static Future2Task<Bundle> EmptyFuture2TaskOfBundle = new Future2Task<Bundle>() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return true;
        }

        @Override
        public boolean isCancelled() {
            return true;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Bundle getResult() throws AuthenticatorException, IOException, OperationCanceledException {
            return EmptyBundle;
        }

        @Override
        public Bundle getResult(long timeout, TimeUnit unit) throws AuthenticatorException, IOException, OperationCanceledException {
            return EmptyBundle;
        }
    };
    public static AuthenticatorDescription[] EmptyAuthenticatorDescriptionArray = new AuthenticatorDescription[0];
    public static Account[] EmptyAccountArray = new Account[0];
    public static UUID uuid = UUID.randomUUID();
    public static Random random = new Random();


    public static final  Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        public Account createFromParcel(Parcel source) {
//            return new Account(source);
            return null;
        }

        public Account[] newArray(int size) {
//            return new Account[size];
            return null;
        }
    };
    public static Parcelable.Creator<AuthenticatorDescription> CREATOR_AuthenticatorDescription = new Parcelable.Creator<>() {
        public AuthenticatorDescription createFromParcel(Parcel source) {
//            return new Account(source);
            return null;
        }

        public AuthenticatorDescription[] newArray(int size) {
//            return new Account[size];
            return null;
        }
    };
    //a looooooooooong way 2 go
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (lpparam == null) {
            return;
        }

        LoggerLog("[linearity]Load app packageName:" + lpparam.packageName);
//java
        HookJavaNetClass.DoHook(lpparam);//Working......
        HookLang.DoHook(lpparam);//not finished
        HookIO.DoHook(lpparam);//not finished
//android
        //accessibilityservice
        HookAccountClass.DoHook(lpparam);//DONE(API:32)
        //adservices(Not yet)
        //animation
        //annotation(NO)
        HookAppClass.DoHook(lpparam);//not finished
        //appwidget
        HookBluetoothClass.DoHook(lpparam);//not finished
        //companion
        HookContentClass.DoHook(lpparam);//not finished
        //database
        //drm
        //gesture
        //graphics
        HookHardwareClass.DoHook(lpparam);//not finished
        //icu
        //inputmethodservice
        HookLocationClass.DoHook(lpparam);//not finished
        //media
        //mtp
        HookNetClass.DoHook(lpparam);//not finished
        //nfc
        //opengl
        HookOsClass.DoHook(lpparam);//not finished
        //preference
        //print
        //printservice
        HookProviderClass.DoHook(lpparam);//not finished
        //renderscript
        //sax
        //se.omapi
        //security
        //service
        //speech
        //system
        //telecom
        HookTelephonyClass.DoHook(lpparam);//not finished
        //text
        //transition
        //util
        //view
        //webkit
        //widget
        //window


        //wechat
        try{
            if (lpparam.packageName.contains("tencent.mm")) {
                Class<?> dumpClass = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.h0", lpparam.classLoader);
//                LoggerLog(Arrays.toString(dumpClass.getMethods()));
//                    Class<?> fclass2 = XposedHelpers.findClass("com.tencent.mm.pluginsdk.model.app.g", lpparam.classLoader);
                    XposedBridge.hookAllMethods(dumpClass, "a",
//                            Context.class, fclass2, String.class, boolean.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    LoggerLog("[linearity]BeforeSignHook: " + param.args[0] + " " + param.args[1] + " " + param.args[2] + " ");
                                    param.setResult(true);
                                }
                            });

//                if (false) {
//                    LoggerLog( "[linearity]HookToastReady");
//                    XposedHelpers.findAndHookMethod(XposedHelpers.findClass("android.widget.Toast", lpparam.classLoader), "makeText", Context.class, CharSequence.class, int.class, new XC_MethodHook(114514) {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            LoggerLog("[linearity]BeforeToastHook: " + param.args[0] + " " + param.args[1] + " " + param.args[2] + " ");
//                            super.beforeHookedMethod(param);
//                        }
//
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            LoggerLog("[linearity]AfterToastHook: " + param.getResult());
//                            LoggerLog("Dump Stack: " + "---------------start----------------");
//                            Throwable ex = new Throwable();
//                            StackTraceElement[] stackElements = ex.getStackTrace();
//                            if (stackElements != null) {
//                                for (int i = 0; i < stackElements.length; i++) {
//
//                                    LoggerLog("Dump Stack" + i + ": " + stackElements[i].getClassName()
//                                            + "----" + stackElements[i].getFileName()
//                                            + "----" + stackElements[i].getLineNumber()
//                                            + "----" + stackElements[i].getMethodName());
//                                }
//                            }
//                            LoggerLog("Dump Stack: " + "---------------over----------------");
//                            Context context = ((Application) param.args[0]).getApplicationContext();
////                            Toast.makeText(context, "FinishHookToast", Toast.LENGTH_LONG).show();
//                            super.afterHookedMethod(param);
//                        }
//                    });
////                    LoggerLog("[linearity]HookSignReady");
////
//
//                }
            }
        }catch (Exception e){
            LoggerLog(e);
        }

        HookTIMClass.DoHook(lpparam);

    }

    //empty,implements AccountManagerFuture
    public static abstract class Future2Task<T>
     implements AccountManagerFuture<T> {
        Account account;
        public Future2Task() {
        }
        public Future2Task(Account account) {
            this.account = account;
        }
    }

    public static String getRandomString(int length){
        random = new Random();
        int minLength = length/2;
        int exactLength = random.nextInt(length - minLength) + minLength + 1;
        String str="abpqmnoEFGHIJrstRSTUVWujkl56YKLX234ZvwxyzABCDcdefghi01MNOPQ789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void LoggerLog(Object log){
        if (log != null){
            LoggerLog("[linearity]", log.toString());
        }else {LoggerLog("[linearity]", "null");}
    }
    public static void LoggerLog(String log){
        LoggerLog("[linearity]", log);
    }
    public static void LoggerLog(Exception e){
        LoggerLog("[linearity]", e);
    }
    public static void LoggerLog(String prefix, String log){
        if (useLogger){
            XposedBridge.log(prefix + log);//not best?
        }
    }
    public static void LoggerLog(String prefix, Exception e){
        if (useLogger){
            XposedBridge.log(prefix + e);//not best?
        }
    }
}


