package com.tianbin.theoldreaderapp.presenter.blog;

import android.support.annotation.NonNull;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;
import com.tianbin.theoldreaderapp.contract.blog.BlogListContract;
import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.data.module.BlogIdItemList;
import com.tianbin.theoldreaderapp.data.module.BlogList;
import com.tianbin.theoldreaderapp.data.rx.ResponseObserver;
import com.tianbin.theoldreaderapp.presenter.base.RxPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * BlogListBasePresenter
 * Created by tianbin on 16/11/12.
 */
public abstract class BlogListBasePresenter extends RxPresenter<BlogListContract.View> implements BlogListContract.Presenter {

    @Inject
    BlogApi mBlogApi;

    protected long mContinuation;

    public BlogListBasePresenter() {
        mContinuation = getTimeInSecond();
    }

    private long getTimeInSecond() {
        return Calendar.getInstance().getTimeInMillis() / 1000;
    }

    @Override
    public void fetchBlogs(final BlogListContract.FetchType type) {
        if (type == BlogListContract.FetchType.REFRESH) {
            mContinuation = getTimeInSecond();
        }

        Subscription fetchBlogsSubscription = getBlogItemIds()
                .subscribeOn(Schedulers.io())
                .map(new Func1<BlogIdItemList, List<String>>() {
                    @Override
                    public List<String> call(BlogIdItemList blogIdItemList) {
                        mContinuation = blogIdItemList.getContinuation();
                        AppLog.d(String.valueOf(mContinuation));
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
                        if (blogList.getItems() == null) {
                            blogList.setItems(new ArrayList<BlogList.Blog>());
                        }
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

        addSubscrebe(fetchBlogsSubscription);

    }

    protected abstract Observable<BlogIdItemList> getBlogItemIds();

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
        if (blogList.getItems() == null) {
            return;
        }
        List<BlogList.Blog> data = mView.getData();
        data.clear();
        data.addAll(blogList.getItems());
    }

}
