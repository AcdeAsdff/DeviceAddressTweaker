package com.linearity.deviceaddresstweaker.AndroidHooks.android.content.pm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.SparseArray;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.getRandomString;

import com.linearity.utils.FakeClass.java.util.CantUseArrayList;

import java.util.UUID;

public class HookPmClass {
    public static boolean HookPm = true;
    public static boolean HookPackageManager = true;
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        if (HookPm){
            if (HookPackageManager){
                try {
                    hookClass = XposedHelpers.findClassIfExists(PackageManager.class.getName(),lpparam.classLoader);
                    if (hookClass != null){
                        XposedHelpers.setStaticIntField(PackageManager.class,"PERMISSION_DENIED",PackageManager.PERMISSION_GRANTED);

                    }
                    hookClass = XposedHelpers.findClassIfExists("android.app.ApplicationPackageManager",lpparam.classLoader);
                    if (hookClass != null){
                        XposedBridge.hookAllMethods(hookClass, "getApplicationInfoAsUser", new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                                String inputPackage = (String) param.args[0];
                                if (inputPackage.equals(lpparam.packageName)){return;}
                                LoggerLog(new Exception("calling method:getApplicationInfoAsUser |" + inputPackage));
                                ApplicationInfo result = (ApplicationInfo) param.getResult();
                                param.setResult(confuseApplicationInfo(result));
                            }
                        });
                    }
                }catch (Exception e){
                    LoggerLog(e);
                }
            }

        }
    }

    public static ApplicationInfo confuseApplicationInfo(ApplicationInfo toConfuse){
        XposedHelpers.setObjectField(toConfuse,"targetActivity",null);
        XposedHelpers.setObjectField(toConfuse,"processName",getRandomString(20));
        XposedHelpers.setObjectField(toConfuse,"permission",getRandomString(20));
        XposedHelpers.setObjectField(toConfuse,"className",getRandomString(20));
        XposedHelpers.setObjectField(toConfuse,"descriptionRes",0);
        XposedHelpers.setObjectField(toConfuse,"theme",0);
        XposedHelpers.setObjectField(toConfuse,"manageSpaceActivityName",null);
        XposedHelpers.setObjectField(toConfuse,"backupAgentName",null);
        XposedHelpers.setObjectField(toConfuse,"dataExtractionRulesRes",0);
        XposedHelpers.setObjectField(toConfuse,"crossProfile",false);
        XposedHelpers.setObjectField(toConfuse,"uiOptions",0);
        XposedHelpers.setObjectField(toConfuse,"flags",0);
        XposedHelpers.setObjectField(toConfuse,"requiresSmallestWidthDp",0);
        XposedHelpers.setObjectField(toConfuse,"compatibleWidthLimitDp",0);
        XposedHelpers.setObjectField(toConfuse,"largestWidthLimitDp", Integer.MAX_VALUE);
        XposedHelpers.setObjectField(toConfuse,"maxAspectRatio",Float.MAX_VALUE);
        XposedHelpers.setObjectField(toConfuse,"minAspectRatio",0.f);
        XposedHelpers.setObjectField(toConfuse,"volumeUuid", UUID.randomUUID().toString());
        XposedHelpers.setObjectField(toConfuse,"storageUuid",UUID.randomUUID().toString());
        XposedHelpers.setObjectField(toConfuse,"scanSourceDir",getRandomString(50));
        XposedHelpers.setObjectField(toConfuse,"scanPublicSourceDir",getRandomString(50));
        XposedHelpers.setObjectField(toConfuse,"splitNames",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"splitSourceDirs",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"splitPublicSourceDirs",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"splitDependencies",new SparseArray<int[]>(0));
        XposedHelpers.setObjectField(toConfuse,"resourceDirs",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"overlayPaths",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"seInfo",getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"seInfoUser",getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"sharedLibraryFiles",new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"sharedLibraryInfos", CantUseArrayList.INSTANCE);
        XposedHelpers.setObjectField(toConfuse,"dataDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"deviceProtectedDataDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"credentialProtectedDataDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"nativeLibraryDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"secondaryNativeLibraryDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"nativeLibraryRootDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"secondaryNativeLibraryDir", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"nativeLibraryRootRequiresIsa", false);
        XposedHelpers.setObjectField(toConfuse,"primaryCpuAbi", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"secondaryCpuAbi", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"uid", 0);
        XposedHelpers.setObjectField(toConfuse,"minSdkVersion", 0);
        XposedHelpers.setObjectField(toConfuse,"targetSdkVersion", 28);
        XposedHelpers.setObjectField(toConfuse,"longVersionCode", 114514L);
        XposedHelpers.setObjectField(toConfuse,"versionCode", 114514);
        XposedHelpers.setObjectField(toConfuse,"compileSdkVersion", 0);
        XposedHelpers.setObjectField(toConfuse,"compileSdkVersionCodename", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"enabled", true);
        XposedHelpers.setObjectField(toConfuse,"enabledSetting", 0);
        XposedHelpers.setObjectField(toConfuse,"installLocation", 0);
        XposedHelpers.setObjectField(toConfuse,"networkSecurityConfigRes", 0);
        XposedHelpers.setObjectField(toConfuse,"targetSandboxVersion", 0);
        XposedHelpers.setObjectField(toConfuse,"appComponentFactory", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"iconRes", 0);
        XposedHelpers.setObjectField(toConfuse,"roundIconRes", 0);
        XposedHelpers.setObjectField(toConfuse,"category", 0);
        XposedHelpers.setObjectField(toConfuse,"classLoaderName", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"splitClassLoaderNames", new String[]{getRandomString(10),getRandomString(10)});
        XposedHelpers.setObjectField(toConfuse,"hiddenUntilInstalled", false);
        XposedHelpers.setObjectField(toConfuse,"zygotePreloadName", getRandomString(10));
        XposedHelpers.setObjectField(toConfuse,"gwpAsanMode", 0);
        XposedHelpers.setObjectField(toConfuse,"mHiddenApiPolicy", 0);


        return toConfuse;
    }
}
