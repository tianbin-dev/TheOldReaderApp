package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.di.component.SimpleFragmentComponent;
import com.tianbin.theoldreaderapp.presenter.blog.SubscriptionBlogListPresenter;
import com.tianbin.theoldreaderapp.ui.base.SimpleFragmentActivity;
import com.tianbin.theoldreaderapp.ui.module.blog.adapter.SubscriptionBlogListAdapter;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */
public class SubscriptionBlogListFragment extends BlogListBaseFragment {

    @Inject
    SubscriptionBlogListPresenter mSubscriptionBlogListPresenter;

    @Inject
    SubscriptionBlogListAdapter mSubscriptionBlogListAdapter;

    private SubscriptionList.Entity mEntity;

    public static void start(Context context, SubscriptionList.Entity entity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ENTITY, entity);
        Intent intent = SimpleFragmentActivity.newIntent(context, entity.getTitle(), SubscriptionBlogListFragment.class, bundle);
        context.startActivity(intent);
    }

    private static final String ENTITY = "entity";

    @Override
    public BlogListContract.Presenter getPresenter() {
        return mSubscriptionBlogListPresenter;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return mSubscriptionBlogListAdapter;
    }

    @Override
    public void addItemClickListener() {
        mNewsRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                SubscriptionBlogListAdapter subscriptionBlogListAdapter = (SubscriptionBlogListAdapter) baseQuickAdapter;
                BlogList.Blog blog = subscriptionBlogListAdapter.getData().get(position);
                jumpToBlogDetailFragment(view, blog);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(SimpleFragmentComponent.class).inject(this);

        mEntity = (SubscriptionList.Entity) getArguments().getSerializable(ENTITY);

        mSubscriptionBlogListPresenter.setSubscriptionId(mEntity.getId());
    }
}
