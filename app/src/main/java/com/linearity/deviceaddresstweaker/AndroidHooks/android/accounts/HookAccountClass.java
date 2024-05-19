package com.linearity.deviceaddresstweaker.AndroidHooks.android.accounts;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.*;
import static com.linearity.utils.LoggerUtils.LoggerLog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.UserHandle;

import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;
import com.linearity.utils.HookUtils;
import com.linearity.utils.ReturnReplacements;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookAccountClass {
    public static boolean HookAccounts = true;
    public static boolean HookAccount = true;
    public static boolean HookAccountManager = true;
    public static boolean HookAccountAuthenticatorResponse = true;
    public static boolean HookAuthenticatorDescription = true;
    public static boolean HookAbstractAccountAuthenticator = true;
    public static Future2Task<Bundle> EmptyFuture2TaskOfBundle = new Future2Task<Bundle>() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return true;
        }

        @Override
        public boolean isCancelled() {
            return true;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Bundle getResult() throws AuthenticatorException, IOException, OperationCanceledException {
            return ReturnReplacements.EmptyBundle;
        }

        @Override
        public Bundle getResult(long timeout, TimeUnit unit) throws AuthenticatorException, IOException, OperationCanceledException {
            return ReturnReplacements.EmptyBundle;
        }
    };
    public static XC_MethodReplacement returnEmptyFuture2TaskOfBundle = new XC_MethodReplacement(114514) {
        @Override
        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
            return EmptyFuture2TaskOfBundle;
        }
    };
    public static AuthenticatorDescription[] EmptyAuthenticatorDescriptionArray = new AuthenticatorDescription[0];
    public static XC_MethodReplacement returnEmptyAuthenticatorDescriptionArray = new XC_MethodReplacement(114514) {
        @Override
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam
        param) throws Throwable {
            return EmptyAuthenticatorDescriptionArray;
        }
    };
    public static Account[] EmptyAccountArray = new Account[0];
    public static XC_MethodReplacement returnEmptyAccountArray = new XC_MethodReplacement(114514) {
        @Override
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam
                                                     param) throws Throwable {
            return EmptyAccountArray;
        }
    };

    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam, String procHead, SharedPreferences sharedPreferences){
        Class<?> hookClass;
        HookAccounts = sharedPreferences.getBoolean("HookAccountClass_HookAccounts",true);
        HookAccount = sharedPreferences.getBoolean("HookAccountClass_HookAccount", true);
        HookAccountManager = sharedPreferences.getBoolean("HookAccountClass_HookAccountManager", true);
        HookAccountAuthenticatorResponse = sharedPreferences.getBoolean("HookAccountClass_HookAccountAuthenticatorResponse", true);
        HookAuthenticatorDescription = sharedPreferences.getBoolean("HookAccountClass_HookAuthenticatorDescription", true);
        HookAbstractAccountAuthenticator = sharedPreferences.getBoolean("HookAccountClass_HookAbstractAccountAuthenticator", true);
        if (HookAccounts){
//            if (HookAbstractAccountAuthenticator){
//                try {
//                    {
//                        hookClass = XposedHelpers.findClassIfExists(android.accounts.AbstractAccountAuthenticator.class.getName(), lpparam.classLoader);
//                        if (hookClass != null){
//                            for (Method m:hookClass.getDeclaredMethods()){
//                                if (m.getReturnType().equals(Bundle.class)){
//                                    XposedBridge.hookMethod(m,returnEmptyBundle);
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    LoggerLog(e);
//                }
//            }
            if (HookAccount){
                //      android.accounts.Account.class CREATOR
                hookClass = XposedHelpers.findClassIfExists(android.accounts.Account.class.getName(),lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {
//            Class<?> clazz = XposedHelpers.setStaticObjectField("android.accounts.Account$Creator",lpparam.classLoader);
                            XposedHelpers.setStaticObjectField(
                                    hookClass,
                                    "CREATOR",
                                    CREATOR
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "equals",
                                    Object.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return true;//lmao
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "hashCode",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return 1;//lmao
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(
                                    hookClass,
                                    "writeToParcel",
                                    Parcel.class, int.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return null;//lmao
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(
                                    hookClass,
                                    "toString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(20);//lmao
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(
                                    hookClass,
                                    "toSafeString",
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(20);//lmao
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(
                                    hookClass,
                                    "toSafeName",
                                    String.class, char.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return ReturnReplacements.getRandomString(20);//lmao
                                        }
                                    }
                            );
                        }
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookAccountManager){
                hookClass = XposedHelpers.findClass(android.accounts.AccountManager.class.getName(), lpparam.classLoader);

                if (hookClass != null){
                    {
                        for (Method m : hookClass.getDeclaredMethods()) {

                            Class<?> t = m.getReturnType();
                            if (t.equals(AccountManager.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnNull);
                            } else if (t.equals(String.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnRandomStr20);
                            } else if (t.equals(boolean.class) || t.equals(Boolean.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnTrue);
                            } else if (t.equals(Integer.class) || t.equals(int.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnIntegerMIN);
                            } else if (t.equals(Long.class) || t.equals(long.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnLongMIN);
                            } else if (t.isInstance(EmptyAuthenticatorDescriptionArray)) {
                                XposedBridge.hookMethod(m, returnEmptyAuthenticatorDescriptionArray);
                            } else if (t.isInstance(EmptyAccountArray)) {
                                XposedBridge.hookMethod(m, returnEmptyAccountArray);
                            } else if (t.equals(Void.TYPE) || t.equals(void.class)) {
                                XposedBridge.hookMethod(m, ReturnReplacements.returnNull);
                            }
                        }
                    }
                    {

                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "hasFeatures",
                                    Account.class, String[].class,
                                    AccountManagerCallback.class, Handler.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOBoolean;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAccountsByTypeAndFeatures",
                                    String.class, String[].class,
                                    AccountManagerCallback.class, Handler.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOfAccountArray;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getPackagesAndVisibilityForAccount",
                                    Account.class, ReturnReplacements.returnEmptyMap_String_Integer);
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAccountsAndVisibilityForPackage",
                                    String.class, String.class, ReturnReplacements.returnEmptyMap_Account_Integer
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "renameAccount",
                                    Account.class, String.class, AccountManagerCallback.class, Handler.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            Account account = (Account) param.args[0];
                                            return new DeviceAddressTweaker.Future2Task<Account>(account) {
                                                @Override
                                                public boolean cancel(boolean mayInterruptIfRunning) {
                                                    return true;
                                                }

                                                @Override
                                                public boolean isCancelled() {
                                                    return true;
                                                }

                                                @Override
                                                public boolean isDone() {
                                                    return true;
                                                }

                                                @Override
                                                public Account getResult() throws AuthenticatorException, IOException, OperationCanceledException {
                                                    return account;
                                                }

                                                @Override
                                                public Account getResult(long timeout, TimeUnit unit) throws AuthenticatorException, IOException, OperationCanceledException {
                                                    return account;
                                                }
                                            };
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "removeAccount",
                                    Account.class, AccountManagerCallback.class, Handler.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOBoolean;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "removeAccount",
                                    Account.class, Activity.class, AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "removeAccountAsUser",
                                    Account.class, AccountManagerCallback.class, Handler.class, UserHandle.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOBoolean;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "removeAccountAsUser",
                                    Account.class, Activity.class, AccountManagerCallback.class, Handler.class, UserHandle.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOfBundle;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "clearPassword",
                                    Account.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "setUserData",
                                    Account.class, String.class, String.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAuthToken",
                                    Account.class, String.class, Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAuthToken",
                                    Account.class, String.class, boolean.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAuthToken",
                                    Account.class, String.class, Bundle.class, boolean.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "addAccount",
                                    String.class, String.class, String[].class, Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "addAccountAsUser",
                                    String.class, String.class, String[].class,
                                    Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, UserHandle.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "confirmCredentials",
                                    Account.class, Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "updateCredentials", Account.class,
                                    String.class, Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "editProperties",
                                    String.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "ensureNotOnMainThread", ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "postToHandler",
                                    Handler.class, AccountManagerCallback.class, AccountManagerFuture.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "postToHandler",
                                    Handler.class, OnAccountsUpdateListener.class, Account[].class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAccountByTypeAndFeatures",
                                    String.class, String[].class, AccountManagerCallback.class, Handler.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "getAuthTokenByFeatures",
                                    String.class, String.class, String[].class,
                                    Activity.class, Bundle.class, Bundle.class,
                                    AccountManagerCallback.class, Handler.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyFuture2TaskOfBundle;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "newChooseAccountIntent",
                                    Account.class, ArrayList.class, String[].class, boolean.class,
                                    String.class, String.class, String[].class, Bundle.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyIntent;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "newChooseAccountIntent",
                                    Account.class, List.class, String[].class,
                                    String.class, String.class, String[].class, Bundle.class,
                                    new XC_MethodReplacement(114514) {
                                        @Override
                                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                            return EmptyIntent;
                                        }
                                    }
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "addOnAccountsUpdatedListener",
                                    OnAccountsUpdateListener.class, Handler.class, boolean.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "addOnAccountsUpdatedListener",
                                    OnAccountsUpdateListener.class, Handler.class, boolean.class, String[].class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "removeOnAccountsUpdatedListener",
                                    OnAccountsUpdateListener.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "startAddAccountSession",
                                    String.class, String.class, String[].class,
                                    Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "startUpdateCredentialsSession",
                                    Account.class, String.class,
                                    Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "finishSession",
                                    Bundle.class, Activity.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "finishSessionAsUser",
                                    Bundle.class, Activity.class, UserHandle.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "isCredentialsUpdateSuggested",
                                    Account.class, String.class,
                                    AccountManagerCallback.class, Handler.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "hasAccountAccess",
                                    Account.class, String.class,
                                    UserHandle.class, returnEmptyFuture2TaskOfBundle
                            );
                        }
                    }
                }
            }
            if (HookAccountAuthenticatorResponse){
                hookClass = XposedHelpers.findClassIfExists(android.accounts.AccountAuthenticatorResponse.class.getName(), lpparam.classLoader);
                if (hookClass != null){
                    try {
                        {

                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "onResult",
                                    Bundle.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "onRequestContinued", ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "onError",
                                    int.class,String.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            HookUtils.findAndHookMethodIfExists(hookClass,
                                    "writeToParcel",
                                    Parcel.class,int.class, ReturnReplacements.returnNull
                            );
                        }
                        {
                            XposedHelpers.setStaticObjectField(
                                    android.accounts.AccountAuthenticatorResponse.class,
                                    "CREATOR",
                                    CREATOR_AccountAuthenticatorResponse);
                        }
                    }catch (Exception e){
                        LoggerLog(e);
                    }
                }
            }
            if (HookAuthenticatorDescription){
                hookClass = XposedHelpers.findClassIfExists(android.accounts.AuthenticatorDescription.class.getName(), lpparam.classLoader);
                try {
                    {
                        XposedHelpers.findAndHookConstructor(hookClass,
                                String.class, String.class, int.class, int.class, int.class, int.class, boolean.class,
                                new XC_MethodHook(114514) {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
                                        param.args[0] = ReturnReplacements.getRandomString(20);
                                        param.args[1] = lpparam.packageName;
                                        param.args[2] = 1;
                                        param.args[3] = 1;
                                        param.args[4] = 1;
                                        param.args[5] = 1;
                                        param.args[6] = false;
                                    }
                                }
                        );
                    }
                    {
                        XposedHelpers.findAndHookConstructor(hookClass,
                                String.class, String.class, int.class, int.class, int.class, int.class,
                                new XC_MethodHook(114514) {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
                                        param.args[0] = ReturnReplacements.getRandomString(20);
                                        param.args[1] = lpparam.packageName;
                                        param.args[2] = 1;
                                        param.args[3] = 1;
                                        param.args[4] = 1;
                                        param.args[5] = 1;
                                    }
                                }
                        );
                    }
                    {
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "newKey",
                                String.class,
                                new XC_MethodHook(114514) {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        param.args[0] = ReturnReplacements.getRandomString(20);
                                    }
                                }
                        );
                    }
                    {
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "hashCode", ReturnReplacements.returnIntegerOne
                        );
                    }
                    {
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "equals",
                                Object.class, ReturnReplacements.returnTrue
                        );
                    }
                    {
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "toString",
                                new XC_MethodReplacement(114514) {

                                    @Override
                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                        return "AuthenticatorDescription {type=114514}";
                                    }
                                }
                        );
                    }
                    {
                        HookUtils.findAndHookMethodIfExists(hookClass,
                                "writeToParcel",
                                Parcel.class, int.class, ReturnReplacements.returnNull
                        );
                    }
                    {
                        XposedHelpers.setStaticObjectField(
                                android.accounts.AuthenticatorDescription.class,
                                "CREATOR",
                                CREATOR_AuthenticatorDescription
                        );
                    }
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
