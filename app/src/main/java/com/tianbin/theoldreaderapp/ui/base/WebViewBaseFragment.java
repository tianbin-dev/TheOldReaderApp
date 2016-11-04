package com.tianbin.theoldreaderapp.ui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tianbin.theoldreaderapp.R;

import butterknife.BindView;

/**
 * WebViewFragment 基类
 * Created by tianbin on 16/7/18.
 */
public abstract class WebViewBaseFragment extends BaseFragment {

    @BindView(R.id.web_view)
    protected WebView mWebView;
    @BindView(R.id.webview_progressbar)
    ProgressBar mWebViewProgressBar;

    private WebViewClient mWebViewClient;

    @Override
    protected int getLayoutResId() {
        return getFragmentLayoutResId();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWebViewClient();
        initWebView(view);

        syncCookies(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
        mWebView.pauseTimers();
    }

    @Override
    public void onResume() {
        mWebView.onResume();
        mWebView.resumeTimers();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    protected void initWebViewClient() {
        mWebViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewLoadPageFinished(view, url);
                if (mWebView.getContentHeight() != 0) {
                    mWebViewProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mWebView.stopLoading();
                mWebView.clearView();

                webViewClientReceiveDataError();
            }
        };
    }

    protected void initWebView(View view) {
        mWebView.setWebViewClient(mWebViewClient);
        // 不设置,无法弹出Alert
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 10) {
                    newProgress = 10;
                }
                if (newProgress == 100) {
                    mWebViewProgressBar.setVisibility(View.GONE);
                } else {
                    if (mWebViewProgressBar.getVisibility() == View.GONE) {
                        mWebViewProgressBar.setVisibility(View.VISIBLE);
                    }
                    mWebViewProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        WebSettings settings = mWebView.getSettings();

        if (Build.VERSION.SDK_INT >= 21) { // 添加这个 if 的原因: http://stackoverflow.com/questions/31509277/webview-images-are-not-showing-with-https
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        //settings.setUserAgentString(UserAgent.newWebView(settings.getUserAgentString()));
    }

    private void syncCookies(final Context context) {

        //CookieManager cookieManager = CookieManager.getInstance();

        //String cookies = LoggedInUser.getCookie();
        //if (TextUtils.isEmpty(cookies)) {
        //    return;
        //}
        //String[] cks = cookies.split(";");
        //if (cks == null || cks.length == 0) {
        //    return;
        //}
        //CookieSyncManager.createInstance(context);
        //
        //cookieManager.setAcceptCookie(true);

        //String[] domains = new String[]{
        //        "", // debug
        //        "", // api release
        //        "", // public release
        //        "" // root domain
        //};
        //
        //for (String domain : domains) {
        //    for (String ck : cks) {
        //        cookieManager.setCookie(domain, ck);
        //    }
        //}
        //CookieSyncManager.getInstance().sync();
    }

    protected abstract int getFragmentLayoutResId();

    /**
     * web页加载完毕回调
     *
     * @param view
     * @param url
     */
    protected abstract void webViewLoadPageFinished(WebView view, String url);

    /**
     * web页加载错误回调
     */
    protected abstract void webViewClientReceiveDataError();
}
