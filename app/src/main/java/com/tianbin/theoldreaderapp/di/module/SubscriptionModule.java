package com.tianbin.theoldreaderapp.di.module;

import com.tianbin.theoldreaderapp.data.api.SubscriptionApi;
import com.tianbin.theoldreaderapp.data.net.SubscriptionDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * class des
 * Created by tianbin on 16/11/11.
 */
@Module
public class SubscriptionModule {

    @Provides
    SubscriptionApi provideSubscriptionApi(SubscriptionDataSource subscriptionDataSource) {
        return subscriptionDataSource;
    }
}
