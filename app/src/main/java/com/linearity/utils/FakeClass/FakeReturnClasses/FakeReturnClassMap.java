package com.linearity.utils.FakeClass.FakeReturnClasses;

import static com.linearity.utils.ReturnReplacements.returnNull;

import android.util.Pair;

import com.linearity.utils.FakeClass.android.hardware.CantUseBatteryState;
import com.linearity.utils.FakeClass.android.hardware.usb.CantUseUsbEndpoint;
import com.linearity.utils.FakeClass.android.hardware.usb.CantUseUsbManager;
import com.linearity.utils.FakeClass.android.hardware.usb.CantUseUsbRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodReplacement;

public class FakeReturnClassMap {
    public static final Map<String, Pair<XC_MethodReplacement,Object>> fakeObjects = new HashMap<>();
    public static final Pair<XC_MethodReplacement,Object> nullPair = new Pair<>(returnNull,null);
    static{
        fakeObjects.put("java.lang.Object",nullPair);
        fakeObjects.put("java.lang.Cloneable",nullPair);
        fakeObjects.put("java.io.Serializable",nullPair);
    }
    public static XC_MethodReplacement GenerateMethodReplacement_FindObjectFromMap(String className){
        return new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return fakeObjects.getOrDefault(className,null);
        }
        };
    }
    public static void registerInstance(Class<?> toRegister,Object instance){
        if (toRegister ==null){return;}
        if (!fakeObjects.containsKey(toRegister.getName())){
            fakeObjects.put(toRegister.getName(), new Pair<>(
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            return instance;
                        }
                    }
            ,instance));
            registerInstance(toRegister.getSuperclass(),instance);
            for (Class<?> interfaceClass:toRegister.getInterfaces()){
                registerInstance(interfaceClass,instance);
            }
        }
    }
}
