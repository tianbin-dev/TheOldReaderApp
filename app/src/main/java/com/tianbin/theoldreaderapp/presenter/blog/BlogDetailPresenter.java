package com.tianbin.theoldreaderapp.presenter.blog;

import com.tianbin.theoldreaderapp.contract.blog.BlogDetailContract;
import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.presenter.base.RxPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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
    public void markAsStared(String id) {
        Subscription markAsStaredSubscription = mBlogApi.markAsLiked(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.starSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.startFailed();
                    }
                });
        addSubscrebe(markAsStaredSubscription);
    }

    @Override
    public void markAsUnstared(String id) {
        Subscription markAsUnStaredSubscription = mBlogApi.markAsUnLiked(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mView.unstarSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.unstartFailed();
                    }
                });
        addSubscrebe(markAsUnStaredSubscription);
    }

}
