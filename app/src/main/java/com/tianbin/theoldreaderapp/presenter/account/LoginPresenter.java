package com.tianbin.theoldreaderapp.presenter.account;

import com.tianbin.theoldreaderapp.data.api.AccountApi;
import com.tianbin.theoldreaderapp.data.module.TokenInfo;
import com.tianbin.theoldreaderapp.data.net.AccountDataSource;
import com.tianbin.theoldreaderapp.data.pref.AccountPref;
import com.tianbin.theoldreaderapp.presenter.base.RxMvpPresenter;
import com.tianbin.theoldreaderapp.ui.module.account.LoginView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tianbin on 16/10/29.
 */
public class LoginPresenter extends RxMvpPresenter<LoginView> {

    private AccountApi mAccountApi;

    public LoginPresenter() {
        mAccountApi = new AccountDataSource();
    }

    public void loginAndSaveToken(String email, String passwd) {
        mAccountApi.login(email, passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TokenInfo>() {
                    @Override
                    public void call(TokenInfo tokenInfo) {
                        getMvpView().dissmissProgress();
                        AccountPref.saveLoginToken(getMvpView().getContext(), tokenInfo.getAuth());
                        getMvpView().loginSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getMvpView().dissmissProgress();
                        getMvpView().showLoginFailureInfo();
                    }
                });
    }
}
