package com.tianbin.theoldreaderapp.presenter.blog;

import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;

import javax.inject.Inject;

import rx.Observable;

/**
 * news presenter
 * Created by tianbin on 16/11/3.
 */
public class LastBlogListPresenter extends BlogListBasePresenter {

    @Inject
    public LastBlogListPresenter() {
    }

    @Override
    protected Observable<BlogIdItemList> getBlogItemIds() {
        return mBlogApi.getUnReadItemIds(mContinuation);
    }
}
