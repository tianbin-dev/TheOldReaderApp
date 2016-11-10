package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseOkHttpClient;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.AppendParamInterceptor;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;

/**
 * cache http client
 * Created by tianbin on 16/11/8.
 */
public class CommonHttpClient extends BaseOkHttpClient {

    private static CommonHttpClient mInstance;

    private CommonHttpClient() {
    }

    public static CommonHttpClient getInstance() {
        if (mInstance == null) {
            mInstance = new CommonHttpClient();
        }
        return mInstance;
    }


    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(new AppendParamInterceptor());
        return builder;
    }

}
