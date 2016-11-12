package com.tianbin.theoldreaderapp.ui.module.subscription.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */

public class SubscriptionsAdapter extends BaseQuickAdapter<SubscriptionList.Entity> {

    @Inject
    public SubscriptionsAdapter() {
        super(R.layout.holder_at_subscriptions, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SubscriptionList.Entity entity) {

    }
}
