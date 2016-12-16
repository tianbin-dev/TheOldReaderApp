package com.tianbin.theoldreaderapp.ui.module.blog.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.common.util.CalendarUtil;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import javax.inject.Inject;

/**
 * LastestBlogListFragment adapter
 * Created by tianbin on 16/11/4.
 */
public class BlogListAdapter extends BaseQuickAdapter<BlogList.Blog, BaseViewHolder> {

    @Inject
    public BlogListAdapter() {
        super(R.layout.holder_at_blog, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BlogList.Blog blog) {
        baseViewHolder.setText(R.id.tv_title, blog.getTitle())
                .setText(R.id.tv_origin_title, blog.getOrigin().getTitle())
                .setText(R.id.tv_post_time, CalendarUtil.getBlogPostTime(blog.getPublished()))
                .addOnClickListener(R.id.ll_blog_holder_root)
                .addOnClickListener(R.id.tv_origin_title);
    }
}
