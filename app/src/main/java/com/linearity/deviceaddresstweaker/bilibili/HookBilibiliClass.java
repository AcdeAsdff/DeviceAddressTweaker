package com.linearity.deviceaddresstweaker.bilibili;

import static com.linearity.deviceaddresstweaker.LoggerUtils.LoggerLog;
import static com.linearity.deviceaddresstweaker.LoggerUtils.showObjectFields;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
                String rootName = "/sdcard/Android/data/tv.danmaku.bili/";
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
                            Class<?> GarbData_LoadEquipClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData$LoadEquip",lpparam.classLoader);
                            Class<?> GarbDetailClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData$GarbDetail",lpparam.classLoader);
                            Class<?> PureGarbDetailClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData$PureGarbDetail",lpparam.classLoader);
                            Class<?> ColorDetailClass = XposedHelpers.findClass("tv.danmaku.bili.ui.garb.model.GarbData$ColorDetail",lpparam.classLoader);
                            Class<?> BiliApiDataCallbackClass = XposedHelpers.findClass("com.bilibili.okretro.BiliApiDataCallback",lpparam.classLoader);
                            Class<?> TabResponseClass = XposedHelpers.findClass("tv.danmaku.bili.ui.main2.resource.MainResourceManager$TabResponse",lpparam.classLoader);
                            Class<?> BiliContextClass = XposedHelpers.findClass("com.bilibili.base.BiliContext",lpparam.classLoader);
                            Class<?> SplashDataClass = XposedHelpers.findClass("tv.danmaku.bili.ui.splash.ad.model.SplashData",lpparam.classLoader);
                            Class<?> SplashShowDataClass = XposedHelpers.findClass("tv.danmaku.bili.ui.splash.ad.model.SplashShowData",lpparam.classLoader);
                            Class<?> BrandSplashDataClass = XposedHelpers.findClass("tv.danmaku.bili.ui.splash.brand.model.BrandSplashData",lpparam.classLoader);
                            Class<?> FastJSONFeatureClass = XposedHelpers.findClass("com.alibaba.fastjson.parser.Feature",lpparam.classLoader);
                            Method FastJSON_getIntValue = XposedHelpers.findMethodExact(FastJSONObjectClass,"getIntValue",String.class);
                            Method FastJSON_getString = XposedHelpers.findMethodExact(FastJSONObjectClass,"getString",String.class);
                            Method FastJSON_containsKey = XposedHelpers.findMethodExact(FastJSONObjectClass,"containsKey",Object.class);
                            Method FastJSON_getJSONObject = XposedHelpers.findMethodExact(FastJSONObjectClass,"getJSONObject",String.class);
                            Method FastJSON_getJSONArray = XposedHelpers.findMethodExact(FastJSONObjectClass,"getJSONArray",String.class);
                            Method Okhttp_ResponseBody_string = XposedHelpers.findMethodExact(OkhttpResponseBodyClass,"string");
                            Method fetchGarbSuccess = XposedHelpers.findMethodExact(BiliApiDataCallbackClass,"onDataSuccess",Object.class);
                            Constructor<?> PureGarbDetailConstructor = XposedHelpers.findConstructorExact(PureGarbDetailClass);
                            List<Object> PureGarbDetailList = new ArrayList<>();
                            {
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "white", 0, 8, false, true, "简洁白", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "pink", 0, 2, false, true, "少女粉", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "black", 0, 1, false, true, "主题黑", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "red", 0, 3, false, true, "高能红", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "yellow", 0, 4, false, true, "咸蛋黄", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "green", 0, 5, false, true, "早苗绿", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "blue", 0, 6, false, true, "宝石蓝", 0, 0));
                                PureGarbDetailList.add(PureGarbDetailArrayInit(PureGarbDetailConstructor, 0, "purple", 0, 7, false, true, "罗兰紫", 0, 0));
                            }

                            {
                                XposedBridge.hookAllMethods(hookClass, "j", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Object responseBody = param.args[0];
                                        Object PegasusPageReporter_a2 = XposedHelpers.callStaticMethod(monitor_b, "a");
                                        if (PegasusPageReporter_a2 != null) {
                                            XposedHelpers.findMethodExact(PegasusPageReporterClass, "v").invoke(PegasusPageReporter_a2);
                                        }
                                        String respStr = (String) XposedHelpers.findMethodExact(OkhttpResponseBodyClass, "string").invoke(responseBody);
                                        JSONObject anotherJSON = new JSONObject(respStr);
                                        JSONArray anotherJSONArray = anotherJSON.getJSONObject("data").getJSONArray("items");
                                        JSONArray result = new JSONArray();
                                        int cards = 0;
                                        for (int i = 0; i < anotherJSONArray.length(); i++) {
                                            JSONObject jsonItem = anotherJSONArray.getJSONObject(i);
                                            if (
                                                    jsonItem.get("card_type").equals("small_cover_v2")
                                            ) {
                                                result.put(jsonItem);
                                                cards += 1;
                                            }
                                        }
                                        anotherJSON.getJSONObject("data").getJSONObject("config").put("autoplay_card", cards);
                                        anotherJSON.getJSONObject("data").put("items", result);
                                        respStr = anotherJSON.toString();

                                        Object parseObject = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject", respStr);
                                        Object generalResponse = XposedHelpers.findConstructorExact(GeneralResponseClass).newInstance();

                                        XposedHelpers.setIntField(generalResponse, "code", (Integer) FastJSON_getIntValue.invoke(parseObject, "code"));
                                        XposedHelpers.setObjectField(generalResponse, "message", FastJSON_getString.invoke(parseObject, "message"));
                                        XposedHelpers.setIntField(generalResponse, "ttl", (Integer) FastJSON_getIntValue.invoke(parseObject, "ttl"));

                                        if ((Boolean) FastJSON_containsKey.invoke(parseObject, "data")) {
                                            Object jSONObject = FastJSON_getJSONObject.invoke(parseObject, "data");//parseObject.getJSONObject("data");
                                            Object jSONArray = jSONObject != null ? FastJSON_getJSONArray.invoke(jSONObject, "items") : null;
                                            Object pegasusFeedResponse = XposedHelpers.findConstructorExact(PegasusFeedResponseClass).newInstance();
                                            XposedHelpers.setObjectField(generalResponse, "data", pegasusFeedResponse);

                                            String string = (String) FastJSON_getString.invoke(jSONObject, "config");
                                            Object parsedConfig = XposedHelpers.callStaticMethod(FastJSONClass, "parseObject", string, ConfigClass);
                                            XposedHelpers.setObjectField(pegasusFeedResponse, "config", parsedConfig);
                                            if (jSONArray != null) {
                                                Object items = XposedHelpers.findMethodExact(BaseTMApiParserClass, "e", FastJSONArrayClass).invoke(param.thisObject, jSONArray);
                                                Object data = XposedHelpers.findField(generalResponse.getClass(), "data").get(generalResponse);

                                                XposedHelpers.setObjectField(data, "items", items);
//                                            generalResponse.data.items = e(jSONArray);
                                            }
                                        }
                                        Object PegasusPageReporter_a3 = XposedHelpers.callStaticMethod(monitor_b, "a");
                                        if (PegasusPageReporter_a3 != null) {
                                            XposedHelpers.findMethodExact(PegasusPageReporterClass, "x").invoke(PegasusPageReporter_a2);
                                        }
                                        return generalResponse;
                                    }
                                });
                            }
                            {
                                hookClass = XposedHelpers.findClass("com.bilibili.biligame.abtest.AbTestHelper",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "getAllExpInfo", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });

                                hookClass = XposedHelpers.findClass("com.bilibili.biligame.helper.GameCopyWritingConfig",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });

                                hookClass = XposedHelpers.findClass("tv.danmaku.bili.ui.splash.ad.c0",lpparam.classLoader);
                                XposedBridge.hookAllMethods(hookClass, "p", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Object result = XposedHelpers.findConstructorExact(SplashShowDataClass).newInstance();
                                        XposedHelpers.setObjectField(result,"splashRequestId","1714881574374q172a27a174a101q1504");
                                        XposedHelpers.setObjectField(result,"strategyList",new ArrayList<>());
                                        return result;
                                    }
                                });

                                XposedBridge.hookAllMethods(hookClass, "t", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Object result = XposedHelpers.findConstructorExact(SplashDataClass).newInstance();
                                        XposedHelpers.setObjectField(result,"splashRequestId","1714881574374q172a27a174a101q1504");
                                        XposedHelpers.setIntField(result,"intervalForShow",0);
                                        XposedHelpers.setIntField(result,"intervalForUpdate",0);
                                        XposedHelpers.setIntField(result,"maxCount",0);
                                        XposedHelpers.setObjectField(result,"keepIds",new ArrayList<Long>());
                                        XposedHelpers.setObjectField(result,"splashList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"strategyList",new ArrayList<>());
                                        return result;
                                    }
                                });
                                hookClass = XposedHelpers.findClass("tv.danmaku.bili.ui.splash.brand.BrandSplashHelper",lpparam.classLoader);
                                XposedHelpers.setStaticLongField(BrandSplashDataClass,"DEFAULT_PULL_INTERVAL",0);
                                XposedBridge.hookAllMethods(hookClass, "J", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Object result = XposedHelpers.findConstructorExact(BrandSplashDataClass).newInstance();
                                        XposedHelpers.setLongField(result,"pullInterval",0L);
                                        XposedHelpers.setIntField(result,"forceShowTimes",0);
                                        XposedHelpers.setObjectField(result,"brandList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"collectionList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"preloadList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"queryList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"showList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"rule","order");
                                        XposedHelpers.setObjectField(result,"hasNewSplash",Boolean.FALSE);
                                        XposedHelpers.setObjectField(result,"newSplashHash","");
                                        XposedHelpers.setObjectField(result,"forceShowHash","");
                                        XposedHelpers.setBooleanField(result,"forcibly",false);
                                        return result;
                                    }
                                });
                                XposedBridge.hookAllMethods(hookClass, "m", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        Object result = XposedHelpers.findConstructorExact(BrandSplashDataClass).newInstance();
                                        XposedHelpers.setLongField(result,"pullInterval",0L);
                                        XposedHelpers.setIntField(result,"forceShowTimes",0);
                                        XposedHelpers.setObjectField(result,"brandList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"collectionList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"preloadList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"queryList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"showList",new ArrayList<>());
                                        XposedHelpers.setObjectField(result,"rule","order");
                                        XposedHelpers.setObjectField(result,"hasNewSplash",Boolean.FALSE);
                                        XposedHelpers.setObjectField(result,"newSplashHash","");
                                        XposedHelpers.setObjectField(result,"forceShowHash","");
                                        XposedHelpers.setBooleanField(result,"forcibly",false);
                                        return result;
                                    }
                                });

                                hookClass = XposedHelpers.findClass("com.bilibili.adcommon.event.b",lpparam.classLoader);

                                XposedBridge.hookAllMethods(hookClass, "h", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });

                                hookClass = XposedHelpers.findClass("com.bilibili.adcommon.commercial.t",lpparam.classLoader);

                                XposedBridge.hookAllMethods(hookClass, "l", new XC_MethodReplacement() {
                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return null;
                                    }
                                });
                            }
                            {
//                                hookClass = XposedHelpers.findClass("com.bilibili.okretro.converter.a",lpparam.classLoader);
//                                XposedBridge.hookAllMethods(hookClass, "convert", new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                        String prefix = "";
//                                        String respStr = (String) Okhttp_ResponseBody_string.invoke(param.args[0]);
//                                        Type a = (Type) XposedHelpers.getObjectField(param.thisObject,"a");
//                                        int b = XposedHelpers.getStaticIntField(param.thisObject.getClass(),"b");
//                                        Object[] features = (Object[]) Array.newInstance(FastJSONFeatureClass,0);
//                                        Object result = XposedHelpers.callStaticMethod(FastJSONClass,"parseObject",respStr,a,b,features);
//                                        Object data;
//                                        if (XposedHelpers.findFieldIfExists(result.getClass(),"data") != null){
//                                        if (result != null && (data=XposedHelpers.getObjectField(result,"data")) != null){
//                                            prefix = data.getClass().getTypeName();
//                                        }
//                                        }else {
//                                            prefix = result.getClass().getTypeName();
//                                        }
//                                        LoggerLog(new Exception(prefix + " | " + respStr));
//                                        return result;
//                                    }
//                                });
                            }
                            {
                                hookClass = XposedHelpers.findClassIfExists("tv.danmaku.android.log.BLog",lpparam.classLoader);
                                if (hookClass != null){
                                    XposedBridge.hookAllMethods(hookClass,"i", new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) {
                                            return null;
                                        }
                                    });
                                }
                            }
                            {
                                {
                                    {
                                        hookClass = XposedHelpers.findClassIfExists("tv.danmaku.bili.ui.garb.core.g", lpparam.classLoader);
                                        if (hookClass != null) {
                                            XposedBridge.hookAllMethods(hookClass, "x", new XC_MethodHook() {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    param.setResult(rootName);
                                                }
                                            });
                                            XposedBridge.hookAllMethods(hookClass, "w", new XC_MethodHook() {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    Object garbDetail = param.args[0];
                                                    Application application;
                                                    if (TextUtils.isEmpty((CharSequence) XposedHelpers.getObjectField(garbDetail, "pkgUrl")) || (application = (Application) XposedHelpers.callStaticMethod(BiliContextClass, "application")) == null) {
                                                        return;
                                                    }
                                                    File file = new File((String) XposedHelpers.callMethod(param.thisObject, "j", application, garbDetail));
                                                    if (!file.exists()) {
                                                        file.mkdirs();
                                                    }
                                                    String valueOf = String.valueOf(XposedHelpers.getLongField(garbDetail, "id"));
                                                    File file2 = new File(file, valueOf);
                                                    if (file2.exists()) {
                                                        param.setResult(null);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    {
                                        hookClass = XposedHelpers.findClassIfExists("tv.danmaku.bili.ui.garb.core.j", lpparam.classLoader);
                                        if (hookClass != null) {
                                            XposedBridge.hookAllMethods(hookClass, "d", new XC_MethodReplacement() {
                                                @Override
                                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                                    String fileName = rootName + "load_equip";
//                                            LoggerLog(new Exception("not an exception:"+fileName));
                                                    File f = new File(fileName);
                                                    if (!f.exists()) {
                                                        f.mkdirs();
                                                    }
                                                    return fileName;
                                                }
                                            });
                                            XposedBridge.hookAllMethods(hookClass, "c", new XC_MethodHook() {
                                                @Override
                                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                                    super.beforeHookedMethod(param);
                                                    Object loadEquip = param.args[0];
                                                    if (loadEquip == null) {
                                                        return;
                                                    }
                                                    String fileName = rootName + "load_equip";
                                                    File file = new File(fileName);
                                                    if (!file.exists()) {
                                                        file.mkdirs();
                                                    }
                                                    File file2 = new File(file, (String) XposedHelpers.callMethod(loadEquip, "getFileName"));
                                                    if (file2.exists()) {
                                                        param.setResult(null);
                                                    }
                                                }
                                            });
                                        }
                                    }
                                }//setting garb path
                                hookClass = XposedHelpers.findClassIfExists("tv.danmaku.bili.ui.garb.model.GarbApiHelper",lpparam.classLoader);
                                if (hookClass != null){
                                    XposedBridge.hookAllMethods(hookClass, "a", new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                                            showObjectFields(param.args[0],"    ");//to check grab using now and fetch info
                                            Object resultData = XposedHelpers.findConstructorExact(GarbDataClass).newInstance();
                                            XposedHelpers.setObjectField(resultData,"opGarb",null);

                                            //CONFIG GRABS(will not download again for same id,file path tweaked to /sdcard/Android/data/tv.danmaku.bili/)
                                            //id decides the storage path
                                            String name = "初音未来-夜版";
                                            //"樱花未来"
                                            String pkgUrl = "https://i0.hdslb.com/bfs/garb/zip/14e2f377e601481e2befab34e0773c81886b7232.zip";
                                            //"https://i0.hdslb.com/bfs/garb/zip/b8faab964ae2f61671e416cca22bcba546799c6d.zip"
                                            String pkgMd5 = "e10f4ff37f3d964c6ecb3f8f2c8434c9";
                                            //"cb2b384cb6d8e7635a394f6f3100d53a"
                                            long ver = 1625571046L;
                                            //1660720238L
                                            long id = 2530L;
                                            //50874L
                                            long loadEquipID = 2531L;
                                            long loadEquipVer = 1598602035L;
                                            String loadEquipName = "初音未来13周年";
                                            String loadEquipUrl = "https://i0.hdslb.com/bfs/garb/item/9b12e8b5cc16a4c2e71e91c43796f09d5e132847.webp";
                                            XposedHelpers.setObjectField(resultData,"pureGarb",PureGarbDetailList);
                                            {
                                                XposedHelpers.setObjectField(resultData,"loadEquip",XposedHelpers.findConstructorExact(GarbData_LoadEquipClass).newInstance());
                                                Object loadEquip = XposedHelpers.getObjectField(resultData,"loadEquip");
                                                XposedHelpers.setLongField(loadEquip,"id",loadEquipID);
                                                XposedHelpers.setLongField(loadEquip,"version",loadEquipVer);
                                                XposedHelpers.setObjectField(loadEquip,"name", loadEquipName);
                                                XposedHelpers.setObjectField(loadEquip,"url", loadEquipUrl);
                                            }
                                            //maybe need restart twice rather than once
                                            {
                                                Object colorData = XposedHelpers.findConstructorExact(ColorDetailClass).newInstance();
                                                {
                                                    XposedHelpers.setObjectField(colorData,"animateMode","once");
                                                    XposedHelpers.setObjectField(colorData,"btnBgEndColor","#8edfe3");
                                                    XposedHelpers.setObjectField(colorData,"btnBgStartColor","#40b7bb");
                                                    XposedHelpers.setObjectField(colorData,"btnIconColor","#4c4975");
                                                    XposedHelpers.setBooleanField(colorData,"hasAnimate",true);
                                                    XposedHelpers.setObjectField(colorData,"headMyselfPlayMode","once");
                                                    XposedHelpers.setObjectField(colorData,"mode","dark");
                                                    XposedHelpers.setObjectField(colorData,"primaryColor","#ffffff");
                                                    XposedHelpers.setObjectField(colorData,"secondaryColor","#4c4974");
                                                    XposedHelpers.setObjectField(colorData,"sideBgColor","#29244d");
                                                    XposedHelpers.setObjectField(colorData,"sideLineColor",null);
                                                    XposedHelpers.setObjectField(colorData,"tailColor","#ffffff");
                                                    XposedHelpers.setObjectField(colorData,"tailIconColor",null);
                                                    XposedHelpers.setObjectField(colorData,"tailIconColorNight",null);
                                                    XposedHelpers.setObjectField(colorData,"tailIconColorSelected",null);
                                                    XposedHelpers.setObjectField(colorData,"tailIconColorSelectedNight",null);
                                                    XposedHelpers.setObjectField(colorData,"tailIconModel","img");
                                                    XposedHelpers.setObjectField(colorData,"tailSelectedColor","#a2f3f7");
                                                }
                                                Object detail = XposedHelpers.findConstructorExact(GarbDetailClass).newInstance();
                                                XposedHelpers.setObjectField(detail,"colorData",colorData);
                                                XposedHelpers.setBooleanField(detail,"isOp",false);
                                                XposedHelpers.setObjectField(detail,"pkgMd5",pkgMd5);
                                                XposedHelpers.setObjectField(detail,"pkgUrl",pkgUrl);
                                                XposedHelpers.setObjectField(detail,"name",name);
                                                XposedHelpers.setLongField(detail,"ver",ver);
                                                XposedHelpers.setLongField(detail,"id",id);
                                                XposedHelpers.setObjectField(detail,"conf",null);

                                                XposedHelpers.setObjectField(resultData,"userGarb",detail);
                                            }
                                            fetchGarbSuccess.invoke(param.args[2],resultData);
                                            return null;
                                        }
                                    });
                                }
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
                                        }
                                    }
                                });
                            }//NO REVISIONISM OCCUPATION!!!!!(I mean the tab like "新征程")
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
                            {
                                hookClass = XposedHelpers.findClassIfExists("com.bilibili.search.result.m", lpparam.classLoader);
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
                            {
                                hookClass = XposedHelpers.findClassIfExists("com.bilibili.search.discover.e", lpparam.classLoader);
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
                    {
                        Class<?> HookClass = XposedHelpers.findClassIfExists("com.bilibili.lib.push.q0", lpparam.classLoader);
                        if (HookClass != null){
                            XposedBridge.hookAllMethods(
                                    HookClass,"i",
                                    new XC_MethodReplacement() {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param){
                                            return null;
                                        }
                                    }
                            );
                        }
                        for (String className:new String[]{
                                "com.bilibili.lib.push.HonorPushRegistry",
                                "com.bilibili.lib.push.OppoPushRegistry",
                                "com.bilibili.lib.push.HuaweiNewPushRegistry",
                                "com.bilibili.lib.push.MiPushRegistry",
                                "com.bilibili.lib.push.VivoPushRegistry",
                                "com.bilibili.lib.push.t",
                        }) {
                            HookClass = XposedHelpers.findClassIfExists(className, lpparam.classLoader);
                            if (HookClass == null){continue;}
                            XposedBridge.hookAllMethods(HookClass, "getPushComponents", new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return new Class<?>[0];
                                }
                            });
                            XposedBridge.hookAllMethods(HookClass, "registerPushService", new XC_MethodReplacement() {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }

    public static Object PureGarbDetailArrayInit(Constructor<?> PureGarbDetailConstructor, long buyTime, String colorName, long dueTime, long id, boolean isBought, boolean isFree, String name, int price, int status) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object result = PureGarbDetailConstructor.newInstance();
        XposedHelpers.setLongField(result,"buyTime",buyTime);
        XposedHelpers.setLongField(result,"dueTime",dueTime);
        XposedHelpers.setLongField(result,"id",id);
        XposedHelpers.setIntField(result,"price",price);
        XposedHelpers.setIntField(result,"status",status);
        XposedHelpers.setBooleanField(result,"isBought",isBought);
        XposedHelpers.setBooleanField(result,"isFree",isFree);
        XposedHelpers.setObjectField(result,"colorName",colorName);
        XposedHelpers.setObjectField(result,"name",name);
        return result;
    }
}
