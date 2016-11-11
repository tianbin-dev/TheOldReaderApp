package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * subscription retrofit
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionRetrofit extends BaseRetrofit {

    @Inject
    public SubscriptionRetrofit() {

    }

    @Override
    public OkHttpClient getHttpClient() {
        return CommonHttpClient.getInstance().get();
    }


}
