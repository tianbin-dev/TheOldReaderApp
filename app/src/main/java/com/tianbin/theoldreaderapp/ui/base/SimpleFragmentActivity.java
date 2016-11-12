package com.tianbin.theoldreaderapp.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tianbin.theoldreaderapp.HasComponent;
import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.di.component.DaggerSimpleFragmentComponent;
import com.tianbin.theoldreaderapp.di.component.SimpleFragmentComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SimpleFragmentActivity extends AppCompatActivity implements HasComponent<SimpleFragmentComponent>{
    private static final String FRAGMENT_TITLE = "fragment_title";
    private static final String FRAGMENT_CLASS_NAME = "fragment_class_name";
    private static final String FRAGMENT_ARGUMENTS = "fragment_arguments";

    private OnPressBackListener mOnPressBackListener;

    public static Intent newIntent(Context context, String title, Class<? extends Fragment> clz, Bundle args) {
        Intent intent = new Intent(context, SimpleFragmentActivity.class);
        intent.putExtra(FRAGMENT_TITLE, title);
        intent.putExtra(FRAGMENT_CLASS_NAME, clz.getName());
        intent.putExtra(FRAGMENT_ARGUMENTS, args);
        return intent;
    }

    @BindView(R.id.ll_title_bar)
    LinearLayout mLlTitleBar;
    @BindView(R.id.tv_title_bar_text)
    TextView mTvTitleBarText;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_simple_fragment);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mUnbinder = ButterKnife.bind(this);

        initTitleBar(getIntent().getStringExtra(FRAGMENT_TITLE));
        initContentFragment(
                getIntent().getStringExtra(FRAGMENT_CLASS_NAME),
                getIntent().getBundleExtra(FRAGMENT_ARGUMENTS));
    }

    private void initTitleBar(String title) {
        if (title == null) {
            hideTitleBar();
        } else {
            setTitleText(getIntent().getStringExtra(FRAGMENT_TITLE));
        }
    }

    private void initContentFragment(String fragmentName, Bundle args) {
        Fragment fragment = Fragment.instantiate(this, fragmentName);
        if (fragment == null) {
            throw new IllegalArgumentException("Content fragment is null.");
        }

        fragment.setArguments(args);
        showContentFragment(fragment, fragmentName);
    }

    protected final void showContentFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment_container, fragment, tag)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @OnClick(R.id.fl_title_bar_back)
    void onClickBack() {
        onBackPressed();
    }

    public void hideTitleBar() {
        if (mLlTitleBar != null) {
            mLlTitleBar.setVisibility(View.GONE);
        }
    }

    public void setTitleText(String text) {
        mTvTitleBarText.setText(text);
    }


    public void unRegisterPressBackListener() {
        mOnPressBackListener = null;
    }

    public void registerPressBackListener(OnPressBackListener onPressBackListener) {
        mOnPressBackListener = onPressBackListener;
    }

    public interface OnPressBackListener {

        boolean onPressBack();
    }

    @Override
    public void onBackPressed() {
        if (mOnPressBackListener != null && mOnPressBackListener.onPressBack()) {
            return;
        }
        // super.super.onBackPressed();
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            supportFinishAfterTransition();
        }
    }

    @Override
    public SimpleFragmentComponent getComponent() {
        return DaggerSimpleFragmentComponent.builder()
                .applicationComponent(MyApplication.get(this).getComponent())
                .build();
    }
}
