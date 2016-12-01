package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
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
 * BlogDetailActivity
 * Created by tianbin on 16/11/4.
 */
public class BlogDetailActivity extends WebViewBaseActivity implements HasComponent<BlogDetailComponent>, BlogDetailContract.View {

    public static final int FROM_FAV = 1;
    public static final int FROM_OTHER = 2;

    private static final String BLOG_URL = "blog_url";
    private static final String FROM_TYPE = "from_type";

    private final int BLOG_READ_STATUS_IDLE = 0;
    private final int BLOG_READ_STATUS_UNREAD = 1;
    private final int BLOG_READ_STATUS_READ = 2;

    @Inject
    BlogDetailPresenter mBlogDetailPresenter;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private int mFromType;

    private ActionBar mActionBar;

    private BlogList.Blog mBlog;

    private int mBlogReadStatus = BLOG_READ_STATUS_IDLE;

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
        mNestedScrollView.scrollTo(0, 0);
    }

    @Override
    protected void webViewClientReceiveDataError() {

    }

    @Override
    protected void webViewStartLoadPage(String url) {
        mActionBar.setTitle(getString(R.string.loading));
    }

    @Override
    protected void webViewOnReceivedTitle(String title) {
        mActionBar.setTitle(title);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent().inject(this);

        mBlogDetailPresenter.attachView(this);

        Bundle arguments = getIntent().getExtras();

        mBlog = (BlogList.Blog) arguments.getSerializable(BLOG_URL);
        mFromType = arguments.getInt(FROM_TYPE);

        mWebView.loadUrl(mBlog.getCanonical().get(0).getHref());

        initToolbar();

        if (!isFaved()) {
            mBlogDetailPresenter.markAsRead(mBlog.getId());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBlogDetailPresenter != null) {
            mBlogDetailPresenter.detachView();
            mBlogDetailPresenter = null;
        }
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
        MenuItem markAsUnReadMenu = menu.findItem(R.id.action_mark_unread);
        if (mFromType == FROM_FAV) {
            menu.findItem(R.id.action_fav).setTitle(getString(R.string.cancel_fav));
            markAsUnReadMenu.setVisible(false);
        } else {
            if (isMarkedAsRead()) {
                markAsUnReadMenu.setTitle(getString(R.string.mark_as_unread));
            } else {
                markAsUnReadMenu.setTitle(getString(R.string.mark_as_read));
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private boolean isMarkedAsRead() {
        return mBlogReadStatus == BLOG_READ_STATUS_READ;
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
                if (isMarkedAsRead()) {
                    mBlogDetailPresenter.markAsUnRead(mBlog.getId());
                } else {
                    mBlogDetailPresenter.markAsRead(mBlog.getId());
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
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
    public void actionSuccess(BlogDetailContract.ActionType actionType) {
        switch (actionType) {
            case READ:
                if (isNotAtIdle()) {
                    Toast.makeText(this, getString(R.string.mark_as_read_yet), Toast.LENGTH_LONG).show();
                }
                mBlogReadStatus = BLOG_READ_STATUS_READ;
                break;
            case UNREAD:
                mBlogReadStatus = BLOG_READ_STATUS_UNREAD;
                Toast.makeText(this, getString(R.string.mark_as_unread_yet), Toast.LENGTH_LONG).show();
                break;
            case STARED:
                Toast.makeText(this, getString(R.string.fav_success), Toast.LENGTH_LONG).show();
                break;
            case UNSTARED:
                Toast.makeText(this, getString(R.string.cancel_fav_success), Toast.LENGTH_LONG).show();
                break;
        }

    }

    private boolean isNotAtIdle() {
        return mBlogReadStatus != BLOG_READ_STATUS_IDLE;
    }

    @Override
    public void actionFailed(BlogDetailContract.ActionType actionType) {
        switch (actionType) {
            case READ:
                Toast.makeText(this, getString(R.string.mark_as_read_failed), Toast.LENGTH_LONG).show();
                break;
            case UNREAD:
                Toast.makeText(this, getString(R.string.mark_as_unread_failed), Toast.LENGTH_LONG).show();
                break;
            case STARED:
                Toast.makeText(this, getString(R.string.fav_failed), Toast.LENGTH_LONG).show();
                break;
            case UNSTARED:
                Toast.makeText(this, getString(R.string.cancel_fav_failed), Toast.LENGTH_LONG).show();
                break;
        }
    }

    private boolean isFaved() {
        return mFromType == FROM_FAV;
    }
}
