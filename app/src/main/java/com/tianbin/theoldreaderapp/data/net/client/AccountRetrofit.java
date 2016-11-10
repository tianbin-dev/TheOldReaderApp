package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.AccountService;

import okhttp3.OkHttpClient;

/**
 * account retrofit
 * Created by tianbin on 16/10/29.
 */
public class AccountRetrofit extends BaseRetrofit {

    private static AccountRetrofit mRetrofit;

    private AccountRetrofit() {
    }

    public static AccountService getService() {
        if (mRetrofit == null) {
            mRetrofit = new AccountRetrofit();
        }
        return mRetrofit.get().create(AccountService.class);
    }

    @Override
    public OkHttpClient getHttpClient() {
        return CommonHttpClient.getInstance().get();
    }
}
