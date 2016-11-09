package com.tianbin.theoldreaderapp.data.net.client;

import android.content.Context;

import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.data.net.client.core.BaseOkHttpClient;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.AppendParamInterceptor;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.CacheControllInterceptor;
import com.tianbin.theoldreaderapp.data.net.client.interceptor.TokenInterceptor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * cache http client
 * Created by tianbin on 16/11/8.
 */
public class CacheHttpClient extends BaseOkHttpClient {

    private static final long CACHE_SIZE = 1024 * 1024 * 50;

    private Context mContext;

    public CacheHttpClient() {
        mContext = MyApplication.getInstance().getApplicationContext();
    }

    @Override
    public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(new AppendParamInterceptor());

        // set cache dir
        File cacheFile = new File(mContext.getCacheDir(), "reader_repo");
        Cache cache = new Cache(cacheFile, CACHE_SIZE);
        builder.cache(cache);
        builder.addNetworkInterceptor(new CacheControllInterceptor(mContext));
        return builder;
    }

}
