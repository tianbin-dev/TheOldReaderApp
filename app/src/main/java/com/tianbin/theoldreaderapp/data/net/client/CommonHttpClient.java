package com.tianbin.theoldreaderapp.data.net.client;

import android.app.Application;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseOkHttpClient;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.AppendParamInterceptor;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.TokenInterceptor;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * cache http client
 * Created by tianbin on 16/11/8.
 */
public class CommonHttpClient extends BaseOkHttpClient {

    @Inject
    Application mContext;

    @Inject
    public CommonHttpClient() {
    }

    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TokenInterceptor(mContext));
        builder.addInterceptor(new AppendParamInterceptor());
        return builder;
    }

}
