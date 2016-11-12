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

public class SubscriptionAdapter extends BaseQuickAdapter<SubscriptionList.Entity> {

    @Inject
    public SubscriptionAdapter() {
        super(R.layout.holder_at_subscriptions, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SubscriptionList.Entity entity) {
        baseViewHolder.setText(R.id.tv_subscription_name, entity.getTitle());
        baseViewHolder.setText(R.id.tv_html_url, entity.getHtmlUrl());
        baseViewHolder.addOnClickListener(R.id.ll_root_view);
    }
}
