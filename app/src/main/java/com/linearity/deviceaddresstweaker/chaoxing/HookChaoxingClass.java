package com.linearity.deviceaddresstweaker.chaoxing;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.autofill.AutofillManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.app.HookAppClass;

import java.lang.reflect.Method;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookChaoxingClass {
    public static boolean paused = false;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String processHead, SharedPreferences sharedPreferences){
        if (!Objects.equals(processHead, "com.chaoxing.mobile")){return;}

        try {
            XposedBridge.hookAllMethods(
                    android.app.Application.class,
                    "onCreate",
                    new XC_MethodHook(1919810) {
                        @Override
                        public void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            String className = param.thisObject.getClass().getName();
                            String classNameLowerCase = className.toLowerCase();
                            LoggerLog("onCreate:" + className);
                            for (Method i:
                                    param.thisObject.getClass().getDeclaredMethods()){
                                String methodName = i.getName();
                                LoggerLog("onCreate:[" + className + "]" + methodName);
                                {
                                    XposedBridge.hookMethod(i,
                                            new XC_MethodHook(114514) {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    if (Objects.equals(param.thisObject,null)){return;}
                                                    StringBuilder argSB = new StringBuilder();
                                                    argSB.append("[");
                                                    for (Object i:param.args){
                                                        if (i == null){
                                                            argSB.append("null|");
                                                            continue;
                                                        }
                                                        argSB.append(i.getClass().getName());
                                                        argSB.append("|");
                                                    }
                                                    argSB.append("]");
                                                    LoggerLog("[" + param.thisObject.getClass().getName() + "]Method Called:" + methodName);
                                                }
                                            });
                                }
                            }
                            if (classNameLowerCase.contains("chaoxing")){
                                LoggerLog("----Loading:" + processHead + "----");
                                DoHook2(lpparam,processHead,sharedPreferences);
                                LoggerLog("----Loaded: " + processHead + "----");
                            }
                        }
                    }
            );
        } catch (Exception e) {
            LoggerLog(e);
        }
    }
    public static void DoHook2(XC_LoadPackage.LoadPackageParam lpparam, String processHead, SharedPreferences sharedPreferences){
        LoggerLog("---Methods:com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity---");
        final String[] lastMethodName = {""};
        final Object[][] lastArgs = {new Object[]{}};
        final boolean[] methodArgGot = {false};

        final MotionEvent[] targMotionEvent = new MotionEvent[1];

        Method resumeVideoMethod = XposedHelpers.findMethodExact(
                XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity", lpparam.classLoader),
                "dispatchTouchEvent",
                android.view.MotionEvent.class
        );
        for (Method i:
                XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity", lpparam.classLoader).getDeclaredMethods()){
            String methodName = i.getName();
            LoggerLog(methodName);
            if (Objects.equals("x5",methodName)){
                XposedBridge.hookMethod(i,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                LoggerLog("[" + param.thisObject.getClass().getName() + "]Method Called:" + methodName);
                                if (Objects.equals(lastMethodName[0], "dispatchTouchEvent") && !methodArgGot[0]){
                                    methodArgGot[0] = true;
                                    targMotionEvent[0] = (MotionEvent) lastArgs[0][0];
                                }
                            }
                        });
                continue;
            }
            {
                XposedBridge.hookMethod(i,
                        new XC_MethodHook(114514) {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (Objects.equals(param.thisObject,null)){return;}
                                StringBuilder argSB = new StringBuilder();
                                argSB.append("[");
                                for (Object i:param.args){
                                    if (i == null){
                                        argSB.append("null|");
                                        continue;
                                    }
                                    argSB.append(i.getClass().getName());
                                    argSB.append("|");
                                }
                                argSB.append("]");
                                LoggerLog("[" + param.thisObject.getClass().getName() + "]Method Called:" + methodName);
                                lastMethodName[0] = methodName;
                                lastArgs[0] = param.args;
                            }
                        });
            }
        }
        LoggerLog("----------------------------------------------------------------------------");

        try {
            Method finalResumeVideoMethod = resumeVideoMethod;
            XposedBridge.hookAllMethods(
                    XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity", lpparam.classLoader),
                    "onPause",
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {

                            Activity activity = (Activity) param.thisObject;
//                            XposedHelpers.callMethod(activity,"onPause");
                            originalOnPause(activity);
                            return null;
                        }
                    }
            );
        }catch (Exception e){LoggerLog(e);}
        try {
            Method finalResumeVideoMethod = resumeVideoMethod;
            XposedBridge.hookAllMethods(
                    XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity", lpparam.classLoader),
                    "onStop",
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {

                            Activity activity = (Activity) param.thisObject;
//                            XposedHelpers.callMethod(activity,"onPause");
                            originalOnStop(activity,lpparam);
                            return null;
                        }
                    }
            );
        }catch (Exception e){LoggerLog(e);}
//        try {
//            Method finalResumeVideoMethod = resumeVideoMethod;
//            XposedBridge.hookAllMethods(
//                    XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity", lpparam.classLoader),
//                    "onPause",
//                    new XC_MethodHook(114514) {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.afterHookedMethod(param);
//                            Activity activity = (Activity) param.thisObject;
//                            if (Objects.equals(activity,null)){return;}
//                            XposedHelpers.callMethod(activity,"onResume");
//                            XposedHelpers.callMethod(activity,"onStart");
//                            XposedHelpers.callMethod(activity,"onRestart");
//                            XposedHelpers.callMethod(activity,"onStart");
//                            XposedHelpers.callMethod(activity,"onResume");
//                            if (methodArgGot[0]){
//
//                                finalResumeVideoMethod.invoke(activity,targMotionEvent[0]);
//                                XposedHelpers.findMethodExact(
//                                        XposedHelpers.findClass("com.chaoxing.mobile.player.course.CoursePlayerWithNoteActivity",lpparam.classLoader),
//                                        "x5"
//                                ).invoke(activity);
//                            }
//
//                        }
//                    }
//            );
//        }catch (Exception e){LoggerLog(e);}
    }

    public static void originalOnPause(Activity activity) throws Exception{
        Method activity_dispatchActivityPaused = XposedHelpers.findMethodExact(Activity.class, "dispatchActivityPaused");
        Method activity_notifyContentCaptureManagerIfNeeded = XposedHelpers.findMethodExact(Activity.class, "notifyContentCaptureManagerIfNeeded", int.class);
        Method activity_getCurrentFocus = XposedHelpers.findMethodExact(Activity.class, "getCurrentFocus");
        Method activity_getAutofillManager = XposedHelpers.findMethodExact(Activity.class, "getAutofillManager");
        Method view_canNotifyAutofillEnterExitEvent = XposedHelpers.findMethodExact(View.class, "canNotifyAutofillEnterExitEvent");
        activity_dispatchActivityPaused.invoke(activity);
        boolean mAutoFillResetNeeded = XposedHelpers.getBooleanField(activity,"mAutoFillResetNeeded");
        boolean mAutoFillIgnoreFirstResumePause = XposedHelpers.getBooleanField(activity,"mAutoFillIgnoreFirstResumePause");
        if (mAutoFillResetNeeded) {
            if (!mAutoFillIgnoreFirstResumePause) {
//                if (DEBUG_LIFECYCLE) Slog.v(TAG, "autofill notifyViewExited " + this);
                View focus = (View) activity_getCurrentFocus.invoke(activity);
                if (focus != null && ((boolean) view_canNotifyAutofillEnterExitEvent.invoke(focus))) {
                    ((AutofillManager) activity_getAutofillManager.invoke(activity)).notifyViewExited(focus);
                }
            } else {
                // reset after first pause()
//                if (DEBUG_LIFECYCLE) Slog.v(TAG, "autofill got first pause " + this);
                XposedHelpers.setBooleanField(activity,"mAutoFillIgnoreFirstResumePause",false);
            }
        }
        activity_notifyContentCaptureManagerIfNeeded.invoke(activity,XposedHelpers.getIntField(activity,"CONTENT_CAPTURE_PAUSE"));
        XposedHelpers.setBooleanField(activity,"mCalled", true);
    }

    public static void originalOnStop(Activity activity,XC_LoadPackage.LoadPackageParam lpparam) throws Exception{
        boolean mAutoFillResetNeeded = XposedHelpers.getBooleanField(activity,"mAutoFillResetNeeded");
        boolean mChangingConfigurations = XposedHelpers.getBooleanField(activity,"mChangingConfigurations");
        Method activity_dispatchActivityStopped = XposedHelpers.findMethodExact(Activity.class, "dispatchActivityStopped");
        Method activity_restoreAutofillSaveUi = XposedHelpers.findMethodExact(Activity.class, "restoreAutofillSaveUi");
        ActionBar activity_mActionBar = (ActionBar) XposedHelpers.getObjectField(activity,"mActionBar");
        Intent activity_mIntent = (Intent) XposedHelpers.getObjectField(activity, "mIntent");
        Method activity_getAutofillManager = XposedHelpers.findMethodExact(Activity.class, "getAutofillManager");
        Method actionBar_setShowHideAnimationEnabled = XposedHelpers.findMethodExact(ActionBar.class, "setShowHideAnimationEnabled",boolean.class);
        Class<?> ActivityTransitionStateClass = XposedHelpers.findClass("android.app.ActivityTransitionState", lpparam.classLoader);
//        Method ActivityTransitionStateClass_onStop = XposedHelpers.findMethodExact(ActivityTransitionStateClass, "onStop", Activity.class);
        Object activity_mActivityTransitionState = XposedHelpers.getObjectField(activity, "mActivityTransitionState");
        if (activity_mActionBar != null) {actionBar_setShowHideAnimationEnabled.invoke(activity_mActionBar,false);}
//        mActivityTransitionState.onStop(this);
        XposedHelpers.callMethod(activity_mActivityTransitionState, "onStop", activity);
        activity_dispatchActivityStopped.invoke(activity);
        XposedHelpers.setObjectField(activity,"mTranslucentCallback", null);
        XposedHelpers.setBooleanField(activity,"mCalled", true);

        if (mAutoFillResetNeeded) {
            // If stopped without changing the configurations, the response should expire.
            AutofillManager autofillManager = (AutofillManager) activity_getAutofillManager.invoke(activity);
            XposedHelpers.callMethod(autofillManager, "onInvisibleForAutofill", !mChangingConfigurations);
        } else if (activity_mIntent != null
                && activity_mIntent.hasExtra((String) XposedHelpers.getStaticObjectField(AutofillManager.class,"EXTRA_RESTORE_SESSION_TOKEN"))
                && activity_mIntent.hasExtra((String) XposedHelpers.getStaticObjectField(AutofillManager.class,"EXTRA_RESTORE_CROSS_ACTIVITY"))) {
            activity_restoreAutofillSaveUi.invoke(activity);
        }
        XposedHelpers.setBooleanField(activity,"mEnterAnimationComplete", false);
    }
}