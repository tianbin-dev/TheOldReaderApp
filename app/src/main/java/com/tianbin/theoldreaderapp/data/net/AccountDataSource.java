package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.net.client.AccountRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.AccountService;

import rx.Observable;

/**
 * Created by tianbin on 16/10/29.
 */
public class AccountDataSource implements AccountApi {

    private AccountRetrofit mAccountRetrofit;

    public AccountDataSource() {
        mAccountRetrofit = new AccountRetrofit();
    }

    @Override
    public Observable<TokenInfo> login(String email, String passwd) {
        AccountService accountService = mAccountRetrofit.get().create(AccountService.class);
        return accountService.login(email, passwd);
    }
}
