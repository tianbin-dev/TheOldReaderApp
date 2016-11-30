package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.tianbin.theoldreaderapp.HasComponent;
import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.blog.BlogDetailContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.di.component.BlogDetailComponent;
import com.tianbin.theoldreaderapp.di.component.DaggerBlogDetailComponent;
import com.tianbin.theoldreaderapp.di.module.BlogModule;
import com.tianbin.theoldreaderapp.presenter.blog.BlogDetailPresenter;
import com.tianbin.theoldreaderapp.ui.base.WebViewBaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * detail fragment
 * Created by tianbin on 16/11/4.
 */
public class BlogDetailActivity extends WebViewBaseActivity implements HasComponent<BlogDetailComponent>, BlogDetailContract.View {

    public static final int FROM_FAV = 1;
    public static final int FROM_OTHER = 2;

    private static final String BLOG_URL = "blog_url";
    private static final String FROM_TYPE = "from_type";

    @Inject
    BlogDetailPresenter mBlogDetailPresenter;

    private int mFromType;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ActionBar mActionBar;

    private BlogList.Blog mBlog;

    public static void start(Context context, BlogList.Blog blog, int fromType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BLOG_URL, blog);
        bundle.putInt(FROM_TYPE, fromType);
        Intent intent = new Intent(context, BlogDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void webViewLoadPageFinished(WebView view, String url) {
        mActionBar.setTitle(view.getTitle());
    }

    @Override
    protected void webViewClientReceiveDataError() {

    }

    @Override
    protected void webViewStartLoadPage(String url) {
        mActionBar.setTitle("加载中...");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent().inject(this);

        Bundle arguments = getIntent().getExtras();

        mBlog = (BlogList.Blog) arguments.getSerializable(BLOG_URL);
        mFromType = arguments.getInt(FROM_TYPE);

        mWebView.loadUrl(mBlog.getCanonical().get(0).getHref());

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        mActionBar = getSupportActionBar();
        mActionBar.setTitle(mBlog.getTitle());
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blog_action, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mFromType == FROM_FAV) {
            menu.findItem(R.id.action_fav).setTitle("取消收藏");
            menu.findItem(R.id.action_mark_unread).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_fav:
                if (!isFaved()) {
                    mBlogDetailPresenter.markAsStared(mBlog.getId());
                } else {
                    mBlogDetailPresenter.markAsUnstared(mBlog.getId());
                }
                break;
            case R.id.action_mark_unread:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public BlogDetailComponent getComponent() {
        return DaggerBlogDetailComponent.builder()
                .applicationComponent(MyApplication.get(this).getComponent())
                .blogModule(new BlogModule())
                .build();
    }

    @Override
    public void starSuccess() {
        Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void startFailed() {
        Toast.makeText(this, "收藏失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void unstarSuccess() {
        Toast.makeText(this, "取消收藏成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void unstartFailed() {
        Toast.makeText(this, "取消收藏失败", Toast.LENGTH_LONG).show();
    }

    private boolean isFaved() {
        return mFromType == FROM_FAV;
    }
}
