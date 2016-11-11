package com.tianbin.theoldreaderapp.di.module;

import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.net.AccountDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * class des
 * Created by tianbin on 16/11/11.
 */
@Module
public class AccountModule {

    @Provides
    AccountApi provideAccountApi(AccountDataSource accountDataSource) {
        return accountDataSource;
    }
}
