package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware;

import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnIntegerOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.uuid;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookHardwareClass {

    public static boolean HookHardware = true;
    public static boolean HookSensor = true;
    public static boolean HookSensorManager = true;
    public static boolean HookSensorEvent = true;
    public static boolean HookGeomagneticField = true;
    
    public static XC_MethodReplacement returnFloatZero = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param){
            return 0.f;
        }
    };

    public static XC_MethodReplacement returnFloatZeroDotOne = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param){
            return 0.1f;
        }
    };
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookHardware = sharedPreferences.getBoolean("HookHardwareClass_HookHardware", true);
        HookSensor = sharedPreferences.getBoolean("HookHardwareClass_HookSensor", true);
        HookSensorManager = sharedPreferences.getBoolean("HookHardwareClass_HookSensorManager", true);
        HookSensorEvent = sharedPreferences.getBoolean("HookHardwareClass_HookSensorEvent", true);
        HookGeomagneticField = sharedPreferences.getBoolean("HookHardwareClass_HookGeomagneticField", true);
        if (HookHardware){
            if (HookGeomagneticField) {
                hookClass = XposedHelpers.findClassIfExists(GeomagneticField.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        //GeomagneticField(float*3,long) constructor
                        {
                            XposedHelpers.findAndHookConstructor(
                                    hookClass,
                                    float.class,
                                    float.class,
                                    float.class,
                                    long.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
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
                        }
//            GeomagneticField.class computeSchmidtQuasiNormFactors(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "computeSchmidtQuasiNormFactors",
                                    int.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
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
                        }
//            GeomagneticField.class computeGeocentricCoordinates(float,float,float)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "computeGeocentricCoordinates",
                                    float.class, float.class, float.class,returnNull);
                        }
//            GeomagneticField.class getFieldStrength()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getFieldStrength",returnFloatZero);
                        }
//            GeomagneticField.class getHorizontalStrength()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getHorizontalStrength",returnFloatZero);
                        }
//            GeomagneticField.class getInclination()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getInclination",returnFloatZeroDotOne);
                        }
//            GeomagneticField.class getDeclination()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getDeclination",returnFloatZeroDotOne);
                        }
//            GeomagneticField.class getZ()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getZ",returnFloatZeroDotOne);
                        }
//            GeomagneticField.class getY()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getY",returnFloatZeroDotOne);
                        }
//            GeomagneticField.class getX()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getX",returnFloatZeroDotOne);
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
            if (HookSensor) {
                hookClass = XposedHelpers.findClassIfExists(android.hardware.Sensor.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        //                android.hardware.Sensor getReportingMode()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getReportingMode",returnIntegerOne

                            );
                        }
//                android.hardware.Sensor getHighestDirectReportRateLevel()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getHighestDirectReportRateLevel",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor isDirectChannelTypeSupported()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isDirectChannelTypeSupported",
                                    int.class,returnTrue

                            );
                        }
//                android.hardware.Sensor getMaxLengthValuesArray()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getMaxLengthValuesArray",
                                    Sensor.class,
                                    int.class,returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getName()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getName",returnRandomStr20

                            );
                        }
//                android.hardware.Sensor getVendor()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getVendor",returnRandomStr20

                            );
                        }
//                android.hardware.Sensor getType()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getType",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getVersion()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getVersion",returnFloatZero

                            );
                        }
//                android.hardware.Sensor getMaximumRange()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getMaximumRange",returnFloatZero

                            );
                        }
//                android.hardware.Sensor getResolution()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getResolution",returnFloatZero

                            );
                        }
//                android.hardware.Sensor getPower()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getPower",returnFloatZero

                            );
                        }
//                android.hardware.Sensor getMinDelay()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getMinDelay",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getFifoReservedEventCount()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getFifoReservedEventCount",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getFifoMaxEventCount()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getFifoMaxEventCount",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getStringType()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getStringType",returnRandomStr20

                            );
                        }
//                android.hardware.Sensor getUuid()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getUuid",
                                    new XC_MethodReplacement(114514) {

                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) {
                                            return uuid;
                                        }
                                    }

                            );
                        }
//                android.hardware.Sensor getId()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getId",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor getRequiredPermission()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getRequiredPermission",returnRandomStr20

                            );
                        }
//                android.hardware.Sensor getMaxDelay()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getMaxDelay",returnIntegerZero

                            );
                        }
//                android.hardware.Sensor isWakeUpSensor()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isWakeUpSensor",returnTrue

                            );
                        }
//                android.hardware.Sensor isDynamicSensor()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isDynamicSensor",returnTrue

                            );
                        }
//                android.hardware.Sensor isAdditionalInfoSupported()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isAdditionalInfoSupported",returnTrue

                            );
                        }
//                android.hardware.Sensor isDataInjectionSupported()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isDataInjectionSupported",returnTrue

                            );
                        }
//                android.hardware.Sensor setRange()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "setRange",
                                    float.class, float.class,returnNull

                            );
                        }
//                android.hardware.Sensor toString()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {

                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return "{Sensor name=\"114514\", vendor=\"114514\", version=0, type=0, maxRange=" + 0f + ", resolution= " + 0f + ", power=" + 0f + ", minDelay=0}";
                                        }
                                    }

                            );
                        }
//                android.hardware.Sensor setType()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "setType",
                                    int.class,returnTrue

                            );
                        }
//                android.hardware.Sensor setUuid()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "setUuid",
                                    long.class, long.class,returnNull

                            );
                        }
////                android.hardware.Sensor setId()
//            try{
                findAndHookMethodIfExists(
                        hookClass,
                        "setId",
                        int.class,returnNull

                );
//            }catch (Exception e){LoggerLog(e);}
                    }catch (Exception e){
                        LoggerLog(e);
                    }
                }
            }
            if (HookSensorEvent) {
                {
                    Class<?> sensorEvent = XposedHelpers.findClassIfExists(SensorEvent.class.getName(), lpparam.classLoader);
                    if (sensorEvent != null){
                        XposedHelpers.findAndHookConstructor(
                                sensorEvent,
                                Sensor.class,
                                int.class,
                                long.class,
                                float[].class,
                                new XC_MethodHook(114514) {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param){
//                                super.beforeHookedMethod(param);
                                        param.args[1] = 1;
                                        param.args[2] = 1L;
                                        param.args[3] = new float[]{1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f, 1f, 1f, 4f, 5f, 1f, 4f,};
                                    }
                                });
                    }
                } 
            }
            if (HookSensorManager) {
                hookClass = XposedHelpers.findClassIfExists(android.hardware.SensorManager.class.getName(),lpparam.classLoader);
                if (hookClass != null) {
                    try {
//            android.hardware.SensorManager getSensors()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSensors",returnIntegerZero
                            );
                        }
//            android.hardware.SensorManager getSensorList(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getSensorList",
                                    int.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return new ArrayList<Sensor>();
                                        }
                                    }
                            );
                        }
//            android.hardware.SensorManager getDynamicSensorList(int)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getDynamicSensorList",
                                    int.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return new ArrayList<Sensor>();
                                        }
                                    }
                            );
                        }
//            android.hardware.SensorManager getDefaultSensor(int)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "getDefaultSensor",returnNull
                            );
                        }
//            android.hardware.SensorManager registerListener(SensorListener,int)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "registerListener",returnTrue
                            );
                        }
//            android.hardware.SensorManager unregisterListener(SensorListener,int)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "unregisterListener",returnNull
                            );
                        }
//            android.hardware.SensorManager flush(SensorEventListener)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "flush",
                                    SensorEventListener.class,returnTrue
                            );
                        }
//            android.hardware.SensorManager createDirectChannel(MemoryFile)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "createDirectChannel",returnNull
                            );
                        }
//            android.hardware.SensorManager registerDynamicSensorCallback(DynamicSensorCallback)
                        {
                            XposedBridge.hookAllMethods(hookClass,
                                    "registerDynamicSensorCallback",returnNull
                            );
                        }
//            android.hardware.SensorManager unregisterDynamicSensorCallback(DynamicSensorCallback)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "unregisterDynamicSensorCallback",
                                    SensorManager.DynamicSensorCallback.class,returnNull
                            );
                        }
//            android.hardware.SensorManager isDynamicSensorDiscoverySupported()
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isDynamicSensorDiscoverySupported",returnFalse
                            );
                        }
//            android.hardware.SensorManager getRotationMatrix(float[]*4)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRotationMatrix",
                                    float[].class, float[].class, float[].class, float[].class,returnTrue
                            );
                        }
//            android.hardware.SensorManager getInclination(float[])
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getInclination",
                                    float[].class,returnFloatZero
                            );
                        }
//            android.hardware.SensorManager remapCoordinateSystem(float[],int,int,float[])
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "remapCoordinateSystem",
                                    float[].class, int.class, int.class, float[].class,returnTrue
                            );
                        }
//            android.hardware.SensorManager getOrientation(float[],float[])
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getOrientation",
                                    float[].class, float[].class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return new float[]{0f, 0f, 0f};
                                        }
                                    }
                            );
                        }
//            android.hardware.SensorManager getAltitude(float,float)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getAltitude",
                                    float.class, float.class,returnFloatZero
                            );
                        }
//            android.hardware.SensorManager getAngleChange(float[]*3)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getAngleChange",
                                    float[].class, float[].class, float[].class,returnNull
                            );
                        }
//            android.hardware.SensorManager getRotationMatrixFromVector(float[]*2)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRotationMatrixFromVector",
                                    float[].class, float[].class,returnNull
                            );
                        }
//            android.hardware.SensorManager getQuaternionFromVector(float[]*2)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getQuaternionFromVector",
                                    float[].class, float[].class,returnNull
                            );
                        }
//            android.hardware.SensorManager requestTriggerSensor(TriggerEventListener,Sensor)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "requestTriggerSensor",
                                    TriggerEventListener.class, Sensor.class,returnTrue
                            );
                        }
//            android.hardware.SensorManager cancelTriggerSensor(TriggerEventListener,Sensor)
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "cancelTriggerSensor",
                                    TriggerEventListener.class, Sensor.class,returnTrue
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }
        }
    }
}
