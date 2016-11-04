package com.tianbin.theoldreaderapp.contract.subscription;

import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

import java.util.List;

/**
 * Created by tianbin on 16/11/3.
 */

public interface NewsContract {

    interface View extends MvpView {

        void fetchNewsSuccess(List<BlogList.ItemsEntity> blogList);

        void fetchNewsFailed();
    }

    interface Presenter extends MvpPresenter<View> {

        void fetchNews(long continuation);
    }
}
