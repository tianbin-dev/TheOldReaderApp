package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;

import okhttp3.OkHttpClient;

/**
 * subscription retrofit
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionRetrofit extends BaseRetrofit {

    private static SubscriptionRetrofit mRetrofit;

    private SubscriptionRetrofit() {
    }

    public static SubscriptionService getService() {
        if (mRetrofit == null) {
            mRetrofit = new SubscriptionRetrofit();
        }
        return mRetrofit.get().create(SubscriptionService.class);
    }

    @Override
    public OkHttpClient getHttpClient() {
        return CommonHttpClient.getInstance().get();
    }


}
