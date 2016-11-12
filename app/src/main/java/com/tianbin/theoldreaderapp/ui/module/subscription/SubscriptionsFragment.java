package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.subscription.SubscriptionContract;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.subscription.SubscriptionPresenter;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.adapter.SubscriptionsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * subscription fragment
 * Created by tianbin on 16/11/4.
 */
public class SubscriptionsFragment extends BaseFragment implements SubscriptionContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    SubscriptionPresenter mSubscriptionPresenter;
    @Inject
    SubscriptionsAdapter subscriptionsAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_subscriptions;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSubscriptionPresenter.attachView(this);
        mSubscriptionPresenter.fetchSubscriptions();

        initAdapter();
    }

    private void initAdapter() {

    }


    @Override
    public void fetchSubscriptionsSuccess(SubscriptionList subscriptionList) {

    }

    @Override
    public void fetchSubscriptionsFail() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
