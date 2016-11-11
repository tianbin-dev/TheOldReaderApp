package com.tianbin.theoldreaderapp.contract.subscription;

import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

/**
 * subscription contract
 * Created by tianbin on 16/11/10.
 */
public interface SubscriptionContract {

    interface View extends MvpView {

        void fetchSubscriptionsSuccess(SubscriptionList subscriptionList);

        void fetchSubscriptionsFail();
    }

    interface Presenter extends MvpPresenter<View> {

        void fetchSubscriptions();
    }
}
