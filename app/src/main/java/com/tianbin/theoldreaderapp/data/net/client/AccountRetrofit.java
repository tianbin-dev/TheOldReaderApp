package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * account retrofit
 * Created by tianbin on 16/10/29.
 */
public class AccountRetrofit extends BaseRetrofit {

    CommonHttpClient mCommonHttpClient;

    @Inject
    AccountRetrofit(CommonHttpClient commonHttpClient) {
        mCommonHttpClient = commonHttpClient;
    }

    @Override
    public OkHttpClient getHttpClient() {
        return mCommonHttpClient.get();
    }
}
