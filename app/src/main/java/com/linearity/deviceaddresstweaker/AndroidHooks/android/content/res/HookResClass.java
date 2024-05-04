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

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;

public class HookResClass {
    public static boolean HookRes = true;
    public static boolean HookResources = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookRes = sharedPreferences.getBoolean("HookContentClass_HookResClass_HookRes", true);
        HookResources = sharedPreferences.getBoolean("HookContentClass_HookResClass_HookResources", true);
        if (HookRes){
            if (HookResources) {
//      android.content.res.Resources.class getConfiguration()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.content.res.Resources.class.getName(),
                            lpparam.classLoader,
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
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.content.res.Resources.class getSystem()
//                try {
//                    XposedHelpers.findAndHookMethod(
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
                try {
                    XposedHelpers.findAndHookMethod(
                            android.content.res.Resources.class.getName(),
                            lpparam.classLoader,
                            "getResourcePackageName",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
                                    if (param.getResult() != null) {
                                        String result = (String) param.getResult();
//                                result.getResourcePackageName()
                                        if (!result.equals(lpparam.packageName)) {
                                            LoggerLog(lpparam.packageName + "调用getResourcePackageName()" + result);
//                                            param.setResult(null);
                                        }
                                    }
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.content.res.Resources.class getIdentifier(String,String,String)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.content.res.Resources.class.getName(),
                            lpparam.classLoader,
                            "getIdentifier",
                            String.class,
                            String.class,
                            String.class,
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
                                    ) {
//                                        LoggerLog(lpparam.packageName + "调用getIdentifier(String,String,String)" + param.getResult());
//                                        LoggerLog(lpparam.packageName + "调用getIdentifier(String,String,String) args:" + Arrays.toString(param.args));
                                        param.setResult(random.nextInt(Integer.MAX_VALUE));
                                    }
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
