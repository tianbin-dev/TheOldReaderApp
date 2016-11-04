package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseOkHttpClient;
import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import okhttp3.OkHttpClient;

/**
 * subscription retrofit
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionRetrofit extends BaseRetrofit {

    @Override
    public OkHttpClient getHttpClient() {
        return new BaseOkHttpClient().get();
    }


}
