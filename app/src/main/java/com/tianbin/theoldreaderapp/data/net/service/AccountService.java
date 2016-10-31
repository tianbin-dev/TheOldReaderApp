package com.tianbin.theoldreaderapp.data.net.service;

import com.tianbin.theoldreaderapp.data.module.TokenInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tianbin on 16/10/29.
 */
public interface AccountService {

    @FormUrlEncoded
    @POST("/accounts/ClientLogin")
    Observable<TokenInfo> login(@Field("Email") String email, @Field("Passwd") String passwd);
}
