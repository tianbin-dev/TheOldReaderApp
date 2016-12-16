package com.tianbin.theoldreaderapp.ui.module.fav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.blog.FavBlogListPresenter;
import com.tianbin.theoldreaderapp.ui.module.blog.BlogListBaseFragment;
import com.tianbin.theoldreaderapp.ui.module.blog.SubscriptionBlogListFragment;
import com.tianbin.theoldreaderapp.ui.module.blog.adapter.BlogListAdapter;

import javax.inject.Inject;

/**
 * FavouriteFragment
 * Created by tianbin on 16/11/4.
 */
public class FavouriteFragment extends BlogListBaseFragment {

    @Inject
    FavBlogListPresenter mFavBlogListPresenter;

    @Inject
    BlogListAdapter mBlogListAdapter;

    @Override
    public BlogListContract.Presenter getPresenter() {
        return mFavBlogListPresenter;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return mBlogListAdapter;
    }

    @Override
    public void addItemClickListener() {
        mNewsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BlogListAdapter blogListAdapter = (BlogListAdapter) baseQuickAdapter;
                BlogList.Blog blog = blogListAdapter.getData().get(position);

                switch (view.getId()){
                    case R.id.tv_origin_title:
                        SubscriptionList.Entity entity = new SubscriptionList.Entity();
                        entity.setId(blog.getOrigin().getStreamId());
                        entity.setTitle(blog.getOrigin().getTitle());
                        SubscriptionBlogListFragment.start(getContext(), entity);
                        break;
                    case R.id.ll_blog_holder_root:
                        jumpToBlogDetailFragment(view, blog);
                        break;
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }
}
