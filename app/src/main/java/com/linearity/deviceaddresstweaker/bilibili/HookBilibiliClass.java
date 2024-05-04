package com.linearity.deviceaddresstweaker.bilibili;

import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;
import static com.linearity.deviceaddresstweaker.LoggerUtils.showObjectFields;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookBilibiliClass {
    public static boolean HookBilibiliClassFlag = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        if (HookBilibiliClassFlag){
            if (lpparam.processName.split(":")[0].contains("tv.danmaku.bili")){
                
                try {
                    {
                        //all ads,DISAPPEAR!
                        Class<?> hookClass = XposedHelpers.findClassIfExists("com.bilibili.pegasus.api.b0", lpparam.classLoader);
                        if (hookClass != null){

                            Class<?> FastJSONObjectClass = XposedHelpers.findClass("com.alibaba.fastjson.JSONObject",lpparam.classLoader);
                            Class<?> FastJSONArrayClass = XposedHelpers.findClass("com.alibaba.fastjson.JSONArray",lpparam.classLoader);
                            Class<?> FastJSONClass = XposedHelpers.findClass("com.alibaba.fastjson.JSON",lpparam.classLoader);
                            Class<?> PegasusPageReporterClass = XposedHelpers.findClass("com.bilibili.pegasus.promo.report.monitor.PegasusPageReporter",lpparam.classLoader);
                            Class<?> PegasusFeedResponseClass = XposedHelpers.findClass("com.bilibili.pegasus.api.modelv2.PegasusFeedResponse",lpparam.classLoader);
                            Class<?> monitor_b = XposedHelpers.findClass("com.bilibili.pegasus.promo.report.monitor.b",lpparam.classLoader);
                            Class<?> OkhttpResponseBodyClass = XposedHelpers.findClass("okhttp3.ResponseBody",lpparam.classLoader);
                            Class<?> GeneralResponseClass = XposedHelpers.findClass("com.bilibili.okretro.GeneralResponse",lpparam.classLoader);
                            Class<?> ConfigClass = XposedHelpers.findClass("com.bilibili.pegasus.api.modelv2.Config",lpparam.classLoader);
                            Class<?> BaseTMApiParserClass = XposedHelpers.findClass("com.bilibili.pegasus.api.BaseTMApiParser",lpparam.classLoader);
                            Class<?> PromoOperationTabClass = XposedHelpers.findClass("com.bilibili.pegasus.api.model.PromoOperationTab",lpparam.classLoader);
                            Class<?> SearchPgcFavoriteResultClass = XposedHelpers.findClass("com.bilibili.search.result.bangumi.SearchPgcFavoriteResult",lpparam.classLoader);
                            Class<?> ChannelFeedV2Class = XposedHelpers.findClass("com.bilibili.pegasus.api.modelv2.ChannelFeedV2",lpparam.classLoader);
                            Class<?> ViewMaterialClass = XposedHelpers.findClass("com.bapis.bilibili.app.view.v1.ViewMaterial",lpparam.classLoader);
                            Class<?> GarbDataClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData",lpparam.classLoader);
                            Class<?> TabResponseClass = XposedHelpers.findClass("tv.danmaku.bili.ui.main2.resource.MainResourceManager$TabResponse",lpparam.classLoader);
                            Class<?> GarbDetailClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData$GarbDetail",lpparam.classLoader);
                            Method FastJSON_getIntValue = XposedHelpers.findMethodExact(FastJSONObjectClass,"getIntValue",String.class);
                            Method FastJSON_getString = XposedHelpers.findMethodExact(FastJSONObjectClass,"getString",String.class);
                            Method FastJSON_containsKey = XposedHelpers.findMethodExact(FastJSONObjectClass,"containsKey",Object.class);
                            Method FastJSON_getJSONObject = XposedHelpers.findMethodExact(FastJSONObjectClass,"getJSONObject",String.class);
                            Method FastJSON_getJSONArray = XposedHelpers.findMethodExact(FastJSONObjectClass,"getJSONArray",String.class);
                            Method Okhttp_ResponseBody_string = XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string");


                            XposedBridge.hookAllMethods(hookClass, "j", new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Object responseBody = param.args[0];
                                    Object PegasusPageReporter_a2 = XposedHelpers.callStaticMethod(monitor_b,"a");
                                    if (PegasusPageReporter_a2 != null) {
                                        XposedHelpers.findMethodExact(PegasusPageReporterClass,"v").invoke(PegasusPageReporter_a2);
                                    }
                                    String respStr = (String) XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string").invoke(responseBody);
//                                    LoggerLog(respStr);
                                    JSONObject anotherJSON = new JSONObject(respStr);
                                    JSONArray anotherJSONArray = anotherJSON.getJSONObject("data").getJSONArray("items");
                                    JSONArray result = new JSONArray();
                                    int cards = 0;
                                    for (int i=0;i<anotherJSONArray.length();i++){
                                        JSONObject jsonItem = anotherJSONArray.getJSONObject(i);
                                        if (
                                                jsonItem.get("card_type").equals("small_cover_v2")
                                        ){
                                            result.put(jsonItem);
                                            cards += 1;
                                        }
//                                        LoggerLog(jsonItem);
                                    }
                                    anotherJSON.getJSONObject("data").getJSONObject("config").put("autoplay_card",cards);
                                    anotherJSON.getJSONObject("data").put("items", result);
                                    respStr = anotherJSON.toString();

                                    Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",respStr);
                                    Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();

                                    XposedHelpers.setIntField(generalResponse,"code", (Integer) FastJSON_getIntValue.invoke(parseObject,"code"));
                                    XposedHelpers.setObjectField(generalResponse,"message", FastJSON_getString.invoke(parseObject,"message"));
                                    XposedHelpers.setIntField(generalResponse,"ttl", (Integer) FastJSON_getIntValue.invoke(parseObject,"ttl"));

                                    if ((Boolean) FastJSON_containsKey.invoke(parseObject, "data")) {
                                        Object jSONObject = FastJSON_getJSONObject.invoke(parseObject,"data");//parseObject.getJSONObject("data");
                                        Object jSONArray = jSONObject != null ? FastJSON_getJSONArray.invoke(jSONObject,"items") : null;
                                        Object pegasusFeedResponse = XposedHelpers.findConstructorExact(PegasusFeedResponseClass).newInstance();
                                        XposedHelpers.setObjectField(generalResponse,"data", pegasusFeedResponse);

                                        String string = (String) FastJSON_getString.invoke(jSONObject,"config");
                                        Object parsedConfig = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",string, ConfigClass);
                                        XposedHelpers.setObjectField(pegasusFeedResponse,"config", parsedConfig);
                                        if (jSONArray != null) {
                                            Object items = XposedHelpers.findMethodExact(BaseTMApiParserClass,"e",FastJSONArrayClass).invoke(param.thisObject, jSONArray);
                                            Object data = XposedHelpers.findField(generalResponse.getClass(),"data").get(generalResponse);

                                            XposedHelpers.setObjectField(data, "items",items);
//                                            generalResponse.data.items = e(jSONArray);
                                        }
                                    }
                                    Object PegasusPageReporter_a3 = XposedHelpers.callStaticMethod(monitor_b,"a");
                                    if (PegasusPageReporter_a3 != null) {
                                        XposedHelpers.findMethodExact(PegasusPageReporterClass,"x").invoke(PegasusPageReporter_a2);
                                    }
                                    return generalResponse;
                                }
                            });
//                            hookClass = XposedHelpers.findClassIfExists("com.bilibili.pegasus.api.c0", lpparam.classLoader);
//                            {
//                                XposedBridge.hookAllMethods(hookClass, "j", new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        Object responseBody = param.args[0];
//                                        Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();
//                                        String respStr = (String) XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string").invoke(responseBody);
//                                        {
//                                            JSONObject anotherJSON = new JSONObject(respStr);
//                                            JSONArray anotherJSONArray = anotherJSON.getJSONObject("data").getJSONArray("items");
//                                            JSONArray result = new JSONArray();
//                                            int cards = 0;
//                                            for (int i=0;i<anotherJSONArray.length();i++){
//                                                JSONObject jsonItem = anotherJSONArray.getJSONObject(i);
//                                                if (
//                                                        !jsonItem.get("card_goto").equals("ad_web_s")
//                                                                && jsonItem.get("card_type").equals("small_cover_v2")
//                                                ){
//                                                    result.put(jsonItem);
//                                                    cards += 1;
//                                                }else {
//                                                    LoggerLog(jsonItem);
//                                                }
//                                            }
//                                            anotherJSON.getJSONObject("data").getJSONObject("config").put("autoplay_card",cards);
//                                            anotherJSON.getJSONObject("data").put("items", result);
//                                            respStr = anotherJSON.toString();
//                                        }
//
//                                        Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",respStr);
//
//                                        XposedHelpers.setIntField(generalResponse,"code", (Integer) FastJSON_getIntValue.invoke(parseObject,"code"));
//                                        XposedHelpers.setObjectField(generalResponse,"message", FastJSON_getString.invoke(parseObject,"message"));
//                                        XposedHelpers.setIntField(generalResponse,"ttl", (Integer) FastJSON_getIntValue.invoke(parseObject,"ttl"));
//
//                                        if ((Boolean) FastJSON_containsKey.invoke(parseObject, "data")) {
//                                            Object newInstance = XposedHelpers.callStaticMethod(PromoOperationTabClass,"newInstance");
//                                            Object jSONArray = FastJSON_getJSONArray.invoke(parseObject,"items");
//                                            List<?> list = (List<?>) XposedHelpers.findField(PromoOperationTabClass,"item").get(newInstance);
//                                            if (list != null) {
//                                                list.addAll((Collection) XposedHelpers.findMethodExact(BaseTMApiParserClass,"e",FastJSONArrayClass).invoke(param.thisObject, jSONArray));
//                                            }
//                                            XposedHelpers.setObjectField(generalResponse,"data",newInstance);
//                                        }
//                                        return generalResponse;
//                                    }
//                                });
//                            }//still listening
//
//                            hookClass = XposedHelpers.findClassIfExists("com.bilibili.pegasus.api.e", lpparam.classLoader);
//                            {
//                                XposedBridge.hookAllMethods(hookClass, "j", new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        Object responseBody = param.args[0];
//                                        Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();
//                                        String respStr = (String) XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string").invoke(responseBody);
//                                        {
//                                            JSONObject anotherJSON = new JSONObject(respStr);
//                                            LoggerLog(anotherJSON);
//                                            respStr = anotherJSON.toString();
//                                        }
//                                        Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",respStr);
//                                        XposedHelpers.setIntField(generalResponse,"code", (Integer) FastJSON_getIntValue.invoke(parseObject,"code"));
//                                        XposedHelpers.setObjectField(generalResponse,"message", FastJSON_getString.invoke(parseObject,"message"));
//                                        XposedHelpers.setIntField(generalResponse,"ttl", (Integer) FastJSON_getIntValue.invoke(parseObject,"ttl"));
//
//                                        if ((Boolean) FastJSON_containsKey.invoke(parseObject, "data")) {
//                                            Object newInstance = XposedHelpers.callStaticMethod(ChannelFeedV2Class,"newInstance");
//                                            Object JSONObject = FastJSON_getJSONObject.invoke(parseObject,"data");
//                                            Object jSONArray = FastJSON_getJSONArray.invoke(JSONObject,"feed");
//                                            List<?> list = (List<?>) XposedHelpers.findField(ChannelFeedV2Class,"feedList").get(newInstance);
//                                            if (list != null) {
//                                                list.addAll((Collection) XposedHelpers.findMethodExact(BaseTMApiParserClass,"e",FastJSONArrayClass).invoke(param.thisObject, jSONArray));
//                                            }
//                                            XposedHelpers.setObjectField(ChannelFeedV2Class,"topstick",XposedHelpers.callMethod(param.thisObject,"k",JSONObject));
//                                            XposedHelpers.setObjectField(generalResponse,"data",newInstance);
//                                        }
//                                        return generalResponse;
//                                    }
//                                });
//                            }//still listening

//
//                            {
//                                XposedHelpers.findAndHookMethod(OkhttpResponseBodyClass, "string", new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        LoggerLog(new Exception((String)param.getResult()));
//                                    }
//                                });
//                            }

                            {
                                hookClass = XposedHelpers.findClass("com.bilibili.okretro.converter.a",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "convert", new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        Field f = XposedHelpers.findFieldIfExists(param.getResult().getClass(),"data");
                                        if (f != null){
                                            Object resultData = f.get(param.getResult());
                                            if (GarbDataClass.isInstance(resultData)){//Grab Options
                                                XposedHelpers.setObjectField(resultData,"opGarb",null);
                                                Object detail = XposedHelpers.getObjectField(resultData,"userGarb");
                                                //maybe need restart twice rather than once
                                                {
                                                    XposedHelpers.setBooleanField(detail,"isOp",false);
                                                    XposedHelpers.setObjectField(detail,"pkgMd5","e10f4ff37f3d964c6ecb3f8f2c8434c9");
                                                    XposedHelpers.setObjectField(detail,"pkgUrl","https://i0.hdslb.com/bfs/garb/zip/14e2f377e601481e2befab34e0773c81886b7232.zip");
                                                    XposedHelpers.setObjectField(detail,"name","初音未来-夜版");
                                                    XposedHelpers.setLongField(detail,"ver",1625571046L);
                                                    XposedHelpers.setLongField(detail,"id",2530L);
                                                    XposedHelpers.setObjectField(detail,"conf",null);
                                                }//an example
                                                /*
                                                {
                                                    XposedHelpers.setBooleanField(detail,"isOp",false);
                                                    XposedHelpers.setObjectField(detail,"pkgMd5","cb2b384cb6d8e7635a394f6f3100d53a");
                                                    XposedHelpers.setObjectField(detail,"pkgUrl","https://i0.hdslb.com/bfs/garb/zip/b8faab964ae2f61671e416cca22bcba546799c6d.zip");
                                                    //↑I believe this is the only useful one
                                                    // (can change into your own link if you have a server (like 127.0.0.1:80) XD)
                                                    XposedHelpers.setObjectField(detail,"name","樱花未来");
                                                    XposedHelpers.setLongField(detail,"ver",1660720238L);
                                                    XposedHelpers.setLongField(detail,"id",50874L);
                                                    XposedHelpers.setObjectField(detail,"conf",null);
                                                }
                                                */
                                                /*
                                                 And another,you may want to ask your friends
                                                 for that magic link or just download
                                                 from the one upper and research it
                                                 [you will find how to replace image inside easily :)]
                                                 */
                                            }
                                        }
//                                        showObjectFields(param.getResult(),"    ");
//                                        LoggerLog(new Exception(param.getResult().getClass().getTypeName()));
                                    }
                                });
                            }

                            {
                                hookClass = XposedHelpers.findClass("com.bilibili.gripper.router.TribeFawkesTask",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
                            {
                                hookClass = XposedHelpers.findClass("tv.danmaku.bili.ui.main.event.EventEntranceHelper",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "v", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "q", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "o", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
                            {
                                hookClass = XposedHelpers.findClass("com.bilibili.okretro.utils.a",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        Object result = param.getResult();
                                        if (result == null){return;}
                                        if (TabResponseClass.isInstance(result)){
                                            Object tabData = XposedHelpers.getObjectField(param.getResult(),"tabData");
                                            List<Object> top = (List<Object>) XposedHelpers.getObjectField(tabData,"top");
                                            top.removeIf(o -> ((String)XposedHelpers.getObjectField(o,"uri")).startsWith("bilibili://game"));
                                            List<Object> tab = (List<Object>) XposedHelpers.getObjectField(tabData,"tab");
                                            tab.removeIf(o -> ((String)XposedHelpers.getObjectField(o,"uri")).startsWith("bilibili://following/"));
                                            List<Object> bottom = (List<Object>) XposedHelpers.getObjectField(tabData,"bottom");
                                            bottom.removeIf(o ->
                                                    (((String)XposedHelpers.getObjectField(o,"uri")).startsWith("bilibili://uper/")
                                                    || ((String)XposedHelpers.getObjectField(o,"uri")).startsWith("bilibili://mall/")
                                                    ));
//                                            showObjectFields(result,"    ");
//                                            LoggerLog(new Exception("not an exception"));
                                        }
                                    }
                                });
                            }//NO REVISIONISM OCCUPATION!!!!!(I mean the tab "新征程")

//                            {
//                                hookClass = XposedHelpers.findClass("com.bilibili.adcommon.commercial.s",lpparam.classLoader);
//                                XposedBridge.hookAllMethods(hookClass,"x", new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        LoggerLog(param.args[0].getClass().getTypeName());
//                                        LoggerLog(new Exception("not an exception"));
//                                    }
//                                });
//                            }

                            {
                                hookClass = XposedHelpers.findClass("tv.danmaku.bili.ui.video.videodetail.function.y$d",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass,"onDataSuccess", new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        super.beforeHookedMethod(param);
                                        List<Object> videoList = (List<Object>) XposedHelpers.getObjectField(param.args[0],"mRelatedVideos");
                                        videoList.removeIf(o -> !((String) XposedHelpers.getObjectField(o, "goTo")).equals("av"));
                                    }
                                });
                            }

                            {
                                hookClass = XposedHelpers.findClass("com.bapis.bilibili.app.view.v1.ViewReply",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "hasCmUnderPlayer", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "hasCmConfig", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "hasCmIpad", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return false;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "getCmsList", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
//                                XposedBridge.hookAllConstructors(OkhttpResponseBodyClass, new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        LoggerLog(new Exception("not an exception:respBody"));
//                                    }
//                                });
//                            }

//                            hookClass = XposedHelpers.findClassIfExists("com.bilibili.okretro.converter.b", lpparam.classLoader);
//                            {
//                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodHook() {
//                                    @Override
//                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.beforeHookedMethod(param);
//                                        LoggerLog(Okhttp_ResponseBody_string.invoke(param.args[0]));
//                                    }
//                                });
//                            }//still listening

                            hookClass = XposedHelpers.findClassIfExists("com.bilibili.search.result.m", lpparam.classLoader);
                            {
                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Object responseBody = param.args[0];
                                    Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();
                                    String respStr = (String) XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string").invoke(responseBody);
                                    Object searchPgcFavoriteResult = XposedHelpers.findConstructorExact(SearchPgcFavoriteResultClass).newInstance();
                                    JSONObject anotherJSON = new JSONObject(respStr);
                                    {
                                        if (anotherJSON == null) {
                                            return generalResponse;
                                        }
                                        LoggerLog(anotherJSON);
                                        respStr = anotherJSON.toString();
                                    }
                                    Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",respStr);
                                    Object jSONObject = parseObject != null ? FastJSON_getJSONObject.invoke(parseObject,"result") : null;
                                    if (jSONObject == null) {
                                        return generalResponse;
                                    }
                                    XposedHelpers.setIntField(searchPgcFavoriteResult,"status",anotherJSON.getJSONObject("result").getInt("status"));
                                    XposedHelpers.setObjectField(searchPgcFavoriteResult,"toast",anotherJSON.getJSONObject("result").getString("toast"));
                                    XposedHelpers.setObjectField(generalResponse,"data",searchPgcFavoriteResult);
                                    return generalResponse;
                                    }
                                });
                            }
                            hookClass = XposedHelpers.findClassIfExists("com.bilibili.search.discover.e", lpparam.classLoader);
                            {
                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        String respStr = (String) Okhttp_ResponseBody_string.invoke(param.args[0]);
                                        JSONObject respJSON = new JSONObject(respStr);
                                        JSONArray respData = respJSON.getJSONArray("data");
                                        JSONArray replacement = new JSONArray();
                                        for (int i=0;i<respData.length();i++){
                                            if (!respData.getJSONObject(i).getString("type").equals("trending")){
                                                replacement.put(respData.getJSONObject(i));
                                            }
                                        }
                                        respJSON.put("data",replacement);
                                        respStr = respJSON.toString();
                                        Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject",respStr);
                                        Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();
                                        int respCode = (Integer) FastJSON_getIntValue.invoke(parseObject,"code");
                                        XposedHelpers.setIntField(generalResponse,"code", respCode);
                                        XposedHelpers.setObjectField(generalResponse,"message", FastJSON_getString.invoke(parseObject,"message"));
                                        if (respCode == 0) {
                                            XposedHelpers.setObjectField(generalResponse,"data", XposedHelpers.callMethod(param.thisObject,"b",FastJSON_getJSONArray.invoke(parseObject,"data")));
//                                            generalResponse.data = b(parseObject.getJSONArray("data"));
                                        }
                                        return generalResponse;
                                    }
                                }
                                );
                            }
                        }

                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesRemoteService", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesService", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.InfoEyesLocalService", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.d", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.infoeyes.d$d", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.CrashReport", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.a", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.b", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.c", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.d", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.e", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.crashreport.CrashReporter", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                    {
                    Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.teenagersmode.TeenagersMode", lpparam.classLoader);
                    if (HookClass != null){
                        XposedBridge.hookAllConstructors(
                                HookClass,
                                new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param){
                                        return null;
                                    }
                                }
                        );
                        for (Method method : HookClass.getDeclaredMethods()) {
                            Class<?> returnType = method.getReturnType();
                            if (returnType.equals(Void.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return null;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Boolean.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return false;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Long.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0L;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(Integer.TYPE)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return 0;
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(String.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return "";
                                            }
                                        }
                                );
                            }
                            else if (returnType.equals(List.class)) {
                                XposedBridge.hookMethod(
                                        method,
                                        new XC_MethodReplacement() {
                                            @Override
                                            protected Object replaceHookedMethod(MethodHookParam param){
                                                return new ArrayList<>();
                                            }
                                        }
                                );
                            }
                        }
                    }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
