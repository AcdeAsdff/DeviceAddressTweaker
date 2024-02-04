package com.linearity.deviceaddresstweaker.AndroidHooks.android.app;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_CANT_SAVE_STATE;
import static android.content.res.Configuration.KEYBOARDHIDDEN_YES;
import static android.content.res.Configuration.KEYBOARD_NOKEYS;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.EmptyIntent;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.EmptyMap_String_Object;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedInFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedOutFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.Menu;

import java.io.FileDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookAppClass {

    public static boolean HookApp = true;
    public static boolean HookApplication = true;
    public static boolean HookActivity = true;
    public static boolean HookActivityManager = true;
    public static boolean HookContextImpl = true;
    public static boolean HookKeyguardManager = true;

    public static boolean HookLifecycleCallbacks = false;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookApp = sharedPreferences.getBoolean("HookAppClass_HookApp", true);
        HookApplication = sharedPreferences.getBoolean("HookAppClass_HookApplication", true);
        HookActivity = sharedPreferences.getBoolean("HookAppClass_HookActivity", true);
        HookActivityManager = sharedPreferences.getBoolean("HookAppClass_HookActivityManager", true);
        HookContextImpl = sharedPreferences.getBoolean("HookAppClass_HookContextImpl", true);
        HookKeyguardManager = sharedPreferences.getBoolean("HookAppClass_HookKeyguardManager", true);
        HookLifecycleCallbacks = sharedPreferences.getBoolean("HookAppClass_HookLifecycleCallbacks", false);
        final ArrayList<ActivityManager.RunningAppProcessInfo>[] RunningAppProcessInfoReturnValue = new ArrayList[]{new ArrayList<>()};

        if (HookApp){
            //remember to pay attention to Activity.onKeyDown
            if (HookActivity){
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "setIntent",
                            Intent.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + " " + param.method.getName() + " " + intent.getComponent());
                                    if (!checkAllowIntent(intent, lpparam)){
                                        param.args[0] = EmptyIntent;
                                    }
//                                    super.beforeHookedMethod(param);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onTopResumedActivityChanged",
                            boolean.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "setVoiceInteractor",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "performTopResumedActivityChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());                                    //LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() +" performTopResumedActivityChanged()");
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isVoiceInteraction",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isVoiceInteractionRoot",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isLocalVoiceInteractionSupported",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startLocalVoiceInteraction",
                            Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onLocalVoiceInteractionStarted",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onLocalVoiceInteractionStopped",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onMultiWindowModeChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isInMultiWindowMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onPictureInPictureModeChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isInPictureInPictureMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onConfigurationChanged",
                            Configuration.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Configuration configuration = (Configuration) param.args[0];
                                    configuration.setLocale(Locale.CHINA);
                                    configuration.mcc = 114514;
                                    configuration.mnc = 114514;
                                    configuration.keyboardHidden = KEYBOARDHIDDEN_YES;
                                    configuration.keyboard = KEYBOARD_NOKEYS;
//                                    configuration.hardKeyboardHidden = HARDKEYBOARDHIDDEN_YES;
//                                    configuration.navigation = NAVIGATION_NONAV;
//                                    configuration.navigationHidden = NAVIGATIONHIDDEN_YES;
//                                    configuration.orientation = ORIENTATION_PORTRAIT;
//                                    configuration.screenLayout = SCREENLAYOUT_LONG_NO;
//                                    configuration.colorMode = COLOR_MODE_HDR_NO;
//                                    configuration.fontScale = 1;
//                                    configuration.uiMode = UI_MODE_NIGHT_YES;
                                    param.args[0]=configuration;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "getChangingConfigurations",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "getLastNonConfigurationInstance",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onRetainNonConfigurationInstance",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "getLastNonConfigurationChildInstances",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return EmptyMap_String_Object;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onRetainNonConfigurationChildInstances",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return EmptyMap_String_Object;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onWindowAttributesChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onContentChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onWindowFocusChanged",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            android.app.Activity.class,
                            "onDetachedFromWindow",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "hasWindowFocus",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName() + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onOptionsMenuClosed",
                            Menu.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "openOptionsMenu",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "closeOptionsMenu",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "requestPermissions",
                            String[].class,int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName()+ " " + param.method.getName()+ " " + Arrays.toString((String[]) param.args[0])+ " " + (int)param.args[1]);
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "onRequestPermissionsResult",
                            int.class,String[].class,int[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName()+ " " + param.method.getName());
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "shouldShowRequestPermissionRationale",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName()+ " " + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivity",
                            Intent.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());
                                    if (!checkAllowIntent(intent, lpparam)) {
                                        if (intent.getComponent() != null){
                                            LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().toString());
                                        }
                                    }
                                    else {
                                        ((Activity) param.thisObject).startActivity(intent, null);
                                    }
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivity",
                            Intent.class, Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());

                                    if (!checkAllowIntent(intent, lpparam)) {
                                        LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().toString());
                                    }
                                    else {
                                        Bundle options = (Bundle) param.args[1];
                                        if (options != null) {
                                            ((Activity) param.thisObject).startActivityForResult(intent, -1, options);
                                        } else {
                                            // Note we want to go through this call for compatibility with
                                            // applications that may have overridden the method.
                                            ((Activity) param.thisObject).startActivityForResult(intent, -1);
                                        }
                                    }
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivityForResult",
                            Intent.class, int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());

                                    if (!checkAllowIntent(intent, lpparam)) {
                                        if (intent.getComponent() != null){
                                            LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().toString());
                                        }
                                    }
                                    else {
                                        int requestCode = (int) param.args[1];
                                        ((Activity) param.thisObject).startActivityForResult(intent, requestCode, null);
                                    }
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivityForResult",
                            Intent.class, int.class,Bundle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());

                                    if (!checkAllowIntent(intent, lpparam)) {
                                        if (intent != EmptyIntent){
                                            LoggerLog(lpparam.packageName + "(From:"+ lpparam.processName + ") trying to launch " + intent.getComponent().toString());
                                            param.args[0] = EmptyIntent;
                                            param.args[2] = null;
                                            param.setResult(null);
                                        }
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "isActivityTransitionRunning",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                    Activity activity = (Activity) param.thisObject;
//                                    LoggerLog(lpparam.packageName + " " + activity.getLocalClassName()+ " " + param.method.getName());
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivityForResultAsUser",
                            Intent.class, int.class, UserHandle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());

                                    if (!checkAllowIntent(intent, lpparam)) {
                                        if (intent != EmptyIntent){
                                            LoggerLog(lpparam.packageName + "(From:"+ lpparam.processName + ") trying to launch " + intent.getComponent().toString());
                                            param.args[0] = EmptyIntent;
                                            param.args[2] = null;
                                            param.setResult(null);
                                        }
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivityForResultAsUser",
                            Intent.class, int.class,Bundle.class,UserHandle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent intent = (Intent) param.args[0];
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());

                                    if (!checkAllowIntent(intent, lpparam)) {
                                        if (intent != EmptyIntent){
                                            LoggerLog(lpparam.packageName + "(From:"+ lpparam.processName + ") trying to launch " + intent.getComponent().toString());
                                            param.args[0] = EmptyIntent;
                                            param.args[2] = null;
                                            param.setResult(null);
                                        }
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.app.Activity.class.getName(),
                            lpparam.classLoader,
                            "startActivities",
                            Intent[].class,Bundle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    Intent[] intents = (Intent[]) param.args[0];
                                    List<Intent> result = new ArrayList<>();
//                                    LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().getPackageName());
                                    for (Intent intent:intents){
                                        if (!checkAllowIntent(intent, lpparam)) {
                                            if (intent.getComponent() != null) {
                                                LoggerLog(lpparam.packageName + " trying to launch " + intent.getComponent().toString());
                                            }
                                        } else {
//                                            ((Activity) param.thisObject).startActivity(intent, null);
                                            result.add(intent);
                                        }
                                    }
                                    param.setResult(result);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }//startActivities:Only the last Hooked
                try{
                    XposedBridge.hookAllMethods(
                            Activity.class,
                            "startActivityIfNeeded",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Intent intent = (Intent) param.args[0];
                                    if (!checkAllowIntent(intent, lpparam)){
                                        param.args[0]=EmptyIntent;
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            Activity.class,
                            "startNextMatchingActivity",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Intent intent = (Intent) param.args[0];
                                    if (!checkAllowIntent(intent, lpparam)){
                                        param.args[0]=EmptyIntent;
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            Activity.class,
                            "startActivityFromChild",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Intent intent = (Intent) param.args[1];
                                    if (!checkAllowIntent(intent, lpparam)){
                                        param.args[1]=EmptyIntent;
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedBridge.hookAllMethods(
                            Activity.class,
                            "startActivityFromFragment",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    Intent intent = (Intent) param.args[1];
                                    if (!checkAllowIntent(intent, lpparam)){
                                        param.args[1]=EmptyIntent;
                                    }
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }


                try{
                    XposedBridge.hookAllMethods(
                            Activity.class,
                            "onPause",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    LoggerLog(param.thisObject.getClass().getName());
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }

                /**
                 *I wont get what I want by this way(below)
                **/
//                try{
//                    XposedHelpers.findAndHookMethod(
//                            Activity.class.getName(),
//                            lpparam.classLoader,
//                            "getSystemService",
//                            String.class,
//                            new XC_MethodHook(114514) {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
//                                    String input = (String) param.args[0];
//                                    if (      input.equals(SENSOR_SERVICE)
//                                            ||input.equals(ALARM_SERVICE)
//                                            ||input.equals(KEYGUARD_SERVICE)
//                                            ||input.equals(LOCATION_SERVICE)
//                                            ||input.equals(LOCALE_SERVICE)
//                                            ||input.equals(SEARCH_SERVICE)
//                                            ||input.equals(STORAGE_SERVICE)
//                                            ||input.equals(VIBRATOR_MANAGER_SERVICE)
//                                            ||input.equals(VIBRATOR_SERVICE)
//                                            ||input.equals(CONNECTIVITY_SERVICE)
//                                            ||input.equals(CONNECTIVITY_DIAGNOSTICS_SERVICE)
//                                            ||input.equals(WIFI_SERVICE)
//                                            ||input.equals(WIFI_P2P_SERVICE)
//                                            ||input.equals(WIFI_AWARE_SERVICE)
//                                            ||input.equals(WIFI_RTT_RANGING_SERVICE)
//                                            ||input.equals(MEDIA_COMMUNICATION_SERVICE)
//                                            ||input.equals(MEDIA_PROJECTION_SERVICE)
//                                            ||input.equals(MEDIA_SESSION_SERVICE)
//                                            ||input.equals(MEDIA_METRICS_SERVICE)
//                                            ||input.equals(MEDIA_ROUTER_SERVICE)
//                                    ){
//                                        param.args[0] = WINDOW_SERVICE;
//                                    }
//                                }
//                            }
//                    );
//                }catch (Exception e) {
//                    LoggerLog(e);
//                }
            }//not finished,maybe no need to finish.
            if (HookActivityManager) {
//        android.app.ActivityManager.class getRecentTasks(int,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRecentTasks",
                            int.class, int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRecentTasks(int,int)" + param.getResult());
                                    param.setResult(new ArrayList<ActivityManager.RecentTaskInfo>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getAppTasks()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getAppTasks",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getAppTasks(int,int)" + param.getResult());
                                    param.setResult(new ArrayList<ActivityManager.AppTask>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getRunningTasks(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRunningTasks",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningTasks(int)" + param.getResult());
                                    param.setResult(new ArrayList<ActivityManager.RunningTaskInfo>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getRunningServices(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRunningServices",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningServices(int)" + param.getResult());
                                    param.setResult(new ArrayList<ActivityManager.RunningServiceInfo>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getRunningServiceControlPanel(ComponentName)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRunningServiceControlPanel",
                            ComponentName.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningServiceControlPanel(ComponentName)" + param.getResult());
                                    param.setResult(null);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getProcessesInErrorState()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getProcessesInErrorState",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getProcessesInErrorState()" + param.getResult());
                                    param.setResult(new ArrayList<ActivityManager.ProcessErrorStateInfo>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getRunningExternalApplications()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRunningExternalApplications",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningExternalApplications()" + param.getResult());
                                    List<ApplicationInfo> result = (List<ApplicationInfo>) param.getResult();
                                    List<ApplicationInfo> returnValue = new ArrayList<>();
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningAppProcesses()" + param.getResult());
                                    for (ApplicationInfo i : result) {
                                        if (i != null) {
                                            if (i.packageName.contains(lpparam.packageName)) {
                                                returnValue.add(i);
                                            }
                                        }
                                    }
                                    param.setResult(returnValue);
//                                param.setResult(new ArrayList<ApplicationInfo>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getRunningAppProcesses()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getRunningAppProcesses",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
<<<<<<< Updated upstream
                                    List<ActivityManager.RunningAppProcessInfo> result = (List<ActivityManager.RunningAppProcessInfo>) param.getResult();
                                    List<ActivityManager.RunningAppProcessInfo> returnValue = new ArrayList<>();
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningAppProcesses()" + param.getResult());
                                    for (ActivityManager.RunningAppProcessInfo i : result) {
=======
                                    final List<ActivityManager.RunningAppProcessInfo>[] result = new List[]{(List<ActivityManager.RunningAppProcessInfo>) param.getResult()};
                                    List<ActivityManager.RunningAppProcessInfo> tempList = new ArrayList<>(result[0]);
                                    result[0] = tempList;
//                                    LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningAppProcesses()" + param.getResult());
                                    ArrayList<ActivityManager.RunningAppProcessInfo> tempRunningAppProcessInfoReturnValue = RunningAppProcessInfoReturnValue[0];
                                    if (result[0] == null){return;}
                                    for (ActivityManager.RunningAppProcessInfo i : result[0]) {
>>>>>>> Stashed changes
                                        if (i != null) {
                                            for (String str : i.pkgList) {
                                                if (RunningAppProcessInfoReturnValue[0].contains(i)){continue;}
                                                if (str.contains(procHead)) {
                                                    tempRunningAppProcessInfoReturnValue.add(cloneRunningAppProcessInfo(i));
                                                }
                                            }
                                        }
                                    }
                                    RunningAppProcessInfoReturnValue[0] = tempRunningAppProcessInfoReturnValue;
                                    param.setResult(RunningAppProcessInfoReturnValue[0]);
//                                LoggerLog(returnValue.toString());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
////        android.app.ActivityManager.class getHistoricalProcessExitReasons()
//        try {
//            XposedHelpers.findAndHookMethod(
//                    android.app.ActivityManager.class.getName(),
//                    lpparam.classLoader,
//                    "getHistoricalProcessExitReasons",
//                    new XC_MethodHook(114514) {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getHistoricalProcessExitReasons()" + param.getResult());
//                            param.setResult(null);
//                        }
//                    }
//            );
//        }catch (Exception e){LoggerLog(e);}
//        android.app.ActivityManager.class getUidProcessState(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getUidProcessState",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getUidProcessState(int)" + param.getResult());
                                    param.setResult(IMPORTANCE_CANT_SAVE_STATE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getUidProcessCapabilities(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getUidProcessCapabilities",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getUidProcessCapabilities(int)" + param.getResult());
                                    param.setResult(0);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getPackageImportance(String)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getPackageImportance",
                            String.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getPackageImportance(String)" + param.getResult());
                                    param.setResult(IMPORTANCE_CANT_SAVE_STATE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getUidImportance(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getUidImportance",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getUidImportance(int)" + param.getResult());
                                    param.setResult(IMPORTANCE_CANT_SAVE_STATE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class killBackgroundProcesses(String)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "killBackgroundProcesses",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class killBackgroundProcesses(String)" + param.args[0]);
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class killUid(int,String)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "killUid",
                            int.class,
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class killUid(int, String)" + param.args[0]);
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class forceStopPackage(String)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "forceStopPackage",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class forceStopPackage(String)" + param.args[0]);
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getSupportedLocales()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getSupportedLocales",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getSupportedLocales()" + param.args[0]);
                                    param.setResult(new ArrayList<Locale>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getDeviceConfigurationInfo()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceConfigurationInfo",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    ConfigurationInfo result = (ConfigurationInfo) param.getResult();
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getDeviceConfigurationInfo()" + result);
                                    result.reqGlEsVersion = Math.max(1, result.reqGlEsVersion - 1);
                                    result.reqKeyboardType = random.nextInt(2) + 1;
                                    result.reqInputFeatures = random.nextInt(1) + 1;
                                    result.reqTouchScreen = random.nextInt(2) + 1;
                                    result.reqNavigation = random.nextInt(4);
                                    //LoggerLog(lpparam.packageName + " android.app.ActivityManager.class getDeviceConfigurationInfo()->" + result);
                                    param.setResult(result);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class isUserAMonkey()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "isUserAMonkey",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class isUserAMonkey()" + param.args[0]);
                                    param.setResult(false);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class isRunningInTestHarness()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "isRunningInTestHarness",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class isRunningInTestHarness()" + param.args[0]);
                                    param.setResult(false);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class isRunningInUserTestHarness()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "isRunningInUserTestHarness",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class isRunningInUserTestHarness()" + param.args[0]);
                                    param.setResult(false);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getCurrentUser()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getCurrentUser",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getCurrentUser()" + param.args[0]);
                                    param.setResult(0);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getSwitchingFromUserMessage()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getSwitchingFromUserMessage",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getSwitchingFromUserMessage()" + param.args[0]);
                                    param.setResult(getRandomString(20));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getSwitchingToUserMessage()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getSwitchingToUserMessage",
                            new XC_MethodHook(114514) {
                                @Override
                                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getSwitchingToUserMessage()" + param.args[0]);
                                    param.setResult(getRandomString(20));
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class updateMccMncConfiguration()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "updateMccMncConfiguration",
                            String.class,
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class updateMccMncConfiguration()" + param.args[0]);
                                    return true;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class isVrModePackageEnabled()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "isVrModePackageEnabled",
                            ComponentName.class,
                            new XC_MethodHook(114514) {
                                @Override
                                public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class isVrModePackageEnabled()" + param.args[0]);
                                    param.setResult(true);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class dumpPackageState()
                try {
                    Method staticMethod = XposedHelpers.findMethodExact(android.app.ActivityManager.class, "dumpPackageState",
                            FileDescriptor.class,
                            String.class);
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "dumpPackageState",
                            FileDescriptor.class,
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class dumpPackageState()" + param.args[0]);
                                    if (((String) param.args[1]).contains(lpparam.packageName)) {
                                        staticMethod.invoke(param.args[0], param.args[1]);
                                    }
                                    return null;
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
//        android.app.ActivityManager.class getBugreportWhitelistedPackages()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.app.ActivityManager.class.getName(),
                            lpparam.classLoader,
                            "getBugreportWhitelistedPackages",
                            new XC_MethodHook(114514) {
                                @Override
                                public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getBugreportWhitelistedPackages()");
                                    param.setResult(new ArrayList<String>());
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookApplication){

                if (HookLifecycleCallbacks && !procHead.contains("chaoxing")){

                    try {
                        XposedBridge.hookAllMethods(
                                android.app.Application.class,
                                "registerActivityLifecycleCallbacks",
                                new XC_MethodHook() {
                                    @Override
                                    public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        String className = param.args[0].getClass().getName();
                                        String classNameLowerCase = className.toLowerCase();
                                        LoggerLog("registerActivityLifecycleCallbacks:" + className);
                                        if (classNameLowerCase.contains("report")
                                                || classNameLowerCase.contains("crash")
                                                || classNameLowerCase.contains("analytics")){
                                            param.args[0] = new voidLifecycleCallbacks();
                                        }
                                    }
                                }
                        );
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityPrePaused",
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
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityPaused",
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
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityPostPaused",
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
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityPreStopped",
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
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityStopped",
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
//                    try {
//                        XposedBridge.hookAllMethods(
//                                android.app.Application.ActivityLifecycleCallbacks.class,
//                                "onActivityPostStopped",
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
                }
            }
            if (HookContextImpl){
                try {
                    XposedHelpers.findAndHookMethod(
                            "android.app.ContextImpl",
                            lpparam.classLoader,
                            "openFileInput",
                            String.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    String path = (String)param.args[0];
//                                    LoggerLog("---------------");
//                                    LoggerLog(path);
//                                    LoggerLog("---------------");
                                    path = checkReplaceFile(path,lpparam);
                                    if (!checkBannedInFile(path, lpparam)){
                                        param.args[0] = "/";
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            "android.app.ContextImpl",
                            lpparam.classLoader,
                            "openFileOutput",
                            String.class,int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    String path = (String)param.args[0];
//                                    LoggerLog("---------------");
//                                    LoggerLog(path);
//                                    LoggerLog("---------------");
                                    path = checkReplaceFile(path, lpparam);
                                    if (!checkBannedOutFile(path, lpparam)){
                                        param.args[0] = "/";
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished,
            if (HookKeyguardManager){
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isKeyguardLocked",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isKeyguardSecure",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "inKeyguardRestrictedInputMode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isDeviceLocked",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isDeviceSecure",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "requestDismissKeyguard",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "exitKeyguardSecurely",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "hasPermission",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isValidLockPasswordComplexity",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "getMinLockLength",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "setLock",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "addWeakEscrowToken",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return random.nextLong();
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "removeWeakEscrowToken",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isWeakEscrowTokenActive",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "isWeakEscrowTokenValid",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "registerWeakEscrowTokenRemovedListener",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "checkLock",
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
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "addKeyguardLockedStateListener",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedBridge.hookAllMethods(
                            android.app.KeyguardManager.class,
                            "removeKeyguardLockedStateListener",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished,
        }
    }

    public static boolean checkAllowIntent(Intent intent, XC_LoadPackage.LoadPackageParam lpparam){
//        return true;
        if (intent.getComponent() != null){
//            LoggerLog(intent.getComponent().toString());
            String checkStr = intent.getComponent().toString();
            return (intent.getComponent() != null
                    && (
                    (checkStr.contains(lpparam.packageName))
                            || (checkStr.contains(lpparam.processName.split(":")[0]))
                            || (checkStr.contains("wxapi"))
                            || (checkStr.contains("com.tencent.mm"))
                            || (checkStr.contains("com.alipay.android"))
            )
            );
        }else {return false;}
    }

    public static ActivityManager.RunningAppProcessInfo cloneRunningAppProcessInfo(ActivityManager.RunningAppProcessInfo i){
        ActivityManager.RunningAppProcessInfo result = new ActivityManager.RunningAppProcessInfo();
        result.pid = i.pid;
        result.uid = i.uid;
        result.processName = i.processName;
        result.pkgList = i.pkgList;
        XposedHelpers.setObjectField(result,"flags",XposedHelpers.getObjectField(i,"flags"));
        result.lastTrimLevel = i.lastTrimLevel;
        result.importance = ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
        result.lru = i.lru;
        result.importanceReasonCode = i.importanceReasonCode;
        result.importanceReasonComponent = i.importanceReasonComponent;
        result.importanceReasonPid = i.importanceReasonPid;
        XposedHelpers.setObjectField(result,"importanceReasonImportance",XposedHelpers.getObjectField(i,"importanceReasonImportance"));
        XposedHelpers.setObjectField(result,"processState",XposedHelpers.getObjectField(i,"processState"));
        XposedHelpers.setObjectField(result,"isFocused",XposedHelpers.getObjectField(i,"isFocused"));
        XposedHelpers.setObjectField(result,"lastActivityTime",XposedHelpers.getObjectField(i,"lastActivityTime"));
        return result;
    }

    public static class voidLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{
        public voidLifecycleCallbacks(){}

        @Override
        public void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreCreated(activity, savedInstanceState);
        }

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostCreated(activity, savedInstanceState);
        }

        @Override
        public void onActivityPreStarted(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreStarted(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStarted(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostStarted(activity);
        }

        @Override
        public void onActivityPreResumed(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreResumed(activity);
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostResumed(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostResumed(activity);
        }

        @Override
        public void onActivityPrePaused(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPrePaused(activity);
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostPaused(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostPaused(activity);
        }

        @Override
        public void onActivityPreStopped(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreStopped(activity);
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostStopped(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostStopped(activity);
        }

        @Override
        public void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreSaveInstanceState(activity, outState);
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostSaveInstanceState(activity, outState);
        }

        @Override
        public void onActivityPreDestroyed(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPreDestroyed(activity);
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPostDestroyed(@NonNull Activity activity) {
            Application.ActivityLifecycleCallbacks.super.onActivityPostDestroyed(activity);
        }
    }
}
