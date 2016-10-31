package com.tianbin.theoldreaderapp.presenter.base;


import com.anly.mvp.BaseMvpPresenter;
import com.anly.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by tianbin on 16/7/20.
 */
public class RxMvpPresenter<V extends MvpView> extends BaseMvpPresenter<V> {

    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V mvpView) {
        super.attachView(mvpView);

        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();

        mCompositeSubscription.clear();
        mCompositeSubscription = null;
    }
}
