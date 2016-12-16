package com.tianbin.theoldreaderapp.ui.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * RecyclerViewLoadingMoreView
 * Created by tianbin on 16/12/16.
 */

public class RecyclerViewLoadingMoreView extends LoadMoreView{

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
