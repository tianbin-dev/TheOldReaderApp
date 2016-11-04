package com.tianbin.theoldreaderapp.contract.subscription;

import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

import java.util.List;

/**
 * news contract
 * Created by tianbin on 16/11/3.
 */
public interface NewsContract {

    interface View extends MvpView {

        void fetchNewsSuccess(List<BlogList.ItemsEntity> blogList);

        void fetchNewsFailed(Throwable throwable);

        void loadMoreNewsSuccess(List<BlogList.ItemsEntity> blogList);

        void loadMoreNewsCompleted();

        void pullDownRefreshSuccess(List<BlogList.ItemsEntity> newDataList);

        List<BlogList.ItemsEntity> getData();
    }

    interface Presenter extends MvpPresenter<View> {

        void fetchNews(FetchType type);
    }

    enum FetchType {
        INIT,
        LOAD_MORE,
        REFRESH
    }
}
