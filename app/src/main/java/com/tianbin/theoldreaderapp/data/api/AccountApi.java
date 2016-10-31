package com.tianbin.theoldreaderapp.data.api;

import com.tianbin.theoldreaderapp.data.module.TokenInfo;

import rx.Observable;

/**
 * Created by tianbin on 16/10/29.
 */
public interface AccountApi {

    Observable<TokenInfo> login(String email, String passwd);
}
