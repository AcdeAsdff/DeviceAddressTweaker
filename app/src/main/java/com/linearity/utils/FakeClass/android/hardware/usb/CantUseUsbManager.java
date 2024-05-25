package com.linearity.utils.FakeClass.android.hardware.usb;

import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.content.Context;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbManager;

import com.linearity.utils.FakeClass.FakeReturnClasses.FakeReturnClassMap;

import de.robv.android.xposed.XposedHelpers;

public class CantUseUsbManager {
    public static UsbManager INSTANCE;
    static {
        try {
            INSTANCE = (UsbManager) UsbManager.class.getConstructors()[0].newInstance(null,null);
            FakeReturnClassMap.registerInstance(UsbManager.class,INSTANCE);
        } catch (Exception e){
            LoggerLog(e);
        }
    }
}
