package com.tianbin.theoldreaderapp.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;

/**
 * Created by tianbin on 16/7/27.
 */
public class AccountPref {

    private static final String KEY_LOGIN_TOKEN = "login_token";
    private static final String KEY_LOGON_USER = "logon_user";

    private static SharedPreferences getPreference(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("com.tianbin.theoldreader.account_preference.xml", Context.MODE_PRIVATE);
    }

    public static void saveLoginToken(Context context, String loginToken) {
        getPreference(context).edit().putString(KEY_LOGIN_TOKEN, loginToken).apply();
    }

    public static String getLogonToken(Context context) {
        return getPreference(context).getString(KEY_LOGIN_TOKEN, "");
    }

    public static void removeLogonToken(Context context) {
        saveLoginToken(context, "");
    }

    public static void saveLogonUser(Context context, TokenInfo tokenInfo) {
        String userJson = new Gson().toJson(tokenInfo);
        getPreference(context).edit().putString(KEY_LOGON_USER, userJson).apply();
    }

    public static void removeLogonUser(Context context) {
        getPreference(context).edit().remove(KEY_LOGON_USER).apply();
    }

    public static TokenInfo getLogonUser(Context context) {
        TokenInfo user = null;
        String userJson = getPreference(context).getString(KEY_LOGON_USER, "");
        if (!TextUtils.isEmpty(userJson)) {
            user = new Gson().fromJson(userJson, TokenInfo.class);
        }
        return user;
    }

    public static boolean isLogon(Context context) {
        return !TextUtils.isEmpty(getLogonToken(context)) && getLogonUser(context) != null;
    }
}
