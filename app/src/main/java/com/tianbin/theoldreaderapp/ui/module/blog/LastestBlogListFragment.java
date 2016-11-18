package com.tianbin.theoldreaderapp.ui.module.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.blog.LastBlogListPresenter;
import com.tianbin.theoldreaderapp.ui.module.blog.adapter.LastestBlogListAdapter;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */
public class LastestBlogListFragment extends BlogListBaseFragment {

    @Inject
    LastBlogListPresenter mLastBlogListPresenter;

    @Inject
    LastestBlogListAdapter mLastestBlogListAdapter;

    @Override
    public BlogListContract.Presenter getPresenter() {
        return mLastBlogListPresenter;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return mLastestBlogListAdapter;
    }

    @Override
    public void addItemClickListener() {
        mNewsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                LastestBlogListAdapter lastestBlogListAdapter = (LastestBlogListAdapter) baseQuickAdapter;
                BlogList.Blog blog = lastestBlogListAdapter.getData().get(position);
                jumpToBlogDetailFragment(view, blog);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }
}
