package com.tianbin.theoldreaderapp.ui.module.fav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.blog.FavBlogListPresenter;
import com.tianbin.theoldreaderapp.ui.module.blog.BlogListBaseFragment;
import com.tianbin.theoldreaderapp.ui.module.blog.adapter.LastestBlogListAdapter;

import javax.inject.Inject;

/**
 * fav fragment
 * Created by tianbin on 16/11/4.
 */
public class FavouriteFragment extends BlogListBaseFragment {

    @Inject
    FavBlogListPresenter mFavBlogListPresenter;

    @Inject
    LastestBlogListAdapter mLastestBlogListAdapter;

    @Override
    public BlogListContract.Presenter getPresenter() {
        return mFavBlogListPresenter;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return mLastestBlogListAdapter;
    }

    @Override
    public void addItemClickListener() {
        mNewsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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
