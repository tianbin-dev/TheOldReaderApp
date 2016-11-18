package com.tianbin.theoldreaderapp.ui.module.blog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import javax.inject.Inject;

/**
 * LastestBlogListFragment adapter
 * Created by tianbin on 16/11/4.
 */
public class LastestBlogListAdapter extends BaseQuickAdapter<BlogList.Blog> {

    @Inject
    public LastestBlogListAdapter() {
        super(R.layout.holder_at_blog, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BlogList.Blog blog) {
        baseViewHolder.setText(R.id.tv_title, blog.getTitle())
                .setText(R.id.tv_origin_title, blog.getOrigin().getTitle())
                .addOnClickListener(R.id.ll_blog_holder_root);
    }
}
