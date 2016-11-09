package com.tianbin.theoldreaderapp.data.net.client.interceptor;

import android.text.TextUtils;

import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.data.pref.AccountPref;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * add token interceptor
 * Created by tianbin on 16/11/8.
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        String token = AccountPref.getLogonToken(MyApplication.getInstance().getApplicationContext());
        if (!TextUtils.isEmpty(token)) {
            requestBuilder.header("Authorization", "GoogleLogin auth=" + token);
        }
        return chain.proceed(requestBuilder.build());
    }
}
