package com.tianbin.theoldreaderapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.presenter.subscription.NewsPresenter;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;

/**
 * news fragment
 * Created by tianbin on 16/11/3.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View{

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsPresenter newsPresenter = new NewsPresenter();
        newsPresenter.attachView(this);

        newsPresenter.fetchNews();
    }
}
