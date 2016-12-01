package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.subscription.SubscriptionContract;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.subscription.SubscriptionPresenter;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;
import com.tianbin.theoldreaderapp.ui.module.blog.SubscriptionBlogListFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.adapter.SubscriptionAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ezy.ui.layout.LoadingLayout;

/**
 * SubscriptionFragment
 * Created by tianbin on 16/11/4.
 */
public class SubscriptionFragment extends BaseFragment implements SubscriptionContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LoadingLayout mLoadingLayout;

    @Inject
    SubscriptionPresenter mSubscriptionPresenter;
    @Inject
    SubscriptionAdapter mSubscriptionAdapter;


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

        mSwipeRefreshLayout.setOnRefreshListener(this);

        initRecyclerView();

        mSubscriptionPresenter.attachView(this);

        mLoadingLayout = LoadingLayout.wrap(view);

        fetchData();
    }

    private void fetchData() {
        mSubscriptionPresenter.fetchSubscriptions();
        mLoadingLayout.showLoading();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mSubscriptionAdapter);
        mRecyclerView.setClipToPadding(false);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                SubscriptionAdapter subscriptionAdapter = (SubscriptionAdapter) baseQuickAdapter;
                SubscriptionList.Entity entity = subscriptionAdapter.getData().get(position);
                SubscriptionBlogListFragment.start(getContext(), entity);
            }
        });
    }

    @Override
    public void fetchSubscriptionsSuccess(List<SubscriptionList.Entity> subscriptionList) {
        mSubscriptionAdapter.setNewData(subscriptionList);
        mSwipeRefreshLayout.setRefreshing(false);
        if (subscriptionList == null || subscriptionList.size() == 0) {
            mLoadingLayout.showEmpty();
        } else {
            mLoadingLayout.showContent();
        }
    }

    @Override
    public void fetchSubscriptionsFail() {
        Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_LONG).show();
        mSwipeRefreshLayout.setRefreshing(false);
        mLoadingLayout.showError();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {
        mSubscriptionPresenter.fetchSubscriptions();
    }
}
