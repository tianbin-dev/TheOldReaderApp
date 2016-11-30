package com.tianbin.theoldreaderapp.contract.blog;

import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

/**
 * BlogDetailContract
 * Created by tianbin on 16/11/18.
 */
public interface BlogDetailContract {

    interface View extends MvpView {

        void actionSuccess(ActionType actionType);

        void actionFailed(ActionType actionType);
    }

    interface Preseneter extends MvpPresenter<View> {

        void markAsRead(String id);

        void markAsUnRead(String id);

        void markAsStared(String id);

        void markAsUnstared(String id);
    }

    enum ActionType {
        READ,
        UNREAD,
        STARED,
        UNSTARED
    }
}
