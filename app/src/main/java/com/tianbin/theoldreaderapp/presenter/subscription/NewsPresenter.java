package com.tianbin.theoldreaderapp.presenter.subscription;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;
import com.tianbin.theoldreaderapp.contract.subscription.NewsContract;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.net.SubscriptionDataSource;
import com.tianbin.theoldreaderapp.data.rx.ResponseObserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
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
        AppLog.d("fetch news --- " + type.toString());
        SubscriptionDataSource.getInstance()
                .getBlogList(mContinuation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<BlogList>() {

                    @Override
                    public void onError(Throwable e) {
                        mView.fetchNewsFailed(e);
                    }

                    @Override
                    public void onSuccess(BlogList blogList) {
                        AppLog.d("fetch news success --- " + type.toString());
                        mContinuation = blogList.getContinuation();
                        switch (type) {
                            case INIT:
                                mView.fetchNewsSuccess(blogList.getItems());
                                break;
                            case REFRESH:
                                appendNewData(blogList);
                                mView.pullDownRefreshSuccess();
                                break;
                            case LOAD_MORE:
                                AppLog.e("continuation --- " + mContinuation);
                                if (mContinuation != 0) {
                                    mView.loadMoreNewsSuccess(blogList.getItems());
                                } else {
                                    mView.loadMoreNewsCompleted();
                                }
                                break;
                        }
                    }
                });
    }

    private void appendNewData(BlogList blogList) {
        List<BlogList.ItemEntity> data = mView.getData();
        List<BlogList.ItemEntity> items = blogList.getItems();

        List<BlogList.ItemEntity> newBlogItems = new ArrayList<>();
        for (BlogList.ItemEntity item : items) {
            if (!data.contains(item)) {
                newBlogItems.add(item);
            } else {
                break;
            }
        }
        data.addAll(newBlogItems);
    }
}
