package com.tianbin.theoldreaderapp.data.net;

import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.net.service.BlogService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * content data source
 * Created by tianbin on 16/11/10.
 */
public class BlogDataSource implements BlogApi {

    private BlogService mBlogService;

    @Inject
    public BlogDataSource(BlogService blogService) {
        mBlogService = blogService;
    }

    @Override
    public Observable<BlogList> getBlogList(long continuation) {
        return mBlogService.getBlogList(continuation);
    }

    @Override
    public Observable markAsRead() {
        return mBlogService.markAsRead();
    }

    @Override
    public Observable<BlogIdItemList> getUnReadItemIds() {
        return mBlogService.getUnReadItemIds();
    }

    @Override
    public Observable<BlogList> getUnReadContents(List<String> idList) {
        return mBlogService.getUnReadContents(idList);
    }

    @Override
    public Observable<BlogIdItemList> getSubscriptionBlogIds(String subscriptionId) {
        return mBlogService.getSubscriptionBlogIds(subscriptionId);
    }
}
