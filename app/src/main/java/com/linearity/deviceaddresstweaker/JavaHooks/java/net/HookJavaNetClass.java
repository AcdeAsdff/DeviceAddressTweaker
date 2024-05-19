package com.linearity.deviceaddresstweaker.JavaHooks.java.net;

import static com.linearity.utils.HookUtils.findAndHookMethodIfExists;

import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.linearity.utils.ReturnReplacements.returnByteArr114514;
import static com.linearity.utils.ReturnReplacements.returnFalse;
import static com.linearity.utils.ReturnReplacements.returnNull;
import static com.linearity.utils.ReturnReplacements.returnRandomStr20;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedInFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

public class HookJavaNetClass {
    public static boolean trackUrlLogger = false;//millions years of war
    public static boolean HookNet;
    public static boolean HookNetworkInterface;
    public static boolean HookHttpUrlConnection;
    public static boolean HookUrl;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, SharedPreferences sharedPreferences){
        HookNet = sharedPreferences.getBoolean("HookJavaNetClass_HookNet",true);
        HookNetworkInterface = sharedPreferences.getBoolean("HookJavaNetClass_HookNetworkInterface", true);
        HookHttpUrlConnection = sharedPreferences.getBoolean("HookJavaNetClass_HookHttpUrlConnection", true);
        HookUrl = sharedPreferences.getBoolean("HookJavaNetClass_HookUrl", true);
        Class<?> hookClass;
        if (HookNet){
            if (HookNetworkInterface) {
                try {
                    hookClass = XposedHelpers.findClassIfExists(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader);
                    if (hookClass != null){//      java.net.NetworkInterface.class getHardwareAddress()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getHardwareAddress",returnByteArr114514
                            );

                        }
//      java.net.NetworkInterface.class getNetworkInterfaces()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getNetworkInterfaces",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) {
                                            //LoggerLog(lpparam.packageName + "调用getNetworkInterfaces()" + param.getResult());
                                            param.setResult(Collections.enumeration(List.of()));
                                        }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                                    }
                            );

                        }
//      java.net.NetworkInterface.class getAll()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getAll",
                                    new XC_MethodHook(114514) {
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) {
                                            //LoggerLog(lpparam.packageName + "调用getAll()" + param.getResult());
                                            param.setResult(new NetworkInterface[0]);
                                        }

//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            //LoggerLog(getMethodStack());
//                            super.afterHookedMethod(param);
//                        }
                                    }
                            );

                        }
//      java.net.NetworkInterface.class getSubInterfaces()
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getSubInterfaces",returnNull

                            );

                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return "name:null";
                                        }
                                    }
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getName",returnRandomStr20
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "getDisplayName",returnNull
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "isVirtual", returnFalse
                            );
                        }
                        {
                            findAndHookMethodIfExists(
                                    hookClass,
                                    "hashCode",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return Integer.MAX_VALUE;
                                        }
                                    }
                            );
                        }
                    }
                }catch (Exception e){LoggerLog(e);}
            }
            if (HookHttpUrlConnection) {
                try {
                    hookClass = XposedHelpers.findClassIfExists(java.net.HttpURLConnection.class.getName(), lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllConstructors(
                                hookClass,
                                new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        if (checkUrl(param)){return;}
                                    }
                                }
                        );
                        findAndHookMethodIfExists(hookClass,
                                "getResponseMessage",
                                new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        setUrlResponseMsg(param);
                                    }
                                }
                        );
                    }


                }catch (Exception e){
                    LoggerLog(e);
                }
            }//not finished
            if (HookUrl){
                 {
                    Class<?> urlClass = XposedHelpers.findClassIfExists(java.net.URL.class.getName(), lpparam.classLoader);
                    if (urlClass != null){XposedBridge.hookAllConstructors(
                            urlClass,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if (param.args.length == 1 && param.args[0] == null){
                                        param.args[0] = "http://127.39.0.1";return;
                                    }
                                    if (
                                            param.args[0] instanceof URL
                                                    && param.args[2] instanceof URLStreamHandler){
                                        if (param.args[1] == null){
                                            param.args[1] = "http://127.39.0.1";
                                        }
                                    }
                                    if (checkUrl(param)){return;}
                                    if (param.args.length < 3){return;}
                                    if (param.args[0] == null){return;}
                                    if (((String)param.args[0]).equals("file")){
                                        if (param.args[2] == null){return;}
                                        if (param.args[2] instanceof String){
                                            String path = (String) param.args[2];
                                            path = checkReplaceFile(path, lpparam);
                                            if (!checkBannedInFile(path, lpparam)) {
                                                path = "0";
                                            }
                                            param.args[2] = path;
                                        }
                                        if (param.args[3] instanceof String){
                                            String path = (String) param.args[3];
                                            path = checkReplaceFile(path, lpparam);
                                            if (!checkBannedInFile(path, lpparam)) {
                                                path = "0";
                                            }
                                            param.args[3] = path;
                                        }
                                    }
                                }
                            }
                    );
                        {
                            findAndHookMethodIfExists(
                                    urlClass,
                                    "isValidProtocol",
                                    String.class,
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            String protocol = (String) param.args[0];
                                            if (protocol == null || Objects.equals(protocol, "")){return false;}
                                            int len = protocol.length();
                                            if (len < 1)
                                                return false;
                                            char c = protocol.charAt(0);
                                            if (!Character.isLetter(c))
                                                return false;
                                            for (int i = 1; i < len; i++) {
                                                c = protocol.charAt(i);
                                                if (!Character.isLetterOrDigit(c) && c != '.' && c != '+' &&
                                                        c != '-') {
                                                    return false;
                                                }
                                            }
                                            return true;
                                        }
                                    }
                            );
                        }
                    }

                }
                 {
                    Class<?> urIClass = XposedHelpers.findClassIfExists(java.net.URI.class.getName(), lpparam.classLoader);
                    if (urIClass != null){
                        XposedBridge.hookAllConstructors(
                                urIClass,
                                new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        if (checkUrl(param)){return;}
                                        if (param.args[0] == null){return;}
                                        if (((String)param.args[0]).equals("file")){
                                            if (param.args[2] == null){return;}
                                            String path = (String) param.args[2];
                                            path = checkReplaceFile(path,lpparam);
                                            if (!checkBannedInFile(path,lpparam)){
                                                path = "0";
                                            }
                                            param.args[2] = path;
                                        }
                                    }
                                }
                        );
                    }

                }
            }//not finished
        }
    }



    public static ArrayList<String> noLogUrlList_startWith = new ArrayList<>();
    public static ArrayList<String> replaceUrlList_startWith = new ArrayList<>();
    public static ArrayList<String> replaceUrlList_equals = new ArrayList<>();
    static{

        replaceUrlList_startWith.add("dl.url.cn");
        replaceUrlList_startWith.add("/sngapp/app/update/");
        replaceUrlList_startWith.add("/cgi-bin/mapp/");
        replaceUrlList_startWith.add("resolver.msg.xiaomi.net");
        replaceUrlList_startWith.add("123.125.102.48:5222");
        replaceUrlList_startWith.add("111.202.1.252");
        replaceUrlList_startWith.add("39.156.150.162:5222");
        replaceUrlList_startWith.add("220.181.106.150:80");
        replaceUrlList_startWith.add("111.13.142.153:5222");
        replaceUrlList_startWith.add("157.148.33.71:443");

        replaceUrlList_startWith.add("android.rqd.qq.com/analytics/");
        replaceUrlList_startWith.add("log.tbs.qq.com");
        replaceUrlList_startWith.add("qappcenterv6.3g.qq.com");
        replaceUrlList_startWith.add("3gimg.qq.com");
        replaceUrlList_startWith.add("hao.gamecenter.qq.com");
        replaceUrlList_startWith.add("h5.qzone.qq.com/report/");
        replaceUrlList_startWith.add("masdkv6.3g.qq.com");
        replaceUrlList_startWith.add("miniapp.gtimg.cn");


        replaceUrlList_startWith.add("d3g.qq.com");
        replaceUrlList_startWith.add("sngmta.qq.com");
        replaceUrlList_startWith.add("cmshow.gtimg.cn");
        replaceUrlList_startWith.add("imgcache.qq.com/club/webview/preload.html");
        replaceUrlList_startWith.add("appservice.qq.com");
        replaceUrlList_startWith.add("wspeed.qq.com");
        replaceUrlList_startWith.add("android.bugly.qq.com");
        replaceUrlList_startWith.add("/qzone/");
        replaceUrlList_startWith.add("i.gtimg.cn");
        replaceUrlList_startWith.add("wa.qq.com/hot-res/");



        noLogUrlList_startWith.add("127.39.0.1");
        noLogUrlList_startWith.add("about:blank");
        noLogUrlList_startWith.add("file");
        noLogUrlList_startWith.add("jar");
        noLogUrlList_startWith.add("/public/appicon/");
        noLogUrlList_startWith.add("/psc?/");
        noLogUrlList_startWith.add("dns.google");


        noLogUrlList_startWith.add("pgdt.gtimg.cn");
        noLogUrlList_startWith.add("qzonestyle.gtimg.cn");
        noLogUrlList_startWith.add("qqsys_emoji");
        noLogUrlList_startWith.add("profile_img_big_fhd");
        noLogUrlList_startWith.add("q.qlogo.cn");
        noLogUrlList_startWith.add("miniapp.gtimg.cn/public/appicon/");
        noLogUrlList_startWith.add("pic.ugcimg.cn");
        noLogUrlList_startWith.add("sec.video.qq.com");
        noLogUrlList_startWith.add("m.qpic.cn");
        noLogUrlList_startWith.add("h5.qzone.qq.com");
        noLogUrlList_startWith.add("hot_pic");
        noLogUrlList_startWith.add("apollo_image");
        noLogUrlList_startWith.add("emotion_pic");
        noLogUrlList_startWith.add("chatthumb");
        noLogUrlList_startWith.add("chatimg");
        noLogUrlList_startWith.add("chatraw");
        noLogUrlList_startWith.add("125.88.187.141");
        noLogUrlList_startWith.add("125.88.187.159");
        noLogUrlList_startWith.add("BOT");
        noLogUrlList_startWith.add("directaddress");
        noLogUrlList_startWith.add("protocol_vas_extension_image");
        noLogUrlList_startWith.add("/club/item/parcel/");


        noLogUrlList_startWith.add("api.bilibili.com/open/monitor/apm/report");
        noLogUrlList_startWith.add("125.88.187.141");
        noLogUrlList_startWith.add("125.88.187.159");
        replaceUrlList_equals.add("miniapp.gtimg.cn");
        replaceUrlList_equals.add("miniapp.gtimg.cn/");
        replaceUrlList_startWith.add("line1-log.biligame.net");
    }
    //maybe i have to improve String#contains() here
    public static boolean checkUrl(XC_MethodHook.MethodHookParam param){
//        ArrayList<String> replaceUrlList_equals = new ArrayList<>();
//        ArrayList<String> replaceUrlList_startWith = new ArrayList<>();
//        ArrayList<String> noLogUrlList_startWith = new ArrayList<>();
        if (param.args[0] == null){return true;}
        String tempUrl = param.args[0].toString();
        if (tempUrl.startsWith("https://")){
            tempUrl = tempUrl.substring(8);
        }
        else if (tempUrl.startsWith("http://")){
            tempUrl = tempUrl.substring(7);
        }
        for (String checkHead : replaceUrlList_startWith) {
            if (tempUrl.startsWith(checkHead)) {
                param.args[0] = "https://127.39.0.1/" + tempUrl;
                return true;
            }
        }
        for (String checkHead:replaceUrlList_equals){
            if (tempUrl.equals(checkHead)){
                param.args[0] = "https://127.39.0.1/" + tempUrl;
                return true;
            }
        }
        boolean getCheckHead = false;
        if (trackUrlLogger){
            for (String checkHead : noLogUrlList_startWith) {
                if (tempUrl.startsWith(checkHead)) {
                    getCheckHead = true;
                    break;
                }
            }
            if (!getCheckHead) {
                if (trackUrlLogger) {
                    LoggerLog("[linearity]---------------------");
                    for (int i = 0; i < param.args.length; i++) {
                        LoggerLog(param.args[i]);
                    }
                    LoggerLog("[linearity]---------------------");
                }
            }
        }
        
        return false;
    }

    public static void setUrlResponseMsg(XC_MethodHook.MethodHookParam param){
        String url = ((HttpURLConnection) param.thisObject).getURL().toString();
        if (url.contains("log.tbs.qq.com")){
            param.setResult("Success!");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","Success!");
            return;
        }
        if (url.contains("android.rqd.qq.com/analytics")){
            param.setResult("");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","");
            return;
        }
        if (url.equals("imgcache.qq.com/club/webview/preload.html")){
            param.setResult("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>webview</title>\n" +
                    "    <link type=\"text/css\" href=\"preload/css.css\" rel=\"stylesheet\" />\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script type=\"text/javascript\" src=\"preload/js.js\"></script>\n" +
                    "</body>\n" +
                    "</html>");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>webview</title>\n" +
                    "    <link type=\"text/css\" href=\"preload/css.css\" rel=\"stylesheet\" />\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script type=\"text/javascript\" src=\"preload/js.js\"></script>\n" +
                    "</body>\n" +
                    "</html>");
            return;
        }
        if (url.contains("settings-android-tim-3-4-2.json")){
            param.setResult("{\n" +
                    "    \"intervalMillis\": 1800000,\n" +
                    "    \"timeExpiredMillis\": \"4102387200000\",\n" +
                    "    \"urlForSettings\": \"https://i.gtimg.cn/ams-web/public/tangram-report/settings-android-tim-3-4-2.json\",\n" +
                    "    \"version\": 1,\n" +
                    "    \"settingsForXJ\": {\n" +
                    "        \"queueLength\": 20,\n" +
                    "        \"canvas\": true,\n" +
                    "        \"offline\": true\n" +
                    "    },\n" +
                    "    \"settingsForInterstitial\" : {\n" +
                    "        \"timeoutMillis\" : 10000,\n" +
                    "        \"enablePreDownload\" : true\n" +
                    "    },\n" +
                    "    \"settingsForExposure\": {\n" +
                    "        \"durationMillis\" : 200\n" +
                    "    }\n" +
                    "}");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","{\n" +
                    "    \"intervalMillis\": 1800000,\n" +
                    "    \"timeExpiredMillis\": \"4102387200000\",\n" +
                    "    \"urlForSettings\": \"https://i.gtimg.cn/ams-web/public/tangram-report/settings-android-tim-3-4-2.json\",\n" +
                    "    \"version\": 1,\n" +
                    "    \"settingsForXJ\": {\n" +
                    "        \"queueLength\": 20,\n" +
                    "        \"canvas\": true,\n" +
                    "        \"offline\": true\n" +
                    "    },\n" +
                    "    \"settingsForInterstitial\" : {\n" +
                    "        \"timeoutMillis\" : 10000,\n" +
                    "        \"enablePreDownload\" : true\n" +
                    "    },\n" +
                    "    \"settingsForExposure\": {\n" +
                    "        \"durationMillis\" : 200\n" +
                    "    }\n" +
                    "}");
            return;
        }
        if (url.contains("3gimg.qq.com/qq_product_operations/nettest/index.html?mType=connCheck")){
            param.setResult("MobileQQ1");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","MobileQQ1");
            return;
        }
        if (url.contains("resolver.msg.xiaomi.net/gslb/")){
            param.setResult("{\"S\":\"Ok\",\"R\":{\"country\":\"未知\",\"province\":\"未知\",\"city\":\"\",\"isp\":\"未知\",\"X-Forwarded-Ip\":\"39.39.39.39\",\"ip\":\"39.39.39.39\",\"stat-domain\":\"s.mi1.cc\",\"stat-percent\":0.05000000074505806,\"ttl\":3600,\"tid\":-1,\"extra-for-pa\":\"debug info\",\"force-use\":\"false\",\"wifi\":{\"cn.app.chat.xiaomi.net\":[\"220.181.106.152:5222\",\"220.181.106.151:5222\",\"118.26.252.226:443\",\"118.26.252.225:443\",\"58.83.177.220:5222\",\"58.83.177.235:5222\"],\"resolver.msg.xiaomi.net\":[\"118.26.252.209:5222\",\"220.181.106.150:5222\",\"118.26.252.209:80\",\"220.181.106.150:80\",\"39.156.150.162:5222\",\"111.13.142.153:5222\",\"123.125.102.48:5222\"]},\"wap\":{\"cn.app.chat.xiaomi.net\":[\"220.181.106.152:5222\",\"220.181.106.151:5222\",\"118.26.252.226:443\",\"118.26.252.225:443\",\"58.83.177.220:5222\",\"58.83.177.235:5222\"],\"resolver.msg.xiaomi.net\":[\"118.26.252.209:5222\",\"220.181.106.150:5222\",\"118.26.252.209:80\",\"220.181.106.150:80\",\"39.156.150.162:5222\",\"111.13.142.153:5222\",\"123.125.102.48:5222\"]},\"ipv6\":{\"cn.app.chat.xiaomi.net\":[],\"resolver.msg.xiaomi.net\":[]},\"reserved-ttl\":86400,\"reserved\":{\"cn.app.chat.xiaomi.net\":[\"111.13.141.211\",\"39.156.81.172\"]}}}");
            XposedHelpers.setObjectField(param.thisObject, "responseMessage","{\"S\":\"Ok\",\"R\":{\"country\":\"未知\",\"province\":\"未知\",\"city\":\"\",\"isp\":\"未知\",\"X-Forwarded-Ip\":\"39.39.39.39\",\"ip\":\"39.39.39.39\",\"stat-domain\":\"s.mi1.cc\",\"stat-percent\":0.05000000074505806,\"ttl\":3600,\"tid\":-1,\"extra-for-pa\":\"debug info\",\"force-use\":\"false\",\"wifi\":{\"cn.app.chat.xiaomi.net\":[\"220.181.106.152:5222\",\"220.181.106.151:5222\",\"118.26.252.226:443\",\"118.26.252.225:443\",\"58.83.177.220:5222\",\"58.83.177.235:5222\"],\"resolver.msg.xiaomi.net\":[\"118.26.252.209:5222\",\"220.181.106.150:5222\",\"118.26.252.209:80\",\"220.181.106.150:80\",\"39.156.150.162:5222\",\"111.13.142.153:5222\",\"123.125.102.48:5222\"]},\"wap\":{\"cn.app.chat.xiaomi.net\":[\"220.181.106.152:5222\",\"220.181.106.151:5222\",\"118.26.252.226:443\",\"118.26.252.225:443\",\"58.83.177.220:5222\",\"58.83.177.235:5222\"],\"resolver.msg.xiaomi.net\":[\"118.26.252.209:5222\",\"220.181.106.150:5222\",\"118.26.252.209:80\",\"220.181.106.150:80\",\"39.156.150.162:5222\",\"111.13.142.153:5222\",\"123.125.102.48:5222\"]},\"ipv6\":{\"cn.app.chat.xiaomi.net\":[],\"resolver.msg.xiaomi.net\":[]},\"reserved-ttl\":86400,\"reserved\":{\"cn.app.chat.xiaomi.net\":[\"111.13.141.211\",\"39.156.81.172\"]}}}");
            return;
        }
        if (url.contains("resolver.msg.xiaomi.net/")){
            param.setResult("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Welcome to mife!</title>\n" +
                    "<style>\n" +
                    "    body {\n" +
                    "        width: 35em;\n" +
                    "        margin: 0 auto;\n" +
                    "        font-family: Tahoma, Verdana, Arial, sans-serif;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Welcome to mife!</h1>\n" +
                    "<p>If you see this page, the mife web server is successfully installed and\n" +
                    "working. Further configuration is required.</p>\n" +
                    "<p><em>Thank you for using mife.</em></p>\n" +
                    "</body>\n" +
                    "</html>");
            return;
        }
        if (url.contains("https://api.bilibili.com/client_info")){
            LoggerLog(param.getResult());
        }
    }
}
