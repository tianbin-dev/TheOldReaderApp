package com.tianbin.theoldreaderapp.contract.blog;

import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

import java.util.List;

/**
 * news contract
 * Created by tianbin on 16/11/3.
 */
public interface BlogListContract {

    interface View extends MvpView {

        void fetchNewsSuccess(List<BlogList.ItemEntity> blogList);

        void fetchNewsFailed(Throwable throwable);

        void loadMoreNewsSuccess(List<BlogList.ItemEntity> blogList);

        void loadMoreNewsCompleted();

        void pullDownRefreshSuccess();

        List<BlogList.ItemEntity> getData();
    }

    interface Presenter extends MvpPresenter<View> {

        void fetchBlogs(FetchType type);
    }

    enum FetchType {
        INIT,
        LOAD_MORE,
        REFRESH
    }
}
