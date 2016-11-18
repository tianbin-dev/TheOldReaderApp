package com.tianbin.theoldreaderapp.ui.module.blog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import javax.inject.Inject;

/**
 * SubscriptionBlogFrament adapter
 * Created by tianbin on 16/11/4.
 */
public class SubscriptionBlogListAdapter extends BaseQuickAdapter<BlogList.Blog> {

    @Inject
    public SubscriptionBlogListAdapter() {
        super(R.layout.holder_at_subscription_blog, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BlogList.Blog blog) {
        baseViewHolder.setText(R.id.tv_blog_title, blog.getTitle())
                .addOnClickListener(R.id.ll_blog_holder_root);
    }
}
