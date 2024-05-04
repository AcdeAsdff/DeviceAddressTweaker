package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.uuid;

import android.hardware.GeomagneticField;
import android.hardware.HardwareBuffer;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.os.Handler;
import android.os.MemoryFile;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;

public class HookHardwareClass {

    public static boolean HookHardware = true;
    public static boolean HookSensor = true;
    public static boolean HookSensorManager = true;
    public static boolean HookSensorEvent = true;
    public static boolean HookGeomagneticField = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        HookHardware = sharedPreferences.getBoolean("HookHardwareClass_HookHardware", true);
        HookSensor = sharedPreferences.getBoolean("HookHardwareClass_HookSensor", true);
        HookSensorManager = sharedPreferences.getBoolean("HookHardwareClass_HookSensorManager", true);
        HookSensorEvent = sharedPreferences.getBoolean("HookHardwareClass_HookSensorEvent", true);
        HookGeomagneticField = sharedPreferences.getBoolean("HookHardwareClass_HookGeomagneticField", true);
        if (HookHardware){
            if (HookGeomagneticField) {
                //GeomagneticField(float*3,long) constructor
                try {
                    Class<?> geomagneticField = XposedHelpers.findClass(GeomagneticField.class.getName(), lpparam.classLoader);
                    XposedHelpers.findAndHookConstructor(
                            geomagneticField,
                            float.class,
                            float.class,
                            float.class,
                            long.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                super.(param);
                                    if ((float) param.args[0] != 1f
                                            || (float) param.args[1] != 1f
                                            || (float) param.args[2] != 1f
                                            || (long) param.args[3] != 1L) {
                                        param.args[0] = 1f;
                                        param.args[1] = 1f;
                                        param.args[2] = 1f;
                                        param.args[3] = 1L;
                                    }
                                    return new GeomagneticField((float) param.args[0], (float) param.args[1], (float) param.args[2], (long) param.args[3]);
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class computeSchmidtQuasiNormFactors(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "computeSchmidtQuasiNormFactors",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                super.(param);
                                    int size = (int) param.args[0] + 1;
                                    float[][] result = new float[size][size];
                                    for (int i = 0; i < size; i++) {
                                        for (int j = 0; i < size; i++) {
                                            result[i][j] = 1f;
                                        }
                                    }
                                    return result;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class computeGeocentricCoordinates(float,float,float)
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "computeGeocentricCoordinates",
                            float.class, float.class, float.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getFieldStrength()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getFieldStrength",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getHorizontalStrength()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getHorizontalStrength",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getInclination()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getInclination",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0.1f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getDeclination()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getDeclination",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0.1f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getZ()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getZ",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0.1f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getY()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getY",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0.1f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            GeomagneticField.class getX()
                try {
                    XposedHelpers.findAndHookMethod(
                            GeomagneticField.class.getName(),
                            lpparam.classLoader,
                            "getX",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 0.1f;
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookSensor) {
//                android.hardware.Sensor getReportingMode()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getReportingMode",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor getReportingMode()" + param.getResult());
                                    return 1;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getHighestDirectReportRateLevel()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getHighestDirectReportRateLevel",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor getHighestDirectReportRateLevel(int)" + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor isDirectChannelTypeSupported()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "isDirectChannelTypeSupported",
                            int.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor isDirectChannelTypeSupported()" + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getMaxLengthValuesArray()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getMaxLengthValuesArray",
                            Sensor.class,
                            int.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor getMaxLengthValuesArray()" + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getName()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getName",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor getName()" + param.getResult());
                                    return getRandomString(20);
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getVendor()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getVendor",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor getVendor()" + param.getResult());
                                    return getRandomString(20);
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getType()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getType",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getVersion()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getVersion",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return (float) 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getMaximumRange()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getMaximumRange",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return (float) 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getResolution()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getResolution",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return (float) 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getPower()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getPower",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return (float) 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getMinDelay()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getMinDelay",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getFifoReservedEventCount()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getFifoReservedEventCount",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getFifoMaxEventCount()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getFifoMaxEventCount",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getStringType()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getStringType",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return getRandomString(20);
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getUuid()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getUuid",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return uuid;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getId()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getId",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getRequiredPermission()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getRequiredPermission",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return getRandomString(20);
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor getMaxDelay()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "getMaxDelay",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor isWakeUpSensor()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "isWakeUpSensor",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor isDynamicSensor()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "isDynamicSensor",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor isAdditionalInfoSupported()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "isAdditionalInfoSupported",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor isDataInjectionSupported()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "isDataInjectionSupported",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor setRange()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "setRange",
                            float.class, float.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor toString()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "toString",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return "{Sensor name=\"114514\", vendor=\"114514\", version=0, type=0, maxRange=" + 0f + ", resolution= " + 0f + ", power=" + 0f + ", minDelay=0}";
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor setType()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "setType",
                            int.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//                android.hardware.Sensor setUuid()
                try {
                    XposedHelpers.findAndHookMethod(
                            Class.forName("android.hardware.Sensor"),
                            "setUuid",
                            long.class, long.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }

                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
////                android.hardware.Sensor setId()
//            try{
//                XposedHelpers.findAndHookMethod(
//                        Class.forName("android.hardware.Sensor"),
//                        "setId",
//                        int.class,
//                        new XC_MethodReplacement(114514) {
//
//                            @Override
//                            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                //StringBuilder paramsName = new StringBuilder(" ");
//                                //for (Object i:param.args){
//                                //    paramsName.append(i.getClass().getName());
//                                //}
//                                //LoggerLog(lpparam.packageName + "调用android.hardware.Sensor " + param.method.getName() + paramsName.toString() + param.getResult());
//                                return null;
//                            }
//                        }
//
//                );
//            }catch (Exception e){LoggerLog(e);}
            }
            if (HookSensorEvent) {
                try {
                    Class<?> sensorEvent = XposedHelpers.findClass(SensorEvent.class.getName(), lpparam.classLoader);
                    XposedHelpers.findAndHookConstructor(
                            sensorEvent,
                            Sensor.class,
                            int.class,
                            long.class,
                            float[].class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                super.beforeHookedMethod(param);
                                    param.args[1] = 1;
                                    param.args[2] = 1L;
                                    param.args[3] = new float[]{1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f,};
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookSensorManager) {
//            android.hardware.SensorManager getSensors()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getSensors",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getSensorList(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getSensorList",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return new ArrayList<Sensor>();
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getDynamicSensorList(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getDynamicSensorList",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return new ArrayList<Sensor>();
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getDefaultSensor(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getDefaultSensor",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getDefaultSensor(int,boolean)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getDefaultSensor",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorListener,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorListener.class,
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorListener,int,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorListener.class,
                            int.class, int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager unregisterListener(SensorListener,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "unregisterListener",
                            SensorListener.class,
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager unregisterListener(SensorListener)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "unregisterListener",
                            SensorListener.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager unregisterListener(SensorEventListener)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "unregisterListener",
                            SensorEventListener.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager unregisterListener(SensorEventListener,Sensor)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "unregisterListener",
                            SensorEventListener.class,
                            Sensor.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorEventListener,Sensor,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorEventListener.class,
                            Sensor.class, int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorEventListener,Sensor,int,int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorEventListener.class,
                            Sensor.class, int.class, int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorEventListener,Sensor,int,Handler)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorEventListener.class,
                            Sensor.class, int.class, Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerListener(SensorEventListener,Sensor,int,int,Handler)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerListener",
                            SensorEventListener.class,
                            Sensor.class, int.class, int.class, Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager flush(SensorEventListener)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "flush",
                            SensorEventListener.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager createDirectChannel(MemoryFile)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "createDirectChannel",
                            MemoryFile.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager createDirectChannel(HardwareBuffer)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "createDirectChannel",
                            HardwareBuffer.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerDynamicSensorCallback(DynamicSensorCallback)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerDynamicSensorCallback",
                            SensorManager.DynamicSensorCallback.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager registerDynamicSensorCallback(DynamicSensorCallback,Handler)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "registerDynamicSensorCallback",
                            SensorManager.DynamicSensorCallback.class,
                            Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager unregisterDynamicSensorCallback(DynamicSensorCallback)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "unregisterDynamicSensorCallback",
                            SensorManager.DynamicSensorCallback.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager isDynamicSensorDiscoverySupported()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "isDynamicSensorDiscoverySupported",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getRotationMatrix(float[]*4)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getRotationMatrix",
                            float[].class, float[].class, float[].class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getInclination(float[])
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getInclination",
                            float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0f;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager remapCoordinateSystem(float[],int,int,float[])
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "remapCoordinateSystem",
                            float[].class, int.class, int.class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getOrientation(float[],float[])
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getOrientation",
                            float[].class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return new float[]{0f, 0f, 0f};
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getAltitude(float,float)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getAltitude",
                            float.class, float.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return 0f;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getAngleChange(float[]*3)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getAngleChange",
                            float[].class, float[].class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getRotationMatrixFromVector(float[]*2)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getRotationMatrixFromVector",
                            float[].class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager getQuaternionFromVector(float[]*2)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "getQuaternionFromVector",
                            float[].class, float[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return null;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager requestTriggerSensor(TriggerEventListener,Sensor)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "requestTriggerSensor",
                            TriggerEventListener.class, Sensor.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//            android.hardware.SensorManager cancelTriggerSensor(TriggerEventListener,Sensor)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.hardware.SensorManager.class.getName(),
                            lpparam.classLoader,
                            "cancelTriggerSensor",
                            TriggerEventListener.class, Sensor.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    //StringBuilder paramsName = new StringBuilder(" ");
                                    //for (Object i:param.args){
                                    //    paramsName.append(i.getClass().getName());
                                    //}
                                    //LoggerLog(lpparam.packageName + "调用android.hardware.SensorManager " + param.method.getName() + paramsName.toString() + param.getResult());
                                    return true;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
