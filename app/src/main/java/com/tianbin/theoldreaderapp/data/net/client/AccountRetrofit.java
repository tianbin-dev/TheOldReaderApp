package com.tianbin.theoldreaderapp.data.net.client;

import com.tianbin.theoldreaderapp.data.net.client.core.ApiEndpoint;
import com.tianbin.theoldreaderapp.data.net.client.core.BaseOkHttpClient;
import com.tianbin.theoldreaderapp.data.net.client.core.BaseRetrofit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by tianbin on 16/10/29.
 */
public class AccountRetrofit extends BaseRetrofit {

    private static final String END_POINT = "https://theoldreader.com/";

    @Override
    public ApiEndpoint getApiEndpoint() {
        return new ApiEndpoint() {
            @Override
            public String getEndpoint() {
                return END_POINT;
            }
        };
    }

    @Override
    public OkHttpClient getHttpClient() {
        return new LoginHttpClient().get();
    }

    public static class LoginHttpClient extends BaseOkHttpClient {

        @Override
        public OkHttpClient.Builder customize(OkHttpClient.Builder builder) {

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "GoogleLogin");
                    Request request = addDefaultParameter(requestBuilder.build(), "&output=json");
                    return chain.proceed(request);
                }
            });
            return builder;
        }

        private Request addDefaultParameter(Request request, String parameter)
                throws IOException {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            Sink sink = Okio.sink(baos);
            BufferedSink bufferedSink = Okio.buffer(sink);

            /**
             * Write old params
             * */
            request.body().writeTo(bufferedSink);

            /**
             * write to buffer additional params
             * */
            bufferedSink.writeString(parameter, Charset.defaultCharset());

            RequestBody newRequestBody = RequestBody.create(
                    request.body().contentType(),
                    bufferedSink.buffer().readUtf8()
            );

            return request.newBuilder().post(newRequestBody).build();
        }
    }
}
