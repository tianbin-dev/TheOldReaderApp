package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.SubscriptionApi;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;

import javax.inject.Inject;

import rx.Observable;

/**
 * Subscription data source
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionDataSource implements SubscriptionApi {

    SubscriptionService mSubscriptionService;

    @Inject
    public SubscriptionDataSource(SubscriptionService subscriptionService) {
        mSubscriptionService = subscriptionService;
    }

    @Override
    public Observable<SubscriptionList> getSubscriptionList() {
        return mSubscriptionService.getSubscriptionList();
    }
}
