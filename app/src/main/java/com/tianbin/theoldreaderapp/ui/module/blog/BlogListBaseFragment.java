package com.tianbin.theoldreaderapp.ui.module.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * BlogListBaseFragment
 * Created by tianbin on 16/11/3.
 */
public abstract class BlogListBaseFragment extends BaseFragment implements BlogListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.news_recycler_view)
    public RecyclerView mNewsRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseQuickAdapter mBaseQuickAdapter;
    private BlogListContract.Presenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getPresenter();
        mPresenter.attachView(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        initAdapter();
        initRecyclerView();

        fetchDataAndShowRefreshLayout();
    }

    private void fetchDataAndShowRefreshLayout() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.fetchBlogs(BlogListContract.FetchType.INIT);
    }

    private void initAdapter() {
        mBaseQuickAdapter = getAdapter();
        mBaseQuickAdapter.setAutoLoadMoreSize(20);
        mBaseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.fetchBlogs(BlogListContract.FetchType.LOAD_MORE);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mNewsRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        mNewsRecyclerView.addItemDecoration(dividerItemDecoration);
        mNewsRecyclerView.setAdapter(mBaseQuickAdapter);
        mNewsRecyclerView.setClipToPadding(false);

        addItemClickListener();
    }

    @Override
    public void onRefresh() {
        if (mPresenter != null && !mBaseQuickAdapter.isLoading()) {
            mPresenter.fetchBlogs(BlogListContract.FetchType.REFRESH);
        }
    }

    @Override
    public void fetchBlogsSuccess(List<BlogList.Blog> blogList) {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.setNewData(blogList);
            mBaseQuickAdapter.notifyDataSetChanged();
        }
        if (blogList != null && blogList.size() > 0) {
            mBaseQuickAdapter.loadMoreComplete();
        } else {
            mBaseQuickAdapter.loadMoreEnd();
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
    public void fetchBlogsFailed(Throwable throwable) {
        mBaseQuickAdapter.loadMoreFail();
        dismissSwipeRefreshLayout();
    }

    @Override
    public void loadMoreBlogsSuccess(List<BlogList.Blog> blogList) {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.addData(blogList);
            mBaseQuickAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadMoreBlogsCompleted() {
        mBaseQuickAdapter.loadMoreEnd();
    }

    @Override
    public void pullDownRefreshSuccess() {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.notifyDataSetChanged();
            mBaseQuickAdapter.loadMoreComplete();
        }
        dismissSwipeRefreshLayout();
    }

    @Override
    public List<BlogList.Blog> getData() {
        return mBaseQuickAdapter.getData();
    }

    protected void jumpToBlogDetailFragment(View view, BlogList.Blog blog) {
        String simpleName = getClass().getSimpleName();
        int fromType;
        if (simpleName.equals("FavouriteFragment")) {
            fromType = BlogDetailActivity.FROM_FAV;
        } else {
            fromType = BlogDetailActivity.FROM_OTHER;
        }
        BlogDetailActivity.start(view.getContext(), blog, fromType);
    }

    public abstract BlogListContract.Presenter getPresenter();

    public abstract BaseQuickAdapter getAdapter();

    public abstract void addItemClickListener();
}
