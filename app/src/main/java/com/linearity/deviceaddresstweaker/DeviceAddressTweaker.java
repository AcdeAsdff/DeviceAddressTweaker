package com.linearity.deviceaddresstweaker;

import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.app.AndroidAppHelper;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import com.linearity.deviceaddresstweaker.Wechat.HookWechatClass;
import com.linearity.deviceaddresstweaker.bilibili.HookBilibiliClass;
import com.linearity.deviceaddresstweaker.chaoxing.HookChaoxingClass;
import com.topjohnwu.superuser.Shell;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import android.content.SharedPreferences;

import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class DeviceAddressTweaker implements IXposedHookLoadPackage, IXposedHookInitPackageResources, IXposedHookZygoteInit {
    public Map<String, Context> ProcHead2Context = new HashMap<>();
    public Map<String, SharedPreferences> ProcHead2SP = new HashMap<>();
    public Context appContext = null;
    public String processHead;
    public String modulePath;
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
    SharedPreferences sharedPreferences;


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
    @SuppressLint({"SetWorldWritable", "SetWorldReadable"})
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws IOException, PackageManager.NameNotFoundException {
        if (lpparam == null) {
            return;
        }

        LoggerLog("[linearity]Load app packageName:" + lpparam.packageName);
        processHead = lpparam.processName.split(":")[0];
        ProcHead2Context.put(processHead, null);
//        sharedPreferences = new XSharedPreferences("com.linearity.deviceaddresstweaker",processHead + "_linearity_dat_settings");
        File spFile = new File("/data/local/tmp/linearity_dat/shared_prefs/" + processHead + "_linearity_dat_settings.xml");

        //        Runtime.getRuntime().exec("chmod +r shared_prefs " + spFile.getAbsolutePath());
//        Context context = AndroidAppHelper.currentApplication().createPackageContext("com.linearity.deviceaddresstweaker",Context.CONTEXT_IGNORE_SECURITY);


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

//    public static void getAppContextInit(XC_LoadPackage.LoadPackageParam lpparam, DeviceAddressTweaker instance, String processHead){
//        Class<?> loadedApkClass = XposedHelpers.findClass(
//                "android.app.LoadedApk",
//                lpparam.classLoader
//        );
//        Class<?> contextImplClass = XposedHelpers.findClass(
//                "android.app.ContextImpl",
//                lpparam.classLoader
//        );
//        Class<?> activityThreadClass = XposedHelpers.findClass(
//                "android.app.ActivityThread",
//                lpparam.classLoader
//        );
//        XposedHelpers.findAndHookMethod(
//                contextImplClass,
//                "createAppContext",
//                activityThreadClass, loadedApkClass, String.class,
//                new XC_MethodHook(1919810) {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        Context result = (Context) param.getResult();
//                        instance.ProcHead2Context.put(processHead, (Context) param.getResult());
//
//                        if (result != null){
//                            instance.ProcHead2SP.put(processHead, result.getSharedPreferences(processHead + "_dat_settings", Context.MODE_PRIVATE));
//                        }else {
//                            instance.ProcHead2SP.put(processHead, new XSharedPreferences(processHead));
//                        }
//                        startHookMethods(lpparam, processHead, instance.ProcHead2SP.get(processHead));
//                    }
//                }
//        );
//    }

    public static void startHookMethods(XC_LoadPackage.LoadPackageParam lpparam, String processHead, SharedPreferences sharedPreferences){
//        LoggerLog(sharedPreferences.getAll());
        //        StrangeHookClass.DoHook(lpparam,processHead,sharedPreferences);
//        if (!lpparam.processName.split(":")[0].contains("com.jingcai.apps")){return;}
//java
        HookJavaNetClass.DoHook(lpparam,processHead,sharedPreferences);//Working......
        HookLang.DoHook(lpparam,processHead,sharedPreferences);//not finished
        HookIO.DoHook(lpparam,processHead,sharedPreferences);//not finished
//android
        //accessibilityservice
        HookAccountClass.DoHook(lpparam,processHead,sharedPreferences);//DONE(API:32)
        //adservices(Not yet)
        //animation
        //annotation(NO)
        HookAppClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //appwidget
        HookBluetoothClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //companion
        HookContentClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //database
        //drm
        //gesture
        //graphics
        HookHardwareClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //icu
        //inputmethodservice
        HookLocationClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //media
        //mtp
        HookNetClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //nfc
        //opengl
        HookOsClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //preference
        //print
        //printservice
        HookProviderClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //renderscript
        //sax
        //se.omapi
        //security
        //service
        //speech
        //system
        //telecom
        HookTelephonyClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //text
        //transition
        //util
        //view
        //webkit
        //widget
        //window


        HookWechatClass.DoHook(lpparam,processHead,sharedPreferences);

        HookTIMClass.DoHook(lpparam,processHead,sharedPreferences);

        HookChaoxingClass.DoHook(lpparam,processHead,sharedPreferences);

        HookBilibiliClass.DoHook(lpparam,processHead,sharedPreferences);

    }
}


