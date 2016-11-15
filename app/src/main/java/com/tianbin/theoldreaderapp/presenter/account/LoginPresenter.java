package com.tianbin.theoldreaderapp.presenter.account;

import com.tianbin.theoldreaderapp.contract.account.LoginContract;
import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.pref.AccountPref;
import com.tianbin.theoldreaderapp.presenter.base.RxPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * login presenter
 * Created by tianbin on 16/10/29.
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Preseneter {

    AccountApi mAccountApi;

    @Inject
    public LoginPresenter(AccountApi accountApi) {
        mAccountApi = accountApi;
    }

    @Override
    public void loginAndSaveToken(String email, String passwd) {
        Subscription loginAndSaveTokenSubscription = mAccountApi.login(email, passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenInfo>() {
                    @Override
                    public void call(TokenInfo tokenInfo) {
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
        addSubscrebe(loginAndSaveTokenSubscription);
    }
}
