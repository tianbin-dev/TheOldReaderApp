package com.tianbin.theoldreaderapp.di.component;

import android.app.Application;

import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.data.net.service.SubscriptionService;
import com.tianbin.theoldreaderapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * Created by tianbin on 16/11/11.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication application);

    Application application();

    SubscriptionService subscriptionService();
}
