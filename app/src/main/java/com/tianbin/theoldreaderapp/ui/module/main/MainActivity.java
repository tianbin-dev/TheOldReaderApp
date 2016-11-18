package com.tianbin.theoldreaderapp.ui.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tianbin.theoldreaderapp.HasComponent;
import com.tianbin.theoldreaderapp.MyApplication;
import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.di.component.DaggerMainComponent;
import com.tianbin.theoldreaderapp.di.component.MainComponent;
import com.tianbin.theoldreaderapp.ui.module.account.FavouriteFragment;
import com.tianbin.theoldreaderapp.ui.module.account.ProfileFragment;
import com.tianbin.theoldreaderapp.ui.module.blog.LastestBlogListFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.SubscriptionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HasComponent<MainComponent>{

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private FragmentManager mFragmentManager = getSupportFragmentManager();

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavigationView();
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("最新");
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switchMenu(getFragmentName(item.getItemId()));
                        return true;
                    }
                });
        switchMenu(LastestBlogListFragment.class.getName());
    }

    private Fragment mCurrentFragment;

    private void switchMenu(String fragmentName) {

        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);

        if (fragment != null) {
            if (fragment == mCurrentFragment) return;

            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.content_frame, fragment, fragmentName).commit();
        }

        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }

        mCurrentFragment = fragment;
    }

    private String getFragmentName(int menuId) {
        switch (menuId) {
            case R.id.action_news_list:
                getSupportActionBar().setTitle("最新");
                return LastestBlogListFragment.class.getName();
            case R.id.action_subscription_list:
                getSupportActionBar().setTitle("全部");
                return SubscriptionFragment.class.getName();
            case R.id.action_fav_list:
                getSupportActionBar().setTitle("收藏");
                return FavouriteFragment.class.getName();
            case R.id.action_profile:
                getSupportActionBar().setTitle("我的");
                return ProfileFragment.class.getName();
            default:
                return null;
        }
    }

    @Override
    public MainComponent getComponent() {
        return DaggerMainComponent.builder()
                .applicationComponent(MyApplication.get(this).getComponent())
                .build();
    }
}
