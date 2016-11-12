package com.tianbin.theoldreaderapp.presenter.blog;

import android.support.annotation.NonNull;

import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */

public class BlogListBasePresenter {

    @Inject
    BlogApi mBlogApi;

    protected long mContinuation;
    protected BlogListContract.View mView;

    @NonNull
    protected List<String> getBlogIdList(BlogIdItemList blogIdItemList) {
        List<BlogIdItemList.BlogIdItem> blogIdItems = blogIdItemList.getItemRefs();

        List<String> idList = new ArrayList<>();
        if (blogIdItems != null && blogIdItems.size() > 0) {
            for (BlogIdItemList.BlogIdItem blogIdItem : blogIdItems) {
                idList.add("tag:google.com,2005:reader/item/" + blogIdItem.getId());
            }
        }
        return idList;
    }

    protected void appendNewData(BlogList blogList) {
        List<BlogList.ItemEntity> data = mView.getData();
        List<BlogList.ItemEntity> items = blogList.getItems();

        List<BlogList.ItemEntity> newBlogItems = new ArrayList<>();
        for (BlogList.ItemEntity item : items) {
            if (!data.contains(item)) {
                newBlogItems.add(item);
            } else {
                break;
            }
        }
        data.addAll(newBlogItems);
    }

}
