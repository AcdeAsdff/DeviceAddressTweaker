package com.linearity.deviceaddresstweaker.AndroidHooks.android.accounts;

import static android.accounts.AccountManager.VISIBILITY_VISIBLE;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.*;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.UserHandle;

import com.linearity.deviceaddresstweaker.DeviceAddressTweaker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
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
    public static void DoHook(XC_LoadPackage.LoadPackageParam lpparam){
        if (HookAccounts){
            if (HookAbstractAccountAuthenticator){
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "getAccountRemovalAllowed",
                            AccountAuthenticatorResponse.class, Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "getAccountCredentialsForCloning",
                            AccountAuthenticatorResponse.class, Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "addAccountFromCredentials",
                            AccountAuthenticatorResponse.class, Account.class,Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "startAddAccountSession",
                            AccountAuthenticatorResponse.class, String.class,String.class,String[].class,Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "startUpdateCredentialsSession",
                            AccountAuthenticatorResponse.class, Account.class,String.class,Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "finishSession",
                            AccountAuthenticatorResponse.class, String.class,Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try {
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AbstractAccountAuthenticator.class.getName(),
                            lpparam.classLoader,
                            "isCredentialsUpdateSuggested",
                            AccountAuthenticatorResponse.class, Account.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyBundle;
                                }
                            }
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookAccount){
                //      android.accounts.Account.class CREATOR
                try {
//            Class<?> clazz = XposedHelpers.setStaticObjectField("android.accounts.Account$Creator",lpparam.classLoader);
                    XposedHelpers.setStaticObjectField(
                            android.accounts.Account.class,
                            "CREATOR",
                            CREATOR
                    );
                } catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 1;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "writeToParcel",
                            Parcel.class,int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "toSafeString",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.Account.class.getName(),
                            lpparam.classLoader,
                            "toSafeName",
                            String.class,char.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookAccountManager){
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "get",
                            Context.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    Context context = (Context)param.args[0];
                                    AccountManager result = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getPassword",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getUserData",
                            Account.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthenticatorTypes",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAuthenticatorDescriptionArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthenticatorTypesAsUser",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAuthenticatorDescriptionArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccounts",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsAsUser",
                            int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsForPackage",
                            String.class,int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsByTypeForPackage",
                            String.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsByType",
                            String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsByTypeAsUser",
                            String.class, UserHandle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyAccountArray;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
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
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
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
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addAccountExplicitly",
                            Account.class,String.class, Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addAccountExplicitly",
                            Account.class,String.class, Bundle.class, Map.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getPackagesAndVisibilityForAccount",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyMap_String_Integer;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountsAndVisibilityForPackage",
                            String.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyMap_Account_Integer;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "setAccountVisibility",
                            Account.class,String.class,int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountVisibility",
                            Account.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return VISIBILITY_VISIBLE;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "notifyAccountAuthenticated",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "renameAccount",
                            Account.class,String.class,AccountManagerCallback.class,Handler.class,
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
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getPreviousName",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeAccount",
                            Account.class,AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOBoolean;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeAccount",
                            Account.class, Activity.class,AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeAccountAsUser",
                            Account.class,AccountManagerCallback.class,Handler.class,UserHandle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOBoolean;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeAccountAsUser",
                            Account.class,Activity.class,AccountManagerCallback.class,Handler.class,UserHandle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeAccountExplicitly",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "invalidateAuthToken",
                            String.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "peekAuthToken",
                            Account.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "setPassword",
                            Account.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "clearPassword",
                            Account.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "setUserData",
                            Account.class,String.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "setAuthToken",
                            Account.class,String.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "blockingGetAuthToken",
                            Account.class,String.class,boolean.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthToken",
                            Account.class,String.class,Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthToken",
                            Account.class,String.class,boolean.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthToken",
                            Account.class,String.class,Bundle.class,boolean.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addAccount",
                            String.class,String.class,String[].class,Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addAccountAsUser",
                            String.class,String.class,String[].class,
                            Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,UserHandle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "confirmCredentials",
                            Account.class,Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "updateCredentials",Account.class,
                            String.class,Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "editProperties",
                            String.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "ensureNotOnMainThread",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "postToHandler",
                            Handler.class,AccountManagerCallback.class, AccountManagerFuture.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "postToHandler",
                            Handler.class, OnAccountsUpdateListener.class,Account[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAccountByTypeAndFeatures",
                            String.class,String[].class,AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "getAuthTokenByFeatures",
                            String.class,String.class,String[].class,
                            Activity.class,Bundle.class,Bundle.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
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
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
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
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addOnAccountsUpdatedListener",
                            OnAccountsUpdateListener.class,Handler.class,boolean.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "addOnAccountsUpdatedListener",
                            OnAccountsUpdateListener.class,Handler.class,boolean.class,String[].class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "removeOnAccountsUpdatedListener",
                            OnAccountsUpdateListener.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "startAddAccountSession",
                            String.class,String.class,String[].class,
                            Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "startUpdateCredentialsSession",
                            Account.class,String.class,
                            Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "finishSession",
                            Bundle.class,Activity.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "finishSessionAsUser",
                            Bundle.class,Activity.class,UserHandle.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "isCredentialsUpdateSuggested",
                            Account.class,String.class,
                            AccountManagerCallback.class,Handler.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return EmptyFuture2TaskOfBundle;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountManager.class.getName(),
                            lpparam.classLoader,
                            "hasAccountAccess",
                            Account.class,String.class,
                            UserHandle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookAccountAuthenticatorResponse){
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountAuthenticatorResponse.class.getName(),
                            lpparam.classLoader,
                            "onResult",
                            Bundle.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountAuthenticatorResponse.class.getName(),
                            lpparam.classLoader,
                            "onRequestContinued",
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountAuthenticatorResponse.class.getName(),
                            lpparam.classLoader,
                            "onError",
                            int.class,String.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AccountAuthenticatorResponse.class.getName(),
                            lpparam.classLoader,
                            "writeToParcel",
                            Parcel.class,int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;//lmao
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.setStaticObjectField(
                            android.accounts.AccountAuthenticatorResponse.class,
                            "CREATOR",
                            CREATOR_AccountAuthenticatorResponse);
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
            if (HookAuthenticatorDescription){
                try{
                    XposedHelpers.findAndHookConstructor(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            String.class, String.class, int.class, int.class, int.class, int.class, boolean.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
                                    param.args[0] = getRandomString(20);
                                    param.args[1] = lpparam.packageName;
                                    param.args[2] = 1;
                                    param.args[3] = 1;
                                    param.args[4] = 1;
                                    param.args[5] = 1;
                                    param.args[6] = false;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookConstructor(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            String.class, String.class, int.class, int.class, int.class, int.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    super.beforeHookedMethod(param);
                                    param.args[0] = getRandomString(20);
                                    param.args[1] = lpparam.packageName;
                                    param.args[2] = 1;
                                    param.args[3] = 1;
                                    param.args[4] = 1;
                                    param.args[5] = 1;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            "newKey",
                            String.class,
                            new XC_MethodHook(114514) {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    param.args[0] = getRandomString(20);
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            "hashCode",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return 1;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            "equals",
                            Object.class,
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return true;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            "toString",
                            new XC_MethodReplacement(114514) {

                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return "AuthenticatorDescription {type=114514}";
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.findAndHookMethod(
                            android.accounts.AuthenticatorDescription.class.getName(),
                            lpparam.classLoader,
                            "writeToParcel",
                            Parcel.class, int.class,
                            new XC_MethodReplacement(114514) {
                                @Override
                                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                                    return null;
                                }
                            }
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
                try{
                    XposedHelpers.setStaticObjectField(
                            android.accounts.AuthenticatorDescription.class,
                            "CREATOR",
                            CREATOR_AuthenticatorDescription
                    );
                }catch (Exception e) {
                    LoggerLog(e);
                }
            }
        }
    }
}
