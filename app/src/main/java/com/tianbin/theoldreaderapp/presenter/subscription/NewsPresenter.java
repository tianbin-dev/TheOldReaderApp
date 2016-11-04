package com.tianbin.theoldreaderapp.presenter.subscription;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;
import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.net.SubscriptionDataSource;

import java.util.Calendar;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * news presenter
 * Created by tianbin on 16/11/3.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private final int limit = 20;

    private NewsContract.View mView;

    private long mContinuation;

    public NewsPresenter() {
        mContinuation = getTimeInSecond();
    }

    private long getTimeInSecond() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

    @Override
    public void attachView(NewsContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void fetchNews() {
        AppLog.d("fetch news");
        SubscriptionDataSource.getInstance()
                .getBlogList(getTimeInSecond())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BlogList>() {
                    @Override
                    public void call(BlogList blogList) {
                        mContinuation = blogList.getContinuation();
                        mView.fetchNewsSuccess(blogList.getItems());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.fetchNewsFailed(throwable);
                    }
                });
    }

    @Override
    public void loadMoreNews() {
        SubscriptionDataSource.getInstance()
                .getBlogList(mContinuation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BlogList>() {
                    @Override
                    public void call(BlogList blogList) {
                        AppLog.e("continuation --- " + blogList.getContinuation());
                        mContinuation = blogList.getContinuation();
                        if (mContinuation != 0) {
                            mView.loadMoreNewsSuccess(blogList.getItems());
                        } else {
                            mView.loadMoreNewsCompleted();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.loadMoreNewsFailed(throwable);
                    }
                });
    }
}
