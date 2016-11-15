package com.tianbin.theoldreaderapp.contract.account;


import android.content.Context;

import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

/**
 * login contract
 * Created by tianbin on 16/11/3.
 */
public interface LoginContract {

    interface View extends MvpView {
        Context getContext();

        void dissmissProgress();

        void loginSuccess();

        void showLoginFailureInfo();
    }

    interface Preseneter extends MvpPresenter<View> {
        void loginAndSaveToken(String email, String passwd);
    }
}
