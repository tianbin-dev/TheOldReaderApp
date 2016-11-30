package com.tianbin.theoldreaderapp.presenter.blog;

import com.tianbin.theoldreaderapp.common.wrapper.RxSchedulersHelper;
import com.tianbin.theoldreaderapp.contract.blog.BlogDetailContract;
import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.presenter.base.RxPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * class des
 * Created by tianbin on 16/11/18.
 */
public class BlogDetailPresenter extends RxPresenter<BlogDetailContract.View> implements BlogDetailContract.Preseneter {

    @Inject
    BlogApi mBlogApi;

    @Inject
    public BlogDetailPresenter() {
    }

    @Override
    public void markAsRead(String id) {
        Subscription markAsReadSubscription = mBlogApi.markAsRead(id)
                .compose(RxSchedulersHelper.<Void>io_main())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.actionSuccess(BlogDetailContract.ActionType.READ);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.actionFailed(BlogDetailContract.ActionType.READ);
                    }
                });
        addSubscrebe(markAsReadSubscription);
    }

    @Override
    public void markAsUnRead(String id) {
        Subscription markAsReadSubscription = mBlogApi.markAsUnRead(id)
                .compose(RxSchedulersHelper.<Void>io_main())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.actionSuccess(BlogDetailContract.ActionType.UNREAD);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.actionFailed(BlogDetailContract.ActionType.UNREAD);
                    }
                });
        addSubscrebe(markAsReadSubscription);
    }

    @Override
    public void markAsStared(String id) {
        Subscription markAsStaredSubscription = mBlogApi.markAsLiked(id)
                .compose(RxSchedulersHelper.<Void>io_main())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.actionSuccess(BlogDetailContract.ActionType.STARED);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.actionFailed(BlogDetailContract.ActionType.STARED);
                    }
                });
        addSubscrebe(markAsStaredSubscription);
    }

    @Override
    public void markAsUnstared(String id) {
        Subscription markAsUnStaredSubscription = mBlogApi.markAsUnLiked(id)
                .compose(RxSchedulersHelper.<Void>io_main())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.actionSuccess(BlogDetailContract.ActionType.UNSTARED);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.actionFailed(BlogDetailContract.ActionType.UNSTARED);
                    }
                });
        addSubscrebe(markAsUnStaredSubscription);
    }

}
