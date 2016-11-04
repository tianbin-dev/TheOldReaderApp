package com.tianbin.theoldreaderapp.presenter.subscription;

import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.net.SubscriptionDataSource;

import java.util.Calendar;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * news presenter
 * Created by tianbin on 16/11/3.
 */
public class NewsPresenter implements NewsContract.Presenter {

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
    public void fetchNews(final NewsContract.FetchType type) {
        SubscriptionDataSource.getInstance()
                .getBlogList(getTimeInSecond())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BlogList>() {
                    @Override
                    public void call(BlogList blogList) {
                        mContinuation = blogList.getContinuation();
                        switch (type) {
                            case INIT:
                                mView.fetchNewsSuccess(blogList.getItems());
                                break;
                            case REFRESH:
                                mView.pullDownRefreshSuccess(getNewData(blogList));
                                break;
                            case LOAD_MORE:
                                if (mContinuation != 0) {
                                    mView.loadMoreNewsSuccess(blogList.getItems());
                                } else {
                                    mView.loadMoreNewsCompleted();
                                }
                                break;
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.fetchNewsFailed(throwable);
                    }
                });
    }

    private List<BlogList.ItemsEntity> getNewData(BlogList blogList) {
        List<BlogList.ItemsEntity> data = mView.getData();
        // // TODO: 16/11/5  
        return null;
    }
}
