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

        void fetchBlogsSuccess(List<BlogList.Blog> blogList);

        void fetchBlogsFailed(Throwable throwable);

        void loadMoreBlogsSuccess(List<BlogList.Blog> blogList);

        void loadMoreBlogsCompleted();

        void pullDownRefreshSuccess();

        List<BlogList.Blog> getData();
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
