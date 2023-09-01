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
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookAppClass {

    public static boolean HookApp = true;
    public static boolean HookActivity = true;
    public static boolean HookActivityManager = true;
    public static boolean HookContextImpl = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
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
                                    List<ActivityManager.RunningAppProcessInfo> result = (List<ActivityManager.RunningAppProcessInfo>) param.getResult();
                                    List<ActivityManager.RunningAppProcessInfo> returnValue = new ArrayList<>();
                                    //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getRunningAppProcesses()" + param.getResult());
                                    for (ActivityManager.RunningAppProcessInfo i : result) {
                                        if (i != null) {
                                            for (String str : i.pkgList) {
                                                if (str.contains(lpparam.packageName)) {
                                                    returnValue.add(i);
                                                }
                                            }
                                        }
                                    }
                                    param.setResult(returnValue);
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
                            || (checkStr.contains(lpparam.processName))
                            || (checkStr.contains("wxapi"))
                            || (checkStr.contains("com.tencent.mm"))
                            || (checkStr.contains("com.alipay.android"))
            )
            );
        }else {return false;}
    }
}
