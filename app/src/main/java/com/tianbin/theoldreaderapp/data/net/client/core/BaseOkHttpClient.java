package com.tianbin.theoldreaderapp.data.net.client.core;

import com.tianbin.theoldreaderapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * base OkHttp client
 * Created by tianbin on 16/10/20.
 */
public abstract class BaseOkHttpClient {

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

    protected abstract OkHttpClient.Builder customize(OkHttpClient.Builder builder);

}
