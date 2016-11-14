package com.tianbin.theoldreaderapp.data.api;

import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import java.util.List;

import rx.Observable;

/**
 * content api
 * Created by tianbin on 16/11/10.
 */
public interface BlogApi {

    Observable<BlogList> getBlogList(long continuation);

    Observable markAsRead();

    Observable<BlogIdItemList> getUnReadItemIds(long continuation);

    Observable<BlogList> getUnReadContents(List<String> idList);

    Observable<BlogIdItemList> getSubscriptionBlogIds(String subscriptionId, long continuation);
}
