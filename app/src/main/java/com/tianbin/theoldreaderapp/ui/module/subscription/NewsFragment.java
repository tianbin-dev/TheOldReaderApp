package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
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

    private NewsContract.Presenter mNewsPresenter;
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

        mNewsPresenter.fetchNews(NewsContract.FetchType.INIT);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    private void initAdapter() {
        mNewsAdapter = new NewsAdapter();
        mNewsAdapter.openLoadMore(20);
        mNewsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mNewsPresenter.fetchNews(NewsContract.FetchType.LOAD_MORE);
            }
        });
        addLoadingView();
    }

    private void addLoadingView() {
        TextView textView = new TextView(getContext());
        textView.setText("loading ...");
        textView.setTextSize(14);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setGravity(Gravity.CENTER);
        int viewHeight = getResources().getDimensionPixelSize(R.dimen.loading_view_height);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, viewHeight);
        textView.setLayoutParams(layoutParams);
        mNewsAdapter.setLoadingView(textView);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mNewsRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        mNewsRecyclerView.addItemDecoration(dividerItemDecoration);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        mNewsRecyclerView.setClipToPadding(false);

        mNewsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                NewsAdapter newsAdapter = (NewsAdapter) baseQuickAdapter;
                String href = newsAdapter.getData().get(position).getCanonical().get(0).getHref();
                DetailFragment.start(view.getContext(), href);
            }
        });
    }

    @Override
    public void onRefresh() {
        if (mNewsPresenter != null && !mNewsAdapter.isLoading()) {
            mNewsPresenter.fetchNews(NewsContract.FetchType.REFRESH);
        }
    }

    @Override
    public void fetchNewsSuccess(List<BlogList.ItemEntity> blogList) {
        if (mNewsAdapter != null) {
            mNewsAdapter.setNewData(blogList);
            mNewsAdapter.notifyDataSetChanged();
        }
        dismissSwipeRefreshLayout();
    }

    private void dismissSwipeRefreshLayout() {
        if (mSwipeRefreshLayout != null) {
            boolean refreshing = mSwipeRefreshLayout.isRefreshing();
            if (refreshing) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void fetchNewsFailed(Throwable throwable) {
        dismissSwipeRefreshLayout();
    }

    @Override
    public void loadMoreNewsSuccess(List<BlogList.ItemEntity> blogList) {
        if (mNewsAdapter != null) {
            mNewsAdapter.addData(blogList);
        }
    }

    @Override
    public void loadMoreNewsCompleted() {
        mNewsAdapter.loadComplete();
        Toast.makeText(getContext(), "no more data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void pullDownRefreshSuccess() {
        if (mNewsAdapter != null) {
            mNewsAdapter.notifyDataSetChanged();
        }
        dismissSwipeRefreshLayout();
    }

    @Override
    public List<BlogList.ItemEntity> getData() {
        return mNewsAdapter.getData();
    }
}
