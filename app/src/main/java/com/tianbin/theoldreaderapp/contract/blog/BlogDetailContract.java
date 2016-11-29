package com.tianbin.theoldreaderapp.contract.blog;

import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

/**
 * BlogDetailContract
 * Created by tianbin on 16/11/18.
 */
public interface BlogDetailContract {

    interface View extends MvpView {
        void starSuccess();

        void startFailed();

        void unstarSuccess();

        void unstartFailed();
    }

    interface Preseneter extends MvpPresenter<View> {

        void markAsStared(String id);

        void markAsUnstared(String id);
    }
}
