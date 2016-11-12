package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.ui.base.SimpleFragmentActivity;
import com.tianbin.theoldreaderapp.ui.base.WebViewBaseFragment;

/**
 * detail fragment
 * Created by tianbin on 16/11/4.
 */
public class BlogDetailFragment extends WebViewBaseFragment {

    private static final String BLOG_URL = "blog_url";

    public static void start(Context context, String blogUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(BLOG_URL, blogUrl);
        Intent intent = SimpleFragmentActivity.newIntent(context, "详情", BlogDetailFragment.class, bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void webViewLoadPageFinished(WebView view, String url) {

    }

    @Override
    protected void webViewClientReceiveDataError() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        mWebView.loadUrl(arguments.getString(BLOG_URL));
    }
}
