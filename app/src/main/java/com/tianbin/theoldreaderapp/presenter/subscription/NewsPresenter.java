package com.tianbin.theoldreaderapp.presenter.subscription;

import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.data.net.SubscriptionDataSource;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * news presenter
 * Created by tianbin on 16/11/3.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View mView;
    private SubscriptionDataSource mSubscriptionDataSource;

    public NewsPresenter() {
        mSubscriptionDataSource = new SubscriptionDataSource();
    }

    @Override
    public void attachView(NewsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void fetchNews() {
        mSubscriptionDataSource.getSubscriptionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SubscriptionList>() {
                    @Override
                    public void call(SubscriptionList subscriptionList) {
                        subscriptionList.getSubscriptions();
                    }
                });
    }
}
