package com.tianbin.theoldreaderapp.data.net.client.interceptor;

import android.content.Context;

import com.tianbin.theoldreaderapp.common.util.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * cache controll interceptor
 * Created by tianbin on 16/11/8.
 */
public class CacheControllInterceptor implements Interceptor {

    private Context mContext;

    public CacheControllInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Add FORCE_CACHE cache control for each request if network is not available.
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response originalResponse = chain.proceed(request);

        if (NetworkUtil.isNetworkAvailable(mContext)) {

            String cacheControl = request.cacheControl().toString();

            // Add cache control header for response same as request's while network is available.
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            // Add cache control header for response to FORCE_CACHE while network is not available.
            return originalResponse.newBuilder()
                    .header("Cache-Control", CacheControl.FORCE_CACHE.toString())
                    .build();
        }
    }
}
