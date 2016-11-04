package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.SubscriptionApi;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.data.net.client.SubscriptionRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;

import rx.Observable;

/**
 * Subscription data source
 * Created by tianbin on 16/11/3.
 */
public class SubscriptionDataSource implements SubscriptionApi {

    private static SubscriptionDataSource mSubsciptionDataSource;

    private SubscriptionService mSubscriptionService;

    public static SubscriptionDataSource getInstance() {
        if (mSubsciptionDataSource == null) {
            mSubsciptionDataSource = new SubscriptionDataSource();
        }
        return mSubsciptionDataSource;
    }

    private SubscriptionDataSource() {
        mSubscriptionService = new SubscriptionRetrofit().get().create(SubscriptionService.class);
    }

    @Override
    public Observable<SubscriptionList> getSubscriptionList() {
        return mSubscriptionService.getSubscriptionList();
    }

    @Override
    public Observable<BlogList> getBlogList(long continuation) {
        return mSubscriptionService.getBlogList(continuation);
    }
}
