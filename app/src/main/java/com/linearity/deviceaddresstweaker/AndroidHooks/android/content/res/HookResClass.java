package com.linearity.deviceaddresstweaker.AndroidHooks.android.content.res;

import static android.content.res.Configuration.COLOR_MODE_HDR_NO;
import static android.content.res.Configuration.HARDKEYBOARDHIDDEN_YES;
import static android.content.res.Configuration.KEYBOARDHIDDEN_YES;
import static android.content.res.Configuration.KEYBOARD_NOKEYS;
import static android.content.res.Configuration.NAVIGATIONHIDDEN_YES;
import static android.content.res.Configuration.NAVIGATION_NONAV;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static android.content.res.Configuration.SCREENLAYOUT_LONG_NO;

import android.content.res.Configuration;

import java.util.Arrays;
import java.util.Locale;

import de.robv.android.xposed.XC_MethodHook;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.random;
import static com.linearity.utils.ReturnReplacements.randomSmallDouble;

public class HookResClass {
    public static boolean HookRes = true;
    public static boolean HookResources = true;
    public static DisplayMetrics defaultDisplayMetrics = null;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookRes = sharedPreferences.getBoolean("HookContentClass_HookResClass_HookRes", true);
        HookResources = sharedPreferences.getBoolean("HookContentClass_HookResClass_HookResources", true);
        if (HookRes){
            if (HookResources) {
                hookClass = XposedHelpers.findClassIfExists(android.content.res.Resources.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {//      android.content.res.Resources.class getConfiguration()
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getConfiguration"
                                    , new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                                            Configuration configuration = (Configuration) param.getResult();
                                            Configuration clone = new Configuration();

                                            clone.setTo(configuration);
                                            //                                    Parcel parcel = Parcel.obtain();
                                            //                                    configuration.writeToParcel(parcel, 0);
                                            //                                    parcel.setDataPosition(0);
                                            //                                    clone.readFromParcel(parcel);
                                            //                                    parcel.recycle();
                                            clone.setLocale(Locale.CHINA);
                                            //                            configuration.setToDefaults();
                                            //                                    configuration.setLocale(Locale.CHINA);
                                            clone.mcc = 1;
                                            clone.mnc = 1;
                                            clone.keyboardHidden = KEYBOARDHIDDEN_YES;
                                            clone.keyboard = KEYBOARD_NOKEYS;
                                            clone.hardKeyboardHidden = HARDKEYBOARDHIDDEN_YES;
                                            clone.navigation = NAVIGATION_NONAV;
                                            clone.navigationHidden = NAVIGATIONHIDDEN_YES;
                                            clone.orientation = ORIENTATION_PORTRAIT;
                                            clone.screenLayout = SCREENLAYOUT_LONG_NO;
                                            clone.colorMode = COLOR_MODE_HDR_NO;
                                            clone.fontScale = 1;
                                            //                                    clone.uiMode = UI_MODE_NIGHT_NO;
                                            param.setResult(clone);
                                        }
                                    });
                        }
                        //      android.content.res.Resources.class getSystem()
                        //                try {
                        //                    findAndHookMethodIfExists(
                        //                            android.content.res.Resources.class.getName(),
                        //                            lpparam.classLoader,
                        //                            "getSystem"
                        //                            , new XC_MethodHook(114514) {
                        //                                @Override
                        //                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        ////                        super.afterHookedMethod(param);
                        //                                    Resources result = (Resources) param.getResult();
                        ////                            LoggerLog(lpparam.packageName + "调用getSystem()" + result);
                        ////                            param.setResult(null);
                        //                                }
                        //                            });
                        //                } catch (Exception e) {
                        //                    LoggerLog(e);
                        //                }
                        //      android.content.res.Resources.class getResourcePackageName()
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getResourcePackageName",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() != null) {
                                                String result = (String) param.getResult();
                                                if (!result.contains(lpparam.packageName)) {
                                                    LoggerLog(lpparam.packageName + "调用getResourcePackageName()" + result);
                                                }
                                            }
                                        }
                                    });
                        }
                        //      android.content.res.Resources.class getIdentifier(String,String,String)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getIdentifier",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            //                        super.afterHookedMethod(param);
                                            String args = Arrays.toString(param.args).toLowerCase();
                                            //                                    param.setResult(random.nextInt(Integer.MAX_VALUE));
                                            if (args.contains("linearity")
                                                    || args.contains("lineage")
                                                    || args.contains("magisk")
                                                    || args.contains("posed")
                                                    || args.contains("google")
                                            ) {param.setResult(random.nextInt(Integer.MAX_VALUE));
                                            }
                                        }
                                    });
                    }

                        {
                            XposedBridge.hookAllMethods(hookClass, "getDisplayMetrics", new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    DisplayMetrics result = (DisplayMetrics) param.getResult();
                                    if (defaultDisplayMetrics == null){
                                        defaultDisplayMetrics = new DisplayMetrics();
                                        defaultDisplayMetrics.setTo(result);
                                    }
                                    result.density = defaultDisplayMetrics.density + (float) randomSmallDouble(result.density*0.001);
                                    result.scaledDensity = defaultDisplayMetrics.scaledDensity + (float) randomSmallDouble(result.scaledDensity*0.001);
                                    result.xdpi = defaultDisplayMetrics.xdpi + (float) randomSmallDouble(result.xdpi*0.001);
                                    result.ydpi = defaultDisplayMetrics.ydpi + (float) randomSmallDouble(result.ydpi*0.001);
                                    result.densityDpi = defaultDisplayMetrics.densityDpi+random.nextInt(10)*(random.nextBoolean()?-1:1);
                                    result.widthPixels = defaultDisplayMetrics.widthPixels+random.nextInt(10)*(random.nextBoolean()?-1:1);
                                    result.heightPixels = defaultDisplayMetrics.heightPixels+random.nextInt(10)*(random.nextBoolean()?-1:1);
//                                    LoggerLog(defaultDisplayMetrics.density+"|"+defaultDisplayMetrics.scaledDensity+"|"+defaultDisplayMetrics.xdpi+"|"+defaultDisplayMetrics.ydpi+"|"+defaultDisplayMetrics.densityDpi+"|"+defaultDisplayMetrics.widthPixels+"|"+defaultDisplayMetrics.heightPixels);
//                                    LoggerLog(result.density+"|"+result.scaledDensity+"|"+result.xdpi+"|"+result.ydpi+"|"+result.densityDpi+"|"+result.widthPixels+"|"+result.heightPixels);
                                }
                            });
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
        }
    }


}
