package com.linearity.deviceaddresstweaker.JavaHooks.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.random;

import android.annotation.SuppressLint;
import android.net.Uri;

public class HookIO {
    public static boolean HookIO = true;
    public static boolean HookFile = true;
    public static boolean HookInputStream = true;
    public static boolean HookOutputStream = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
        String processHead = lpparam.processName.split(":")[0];
        if (HookIO){
            if (HookFile){
                try {
                    Class<?> javaFileClass = XposedHelpers.findClass(java.io.File.class.getName(),lpparam.classLoader);
                    XposedBridge.hookAllConstructors(javaFileClass,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);

                                    if (checkBannedFile(param, lpparam)){
//                                        boolean pass = false;
//                                        StringBuilder sb = new StringBuilder();
//                                        for (Object i:param.args){
//                                            sb.append("|");
//                                            if (i != null){
//                                                sb.append(i.toString());
//                                            }
//                                        }
//                                        String check = sb.toString();
//                                        if (check.contains(processHead)
//                                                ||check.contains(lpparam.packageName)
//                                                ||check.endsWith("/lib64")
//                                                ||check.contains("/lib64|")
//                                        ){
//                                            pass = true;
//                                        }
//                                        if (!pass){
//                                            LoggerLog("[file]"+sb);
//                                        }
//                                        return;
                                    }
                                }
                            });
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "readObject",
                            java.io.ObjectInputStream.class,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    File file = (File) param.thisObject;
                                    if (!file.getAbsolutePath().contains(lpparam.packageName)
                                            && !file.getAbsolutePath().contains(processHead)
                                    && (
                                            !file.getAbsolutePath().contains("tencent")
                                            && !processHead.contains("tencent")
                                            )){
                                        param.thisObject = new File("/");
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "writeObject",
                            java.io.ObjectOutputStream.class,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    File file = (File) param.thisObject;
                                    if (!file.getAbsolutePath().contains(lpparam.packageName)
                                            && !file.getAbsolutePath().contains(processHead)
                                            && (
                                            !file.getAbsolutePath().contains("tencent")
                                                    && !processHead.contains("tencent")
                                    )){
                                        param.thisObject = new File("/");
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                //        java.io.File.class list
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "list",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    File thisFile = (File) param.thisObject;
                                    String[] result = (String[]) param.getResult();
                                    ArrayList<String> setReslut = new ArrayList<>();
                                    if (result != null) {
                                        for (String filePath : result) {
                                            String AbsoluteFilePath = thisFile.getAbsolutePath() + filePath;
                                            if (AbsoluteFilePath.contains(lpparam.packageName)
                                                    || (lpparam.packageName.equals("com.android.webview")
                                                    && !filePath.contains("linearity")
                                                    && !filePath.contains("lineage")
                                                    && !filePath.contains("magisk")
                                                    && !filePath.contains("android")
                                                    && !filePath.contains("system")
                                                    && !filePath.contains("devices")
                                                    && !filePath.contains("/sys")
                                                    && !filePath.contains("/etc"))) {
                                                setReslut.add(filePath);
                                            } else {
                                                //LoggerLog(lpparam.packageName + "调用java.io.File.class list()" + AbsoluteFilePath);
                                            }
                                        }
                                    }
                                    param.setResult(setReslut.toArray(new String[0]));
//                            param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        java.io.File.class list(FilenameFilter)
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "list",
                            FilenameFilter.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    File thisFile = (File) param.thisObject;
                                    String[] result = (String[]) param.getResult();
                                    ArrayList<String> setReslut = new ArrayList<>();
                                    if (result != null) {
                                        for (String filePath : result) {
                                            String AbsoluteFilePath = thisFile.getAbsolutePath() + filePath;
                                            if (AbsoluteFilePath.contains(lpparam.packageName)
                                                    || (lpparam.packageName.equals("com.android.webview")
                                                    && !filePath.contains("linearity")
                                                    && !filePath.contains("lineage")
                                                    && !filePath.contains("magisk")
                                                    && !filePath.contains("android")
                                                    && !filePath.contains("system")
                                                    && !filePath.contains("devices")
                                                    && !filePath.contains("/sys")
                                                    && !filePath.contains("/etc"))) {
                                                setReslut.add(filePath);
                                            } else {
                                                //LoggerLog(lpparam.packageName + "调用java.io.File.class list(FilenameFilter)" + AbsoluteFilePath);
                                            }
                                        }
                                    }
                                    param.setResult(setReslut.toArray(new String[0]));
//                            param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        java.io.File.class listFiles
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "listFiles",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    File[] result = (File[]) param.getResult();
                                    ArrayList<File> setReslut = new ArrayList<>();
                                    if (result != null) {
                                        for (File file : result) {
//                                resultStr.append(file.getAbsolutePath());
                                            String filePath = file.getAbsolutePath();
                                            if (filePath.contains(lpparam.packageName)
                                                    || (lpparam.packageName.equals("com.android.webview")
                                                    && !filePath.contains("linearity")
                                                    && !filePath.contains("lineage")
                                                    && !filePath.contains("magisk")
                                                    && !filePath.contains("android")
                                                    && !filePath.contains("system")
                                                    && !filePath.contains("devices")
                                                    && !filePath.contains("/sys")
                                                    && !filePath.contains("/etc"))) {
                                                setReslut.add(file);
                                            } else {
                                                //LoggerLog(lpparam.packageName + "调用java.io.File.class listFiles()" + filePath);
                                            }
                                        }
                                    }
                                    param.setResult(setReslut.toArray(new File[0]));
//                            param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
//        java.io.File.class listFiles(FilenameFilter)
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.File.class.getName(),
                            lpparam.classLoader,
                            "listFiles",
                            FilenameFilter.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    File[] result = (File[]) param.getResult();
                                    ArrayList<File> setReslut = new ArrayList<>();
                                    if (result != null) {
                                        for (File file : result) {
//                                resultStr.append(file.getAbsolutePath());
                                            String filePath = file.getAbsolutePath();
                                            if (filePath.contains(lpparam.packageName)
                                                    || (lpparam.packageName.equals("com.android.webview")
                                                    && !filePath.contains("linearity")
                                                    && !filePath.contains("lineage")
                                                    && !filePath.contains("magisk")
                                                    && !filePath.contains("android")
                                                    && !filePath.contains("system")
                                                    && !filePath.contains("/devices")
                                                    && !filePath.contains("/sys")
                                                    && !filePath.contains("/etc"))) {
                                                setReslut.add(file);
                                            } else {
                                                //LoggerLog(lpparam.packageName + "调用java.io.File.class listFiles(FilenameFilter)" + filePath);
                                            }
                                        }
                                    }
                                    param.setResult(setReslut.toArray(new File[0]));
//                            param.setResult(-1);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
            if (HookOutputStream){
                try {
                    XposedHelpers.findAndHookMethod(
                            java.io.OutputStream.class.getName(),
                            lpparam.classLoader,
                            "flush",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if (param.thisObject instanceof FileOutputStream){
                                        String path = (String) XposedHelpers.getObjectField(param.thisObject, "path");
                                        path = checkReplaceFile(path, lpparam);
                                        if (!checkBannedOutFile(path, lpparam)) {
                                            param.thisObject = new FileOutputStream("/storage/emulated/0/AAAAAA.foolish");
                                        }
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
            if (HookInputStream){
                try {
                    Class<?> fileInputStream = XposedHelpers.findClass(java.io.FileInputStream.class.getName(),lpparam.classLoader);
                    XposedBridge.hookAllConstructors(
                            fileInputStream,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    String path = "";
                                    Object name = param.args[0];
                                    if (param.args[0] instanceof String){
                                        if (Objects.equals((String) name, "/storage/emulated/0/AAAAAA.foolish")){return;}
                                        if (Objects.equals((String) name, "/")){return;}
                                        path = name != null ? (String) name : "";
                                        path = checkReplaceFile(path, lpparam);
                                        if (!checkBannedInFile(path, lpparam)) {
                                            param.args[0] = path;
                                        }
                                    }
                                    if (param.args[0] instanceof File){
                                        path = name != null ? ((File) name).getAbsolutePath() : "";
                                        path = checkReplaceFile(path, lpparam);
                                        if (!checkBannedInFile(path, lpparam)) {
                                            param.args[0] = new File(path);
                                        }
                                    }
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }

    public static String[] bannedFileExact = new String[]{
            "/system/app/Superuser.apk",
            "/data/misc",
            "/system",
    };
    @SuppressLint("SdCardPath")
    public static String[] bannedFileHead = new String[]{
            "/sdcard",
            "/storage/emulated",
            "/acct",
            "/apex",
            "/bin",
            "/bugreports",
            "/cache",
            "/config",
            "/proc",
            "/sys/devices/system",
            "/etc",
            "/data/misc",
            "/sys/block",
            "/system",
            "/system/build.prop",
            "/dev",
            "/su",
            "/data/local/su",
            "/data/local/xbin/su",
            "/sbin/su",
            "/data/local/bin/su",
    };
    public static String[] whiteListHead = new String[]{
            "/system/etc/security/cacerts",
            "/data/misc/user/0/cacerts-added",
            "/system/framework/core.jar.jex",
    };

    public static String[] tweakPathExact = new String[]{
            "/sdcard",
            "/sdcard/",
            "/storage/emulated/0",
            "/storage/emulated/0/",
            "/storage/emulated/0/Android",
            "/storage/emulated/0/Android/",
    };//add processHead after detected
    public static boolean useChecker = true;

    public static boolean checkBannedFile(XC_MethodHook.MethodHookParam param, XC_LoadPackage.LoadPackageParam lpparam) throws Exception{
        String processHead = lpparam.processName.split(":")[0];
        boolean mark = true;
        if (param.args.length == 1)
        {
            String path = "";
            if (param.args[0] instanceof String)
            {
                path = (String) param.args[0];
                path = checkReplaceFile(path, lpparam);
                if (path.contains(processHead)
                        ||path.contains(lpparam.packageName)){return true;}
                else {
//                    LoggerLog(path);
                }
                if (path.contains("tencent") && processHead.contains("tencent")) {
                    return true;
                }
                if (useChecker){

                    for (String checker : bannedFileHead) {
                        boolean tempBreak = false;
                        for (String checker2:whiteListHead){
                            if (path.startsWith(checker2)){
                                tempBreak = true;
                                break;
                            }
                        }
                        if (tempBreak){
                            break;
                        }
                        if (path.startsWith(checker)) {
//                            param.args[0] = getRandomString(14);
                            mark = false;
                            break;
                        }
                    }
                    for (String checker : bannedFileExact) {
                        if (path.equals(checker)) {
//                            param.args[0] = getRandomString(14);
                            mark = false;
                            break;
                        }
                    }
                }
            }
            else
            {
                if (param.args[0] != null){
                    path = (String) param.args[0].toString();
                }
                path = checkReplaceFile(path, lpparam);
                if (path.contains(processHead)
                        ||path.contains(lpparam.packageName)){return true;}else {
                    LoggerLog(path);
                }
                if (path.contains("tencent") && processHead.contains("tencent")) {
                    return true;
                }
                if (useChecker){
                    for (String checker : bannedFileHead) {
                        boolean tempBreak = false;
                        for (String checker2:whiteListHead){
                            if (path.startsWith(checker2)){
                                tempBreak = true;
                                break;
                            }
                        }
                        if (tempBreak){
                            break;
                        }
                        if (path.startsWith(checker)) {
                            param.args[0] = new URI(getRandomString(14));
                            mark = false;
                            break;
                        }
                    }
                    for (String checker : bannedFileExact) {
                        if (path.equals(checker)) {
                            param.args[0] = new URI(getRandomString(14));
                            mark = false;
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            int ConstructorType = 0;
            File file;
            String path = "";
            String parentPath = "";
            if (param.args[0] instanceof File && param.args[1] instanceof String){
                ConstructorType = FileConstructor_Type_File_String;
                file = (File) param.args[0];
                path = (String) param.args[1];
                parentPath = file.getAbsolutePath();
            }
            else if (param.args[1] instanceof File && param.args[0] instanceof String){
                ConstructorType = FileConstructor_Type_String_File;
                file = (File) param.args[1];
                path = (String) param.args[0];
                parentPath = file.getAbsolutePath();
            }
            else if (param.args[0] instanceof String && param.args[1] instanceof String){
                ConstructorType = FileConstructor_Type_String_String;
                path = (String) param.args[1];
                parentPath = (String) param.args[0];
            }
            else {
                ConstructorType = FileConstructor_Type_String_Integer;
                path = "";
                if (param.args[0] instanceof String){
                    parentPath = (String) param.args[0];
                }else if (param.args[0] != null){
                    parentPath = ((File) param.args[0]).getAbsolutePath();
                }
            }
            String totalPath = "";
            if (!parentPath.endsWith("/")) {
                totalPath = parentPath + "/" + path;
            } else {
                totalPath = parentPath + path;
            }
            if (totalPath.contains("tencent") && processHead.contains("tencent")) {
                return true;
            }
            totalPath = checkReplaceFile(totalPath, lpparam);
            if (
                    (totalPath).contains(processHead)
                            ||(totalPath).contains(lpparam.packageName)
            )
            {
                return true;
            }else {
//                LoggerLog(totalPath);
            }
            if (useChecker){
                for (String checker : bannedFileHead) {
                    if (totalPath.startsWith(checker)) {
                        boolean tempBreak = false;
                        for (String checker2:whiteListHead){
                            if (totalPath.startsWith(checker2)){
                                tempBreak = true;
                                break;
                            }
                        }
                        if (tempBreak){
                            break;
                        }
                        if (ConstructorType == FileConstructor_Type_File_String) {
                            param.args[0] = new File(getRandomString(14));
                            param.args[1] = getRandomString(14);
                        } else if (ConstructorType == FileConstructor_Type_String_File) {
                            param.args[0] = getRandomString(14);
                            param.args[1] = new File(getRandomString(14));
                        } else if (ConstructorType == FileConstructor_Type_String_String) {
                            param.args[0] = getRandomString(14);
                            param.args[1] = getRandomString(14);
                        } else {
                            param.args[0] = getRandomString(14);
                            param.args[1] = random.nextInt(Integer.MAX_VALUE);
                        }
                        mark = false;
                        break;
                    }
                }
                for (String checker : bannedFileExact) {
                    if ((totalPath).equals(checker)) {
                        if (ConstructorType == FileConstructor_Type_File_String) {
                            param.args[0] = new File(getRandomString(14));
                            param.args[1] = getRandomString(14);
                        } else if (ConstructorType == FileConstructor_Type_String_File) {
                            param.args[0] = getRandomString(14);
                            param.args[1] = new File(getRandomString(14));
                        } else if (ConstructorType == FileConstructor_Type_String_String) {
                            param.args[0] = getRandomString(14);
                            param.args[1] = getRandomString(14);
                        } else {
                            param.args[0] = getRandomString(14);
                            param.args[1] = random.nextInt(Integer.MAX_VALUE);
                        }
                        mark = false;
                        break;
                    }
                }
            }
        }
        return mark;
    }
    public static String checkReplaceFile(String path, XC_LoadPackage.LoadPackageParam lpparam){
        if (path == null){return null;}
        if (path.equals("")){return "";}
        if (!path.startsWith("/")){
            path = "/" + path;
        }
        String processHead = lpparam.processName.split(":")[0];
        for (String checker:tweakPathExact){
            if (path.equals(checker)){
                if (!checker.endsWith("/")){
                    path += "/" + processHead;
                }else {
                    path += "/" + processHead + "/";
                }
                File file1 = new File(path);
                if (!file1.exists()){
                    file1.mkdirs();
                }
//                LoggerLog("checked File!");
                break;
            }
        }
        return path;
    }
    public static boolean checkBannedInFile(String path, XC_LoadPackage.LoadPackageParam lpparam){
        boolean mark = true;
        String processHead = lpparam.processName.split(":")[0];
        if (path.contains(processHead)
                ||path.contains(lpparam.packageName)){return true;}
        else {
//                    LoggerLog(path);
        }
        if (path.contains("tencent") && processHead.contains("tencent")) {
            return true;
        }
        if (useChecker){
            for (String checker : bannedFileHead) {
                boolean tempBreak = false;
                for (String checker2:whiteListHead){
                    if (path.startsWith(checker2)){
                        tempBreak = true;
                        break;
                    }
                }
                if (tempBreak){
                    break;
                }
                if (path.startsWith(checker)) {
//                            param.args[0] = getRandomString(14);
                    mark = false;
                    break;
                }
            }
            for (String checker : bannedFileExact) {
                if (path.equals(checker)) {
//                            param.args[0] = getRandomString(14);
                    mark = false;
                    break;
                }
            }
        }
        return mark;
    }
    public static boolean checkBannedOutFile(String path, XC_LoadPackage.LoadPackageParam lpparam){
        if (path == null){return true;}
        String processHead = lpparam.processName.split(":")[0];
        if (path.contains(processHead)
                ||path.contains(lpparam.packageName)){return true;}
        if (path.contains("tencent") && processHead.contains("tencent")) {
            return true;
        }
        return false;
    }
    public static Uri checkFileUri(Uri fileUri, XC_LoadPackage.LoadPackageParam lpparam){
        if (fileUri == null){return null;}
        if (!Objects.equals(fileUri.getScheme(), "file")){return fileUri;}
        String file = fileUri.getPath();
        file = checkReplaceFile(file, lpparam);
        if (!checkBannedInFile(file,lpparam)){
            file = "/";
        }
        return Uri.fromFile(new File(file));
    }

    public static String checkFileUriStr(String fileUri, XC_LoadPackage.LoadPackageParam lpparam){
        if (!fileUri.startsWith("file://")){return fileUri;}
        String file = fileUri.substring(7);
        file = checkReplaceFile(file, lpparam);
        if (!checkBannedInFile(file,lpparam)){
            file = "/";
        }
        return file;
    }

    public static int FileConstructor_Type_File_String = 0;
    public static int FileConstructor_Type_String_File = 1;
    public static int FileConstructor_Type_String_String = 2;
    public static int FileConstructor_Type_String_Integer = 3;
}
