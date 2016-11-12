package com.tianbin.theoldreaderapp.contract.subscription;

import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.mvp.MvpPresenter;
import com.tianbin.theoldreaderapp.mvp.MvpView;

import java.util.List;

/**
 * subscription contract
 * Created by tianbin on 16/11/10.
 */
public interface SubscriptionContract {

    interface View extends MvpView {

        void fetchSubscriptionsSuccess(List<SubscriptionList.Entity> subscriptionList);

        void fetchSubscriptionsFail();
    }

    interface Presenter extends MvpPresenter<View> {

        void fetchSubscriptions();
    }
}
