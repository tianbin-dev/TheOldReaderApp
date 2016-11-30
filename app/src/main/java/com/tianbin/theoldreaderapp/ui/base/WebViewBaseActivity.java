package com.tianbin.theoldreaderapp.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tianbin.theoldreaderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * WebViewFragment 基类
 * Created by tianbin on 16/7/18.
 */
public abstract class WebViewBaseActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    protected WebView mWebView;

    private WebViewClient mWebViewClient;

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        initWebViewClient();
        initWebView();
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
                webViewStartLoadPage(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.scrollTo(0,0);
                webViewLoadPageFinished(view, url);
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

    protected void initWebView() {
        mWebView.setWebViewClient(mWebViewClient);
        // 不设置,无法弹出Alert

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

    /**
     * web页开始加载回调
     * @param url
     */
    protected abstract void webViewStartLoadPage(String url);
}
