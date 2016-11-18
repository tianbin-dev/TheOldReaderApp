package com.tianbin.theoldreaderapp.presenter.base;

import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * RxPresenter
 * Created by tianbin on 16/11/15.
 */

public class RxPresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
