package com.tianbin.theoldreaderapp.presenter.blog;

import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;

import javax.inject.Inject;

import rx.Observable;

/**
 * FavBlogListPresenter
 * Created by tianbin on 16/11/18.
 */
public class FavBlogListPresenter extends BlogListBasePresenter{

    @Inject
    public FavBlogListPresenter() {
    }

    @Override
    protected Observable<BlogIdItemList> getBlogItemIds() {
        return mBlogApi.getLikedBlogIds(mContinuation);
    }
}
