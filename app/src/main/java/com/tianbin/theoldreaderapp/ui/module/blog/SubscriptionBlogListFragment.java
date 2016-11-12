package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.di.component.SimpleFragmentComponent;
import com.tianbin.theoldreaderapp.presenter.blog.SubscriptionBlogListPresenter;
import com.tianbin.theoldreaderapp.ui.base.SimpleFragmentActivity;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */
public class SubscriptionBlogListFragment extends BlogListBaseFragment {

    @Inject
    SubscriptionBlogListPresenter mSubscriptionBlogListPresenter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(SimpleFragmentComponent.class).inject(this);

        mEntity = (SubscriptionList.Entity) getArguments().getSerializable(ENTITY);

        mSubscriptionBlogListPresenter.setSubscriptionId(mEntity.getId());
    }
}
