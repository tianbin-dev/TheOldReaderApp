package com.tianbin.theoldreaderapp.presenter.blog;

import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;

import javax.inject.Inject;

import rx.Observable;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */

public class SubscriptionBlogListPresenter extends BlogListBasePresenter {

    private String mSubscriptionId;

    @Inject
    public SubscriptionBlogListPresenter() {
    }

    public void setSubscriptionId(String subscriptionId) {
        mSubscriptionId = subscriptionId;
    }

    @Override
    protected Observable<BlogIdItemList> getBlogItemIds() {
        return mBlogApi.getSubscriptionBlogIds(mSubscriptionId);
    }
}
