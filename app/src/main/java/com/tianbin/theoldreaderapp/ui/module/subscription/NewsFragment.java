package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.presenter.subscription.NewsPresenter;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.adapter.NewsAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * news fragment
 * Created by tianbin on 16/11/3.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.news_recycler_view)
    RecyclerView mNewsRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private NewsPresenter mNewsPresenter;
    private BaseQuickAdapter mNewsAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsPresenter = new NewsPresenter();
        mNewsPresenter.attachView(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        initAdapter();
        initRecyclerView();

        mNewsPresenter.fetchNews(-1);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    private void initAdapter() {
        mNewsAdapter = new NewsAdapter();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mNewsRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        mNewsRecyclerView.addItemDecoration(dividerItemDecoration);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsRecyclerView.setClipToPadding(false);
    }

    @Override
    public void onRefresh() {
        if (mNewsPresenter != null) {
            mNewsPresenter.fetchNews(-1);
        }
    }

    @Override
    public void fetchNewsSuccess(List<BlogList.ItemsEntity> blogList) {
        if (mNewsAdapter != null) {
            mNewsAdapter.setNewData(blogList);
            mNewsAdapter.notifyDataSetChanged();
        }
        if (mSwipeRefreshLayout != null) {
            boolean refreshing = mSwipeRefreshLayout.isRefreshing();
            if (refreshing) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
        Toast.makeText(getContext(), "fetch success", Toast.LENGTH_LONG);
    }

    @Override
    public void fetchNewsFailed() {
        Toast.makeText(getContext(), "fetch failed", Toast.LENGTH_LONG);
    }
}
