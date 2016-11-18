package com.tianbin.theoldreaderapp.ui.module.blog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.tianbin.theoldreaderapp.HasComponent;
import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.R;
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
public class BlogDetailActivity extends WebViewBaseActivity implements HasComponent<BlogDetailComponent>{

    private static final String BLOG_URL = "blog_url";

    @Inject
    BlogDetailPresenter mBlogDetailPresenter;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private BlogList.Blog mBlog;

    public static void start(Context context, BlogList.Blog blog) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BLOG_URL, blog);
        Intent intent = new Intent(context, BlogDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void webViewLoadPageFinished(WebView view, String url) {

    }

    @Override
    protected void webViewClientReceiveDataError() {

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

        mWebView.loadUrl(mBlog.getCanonical().get(0).getHref());

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(mBlog.getOrigin().getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blog_action, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_read).setTitle("标记为未读");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_fav:
                mBlogDetailPresenter.markAsStared(mBlog.getId());
                break;
            case R.id.action_read:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public BlogDetailComponent getComponent() {
        return DaggerBlogDetailComponent.builder()
                .applicationComponent(MyApplication.get(this).getComponent())
                .blogModule(new BlogModule())
                .build();
    }
}
