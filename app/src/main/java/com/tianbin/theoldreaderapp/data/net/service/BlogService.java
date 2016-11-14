package com.tianbin.theoldreaderapp.data.net.service;

import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * content service
 * Created by tianbin on 16/11/10.
 */
public interface BlogService {

    @GET("/reader/api/0/stream/contents")
    Observable<BlogList> getBlogList(@Query("c") long continuation);

    @POST("/reader/api/0/edit-tag")
    Observable markAsRead();

    @GET("/reader/api/0/stream/items/ids?xt=user/-/state/com.google/read&s=user/-/state/com.google/reading-list")
    Observable<BlogIdItemList> getUnReadItemIds(@Query("c") long continuation);

    @GET("/reader/api/0/stream/items/contents")
    Observable<BlogList> getUnReadContents(@Query("i") List<String> idList);

    @GET("/reader/api/0/stream/items/ids")
    Observable<BlogIdItemList> getSubscriptionBlogIds(@Query("s") String subscriptionId, @Query("c") long continuation);
}
