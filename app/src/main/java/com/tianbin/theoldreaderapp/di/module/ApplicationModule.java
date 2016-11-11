package com.tianbin.theoldreaderapp.di.module;

import android.app.Application;
import android.content.Context;

import com.tianbin.theoldreaderapp.data.net.client.AccountRetrofit;
import com.tianbin.theoldreaderapp.data.net.client.BlogRetrofit;
import com.tianbin.theoldreaderapp.data.net.client.SubscriptionRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.AccountService;
import com.tianbin.theoldreaderapp.data.net.service.BlogService;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;
import com.tianbin.theoldreaderapp.di.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * android module
 * Created by tianbin on 16/11/11.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ForApplication
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SubscriptionService provideSubscriptionService(SubscriptionRetrofit retrofit) {
        return retrofit.get().create(SubscriptionService.class);
    }

    @Provides
    @Singleton
    AccountService provideAccountService(AccountRetrofit retrofit) {
        return retrofit.get().create(AccountService.class);
    }

    @Provides
    @Singleton
    BlogService provideBlogService(BlogRetrofit retrofit) {
        return retrofit.get().create(BlogService.class);
    }
}
