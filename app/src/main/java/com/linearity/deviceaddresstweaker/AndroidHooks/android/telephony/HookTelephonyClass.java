package com.linearity.deviceaddresstweaker.AndroidHooks.android.telephony;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.SIM_STATE_UNKNOWN;
import static android.telephony.TelephonyManager.UNINITIALIZED_CARD_ID;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import android.telecom.PhoneAccountHandle;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

public class HookTelephonyClass {
    public static boolean HookTelephony = true;
    public static boolean HookTelephonyManager = true;
    public static boolean HookGSMCellLocation = true;
    public static boolean HookCdmaCellLocation = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam) {
        if (HookTelephony){
            if (HookGSMCellLocation){//      android.telephony.gSM.GSMCellLocation.class getLac()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getLac",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getLac()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.gSM.GSMCellLocation.class getCid()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getCid",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getCid()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.gSM.GSMCellLocation.class getPsc()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.gsm.GsmCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getPsc",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.gSM.GSMCellLocation.class getPsc()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookCdmaCellLocation){//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLatitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLatitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLatitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLongitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationLongitude",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationLongitude()" + param.getResult());
                                    param.setResult(Integer.MAX_VALUE);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getBaseStationId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getBaseStationId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getBaseStationId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getSystemId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getSystemId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getSystemId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//      android.telephony.cdma.CdmaCellLocation.class getNetworkId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.cdma.CdmaCellLocation.class.getName(),
                            lpparam.classLoader,
                            "getNetworkId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    //LoggerLog(lpparam.packageName + "调用android.telephony.cdma.CdmaCellLocation.class getNetworkId()" + param.getResult());
                                    param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookTelephonyManager) {//      android.telephony.TelephonyManager.class getDeviceId()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceId",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceId()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getDeviceId(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceId",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceId(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getTypeAllocationCode()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getTypeAllocationCode",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getTypeAllocationCode()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getTypeAllocationCode(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getTypeAllocationCode",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getTypeAllocationCode(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class createForSubscriptionId(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "createForSubscriptionId",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用createForSubscriptionId(int)" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class createForPhoneAccountHandle(PhoneAccountHandle)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "createForPhoneAccountHandle",
                            PhoneAccountHandle.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用createForPhoneAccountHandle(PhoneAccountHandle)" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getDeviceSoftwareVersion()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDeviceSoftwareVersion",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDeviceSoftwareVersion()" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getImei()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getImei",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用ggetImei()" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getImei(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getImei",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getImei(int)" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getCellLocation()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getCellLocation",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getCellLocation()" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkOperatorName()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkOperatorName",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkOperatorName()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkOperator()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkOperator",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkOperator()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkSpecifier()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkSpecifier",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkSpecifier()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkCountryIso()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkCountryIso",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkCountryIso()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkCountryIso(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkCountryIso",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkCountryIso(int)" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getDataNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getDataNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getDataNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getVoiceNetworkType()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getVoiceNetworkType",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getVoiceNetworkType()" + param.getResult());
                                    param.setResult(NETWORK_TYPE_1xRTT);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class hasIccCard()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "hasIccCard",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用hasIccCard()" + param.getResult());
                                    param.setResult(false);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getSimState()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimState",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //                            //LoggerLog(lpparam.packageName + "调用getSimState()" + param.getResult());
                                    param.setResult(SIM_STATE_UNKNOWN);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getSimState(int)
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimState",
                            int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimState(int)" + param.getResult());
                                    param.setResult(SIM_STATE_UNKNOWN);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getSimOperator()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimOperator",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimOperator()" + param.getResult());
                                    param.setResult(null);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getSimOperatorName()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimOperatorName",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimOperatorName()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getSimCountryIso()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getSimCountryIso",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSimCountryIso()" + param.getResult());
                                    param.setResult(getRandomString(20));
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getCardIdForDefaultEuicc()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getCardIdForDefaultEuicc",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getCardIdForDefaultEuicc()" + param.getResult());
                                    param.setResult(UNINITIALIZED_CARD_ID);
                                }

                                //                        @Override
                                //                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                //                            //LoggerLog(getMethodStack());
                                //                            super.afterHookedMethod(param);
                                //                        }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //      android.telephony.TelephonyManager.class getUiccCardsInfo()
                try {
                    XposedHelpers.findAndHookMethod(
                            android.telephony.TelephonyManager.class.getName(),
                            lpparam.classLoader,
                            "getUiccCardsInfo",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
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
