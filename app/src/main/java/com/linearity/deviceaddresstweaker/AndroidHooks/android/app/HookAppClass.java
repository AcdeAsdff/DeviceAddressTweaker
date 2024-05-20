package com.linearity.deviceaddresstweaker.AndroidHooks.android.app;

import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_CANT_SAVE_STATE;
import static android.content.res.Configuration.KEYBOARDHIDDEN_YES;
import static android.content.res.Configuration.KEYBOARD_NOKEYS;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.EmptyIntent;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.returnCantUseHashMap;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnIntegerMAX;
import static com.linearity.utils.ReturnReplacements.returnIntegerOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.random;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.FragmentController;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.Menu;

import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.autofill.AutofillManager;

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
    
    
    public static XC_MethodReplacement returnCantSaveState = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam
        param) throws Throwable {
            return IMPORTANCE_CANT_SAVE_STATE;
        }
    };

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookApp = sharedPreferences.getBoolean("HookAppClass_HookApp", true);
        HookApplication = sharedPreferences.getBoolean("HookAppClass_HookApplication", true);
        HookActivity = sharedPreferences.getBoolean("HookAppClass_HookActivity", true);
        HookActivityManager = sharedPreferences.getBoolean("HookAppClass_HookActivityManager", true);
        HookContextImpl = sharedPreferences.getBoolean("HookAppClass_HookContextImpl", true);
        HookKeyguardManager = sharedPreferences.getBoolean("HookAppClass_HookKeyguardManager", true);
        HookLifecycleCallbacks = sharedPreferences.getBoolean("HookAppClass_HookLifecycleCallbacks", false);

        Class<?> hookClass;
        if (HookApp){
            if (HookActivity){
                hookClass = XposedHelpers.findClassIfExists(android.app.Activity.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            Class<?> ActivityClientClass = XposedHelpers.findClass("android.app.ActivityClient",lpparam.classLoader);
                            Class<?> RequestFinishCallbackClass = XposedHelpers.findClass("android.app.Activity$RequestFinishCallback",lpparam.classLoader);
                            Constructor<?> RequestFinishCallbackConstructor = XposedHelpers.findConstructorExact(RequestFinishCallbackClass, WeakReference.class);
                            Class<?> androidxFragmentControllerClass = XposedHelpers.findClassIfExists(androidx.fragment.app.FragmentController.class.getName(),lpparam.classLoader);
                            Class<?> FragmentControllerClass = XposedHelpers.findClassIfExists("android.support.v4.app.FragmentController",lpparam.classLoader);
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "setIntent",
                                        Intent.class,
                                        new XC_MethodHook(114514) {
                                            @Override
                                            protected void beforeHookedMethod(MethodHookParam param) {
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onTopResumedActivityChanged",
                                        boolean.class,returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "setVoiceInteractor",returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "performTopResumedActivityChanged",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "isVoiceInteraction",returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "isVoiceInteractionRoot",returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "isLocalVoiceInteractionSupported",returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "startLocalVoiceInteraction",
                                        Bundle.class,returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onLocalVoiceInteractionStarted",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onLocalVoiceInteractionStopped",returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onMultiWindowModeChanged",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onPictureInPictureModeChanged",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getChangingConfigurations",returnIntegerMAX
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getLastNonConfigurationInstance",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onRetainNonConfigurationInstance",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "getLastNonConfigurationChildInstances",returnCantUseHashMap
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onRetainNonConfigurationChildInstances",returnCantUseHashMap
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onWindowAttributesChanged",returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onContentChanged",returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onWindowFocusChanged",returnNull
                                );
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        android.app.Activity.class,
                                        "onDetachedFromWindow",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "hasWindowFocus",returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onOptionsMenuClosed",
                                        Menu.class,returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "openOptionsMenu",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "closeOptionsMenu",returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "requestPermissions",
                                        String[].class,int.class,returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "onRequestPermissionsResult",
                                        int.class,String[].class,int[].class,returnNull
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "shouldShowRequestPermissionRationale",
                                        String.class,returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "startActivity",
                                        Intent.class,
                                        new XC_MethodReplacement(114514) {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                Intent intent = (Intent) param.args[0];
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
                                        "isActivityTransitionRunning",returnTrue
                                );
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }
                            {
                                findAndHookMethodIfExists(hookClass,
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
                            }//startActivities:Only the last Hooked
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
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
                            }
                            {
                                XposedBridge.hookAllMethods(
                                        Activity.class,
                                        "onPause",
                                        new XC_MethodHook(114514) {
                                            @Override
                                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                super.beforeHookedMethod(param);
                                                LoggerLog("[linearity-activityLifeListener]",param.thisObject.getClass().getName() + " | onPause");
                                            }
                                        }
                                );
                            }
                            //👇protecting method
                            {
                                XposedBridge.hookAllMethods(hookClass, "onKeyDown", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Activity thisObj = (Activity) param.thisObject;
                                        int keyCode = (int) param.args[0];
                                        KeyEvent event = (KeyEvent) param.args[1];
                                        int mDefaultKeyMode = XposedHelpers.getIntField(thisObj,"mDefaultKeyMode");
                                        SpannableStringBuilder mDefaultKeySsb = (SpannableStringBuilder) XposedHelpers.getObjectField(thisObj,"mDefaultKeySsb");
                                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                                            if (thisObj.getApplicationInfo().targetSdkVersion
                                                    >= Build.VERSION_CODES.ECLAIR) {
                                                event.startTracking();
                                            } else {
                                                ActivityDefaultOnBackPressed(thisObj,ActivityClientClass,RequestFinishCallbackConstructor,androidxFragmentControllerClass,FragmentControllerClass);
                                            }
                                            return true;
                                        }

                                        if (mDefaultKeyMode == Activity.DEFAULT_KEYS_DISABLE) {
                                            return false;
                                        } else if (mDefaultKeyMode == Activity.DEFAULT_KEYS_SHORTCUT) {
                                            Window w = thisObj.getWindow();
                                            if (w.hasFeature(Window.FEATURE_OPTIONS_PANEL) &&
                                                    w.performPanelShortcut(Window.FEATURE_OPTIONS_PANEL, keyCode, event,
                                                            Menu.FLAG_ALWAYS_PERFORM_CLOSE)) {
                                                return true;
                                            }
                                            return false;
                                        } else if (keyCode == KeyEvent.KEYCODE_TAB) {
                                            // Don't consume TAB here since it's used for navigation. Arrow keys
                                            // aren't considered "typing keys" so they already won't get consumed.
                                            return false;
                                        } else {
                                            // Common code for DEFAULT_KEYS_DIALER & DEFAULT_KEYS_SEARCH_*
                                            boolean clearSpannable = false;
                                            boolean handled;
                                            if ((event.getRepeatCount() != 0) || event.isSystem()) {
                                                clearSpannable = true;
                                                handled = false;
                                            } else {
                                                handled = TextKeyListener.getInstance().onKeyDown(
                                                        null, mDefaultKeySsb, keyCode, event);
                                                if (handled && mDefaultKeySsb.length() > 0) {
                                                    // something useable has been typed - dispatch it now.

                                                    final String str = mDefaultKeySsb.toString();
                                                    clearSpannable = true;

                                                    switch (mDefaultKeyMode) {
                                                        case Activity.DEFAULT_KEYS_DIALER:
                                                            Intent intent = new Intent(Intent.ACTION_DIAL,  Uri.parse("tel:" + str));
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            thisObj.startActivity(intent);
                                                            break;
                                                        case Activity.DEFAULT_KEYS_SEARCH_LOCAL:
                                                            thisObj.startSearch(str, false, null, false);
                                                            break;
                                                        case Activity.DEFAULT_KEYS_SEARCH_GLOBAL:
                                                            thisObj.startSearch(str, false, null, true);
                                                            break;
                                                    }
                                                }
                                            }
                                            if (clearSpannable) {
                                                mDefaultKeySsb.clear();
                                                mDefaultKeySsb.clearSpans();
                                                Selection.setSelection(mDefaultKeySsb,0);
                                            }
                                            return handled;
                                        }
                                    }
                                });
                            }
                            {
                                XposedBridge.hookAllMethods(hookClass, "onKeyUp", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Activity thisObj = (Activity) param.thisObject;
                                        int keyCode = (int) param.args[0];
                                        KeyEvent event = (KeyEvent) param.args[1];
                                        if (thisObj.getApplicationInfo().targetSdkVersion
                                                >= Build.VERSION_CODES.ECLAIR) {
                                            if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                                                    && !event.isCanceled()) {
                                                ActivityDefaultOnBackPressed(thisObj,ActivityClientClass,RequestFinishCallbackConstructor,androidxFragmentControllerClass,FragmentControllerClass);
                                                return true;
                                            }
                                        }
                                        return false;
                                    }
                                });
                            }
                            {
                                XposedBridge.hookAllMethods(hookClass, "onBackPressed", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        ActivityDefaultOnBackPressed((Activity) param.thisObject,ActivityClientClass,RequestFinishCallbackConstructor,androidxFragmentControllerClass,FragmentControllerClass);
                                        return null;
                                    }
                                });
                            }
                        }
                    }catch (Exception e){
                        LoggerLog(e);
                    }
                }

                /**
                 *I wont get what I want by this way(below)
                **/
//                try{
//                    findAndHookMethodIfExists(
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
                hookClass = XposedHelpers.findClassIfExists(android.app.ActivityManager.class.getName(),lpparam.classLoader);
                if (hookClass != null) {
                    try {

//                        android.app.ActivityManager.class getRecentTasks(int,int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class getAppTasks()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getAppTasks",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getAppTasks(int,int)" + param.getResult());
                                            param.setResult(new ArrayList<ActivityManager.AppTask>());
                                        }
                                    }
                            );
                        }
//        android.app.ActivityManager.class getRunningTasks(int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class getRunningServices(int)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class getRunningServiceControlPanel(ComponentName)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRunningServiceControlPanel",
                                    ComponentName.class,returnNull
                            );
                        }
//        android.app.ActivityManager.class getProcessesInErrorState()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getProcessesInErrorState",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getProcessesInErrorState()" + param.getResult());
                                            param.setResult(new ArrayList<ActivityManager.ProcessErrorStateInfo>());
                                        }
                                    }
                            );
                        }
//        android.app.ActivityManager.class getRunningExternalApplications()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                                                    if (i.packageName.contains(procHead)) {
                                                        returnValue.add(i);
                                                    }
                                                }
                                            }
                                            param.setResult(returnValue);
//                                param.setResult(new ArrayList<ApplicationInfo>());
                                        }
                                    }
                            );
                        }
//        android.app.ActivityManager.class getRunningAppProcesses()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRunningAppProcesses",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            final List<ActivityManager.RunningAppProcessInfo>[] result = new List[]{(List<ActivityManager.RunningAppProcessInfo>) param.getResult()};
                                            List<ActivityManager.RunningAppProcessInfo> tempList = new ArrayList<>(result[0]);
                                            result[0] = tempList;
                                            ArrayList<ActivityManager.RunningAppProcessInfo> tempRunningAppProcessInfoReturnValue = new ArrayList<>();
                                            if (result[0] == null) {
                                                return;
                                            }
                                            for (ActivityManager.RunningAppProcessInfo i : result[0]) {
                                                if (i != null) {
                                                    for (String str : i.pkgList) {
                                                        if (str.contains(procHead)) {
                                                            tempRunningAppProcessInfoReturnValue.add(cloneRunningAppProcessInfo(i));
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            param.setResult(tempRunningAppProcessInfoReturnValue);
//                                LoggerLog(returnValue.toString());
                                        }
                                    }
                            );
                        }
////        android.app.ActivityManager.class getHistoricalProcessExitReasons()
//        try {
//            findAndHookMethodIfExists(
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
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getUidProcessState",
                                    int.class, returnCantSaveState
                            );
                        }
//        android.app.ActivityManager.class getUidProcessCapabilities(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getUidProcessCapabilities",
                                    int.class,returnIntegerOne
                            );
                        }
//        android.app.ActivityManager.class getPackageImportance(String)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getPackageImportance",
                                    String.class,returnCantSaveState
                            );
                        }
//        android.app.ActivityManager.class getUidImportance(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getUidImportance",
                                    int.class,returnCantSaveState
                            );
                        }
//        android.app.ActivityManager.class killBackgroundProcesses(String)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class killUid(int,String)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class forceStopPackage(String)
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class getSupportedLocales()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSupportedLocales",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        public void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getSupportedLocales()" + param.args[0]);
                                            param.setResult(new ArrayList<Locale>());
                                        }
                                    }
                            );
                        }
//        android.app.ActivityManager.class getDeviceConfigurationInfo()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class isUserAMonkey()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isUserAMonkey",returnFalse
                            );
                        }
//        android.app.ActivityManager.class isRunningInTestHarness()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isRunningInTestHarness",returnFalse
                            );
                        }
//        android.app.ActivityManager.class isRunningInUserTestHarness()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isRunningInUserTestHarness",returnFalse
                            );
                        }
//        android.app.ActivityManager.class getCurrentUser()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getCurrentUser",returnIntegerZero
                            );
                        }
//        android.app.ActivityManager.class getSwitchingFromUserMessage()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSwitchingFromUserMessage",returnRandomStr20
                            );
                        }
//        android.app.ActivityManager.class getSwitchingToUserMessage()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSwitchingToUserMessage",returnRandomStr20
                            );
                        }
//        android.app.ActivityManager.class updateMccMncConfiguration()
                        {
                            findAndHookMethodIfExists(hookClass,
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
                        }
//        android.app.ActivityManager.class isVrModePackageEnabled()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isVrModePackageEnabled",
                                    ComponentName.class,returnTrue
                            );
                        }
//        android.app.ActivityManager.class dumpPackageState()
                        {
                            Method staticMethod = XposedHelpers.findMethodExact(android.app.ActivityManager.class, "dumpPackageState",
                                    FileDescriptor.class,
                                    String.class);
                            findAndHookMethodIfExists(hookClass,
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

                        }
//        android.app.ActivityManager.class getBugreportWhitelistedPackages()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getBugreportWhitelistedPackages",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            //LoggerLog(lpparam.packageName + "调用android.app.ActivityManager.class getBugreportWhitelistedPackages()");
                                            param.setResult(new ArrayList<String>());
                                        }
                                    }
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookApplication){

                if (HookLifecycleCallbacks){

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
//            if (HookContextImpl){
//                try {
//                    findAndHookMethodIfExists(
//                            "android.app.ContextImpl",
//                            lpparam.classLoader,
//                            "openFileInput",
//                            String.class,
//                            new XC_MethodHook(114514) {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
//                                    String path = (String)param.args[0];
//                                    LoggerLog("---------------");
//                                    LoggerLog(path);
//                                    path = checkReplaceFile(path,lpparam);
//                                    if (!checkBannedInFile(path, lpparam)){
//                                        param.args[0] = Environment.getExternalStorageDirectory().getPath() + "/Android/"+procHead+"/";
//                                    }
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
//                try {
//                    findAndHookMethodIfExists(
//                            "android.app.ContextImpl",
//                            lpparam.classLoader,
//                            "openFileOutput",
//                            String.class,int.class,
//                            new XC_MethodHook(114514) {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
//                                    String path = (String)param.args[0];
//                                    LoggerLog("---------------");
//                                    LoggerLog(path);
//                                    path = checkReplaceFile(path, lpparam);
//                                    if (!checkBannedOutFile(path, lpparam)){
//                                        param.args[0] = Environment.getExternalStorageDirectory().getPath() + "/Android/"+procHead+"/";
//                                    }
//                                }
//                            }
//                    );
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
//            }//not finished,
            if (HookKeyguardManager){
                hookClass = XposedHelpers.findClassIfExists(KeyguardManager.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isKeyguardLocked",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isKeyguardSecure",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "inKeyguardRestrictedInputMode",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isDeviceLocked",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isDeviceSecure",returnFalse
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "requestDismissKeyguard",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "exitKeyguardSecurely",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "hasPermission",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isValidLockPasswordComplexity",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getMinLockLength",returnIntegerZero
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setLock",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "addWeakEscrowToken",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return random.nextLong();
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "removeWeakEscrowToken",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isWeakEscrowTokenActive",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "isWeakEscrowTokenValid",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "registerWeakEscrowTokenRemovedListener",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "checkLock",returnTrue
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "addKeyguardLockedStateListener",returnNull
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "removeKeyguardLockedStateListener",returnNull
                            );
                        }
                    }catch (Exception e){
                        LoggerLog(e);
                    }
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


    public static void ActivityDefaultOnBackPressed(Activity thisObj,Class<?> ActivityClientClass,Constructor<?> RequestFinishCallbackConstructor,Class<?> androidxFragmentControllerClass,Class<?> FragmentControllerClass) throws InvocationTargetException, IllegalAccessException, InstantiationException {

//            Activity thisObj = (Activity) activityParam.thisObject;
        ActionBar mActionBar = (ActionBar) XposedHelpers.getObjectField(thisObj,"mActionBar");
        Object mFragments = XposedHelpers.getObjectField(thisObj,"mFragments");
        IBinder mToken = (IBinder) XposedHelpers.getObjectField(thisObj,"mToken");
        Intent mIntent = (Intent) XposedHelpers.getObjectField(thisObj,"mIntent");
        String EXTRA_RESTORE_SESSION_TOKEN = (String) XposedHelpers.getStaticObjectField(AutofillManager.class,"EXTRA_RESTORE_SESSION_TOKEN");
        if (mActionBar != null && (boolean)XposedHelpers.callMethod(mActionBar,"collapseActionView")) {
            return;
        }

//        LoggerLog("mFragments: "+mFragments.getClass().getTypeName());
        Object fragmentManager = null;//mFragments.getFragmentManager();
        try {
            boolean controllerFlag = false;
            if (FragmentControllerClass != null){
                if (FragmentControllerClass.isInstance(mFragments)){
                    controllerFlag = true;
                }
            }
            if (mFragments instanceof FragmentController || controllerFlag) {
                fragmentManager = XposedHelpers.callMethod(mFragments, "getFragmentManager");
            } else if (androidxFragmentControllerClass.isInstance(mFragments)) {
                fragmentManager = XposedHelpers.callMethod(mFragments, "getSupportFragmentManager");
            } else {
                fragmentManager = mFragments;
            }
            ;
            boolean isStateSaved = (boolean) XposedHelpers.getBooleanField(fragmentManager, "mStateSaved");
            if (!isStateSaved
                    && ((boolean) XposedHelpers.callMethod(fragmentManager, "popBackStackImmediate"))) {
                return;
            }
        }catch (Exception e){
            LoggerLog(e);
        }
        if (!thisObj.isTaskRoot()) {
            // If the activity is not the root of the task, allow finish to proceed normally.
            thisObj.finishAfterTransition();
            return;
        }
        // Inform activity task manager that the activity received a back press while at the
        // root of the task. This call allows ActivityTaskManager to intercept or move the task
        // to the back.
        XposedHelpers.callMethod(
                XposedHelpers.callStaticMethod(ActivityClientClass,"getInstance"),
                "onBackPressedOnTaskRoot",
                mToken,
                RequestFinishCallbackConstructor.newInstance(new WeakReference<>(thisObj))
        );

        // Activity was launched when user tapped a link in the Autofill Save UI - Save UI must
        // be restored now.
        if (mIntent != null && mIntent.hasExtra(EXTRA_RESTORE_SESSION_TOKEN)) {
            XposedHelpers.callMethod(thisObj,"restoreAutofillSaveUi");
        }
        return;

    }
}
