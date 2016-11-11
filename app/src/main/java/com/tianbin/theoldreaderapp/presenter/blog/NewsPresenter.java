package com.tianbin.theoldreaderapp.presenter.blog;

import android.support.annotation.NonNull;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;
import com.tianbin.theoldreaderapp.contract.blog.NewsContract;
import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.rx.ResponseObserver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * news presenter
 * Created by tianbin on 16/11/3.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View mView;

    private long mContinuation;

    BlogApi mBlogApi;

    @Inject
    public NewsPresenter(BlogApi blogApi) {
        mBlogApi = blogApi;
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
    public void fetchAllBlog(final NewsContract.FetchType type) {
        AppLog.d("fetch all blog --- " + type.toString());
        mBlogApi.getBlogList(mContinuation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<BlogList>() {

                    @Override
                    public void onError(Throwable e) {
                        AppLog.d(e.toString());
                        mView.fetchNewsFailed(e);
                    }

                    @Override
                    public void onSuccess(BlogList blogList) {
                        AppLog.d("fetch all blog success --- " + type.toString());

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

    @Override
    public void fetchUnReadBlog(final NewsContract.FetchType type) {
        mBlogApi.getUnReadItemIds()
                .subscribeOn(Schedulers.io())
                .map(new Func1<BlogIdItemList, List<String>>() {
                    @Override
                    public List<String> call(BlogIdItemList blogIdItemList) {
                        return getBlogIdList(blogIdItemList);
                    }
                })
                .flatMap(new Func1<List<String>, Observable<BlogList>>() {
                    @Override
                    public Observable<BlogList> call(List<String> idList) {
                        return mBlogApi.getUnReadContents(idList);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<BlogList>() {
                    @Override
                    public void onSuccess(BlogList blogList) {
                        AppLog.d("fetch unread blog success");

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

                    @Override
                    public void onError(Throwable e) {
                        AppLog.d(e.toString());
                        mView.fetchNewsFailed(e);
                    }
                });

    }

    @NonNull
    private List<String> getBlogIdList(BlogIdItemList blogIdItemList) {
        List<BlogIdItemList.BlogIdItem> blogIdItems = blogIdItemList.getItemRefs();

        List<String> idList = new ArrayList<>();
        if (blogIdItems != null && blogIdItems.size() > 0) {
            for (BlogIdItemList.BlogIdItem blogIdItem : blogIdItems) {
                idList.add("tag:google.com,2005:reader/item/" + blogIdItem.getId());
            }
        }
        return idList;
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
