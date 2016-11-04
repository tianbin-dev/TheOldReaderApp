package com.tianbin.theoldreaderapp.data.net.client.core;

import android.text.TextUtils;

import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.data.pref.AccountPref;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * base interceptor
 * Created by tianbin on 16/11/3.
 */
public class BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        String token = AccountPref.getLogonToken(MyApplication.getInstance().getApplicationContext());
        if (!TextUtils.isEmpty(token)) {
            requestBuilder.header("Authorization", "GoogleLogin auth=" + token);
        }
        Request request = addDefaultParameter(requestBuilder.build());
        return chain.proceed(request);
    }

    protected Request addDefaultParameter(Request request)
            throws IOException {
        Request.Builder builder;
        if (request.method().equals("GET")) {
            builder = appendDefaultParameterToUrl(request);
        } else {
            builder = addDefaultParameterToBody(request);
        }
        return builder.build();
    }

    private Request.Builder appendDefaultParameterToUrl(Request request) {
        Request.Builder builder;
        HttpUrl originalHttpUrl = request.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("output", "json")
                .build();
        builder = request.newBuilder().url(url);
        return builder;
    }

    private Request.Builder addDefaultParameterToBody(Request request) throws IOException {
        Request.Builder builder;
        RequestBody newRequestBody;
        if (request.body() != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Sink sink = Okio.sink(baos);
            BufferedSink bufferedSink = Okio.buffer(sink);
            // Write old params
            request.body().writeTo(bufferedSink);
            // write to buffer additional params
            bufferedSink.writeString("&output=json", Charset.defaultCharset());
            newRequestBody = RequestBody.create(
                    request.body().contentType(),
                    bufferedSink.buffer().readUtf8());
        } else {
            newRequestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("output", "json")
                    .build();
        }
        builder = request.newBuilder().post(newRequestBody);
        return builder;
    }
}
