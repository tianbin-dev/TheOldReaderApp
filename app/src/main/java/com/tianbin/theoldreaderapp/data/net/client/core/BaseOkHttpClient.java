package com.tianbin.theoldreaderapp.data.net.client.core;

import com.tianbin.theoldreaderapp.BuildConfig;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.AppendParamInterceptor;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * base OkHttp client
 * Created by tianbin on 16/10/20.
 */
public class BaseOkHttpClient {

    private static final long TIMEOUT_CONNECT = 30 * 1000;

    public OkHttpClient get() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(level);
        builder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor);
        builder = customize(builder);
        return builder.build();
    }

    protected OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(new AppendParamInterceptor());
        return builder;
    }

}
