package com.tianbin.theoldreaderapp.presenter.account;

import android.util.Log;

import com.tianbin.theoldreaderapp.contract.account.LoginContract;
import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.pref.AccountPref;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * login presenter
 * Created by tianbin on 16/10/29.
 */
public class LoginPresenter implements LoginContract.Preseneter {

    private LoginContract.View mView;

    AccountApi mAccountApi;

    @Inject
    public LoginPresenter(AccountApi accountApi) {
        mAccountApi = accountApi;
    }

    @Override
    public void loginAndSaveToken(String email, String passwd) {
        mAccountApi.login(email, passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenInfo>() {
                    @Override
                    public void call(TokenInfo tokenInfo) {
                        Log.e("tag", tokenInfo.getAuth());
                        mView.dissmissProgress();
                        AccountPref.saveLoginToken(mView.getContext(), tokenInfo.getAuth());
                        mView.loginSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.dissmissProgress();
                        mView.showLoginFailureInfo();
                    }
                });
    }

    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
