package com.tianbin.theoldreaderapp.presenter.subscription;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;
import com.tianbin.theoldreaderapp.contract.subscription.SubscriptionContract;
import com.tianbin.theoldreaderapp.data.api.SubscriptionApi;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.data.rx.ResponseObserver;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * subscription presenter
 * Created by tianbin on 16/11/10.
 */
public class SubscriptionPresenter implements SubscriptionContract.Presenter {

    private SubscriptionContract.View mView;

    SubscriptionApi mSubscriptionApi;

    @Inject
    public SubscriptionPresenter(SubscriptionApi subscriptionApi) {
        mSubscriptionApi = subscriptionApi;
    }

    @Override
    public void fetchSubscriptions() {
        mSubscriptionApi.getSubscriptionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<SubscriptionList>() {
                    @Override
                    public void onSuccess(SubscriptionList subscriptionList) {
                        mView.fetchSubscriptionsSuccess(subscriptionList.getSubscriptions());
                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLog.d("fetch subscription failed " + e.toString());
                        mView.fetchSubscriptionsFail();
                    }
                });

    }

    @Override
    public void attachView(SubscriptionContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
