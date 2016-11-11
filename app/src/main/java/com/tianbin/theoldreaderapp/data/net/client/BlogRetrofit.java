package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * content retrofit
 * Created by tianbin on 16/10/29.
 */
public class BlogRetrofit extends BaseRetrofit {

    CommonHttpClient mCommonHttpClient;

    @Inject
    public BlogRetrofit(CommonHttpClient commonHttpClient) {
        mCommonHttpClient = commonHttpClient;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return mCommonHttpClient.get();
    }
}
