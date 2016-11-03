package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.SubscriptionApi;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.data.net.client.SubscriptionRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;

import rx.Observable;

/**
 * Subscription data source
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionDataSource implements SubscriptionApi {

    private SubscriptionRetrofit mSubscriptionRetrofit;

    public SubscriptionDataSource() {
        mSubscriptionRetrofit = new SubscriptionRetrofit();
    }

    @Override
    public Observable<SubscriptionList> getSubscriptionList() {
        return mSubscriptionRetrofit.get().create(SubscriptionService.class).getSubscriptionList();
    }
}
