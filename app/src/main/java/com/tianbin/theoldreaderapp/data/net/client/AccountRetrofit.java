package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import okhttp3.OkHttpClient;

/**
 * account retrofit
 * Created by tianbin on 16/10/29.
 */
public class AccountRetrofit extends BaseRetrofit {

    @Override
    public OkHttpClient getHttpClient() {
        return new CacheHttpClient().get();
    }
}
