package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;
import com.tianbin.theoldreaderapp.data.net.service.BlogService;

import okhttp3.OkHttpClient;

/**
 * content retrofit
 * Created by tianbin on 16/10/29.
 */
public class BlogRetrofit extends BaseRetrofit {

    private static BlogRetrofit mRetrofit;

    private BlogRetrofit() {
    }

    public static BlogService getService() {
        if (mRetrofit == null) {
            mRetrofit = new BlogRetrofit();
        }
        return mRetrofit.get().create(BlogService.class);
    }

    @Override
    public OkHttpClient getHttpClient() {
        return CommonHttpClient.getInstance().get();
    }
}
