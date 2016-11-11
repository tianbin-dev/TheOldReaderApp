package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * subscription retrofit
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionRetrofit extends BaseRetrofit {

    CommonHttpClient mOkHttpClient;

    @Inject
    public SubscriptionRetrofit(CommonHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return mOkHttpClient.get();
    }


}
