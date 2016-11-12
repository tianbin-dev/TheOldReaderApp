package com.tianbin.theoldreaderapp.ui.module.subscription;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
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

    private ItemTouchHelperCallback mCallback;
    private ItemTouchHelperExtension mItemTouchHelper;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEntity = (SubscriptionList.Entity) getArguments().getSerializable(ENTITY);
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }


    public class ItemTouchHelperCallback extends ItemTouchHelperExtension.Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.START);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //MainRecyclerAdapter.ItemBaseViewHolder holder = (MainRecyclerAdapter.ItemBaseViewHolder) viewHolder;
            //holder.mViewContent.setTranslationX(dX);
        }
    }

}
