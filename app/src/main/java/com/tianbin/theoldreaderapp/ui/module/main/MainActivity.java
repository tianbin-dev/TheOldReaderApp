package com.tianbin.theoldreaderapp.ui.module.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tianbin.theoldreaderapp.R;
import com.tianbin.theoldreaderapp.ui.module.account.FavouriteFragment;
import com.tianbin.theoldreaderapp.ui.module.account.ProfileFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.NewsFragment;
import com.tianbin.theoldreaderapp.ui.module.subscription.SubscriptionsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

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
        switchMenu(NewsFragment.class.getName());
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
                return NewsFragment.class.getName();
            case R.id.action_subscription_list:
                return SubscriptionsFragment.class.getName();
            case R.id.action_fav_list:
                return FavouriteFragment.class.getName();
            case R.id.action_profile:
                return ProfileFragment.class.getName();
            default:
                return null;
        }
    }
}
