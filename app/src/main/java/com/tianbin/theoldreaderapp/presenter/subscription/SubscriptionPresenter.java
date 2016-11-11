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

    SubscriptionApi mSubscriptionDataService;

    @Inject
    public SubscriptionPresenter(SubscriptionApi subscriptionDataService) {
        mSubscriptionDataService = subscriptionDataService;
    }

    @Override
    public void fetchSubscriptions() {
        mSubscriptionDataService.getSubscriptionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<SubscriptionList>() {
                    @Override
                    public void onSuccess(SubscriptionList subscriptionList) {
                        AppLog.d("fetch subscription success");

                    }

                    @Override
                    public void onError(Throwable e) {
                        AppLog.d("fetch subscription failed " + e.toString());
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
