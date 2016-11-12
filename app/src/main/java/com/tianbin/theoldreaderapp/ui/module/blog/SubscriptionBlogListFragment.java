package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.ui.base.SimpleFragmentActivity;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */
public class SubscriptionBlogListFragment extends BlogListBaseFragment{

    public static void start(Context context, SubscriptionList.Entity entity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ENTITY, entity);
        Intent intent = SimpleFragmentActivity.newIntent(context, entity.getTitle(), SubscriptionBlogListFragment.class, bundle);
        context.startActivity(intent);
    }

    private static final String ENTITY = "entity";

    @Override
    public BlogListContract.Presenter getPresenter() {
        return null;
    }
}
