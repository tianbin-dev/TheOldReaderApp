package com.tianbin.theoldreaderapp.data.net.client.core;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * base retrofit
 * Created by tianbin on 16/11/2.
 */
public abstract class BaseRetrofit {

    protected static final String END_POINT = "https://theoldreader.com/";

    public Retrofit get() {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(END_POINT)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public abstract OkHttpClient getHttpClient();

}
