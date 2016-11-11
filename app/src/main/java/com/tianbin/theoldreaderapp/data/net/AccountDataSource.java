package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.net.service.AccountService;

import javax.inject.Inject;

import rx.Observable;

/**
 * account source
 * Created by tianbin on 16/10/29.
 */
public class AccountDataSource implements AccountApi {

    private AccountService mAccountService;

    @Inject
    public AccountDataSource(AccountService accountService) {
        mAccountService = accountService;
    }

    @Override
    public Observable<TokenInfo> login(String email, String passwd) {
        return mAccountService.login(email, passwd);
    }


}
