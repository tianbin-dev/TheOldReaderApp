package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.net.client.AccountRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.AccountService;

import rx.Observable;

/**
 * account source
 * Created by tianbin on 16/10/29.
 */
public class AccountDataSource implements AccountApi {

    private AccountService mAccountService;

    public AccountDataSource() {
        mAccountService = AccountRetrofit.getService();
    }

    @Override
    public Observable<TokenInfo> login(String email, String passwd) {
        return mAccountService.login(email, passwd);
    }


}
