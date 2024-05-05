package com.linearity.deviceaddresstweaker;

import de.robv.android.xposed.XposedBridge;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class LoggerUtils {

    public static final boolean useLogger = true;

    public static void LoggerLog(Object log){
        if (useLogger){
            if (log != null){
                LoggerLog("[linearity]", log.toString());
            }else {LoggerLog("[linearity]", "null");}
        }
    }

    public static void LoggerLog(String log){
        LoggerLog("[linearity]", log);
    }

    public static void LoggerLog(Throwable e){
        if (useLogger){
            LoggerLog("[linearity]", e);
            for (StackTraceElement s:e.getStackTrace()){
                LoggerLog("        at "+s);
            }
            LoggerLog("--------------------------------");
        }
    }

    public static void LoggerLog(String prefix, String log){
        if (useLogger){
            XposedBridge.log(prefix + log);//not best?
        }
    }

    public static void LoggerLog(String prefix, Throwable e){
        if (useLogger){
            XposedBridge.log(prefix + e);//not best?
        }
    }

    public static void showChildViewGroups(ViewGroup viewGroup, String pre){
        for (int i=0;i<viewGroup.getChildCount();i++){
            View child = viewGroup.getChildAt(i);
            LoggerLog(pre + i + ":"  + child.toString());
            if(child instanceof ViewGroup){
                showChildViewGroups((ViewGroup) child,"ChildOf" + pre);
            }else if (child instanceof TextView){
                LoggerLog("text:" + ((TextView)child).getText());
            }
        }
    }

    public static void showArgs(Object[] args){
        HashSet<Object> filter = new HashSet<>();
        LoggerLog("---item start---");
        for (Object o: args){
            LoggerLog("object:");
            LoggerLog(o);
            showObjectFields(o,"    ",filter);
        }
        LoggerLog("---------");
    }

    public static void showObjectFields_noExpand(Object obj, String prefix) {
        if (obj==null){return;}

        LoggerLog(prefix + " fields:");
        for (Field f:obj.getClass().getDeclaredFields()){
            try {
                String typeName = f.getType().getTypeName();
                String typeNameLower = typeName.toLowerCase();
                Object fobj = XposedHelpers.getObjectField(obj,f.getName());
                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + fobj + "      " + typeName);
                if (!typeNameLower.contains("java")
                        && !typeNameLower.contains("integer")
                        && !typeNameLower.contains("long")
                        && !typeNameLower.contains("byte")
                        && !typeNameLower.contains("boolean")
                        && !typeNameLower.equals("int")
                        && !typeNameLower.startsWith("android")
                ){
                    if (fobj.getClass().isArray()){
                    }
                } if (fobj instanceof Collection){
                    LoggerLog(prefix + "collection size:" + ((Collection)fobj).size());
                }
            }catch (Exception e){
                LoggerLog(prefix + "     cannotAccess(" + f.getType().toString() + ")");
            }
        }
//        LoggerLog(prefix + " ----fields end-----");
    }

    public static void showObjectFields(Object obj, String prefix){
        showObjectFields(obj, prefix,new HashSet<Object>());
    }

    public static void showObjectFields(Object obj, String prefix, HashSet<Object> filter) {
        if (obj==null){return;}
        String typeName = obj.getClass().getTypeName();
        String typeNameLower = typeName.toLowerCase();
        if (!typeNameLower.startsWith("java")
                && !typeNameLower.contains("integer")
                && !typeNameLower.contains("long")
                && !typeNameLower.contains("byte")
                && !typeNameLower.contains("boolean")
                && !typeNameLower.equals("int")
                && !typeNameLower.startsWith("android")
                && !obj.getClass().isEnum()
        ){
            LoggerLog(prefix + " fields:");
            for (Field f:obj.getClass().getDeclaredFields()){
                try {
                    typeName = f.getType().getTypeName();
                    typeNameLower = typeName.toLowerCase();
                    Object fobj = XposedHelpers.getObjectField(obj,f.getName());
                    if (!typeNameLower.startsWith("java")
                            && !typeNameLower.contains("integer")
                            && !typeNameLower.contains("long")
                            && !typeNameLower.contains("byte")
                            && !typeNameLower.contains("boolean")
                            && !typeNameLower.equals("int")
                            && !typeNameLower.startsWith("android")
                            && !f.getType().isEnum()
                    ){
                        LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + fobj + "      " + typeName);
                        if (filter.contains(fobj)){
//                        LoggerLog("--contained:"+fobj);
                            continue;
                        }
                        filter.add(fobj);
                        showObjectFields(fobj,prefix + "     ",filter);
                        if (fobj.getClass().isArray()){
                            int len = Array.getLength(fobj);
                            LoggerLog(prefix + "array length:" + len);
                            for (int i=0;i<len;i++){
                                Object o = Array.get(fobj,i);
                                if (o==null){continue;}
                                if (filter.contains(o)){continue;}
                                filter.add(o);
                                showObjectFields(o,prefix + "     ",filter);
                            }
                        }
                    }
                    else {
                        if (f.getType().equals(long.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getLongField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(int.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getIntField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(byte.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getByteField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(boolean.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getBooleanField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(short.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getShortField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(double.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getDoubleField(obj,f.getName()) + "      " + typeName);
                        }else if (f.getType().equals(float.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getFloatField(obj,f.getName())+ "      " + typeName);
                        }else if (f.getType().equals(char.class)){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getCharField(obj,f.getName()) + "      " + typeName);
                        }else {
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getObjectField(obj,f.getName()) + "      " + typeName);
                        }
                    }
                    if (fobj instanceof Map){
                        LoggerLog(prefix + "map size:" + ((Map)fobj).size());
                        for (Map.Entry<?,?> entry:((Map<?,?>)fobj).entrySet()){
                            LoggerLog(prefix + "key: " + entry.getKey() + "      value:" + entry.getValue());
                            if (!filter.contains(entry.getKey())){
                                filter.add(entry.getKey());
                                LoggerLog(prefix + "keyField:");
                                showObjectFields(entry.getKey(),prefix + "     ",filter);
                            }
                            if (!filter.contains(entry.getValue())){
                                filter.add(entry.getValue());
                                LoggerLog(prefix + "valueField:");
                                showObjectFields(entry.getValue(),prefix + "     ",filter);
                            }
                        }
                    }
                    else if (fobj instanceof Collection){
                        LoggerLog(prefix + "collection size:" + ((Collection)fobj).size());
                        for (Object o:(Collection)fobj){
                            if (o==null){continue;}
                            if (filter.contains(o)){continue;}
                            filter.add(o);
                            showObjectFields(o,prefix + "     ",filter);
                        }
                    }
                }catch (Exception e){
                    LoggerLog(prefix + "     cannotAccess(" + f.getType().toString() + ")");
                }
            }
            Class<?> superClass = obj.getClass().getSuperclass();
            while (!Objects.equals(superClass, Object.class)
                    && superClass != null
                    && !superClass.getTypeName().startsWith("java")
                    && !superClass.isEnum()
            ){
                LoggerLog(prefix + "superClass:" + superClass.getTypeName());
                for (Field f:superClass.getDeclaredFields()){
                    try {
                        typeName = f.getType().getTypeName();
                        typeNameLower = typeName.toLowerCase();
                        Object fobj =  XposedHelpers.getObjectField(obj,f.getName());
                        if (!typeNameLower.startsWith("java")
                                && !typeNameLower.contains("integer")
                                && !typeNameLower.contains("long")
                                && !typeNameLower.contains("byte")
                                && !typeNameLower.contains("boolean")
                                && !typeNameLower.equals("int")
                                && !typeNameLower.startsWith("android")
                                && !f.getType().isEnum()
                        ){
                            LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + fobj + "      " + typeName);
                            if (filter.contains(fobj)){
//                        LoggerLog("--contained:"+fobj);
                                continue;
                            }
                            filter.add(fobj);
                            showObjectFields(fobj,prefix + "     ",filter);
                            if (fobj.getClass().isArray()){
                                int len = Array.getLength(fobj);
                                LoggerLog(prefix + "array length:" + len);
                                for (int i=0;i<len;i++){
                                    Object o = Array.get(fobj,i);
                                    if (o==null){continue;}
                                    if (filter.contains(o)){continue;}
                                    filter.add(o);
                                    showObjectFields(o,prefix + "     ",filter);
                                }
                            }
                        }
                        else {
                            if (f.getType().equals(long.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getLongField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(int.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getIntField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(byte.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getByteField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(boolean.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getBooleanField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(short.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getShortField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(double.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getDoubleField(obj,f.getName()) + "      " + typeName);
                            }else if (f.getType().equals(float.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getFloatField(obj,f.getName())+ "      " + typeName);
                            }else if (f.getType().equals(char.class)){
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getCharField(obj,f.getName()) + "      " + typeName);
                            }else {
                                LoggerLog(prefix + "     fieldName:" + f.getName() + "     " + XposedHelpers.getObjectField(obj,f.getName()) + "      " + typeName);
                            }
                        }
                        if (fobj instanceof Map){
                            LoggerLog(prefix + "map size:" + ((Map)fobj).size());
                            for (Map.Entry<?,?> entry:((Map<?,?>)fobj).entrySet()){
                                LoggerLog(prefix + "key: " + entry.getKey() + "      value:" + entry.getValue());
                                if (!filter.contains(entry.getKey())){
                                    filter.add(entry.getKey());
                                    LoggerLog(prefix + "keyField:");
                                    showObjectFields(entry.getKey(),prefix + "     ",filter);
                                }
                                if (!filter.contains(entry.getValue())){
                                    filter.add(entry.getValue());
                                    LoggerLog(prefix + "valueField:");
                                    showObjectFields(entry.getValue(),prefix + "     ",filter);
                                }
                            }
                        }
                        else if (fobj instanceof Collection){
                            LoggerLog(prefix + "collection size:" + ((Collection)fobj).size());
                            for (Object o:(Collection)fobj){
                                if (o==null){continue;}
                                if (filter.contains(o)){continue;}
                                filter.add(o);
                                showObjectFields(o,prefix + "     ",filter);
                            }
                        }
                    }catch (Exception e){
                        LoggerLog(prefix + "     cannotAccess(" + f.getType().toString() + ")");
                    }
                }
                superClass = superClass.getSuperclass();
            }
            if (superClass != null && superClass.isEnum()){
                LoggerLog(prefix + "     enumFieldName:" + obj.getClass() + "     " + obj + "      " + typeName);
            }else {
                LoggerLog(prefix + "     fieldName:" + obj.getClass() + "     " + obj + "      " + typeName);
            }

        }
        else {
            if (obj.getClass().isEnum()){
                LoggerLog(prefix + "     enumFieldName:" + obj.getClass() + "     " + obj + "      " + typeName);
            }else {
                LoggerLog(prefix + "     fieldName:" + obj.getClass() + "     " + obj + "      " + typeName);
            }
        }

//        LoggerLog(prefix + " ----fields end-----");
    }
}
