package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.data.module.SubscriptionList;
import com.tianbin.theoldreaderapp.ui.base.BaseFragment;
import com.tianbin.theoldreaderapp.ui.base.SimpleFragmentActivity;

import butterknife.BindView;

/**
 * class des
 * Created by tianbin on 16/11/12.
 */

public class SubscriptionDetailFragment extends BaseFragment {

    public static void start(Context context, SubscriptionList.Entity entity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ENTITY, entity);
        Intent intent = SimpleFragmentActivity.newIntent(context, entity.getTitle(), SubscriptionDetailFragment.class, bundle);
        context.startActivity(intent);
    }

    private static final String ENTITY = "entity";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SubscriptionList.Entity mEntity;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_subscription_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEntity = (SubscriptionList.Entity) getArguments().getSerializable(ENTITY);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
