package com.tianbin.theoldreaderapp.data.api;

import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;

import rx.Observable;

/**
 * subscription api
 * Created by tianbin on 16/11/3.
 */
public interface SubscriptionApi {

    /**
     * get subscription list
     *
     * @return
     */
    Observable<SubscriptionList> getSubscriptionList();

    /**
     * get blog list
     *
     * @return
     */
    Observable<BlogList> getBlogList(long continuation);
}
