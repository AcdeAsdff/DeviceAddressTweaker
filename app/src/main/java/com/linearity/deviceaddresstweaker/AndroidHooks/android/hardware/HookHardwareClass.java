package com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware;

import static com.linearity.utils.HookUtils.disableClass;
import static com.linearity.utils.HookUtils.disableClass_random;
import static com.linearity.utils.HookUtils.disableMethod;
import static com.linearity.utils.HookUtils.disableMethod_random;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;
import static com.linearity.utils.ReturnReplacements.random;
import static com.linearity.utils.ReturnReplacements.returnCantUseArrayList;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnFloatRandom;
import static com.linearity.utils.ReturnReplacements.returnIntegerOne;
import static com.linearity.utils.ReturnReplacements.returnIntegerRandom;
import static com.linearity.utils.ReturnReplacements.returnIntegerZero;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomBoolean;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnRandomUUID;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.uuid;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.SharedPreferences;
import android.hardware.ConsumerIrManager;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorAdditionalInfo;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.hardware.biometrics.BiometricManager;

import com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.input.HookInputDeviceBatteryStateClass;
import com.linearity.deviceaddresstweaker.AndroidHooks.android.hardware.usb.HookUSBClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public static boolean HookInput = true;
    public static boolean HookUSBPackage = true;
    public static boolean HookBiometrics = true;
    public static boolean HookLights = true;
    public static boolean HookFingerprints = true;
    public static boolean HookConsumerIrManager = true;

    
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
    public static final String[] biometricClassNames = new String[]{
        "android.hardware.biometricsSensorPropertiesInternal.java",
                "android.hardware.biometricsSensorProperties.java",
                "android.hardware.biometricsSensorLocationInternal.java",
                "android.hardware.biometricsPromptInfo.java",
                "android.hardware.biometricsParentalControlsUtilsInternal.java",
                "android.hardware.biometricsCryptoObject.java",
                "android.hardware.biometricsComponentInfoInternal.java",
                "android.hardware.biometricsBiometricTestSession.java",
                "android.hardware.biometricsBiometricSourceType.java",
                "android.hardware.biometricsBiometricPrompt.java",
                "android.hardware.biometricsBiometricOverlayConstants.java",
                "android.hardware.biometricsBiometricFingerprintConstants.java",
                "android.hardware.biometricsBiometricFaceConstants.java",
                "android.hardware.biometricsBiometricConstants.java",
                "android.hardware.biometricsBiometricAuthenticator.java",
                "android.hardware.biometricsBiometricManager.java",
    };
    public static final String[] lightsClasses = new String[]{
            "android.hardware.lights.SystemLightsManager.java",
            "android.hardware.lights.LightsRequest.java",
            "android.hardware.lights.LightsManager.java",
            "android.hardware.lights.LightState.java",
            "android.hardware.lights.Light.java",
    };
    public static final String[] fingerPrintClasses = new String[]{
            "android.hardware.fingerprint.FingerprintStateListener.java",
            "android.hardware.fingerprint.FingerprintServiceReceiver.java",
            "android.hardware.fingerprint.FingerprintSensorPropertiesInternal.java",
            "android.hardware.fingerprint.FingerprintSensorProperties.java",
            "android.hardware.fingerprint.Fingerprint.java",
            "android.hardware.fingerprint.FingerprintManager.java",
    };
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> hookClass;
        HookHardware = sharedPreferences.getBoolean("HookHardwareClass_HookHardware", true);
        HookSensor = sharedPreferences.getBoolean("HookHardwareClass_HookSensor", true);
        HookSensorManager = sharedPreferences.getBoolean("HookHardwareClass_HookSensorManager", true);
        HookSensorEvent = sharedPreferences.getBoolean("HookHardwareClass_HookSensorEvent", true);
        HookGeomagneticField = sharedPreferences.getBoolean("HookHardwareClass_HookGeomagneticField", true);
        HookInput = sharedPreferences.getBoolean("HookHardwareClass_input", true);
        HookUSBPackage = sharedPreferences.getBoolean("HookHardwareClass_usb",true);
        HookBiometrics = sharedPreferences.getBoolean("HookHardwareClass_HookBiometrics",true);
        HookLights = sharedPreferences.getBoolean("HookHardwareClass_HookLights",true);
        HookFingerprints = sharedPreferences.getBoolean("HookHardwareClass_HookFingerprints",true);
        HookConsumerIrManager = sharedPreferences.getBoolean("HookHardwareClass_ConsumerIrManager",true);
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
                        {
                            XposedBridge.hookAllMethods(hookClass, "getReportingMode",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getHighestDirectReportRateLevel",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "isDirectChannelTypeSupported",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "getMaxLengthValuesArray",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getName",returnRandomStr20);
                            XposedBridge.hookAllMethods(hookClass, "getVendor",returnRandomStr20);
                            XposedBridge.hookAllMethods(hookClass, "getType",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getVersion",returnFloatRandom);
                            XposedBridge.hookAllMethods(hookClass, "getMaximumRange",returnFloatRandom);
                            XposedBridge.hookAllMethods(hookClass, "getResolution",returnFloatRandom);
                            XposedBridge.hookAllMethods(hookClass, "getPower",returnFloatRandom);
                            XposedBridge.hookAllMethods(hookClass, "getMinDelay",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getFifoReservedEventCount",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getFifoMaxEventCount",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getStringType",returnRandomStr20);
                            XposedBridge.hookAllMethods(hookClass, "getUuid",returnRandomUUID);
                            XposedBridge.hookAllMethods(hookClass, "getId",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "getRequiredPermission",returnRandomStr20);
                            XposedBridge.hookAllMethods(hookClass, "getMaxDelay",returnIntegerRandom);
                            XposedBridge.hookAllMethods(hookClass, "isWakeUpSensor",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "isDynamicSensor",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "isAdditionalInfoSupported",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "isDataInjectionSupported",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "setRange",returnNull);
                            XposedBridge.hookAllMethods(hookClass, "toString", new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param){
                                    return "{Sensor name=\""+ random.nextInt(Integer.MAX_VALUE) 
                                            +"\", vendor=\""+ random.nextInt(Integer.MAX_VALUE) 
                                            +"\", version="+random.nextInt(Integer.MAX_VALUE)
                                            +", type="
                                            +random.nextInt(Integer.MAX_VALUE)
                                            +", maxRange=" 
                                            + random.nextFloat() + ", resolution= " 
                                            + random.nextFloat() + ", power=" 
                                            + random.nextFloat() + ", minDelay="+random.nextInt(Integer.MAX_VALUE)+"}";
                                }
                            });
                            XposedBridge.hookAllMethods(hookClass, "setUuid",returnNull);
                            XposedBridge.hookAllMethods(hookClass, "setType",returnRandomBoolean);
                            XposedBridge.hookAllMethods(hookClass, "setId",returnNull);
                        }
                    }catch (Exception e){
                        LoggerLog(e);
                    }
                }
                hookClass = XposedHelpers.findClassIfExists(SensorAdditionalInfo.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
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
                                        param.args[1] = random.nextInt(Integer.MAX_VALUE);
                                        param.args[2] = random.nextLong();
                                        param.args[3] = new float[]{
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
                                                random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(),};
                                    }
                                });
                    }
                } 
            }
            if (HookSensorManager) {
                Class<?>[] classes = new Class[]{
                        XposedHelpers.findClassIfExists(android.hardware.SensorManager.class.getName(),lpparam.classLoader),
                        XposedHelpers.findClassIfExists("android.hardware.SystemSensorManager",lpparam.classLoader),
                        XposedHelpers.findClassIfExists("android.hardware.input.InputSensorManager",lpparam.classLoader),
                        XposedHelpers.findClassIfExists("com.android.systemui.util.sensors.AsyncSensorManager",lpparam.classLoader)
                };
                for (Class cls:classes){

                    if (cls != null) {
                        try {
                            for (Method m:cls.getDeclaredMethods()){
                                if (m.getName().equals("getOrientation")){
                                    XposedBridge.hookMethod(m,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param){
                                                    float[] result = (float[]) param.args[1];
                                                    for (int i=0;i<result.length;i++){
                                                        result[i]=random.nextFloat();
                                                    }
                                                    return result;
                                                }
                                            }
                                    );
                                }
                                else if (m.getName().equals("getRotationMatrix")){
                                    XposedBridge.hookMethod(m,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param){
                                                    float[] result = (float[]) param.args[0];
                                                    for (int i=0;i<result.length;i++){
                                                        result[i]=random.nextFloat();
                                                    }
                                                    return true;
                                                }
                                            }
                                    );
                                }
                                else if (m.getName().equals("remapCoordinateSystem")){
                                    XposedBridge.hookMethod(m,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param){
                                                    float[] result = (float[]) param.args[3];
                                                    for (int i=0;i<result.length;i++){
                                                        result[i]=random.nextFloat();
                                                    }
                                                    return true;
                                                }
                                            }
                                    );
                                }
                                else if (m.getName().equals("remapCoordinateSystemImpl")){
                                    XposedBridge.hookMethod(m,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param){
                                                    float[] result = (float[]) param.args[3];
                                                    for (int i=0;i<result.length;i++){
                                                        result[i]=random.nextFloat();
                                                    }
                                                    return true;
                                                }
                                            }
                                    );
                                }
                                else if (m.getName().equals("getInclination")){
                                    XposedBridge.hookMethod(m,
                                            new XC_MethodReplacement(114514) {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param){
                                                    float[] result = (float[]) param.args[0];
                                                    for (int i=0;i<result.length;i++){
                                                        result[i]=random.nextFloat();
                                                    }
                                                    return random.nextFloat();
                                                }
                                            }
                                    );
                                }
                                else {
                                    disableMethod_random(m,cls);
                                }
                            }

                        }catch (Exception e){LoggerLog(e);}
                    }
                }
            }
            if (HookInput){
                HookInputDeviceBatteryStateClass.DoHook(lpparam,procHead,sharedPreferences);
            }
            if (HookUSBPackage){
                HookUSBClass.DoHook(lpparam,procHead,sharedPreferences);
            }
            if (HookBiometrics){
                for (String className:biometricClassNames){
                    hookClass = XposedHelpers.findClassIfExists(className,lpparam.classLoader);
                    if (hookClass != null){
                        disableClass_random(hookClass);
                    }

                }
            }
            if (HookLights){
                for (String className:lightsClasses){
                    hookClass = XposedHelpers.findClassIfExists(className,lpparam.classLoader);
                    if (hookClass != null){
                        disableClass_random(hookClass);
                    }

                }
            }
            if (HookFingerprints){
                for (String className:fingerPrintClasses){
                    hookClass = XposedHelpers.findClassIfExists(className,lpparam.classLoader);
                    if (hookClass != null){
                        disableClass_random(hookClass);
                    }
                }
            }
            if (HookConsumerIrManager){
                hookClass = XposedHelpers.findClassIfExists(ConsumerIrManager.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    disableClass_random(hookClass);
                    hookClass = XposedHelpers.findClassIfExists(ConsumerIrManager.CarrierFrequencyRange.class.getName(),lpparam.classLoader);
                    disableClass_random(hookClass);
                }
            }
        }
    }
}
