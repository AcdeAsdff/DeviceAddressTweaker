package com.linearity.deviceaddresstweaker;

import static com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass.byteArray114514;
import static com.linearity.utils.FakeInfo.FakeProcInfoGenerator.random;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.wifi.WifiInfo;
import android.system.Os;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.HookForSystem;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.accessibilityservice.HookAccessibilityClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.accounts.HookAccountClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.app.HookAppClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.bluetooth.HookBluetoothClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.content.HookContentClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.drm.HookDrmClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.HookHardwareClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.location.HookLocationClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.mtp.HookMTPClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.net.HookNetClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.os.HookOsClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.provider.HookProviderClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.telecom.HookTelecomClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.telephony.HookTelephonyClass;
import com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO;
import com.linearity.deviceaddresstweaker.JavaHooks.java.lang.HookLang;
import com.linearity.deviceaddresstweaker.JavaHooks.java.net.HookJavaNetClass;
import com.linearity.deviceaddresstweaker.TIM.HookTIMClass;
import com.linearity.deviceaddresstweaker.Wechat.HookWechatClass;
import com.linearity.utils.FakeClass.FakeReturnClasses.FakeReturnClassMap;
import com.linearity.utils.ReturnReplacements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class DeviceAddressTweaker implements IXposedHookLoadPackage, IXposedHookInitPackageResources, IXposedHookZygoteInit {
    public Map<String, Context> ProcHead2Context = new HashMap<>();
//    public Map<String, SharedPreferences> ProcHead2SP = new HashMap<>();
//    public Context appContext = null;
    public String processHead;
    public String modulePath;

    public static WifiInfo fakeWifiInfo =
            new WifiInfo.Builder()
                    .setBssid(ReturnReplacements.getRandomString(20))
                    .setSsid(byteArray114514)
                    .setCurrentSecurityType(random.nextInt())
                    .build();
    public static Location fakeLocation = new Location("jerk");
    public static Intent EmptyIntent = new Intent();

    static {
        FakeReturnClassMap.registerInstance(AccountManagerFuture.class, HookAccountClass.EmptyFuture2TaskOBoolean);
    }
    public static UUID uuid = UUID.randomUUID();
    SharedPreferences sharedPreferences;


    public XC_InitPackageResources.InitPackageResourcesParam resparam;

    //a looooooooooong way 2 go
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)throws Exception{
        if (lpparam == null) {
            return;
        }

        if (lpparam.processName == null){return;}
        if (lpparam.packageName.startsWith("system") || lpparam.processName.equals("android")){
//            HookForSystem.DoHook(lpparam,processHead,sharedPreferences);
            return;
        }
        LoggerLog("Load app processName:" + lpparam.processName);
//        if (!lpparam.processName.contains(".")){return;}
        System.loadLibrary("deviceaddresstweaker");
        processHead = lpparam.processName.split(":")[0];
        ProcHead2Context.put(processHead, null);
        File spFile = new File("/data/local/tmp/linearity_dat/shared_prefs/" + processHead + "_linearity_dat_settings.xml");
        sharedPreferences = new XSharedPreferences(spFile);
        startHookMethods(lpparam, processHead, sharedPreferences);
    }


    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) {
        this.resparam = resparam;
//        HookTIMClass.DoColor(this);
    }

    @Override
    public void initZygote(StartupParam startupParam) {
        this.modulePath = startupParam.modulePath;
    }


    public static void startHookMethods(XC_LoadPackage.LoadPackageParam lpparam, String processHead, SharedPreferences sharedPreferences) throws Exception{

        HookJavaNetClass.DoHook(lpparam, sharedPreferences);//not finished
        HookLang.DoHook(lpparam,processHead,sharedPreferences);//not finished
        HookIO.DoHook(lpparam,processHead,sharedPreferences);//not finished
//android
        HookAccessibilityClass.DoHook(lpparam,processHead,sharedPreferences);
        HookAccountClass.DoHook(lpparam,processHead,sharedPreferences);//DONE(API:32)
        //adservices(on and above android 13,not 10)
        //animation
        //annotation(NO)
        HookAppClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //appwidget
        HookBluetoothClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //companion
        HookContentClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //database
        HookDrmClass.DoHook(lpparam,processHead,sharedPreferences);//finished in a lazy way(disabled all classes)
        //gesture
        //graphics
        HookHardwareClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //icu
        //inputmethodservice
        HookLocationClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //media
        HookMTPClass.DoHook(lpparam,processHead,sharedPreferences);//(disabled all classes)
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
        HookTelecomClass.DoHook(lpparam,processHead,sharedPreferences);//(disabled all classes)
        HookTelephonyClass.DoHook(lpparam,processHead,sharedPreferences);//not finished
        //text
        //transition
        //util
        //view
        //webkit
        //widget
        //window

        HookWechatClass.DoHook(lpparam,processHead,sharedPreferences);

//        HookTIMClass.DoHook(lpparam,processHead,sharedPreferences);

    }

}

