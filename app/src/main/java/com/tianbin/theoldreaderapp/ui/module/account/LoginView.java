package com.tianbin.theoldreaderapp.ui.module.account;

import android.content.Context;

import com.anly.mvp.MvpView;

/**
 * Created by tianbin on 16/10/29.
 */

public interface LoginView extends MvpView {

    void dissmissProgress();

    void showLoginFailureInfo();

    void loginSuccess();

    Context getContext();
}
