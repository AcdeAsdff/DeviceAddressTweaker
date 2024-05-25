package com.linearity.deviceaddresstweaker.AndroidHooks.android.provider;

import static com.linearity.utils.FakeInfo.FakeProviderSettings.systemSettingsMap;
import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.ArraySet;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.ReturnReplacements.returnTrue;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.random;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedInFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkFileUri;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HookProviderClass {
    public static boolean HookProvider = true;
    public static boolean HookMediaStore = true;
    public static boolean HookSettings = true;
    public static boolean HookSecure = true;
    public static boolean HookSettings_System =true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookProvider = sharedPreferences.getBoolean("HookProviderClass_HookProvider", true);
        HookMediaStore = sharedPreferences.getBoolean("HookProviderClass_HookMediaStore", true);
        HookSettings = sharedPreferences.getBoolean("HookProviderClass_HookSettings", true);
        HookSettings_System = sharedPreferences.getBoolean("HookProviderClass_HookSettings_System", true);
        HookSecure = sharedPreferences.getBoolean("HookProviderClass_HookSecure", true);
        if (HookProvider){
            if (HookSettings){
                if (HookSecure) {//      android.provider.Settings.Secure.class getString()
                    hookClass = XposedHelpers.findClassIfExists(
                            android.provider.Settings.Secure.class.getName()
                            , lpparam.classLoader);
                    if (hookClass != null){
                        try {
                            findAndHookMethodIfExists(hookClass
                                    , "getString"
                                    , ContentResolver.class,
                                    String.class, returnRandomStr20);
                        }catch (Exception e){LoggerLog(e);}
                    }
                }//not finished
                if (HookSettings_System){
                    hookClass = XposedHelpers.findClassIfExists(Settings.System.class.getName(),lpparam.classLoader);
                    if (hookClass != null){

                        XposedBridge.hookAllMethods(hookClass, "getStringForUser", new XC_MethodHook() {
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                super.beforeHookedMethod(param);
                                if (param.args[1]==null){return;}
                                if (systemSettingsMap.containsKey((String)param.args[1])){
                                    param.setResult(systemSettingsMap.get((String)param.args[1]));return;
                                }
                            }
                        });
                    }
                }
            }
            if (HookMediaStore){
                hookClass = XposedHelpers.findClassIfExists(
                        android.provider.MediaStore.class.getName()
                        , lpparam.classLoader);
                if (hookClass != null){
                    try{
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setIncludePending",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[0];
                                            result = checkFileUri(result, lpparam);
                                            param.args[0] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "setRequireOriginal",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[0];
                                            result = checkFileUri(result, lpparam);
                                            param.args[0] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getRequireOriginal",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[0];
                                            result = checkFileUri(result, lpparam);
                                            param.args[0] = result;
                                        }
                                    }
                            );
                        }
                        //getOriginalMediaFormatFileDescriptor
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "createWriteRequest",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null) {
                                                return;
                                            }
                                            Collection<Uri> uris = (Collection<Uri>) param.args[1];
                                            ArrayList<Uri> result = new ArrayList<>();
                                            for (Uri uri : uris) {
                                                uri = checkFileUri(uri, lpparam);
                                                result.add(uri);
                                            }
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "createTrashRequest",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null) {
                                                return;
                                            }
                                            Collection<Uri> uris = (Collection<Uri>) param.args[1];
                                            ArrayList<Uri> result = new ArrayList<>();
                                            for (Uri uri : uris) {
                                                uri = checkFileUri(uri, lpparam);
                                                result.add(uri);
                                            }
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "createFavoriteRequest",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null) {
                                                return;
                                            }
                                            Collection<Uri> uris = (Collection<Uri>) param.args[1];
                                            ArrayList<Uri> result = new ArrayList<>();
                                            for (Uri uri : uris) {
                                                uri = checkFileUri(uri, lpparam);
                                                result.add(uri);
                                            }
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "createDeleteRequest",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null) {
                                                return;
                                            }
                                            Collection<Uri> uris = (Collection<Uri>) param.args[1];
                                            ArrayList<Uri> result = new ArrayList<>();
                                            for (Uri uri : uris) {
                                                uri = checkFileUri(uri, lpparam);
                                                result.add(uri);
                                            }
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getExternalVolumeNames",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Set<String> volumes = (Set<String>) param.getResult();
                                            Set<String> result = new HashSet<>();
                                            for (String str : volumes) {
                                                str = checkReplaceFile(str, lpparam);
                                                if (!checkBannedInFile(str, lpparam)) {
                                                    str = "/";
                                                }
                                                result.add(str);
                                            }
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getRecentExternalVolumeNames",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Set<String> volumes = (Set<String>) param.getResult();
                                            Set<String> result = new HashSet<>();
                                            for (String str : volumes) {
                                                str = checkReplaceFile(str, lpparam);
                                                if (!checkBannedInFile(str, lpparam)) {
                                                    str = "/";
                                                }
                                                result.add(str);
                                            }
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getVolumeName",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[0];
                                            result = checkFileUri(result, lpparam);
                                            param.args[0] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getMediaScannerUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.afterHookedMethod(param);
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    hookClass,
                                    "getVersion", returnRandomStr20
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getGeneration",
                                    Context.class,
                                    String.class, new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return random.nextInt(Integer.MAX_VALUE);
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getDocumentUri",
                                    Context.class,
                                    Uri.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getMediaUri",
                                    Context.class,
                                    Uri.class,
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "isCurrentSystemGallery",
                                    ContentResolver.class,
                                    int.class,
                                    String.class,returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRedactedUri",
                                    ContentResolver.class, Uri.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                            Uri result1 = (Uri) param.getResult();
                                            result1 = checkFileUri(result1, lpparam);
                                            param.setResult(result1);
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "getRedactedUri",
                                    ContentResolver.class, List.class,
                                    new XC_MethodHook() {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            super.beforeHookedMethod(param);
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            List<Uri> arg1 = (List<Uri>) param.args[1];
                                            List<Uri> emptyList1 = new ArrayList<>();
                                            for (Uri uri : arg1) {
                                                uri = checkFileUri(uri, lpparam);
                                                emptyList1.add(uri);
                                            }
                                            param.args[1] = emptyList1;

                                            List<Uri> result = (List<Uri>) param.getResult();
                                            List<Uri> emptyList2 = new ArrayList<>();
                                            for (Uri uri : result) {
                                                uri = checkFileUri(uri, lpparam);
                                                emptyList2.add(uri);
                                            }
                                            param.setResult(emptyList2);
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(hookClass,
                                    "canManageMedia",
                                    Context.class,returnTrue
                            );
                        }

                        //Video:Thumbnails:
                        //cancelThumbnailRequest
                        // getThumbnail
                        // getKindSize
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Video.Thumbnails.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Video.class,
                                    "query",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof Uri)) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Video.Media.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }

                        //Images:Thumbnails:
                        // queryMiniThumbnails(only url)
                        // queryMiniThumbnail(only url)
                        // cancelThumbnailRequest
                        // getThumbnail
                        // getKindSize
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Thumbnails.class,
                                    "query",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof Uri)) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Thumbnails.class,
                                    "queryMiniThumbnails",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof Uri)) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Thumbnails.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Media.class,
                                    "query",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof Uri)) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Media.class,
                                    "getBitmap",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof Uri)) {
                                                return;
                                            }
                                            Uri result = (Uri) param.args[1];
                                            result = checkFileUri(result, lpparam);
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Media.class,
                                    "insertImage",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[1] == null || !(param.args[1] instanceof String)) {
                                                return;
                                            }
                                            String result = (String) param.args[1];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[1] = result;
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Images.Media.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Files.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Downloads.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }

                        //Audio:
                        //keyFor
                        //Audio:Playlist:Members:
                        //moveItem
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Playlists.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Playlists.Members.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Media.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Media.class,
                                    "getContentUriForPath",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Genres.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Genres.class,
                                    "getContentUriForAudioId",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    android.provider.MediaStore.Audio.Genres.Members.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Audio.Artists.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Audio.Artists.Albums.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                        {
                            XposedBridge.hookAllMethods(
                                    MediaStore.Audio.Albums.class,
                                    "getContentUri",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.args[0] == null) {
                                                return;
                                            }
                                            String result = (String) param.args[0];
                                            result = checkReplaceFile(result, lpparam);
                                            if (!checkBannedInFile(result, lpparam)) {
                                                result = "/";
                                            }
                                            param.args[0] = result;
                                        }

                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                            if (param.getResult() == null) {
                                                return;
                                            }
                                            Uri result = (Uri) param.getResult();
                                            result = checkFileUri(result, lpparam);
                                            param.setResult(result);
                                        }
                                    }
                            );
                        }
                    }catch (Exception e){LoggerLog(e);}
                }
            }//close to done

        }
    }
}
