package com.tianbin.theoldreaderapp.contract.subscription;

import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

/**
 * Created by tianbin on 16/11/3.
 */

public interface NewsContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View> {
        void fetchNews();
    }
}
