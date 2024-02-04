package com.linearity.deviceaddresstweaker.bilibili;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookBilibiliClass {
    public static boolean HookBilibiliClassFlag = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        if (HookBilibiliClassFlag){
            if (lpparam.processName.split(":")[0].contains("tv.danmaku.bili")){
                try {

                    try {
                        Class<?> hookClass = XposedHelpers.findClassIfExists("com.bilibili.ad.adview.videodetail.upper.common.CommonHolderSmallNew", lpparam.classLoader);
                        if (hookClass != null){
                            XposedBridge.hookAllConstructors(
                                    hookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : hookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Byte.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return (byte) 0;
                                                }
                                            }
                                    );
                                }else if(returnType.isInstance(Object.class)){
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );

                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> hookClass = XposedHelpers.findClassIfExists("com.bilibili.ad.adview.videodetail.upper.c", lpparam.classLoader);
                        Class<?> newInstanceClass = XposedHelpers.findClassIfExists("com.bilibili.ad.adview.videodetail.upper.UpperHolderNone", lpparam.classLoader);

                        if (hookClass != null && newInstanceClass != null){
                            Object staticObj = XposedHelpers.getStaticObjectField(newInstanceClass,"s");
                            Method m = XposedHelpers.findMethodExact("com.bilibili.ad.adview.videodetail.upper.UpperHolderNone$a",lpparam.classLoader,"a",ViewGroup.class);
                            XposedBridge.hookAllMethods(hookClass, "b", new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return m.invoke(staticObj,param.args[0]);

                            });
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> XMPushServiceClass = XposedHelpers.findClassIfExists("com.bilibili.push.generated.PushFactoryImp", lpparam.classLoader);
                        if (XMPushServiceClass != null){
                            XposedHelpers.findAndHookMethod(XMPushServiceClass, "create", String.class, new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            });
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> hookClass = XposedHelpers.findClassIfExists("com.bilibili.adcommon.biz.AdAbsView", lpparam.classLoader);
                        if (hookClass != null){
                            XposedBridge.hookAllConstructors(
                                    hookClass,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            ((View) param.args[0]).setVisibility(View.INVISIBLE);
                                            ViewGroup.LayoutParams layoutParams = ((View) param.args[0]).getLayoutParams();
                                            if (layoutParams != null){
                                                layoutParams.height = 0;
                                                layoutParams.width = 0;
                                            }
                                        }
                                    }
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> AdMarkLayoutClass = XposedHelpers.findClassIfExists("com.bilibili.adcommon.basic.marker.AdMarkLayout", lpparam.classLoader);
                        if (AdMarkLayoutClass != null){
                            XposedBridge.hookAllConstructors(
                                    AdMarkLayoutClass,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            ((ViewGroup) param.thisObject).setVisibility(View.INVISIBLE);
                                            ViewGroup.LayoutParams layoutParams = ((View) param.thisObject).getLayoutParams();
                                            if (layoutParams != null){
                                                layoutParams.height = 0;
                                                layoutParams.width = 0;
                                            }
                                        }
                                    }
                            );
                            for (Method method : AdMarkLayoutClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                } else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                            }
                        }

                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> AdUpperRootFrameLayoutClass = XposedHelpers.findClassIfExists("com.bilibili.ad.adview.videodetail.upper.AdUpperRootFrameLayout", lpparam.classLoader);
                        if (AdUpperRootFrameLayoutClass != null){
                            XposedBridge.hookAllConstructors(
                                    AdUpperRootFrameLayoutClass,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            ((ViewGroup) param.thisObject).setVisibility(View.INVISIBLE);
                                            ViewGroup.LayoutParams layoutParams = ((View) param.thisObject).getLayoutParams();
                                            if (layoutParams != null){
                                                layoutParams.height = 0;
                                                layoutParams.width = 0;
                                            }
                                        }
                                    }
                            );
                            for (Method method : AdUpperRootFrameLayoutClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                } else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> AdQualityInfoViewClass = XposedHelpers.findClassIfExists("com.bilibili.ad.adview.widget.AdQualityInfoView", lpparam.classLoader);
                        if (AdQualityInfoViewClass != null){
                            XposedBridge.hookAllConstructors(
                                    AdQualityInfoViewClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            ((ViewGroup) param.thisObject).setVisibility(View.INVISIBLE);
                                            ViewGroup.LayoutParams layoutParams = ((View) param.thisObject).getLayoutParams();
                                            if (layoutParams != null){
                                                layoutParams.height = 0;
                                                layoutParams.width = 0;
                                            }
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : AdQualityInfoViewClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                } else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> AdTintRelativeLayoutClass = XposedHelpers.findClassIfExists("com.bilibili.adcommon.widget.AdTintRelativeLayout", lpparam.classLoader);
                        if (AdTintRelativeLayoutClass != null){
                            XposedBridge.hookAllConstructors(
                                    AdTintRelativeLayoutClass,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            ((ViewGroup) param.thisObject).setVisibility(View.INVISIBLE);
                                            ViewGroup.LayoutParams layoutParams = ((View) param.thisObject).getLayoutParams();
                                            if (layoutParams != null){
                                                layoutParams.height = 0;
                                                layoutParams.width = 0;
                                            }
                                        }
                                    }
                            );
                            for (Method method : AdTintRelativeLayoutClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                } else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> AdContentClass = XposedHelpers.findClassIfExists("com.bilibili.adcommon.basic.model.SourceContent$AdContent", lpparam.classLoader);
                        if (AdContentClass != null){
                            XposedBridge.hookAllConstructors(
                                    AdContentClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : AdContentClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<String>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesRemoteService", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesService", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesLocalService", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.d", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.d$d", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.CrashReport", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.a", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.b", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.c", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.d", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.e", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.crashreport.CrashReporter", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                    try {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.teenagersmode.TeenagersMode", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllConstructors(
                                    HookClass,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;
                                        }
                                    }
                            );
                            for (Method method : HookClass.getDeclaredMethods()) {
                                Class<?> returnType = method.getReturnType();
                                if (returnType.equals(Void.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return null;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Boolean.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return false;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Long.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0L;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(Integer.TYPE)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return 0;
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(String.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return "";
                                                }
                                            }
                                    );
                                }
                                else if (returnType.equals(List.class)) {
                                    XposedBridge.hookMethod(
                                            method,
                                            new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    return new ArrayList<>();
                                                }
                                            }
                                    );
                                }
                            }
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
