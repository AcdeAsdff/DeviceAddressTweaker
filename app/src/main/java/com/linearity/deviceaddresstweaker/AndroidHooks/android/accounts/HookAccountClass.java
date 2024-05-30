package com.linearity.deviceaddresstweaker.AndroidHooks.android.accounts;

import static com.linearity.utils.FakeInfo.FakeProcInfoGenerator.random;
import static com.linearity.utils.HookUtils.disableClass_random;
import static com.linearity.utils.LoggerUtils.LoggerLog;
import static com.linearity.utils.ReturnReplacements.returnTrue;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.linearity.utils.HookUtils;
import com.linearity.utils.ReturnReplacements;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.SharedPreferences;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookAccountClass {
    public static final  Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<>() {
        public Account createFromParcel(Parcel source) {
//            return new Account(source);
            return null;
        }

        public Account[] newArray(int size) {
//            return new Account[size];
            return null;
        }
    };
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
    public static Future2Task<Account[]> EmptyFuture2TaskOfAccountArray = new Future2Task<>() {
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
        public Account[] getResult() {
            return EmptyAccountArray;
        }

        @Override
        public Account[] getResult(long timeout, TimeUnit unit) {
            return EmptyAccountArray;
        }
    };
    public static XC_MethodReplacement returnEmptyAccountArray = new XC_MethodReplacement(114514) {
        @Override
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam
                                                     param) throws Throwable {
            return EmptyAccountArray;
        }
    };
    public static Parcelable.Creator<AccountAuthenticatorResponse> CREATOR_AccountAuthenticatorResponse = new Parcelable.Creator<>() {
        public AccountAuthenticatorResponse createFromParcel(Parcel source) {
            return new AccountAuthenticatorResponse(source);
        }

        public AccountAuthenticatorResponse[] newArray(int size) {
            return EmptyAccountAuthenticatorResponse;
        }
    };
    public static AccountAuthenticatorResponse[] EmptyAccountAuthenticatorResponse = new AccountAuthenticatorResponse[0];
    public static Future2Task<Boolean> EmptyFuture2TaskOBoolean = new Future2Task<>() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return random.nextBoolean();
        }

        @Override
        public boolean isCancelled() {
            return random.nextBoolean();
        }

        @Override
        public boolean isDone() {
            return random.nextBoolean();
        }

        @Override
        public Boolean getResult() {
            return random.nextBoolean();
        }

        @Override
        public Boolean getResult(long timeout, TimeUnit unit) {
            return random.nextBoolean();
        }
    };
    public static Parcelable.Creator<AuthenticatorDescription> CREATOR_AuthenticatorDescription = new Parcelable.Creator<>() {
        public AuthenticatorDescription createFromParcel(Parcel source) {
//            return new Account(source);
            return null;
        }

        public AuthenticatorDescription[] newArray(int size) {
//            return new Account[size];
            return null;
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
                        XposedHelpers.setStaticObjectField(hookClass, "CREATOR", CREATOR);
                        disableClass_random(hookClass);
                    } catch (Exception e) {
                        LoggerLog(e);
                    }
                }
            }
            if (HookAccountManager){
                hookClass = XposedHelpers.findClass(android.accounts.AccountManager.class.getName(), lpparam.classLoader);

                if (hookClass != null){
                    disableClass_random(hookClass);
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
                                Object.class, returnTrue
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

    //empty,implements AccountManagerFuture
 public static abstract class Future2Task<T>
     implements AccountManagerFuture<T> {
        Account account;
        public Future2Task() {
        }
        public Future2Task(Account account) {
            this.account = account;
        }
    }
}
