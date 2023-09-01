package com.linearity.deviceaddresstweaker.JavaHooks.java.net;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.getRandomString;

import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkBannedInFile;
import static com.linearity.deviceaddresstweaker.JavaHooks.java.io.HookIO.checkReplaceFile;

public class HookJavaNetClass {
    public static boolean trackUrlLogger = false;//millions years of war
    public static boolean HookNet = true;
    public static boolean HookNetworkInterface = true;
    public static boolean HookHttpUrlConnection = true;
    public static boolean HookUrl = true;

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
        if (HookNet){
            if (HookNetworkInterface) {
//      java.net.NetworkInterface.class getHardwareAddress()
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "getHardwareAddress",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getHardwareAddress()" + param.getResult());
                                    param.setResult(new byte[]{1, 1, 4, 5, 1, 4});
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
//      java.net.NetworkInterface.class getNetworkInterfaces()
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
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

                } catch (Exception e) {
                    LoggerLog(e);
                }
//      java.net.NetworkInterface.class getAll()
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
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

                } catch (Exception e) {
                    LoggerLog(e);
                }
//      java.net.NetworkInterface.class getSubInterfaces()
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "getSubInterfaces",
                            new XC_MethodHook(114514) {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) {
                                    //LoggerLog(lpparam.packageName + "调用getSubInterfaces()" + param.getResult());
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
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "name:null";
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "getName",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "getDisplayName",
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
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "isVirtual",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return false;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            java.net.NetworkInterface.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return Integer.MAX_VALUE;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookHttpUrlConnection) {
                try {
                    Class<?> urlClass = XposedHelpers.findClass(java.net.HttpURLConnection.class.getName(), lpparam.classLoader);
                    XposedBridge.hookAllConstructors(
                            urlClass,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if (!checkUrl(param)){return;}
                                }
                            }
                    );


                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {

                    XposedHelpers.findAndHookMethod(
                            java.net.HttpURLConnection.class.getName(),
                            lpparam.classLoader,
                            "getResponseMessage",
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    setUrlResponseMsg(param);
                                }
                            }
                    );


                } catch (Exception e) {
                    LoggerLog(e);
                }

            }//not finished
            if (HookUrl){
                try {
                    Class<?> urlClass = XposedHelpers.findClass(java.net.URL.class.getName(), lpparam.classLoader);
                    XposedBridge.hookAllConstructors(
                            urlClass,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if (!checkUrl(param)){return;}
                                    if (param.args.length < 3){return;}
                                    if (param.args[0] == null){return;}
                                    if (((String)param.args[0]).equals("file")){
                                        if (param.args[2] == null){return;}
                                        if (param.args[2] instanceof String){
                                            String path = (String) param.args[2];
                                            path = checkReplaceFile(path, lpparam);
                                            if (!checkBannedInFile(path, lpparam)) {
                                                path = "/";
                                            }
                                            param.args[2] = path;
                                        }
                                        if (param.args[3] instanceof String){
                                            String path = (String) param.args[3];
                                            path = checkReplaceFile(path, lpparam);
                                            if (!checkBannedInFile(path, lpparam)) {
                                                path = "/";
                                            }
                                            param.args[3] = path;
                                        }
                                    }
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    Class<?> urIClass = XposedHelpers.findClass(java.net.URI.class.getName(), lpparam.classLoader);
                    XposedBridge.hookAllConstructors(
                            urIClass,
                            new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    if (!checkUrl(param)){return;}
                                    if (param.args[0] == null){return;}
                                    if (((String)param.args[0]).equals("file")){
                                        if (param.args[2] == null){return;}
                                        String path = (String) param.args[2];
                                        path = checkReplaceFile(path,lpparam);
                                        if (!checkBannedInFile(path,lpparam)){
                                            path = "/";
                                        }
                                        param.args[2] = path;
                                    }
                                }
                            }
                    );

                } catch (Exception e) {
                    LoggerLog(e);
                }
            }//not finished
        }
    }



    //maybe i have to improve String#contains() here
    public static boolean checkUrl(XC_MethodHook.MethodHookParam param){
        if (param.args[0] == null){return false;}
        String tempUrl = param.args[0].toString();
        if (tempUrl.startsWith("https://")){
            tempUrl = tempUrl.substring(8);
        }
        else if (tempUrl.startsWith("http://")){
            tempUrl = tempUrl.substring(7);
        }
        if (!tempUrl.startsWith("127.39.0.1")){
            if (
                    (
                    tempUrl.startsWith("d3g.qq.com")
                    ||tempUrl.startsWith("sngmta.qq.com:80")
                    ||tempUrl.startsWith("cmshow.gtimg.cn")
                    ||tempUrl.startsWith("android.rqd.qq.com/analytics/")
                    ||tempUrl.startsWith("log.tbs.qq.com")
                    ||tempUrl.startsWith("dl.url.cn")
                    ||tempUrl.startsWith("qappcenterv6.3g.qq.com")
                    ||tempUrl.startsWith("3gimg.qq.com")
                    ||tempUrl.startsWith("hao.gamecenter.qq.com")
                    ||tempUrl.startsWith("157.148.33.71:443")
                    ||tempUrl.startsWith("h5.qzone.qq.com/report/")
                    ||tempUrl.startsWith("masdkv6.3g.qq.com")
                    ||tempUrl.startsWith("imgcache.qq.com/club/webview/preload.html")
                    ||tempUrl.startsWith("appservice.qq.com")
                    ||tempUrl.startsWith("wspeed.qq.com")
                    ||tempUrl.startsWith("android.bugly.qq.com")
                    ||tempUrl.startsWith("line1-log.biligame.net")

                    ||tempUrl.startsWith("/sngapp/app/update/")
                    ||tempUrl.startsWith("/cgi-bin/mapp/")
                    ||tempUrl.startsWith("/qzone/")
                    ||tempUrl.startsWith("i.gtimg.cn")
                    ||tempUrl.startsWith("resolver.msg.xiaomi.net")
                    ||tempUrl.startsWith("123.125.102.48:5222")
                    ||tempUrl.startsWith("111.202.1.252")
                    ||tempUrl.startsWith("39.156.150.162:5222")
                    ||tempUrl.startsWith("220.181.106.150:80")
                    ||tempUrl.startsWith("111.13.142.153:5222")
                    ||tempUrl.equals("miniapp.gtimg.cn/")
                    ||tempUrl.equals("miniapp.gtimg.cn")
                    ||tempUrl.startsWith("wa.qq.com/hot-res/")
            )){
                param.args[0] = "https://127.39.0.1/" + tempUrl;
                return false;
            }
        }
        if (!(
                tempUrl.startsWith("127.39.0.1")
                ||tempUrl.startsWith("chatthumb")
                ||tempUrl.startsWith("file")
                ||tempUrl.startsWith("chatimg")
                ||tempUrl.startsWith("jar")
                ||tempUrl.startsWith("pgdt.gtimg.cn")
                ||tempUrl.startsWith("qzonestyle.gtimg.cn")
                ||tempUrl.startsWith("125.88.187.141")
                ||tempUrl.startsWith("qqsys_emoji")
                ||tempUrl.startsWith("profile_img_big_fhd")
                ||tempUrl.startsWith("q.qlogo.cn/")
                ||tempUrl.startsWith("chatraw")
                ||tempUrl.startsWith("/public/appicon/")
                ||tempUrl.startsWith("miniapp.gtimg.cn/public/appicon/")
                ||tempUrl.startsWith("pic.ugcimg.cn/")
                ||tempUrl.startsWith("about:blank")
                ||tempUrl.startsWith("/psc?/")
                ||tempUrl.startsWith("sec.video.qq.com/")
                ||tempUrl.startsWith("m.qpic.cn/")
                ||tempUrl.startsWith("h5.qzone.qq.com/")
                ||tempUrl.startsWith("125.88.187.159")
                ||tempUrl.startsWith("BOT")
                ||tempUrl.startsWith("directaddress")
                ||tempUrl.startsWith("dns.google")
                ||tempUrl.startsWith("api.bilibili.com/open/monitor/apm/report")
                ||tempUrl.startsWith("protocol_vas_extension_image")
                ||tempUrl.startsWith("emotion_pic")
                ||tempUrl.startsWith("/club/item/parcel/")
                ||tempUrl.startsWith("apollo_image")
                ||tempUrl.startsWith("hot_pic")
                )
        ){
            if (trackUrlLogger){
                LoggerLog("[linearity]---------------------");
                for (int i = 0; i < param.args.length; i++) {
                    LoggerLog(param.args[i]);
                }
                LoggerLog("[linearity]---------------------");
            }
        }
        return true;
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
