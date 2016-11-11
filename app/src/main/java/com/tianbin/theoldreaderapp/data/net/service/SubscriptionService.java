package com.tianbin.theoldreaderapp.data.net.service;

import com.tianbin.theoldreaderapp.data.module.SubscriptionList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * subscription service
 * Created by tianbin on 16/11/3.
 */
public interface SubscriptionService {

    @GET("/reader/api/0/subscription/list")
    Observable<SubscriptionList> getSubscriptionList();


}
