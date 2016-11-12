package com.tianbin.theoldreaderapp.ui.module.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.presenter.blog.LastBlogListPresenter;

import javax.inject.Inject;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */
public class LastestBlogListFragment extends BlogListBaseFragment {

    @Inject
    LastBlogListPresenter mLastBlogListPresenter;

    @Override
    public BlogListContract.Presenter getPresenter() {
        return mLastBlogListPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(MainComponent.class).inject(this);
    }
}
