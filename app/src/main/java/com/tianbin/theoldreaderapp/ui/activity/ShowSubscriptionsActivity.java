package com.tianbin.theoldreaderapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tianbin.theoldreaderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowSubscriptionsActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ShowSubscriptionsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subscriptions);
        ButterKnife.bind(this);

        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_news_list:

                                break;
                            case R.id.action_subscription_list:

                                break;
                            case R.id.action_fav_list:

                                break;
                            case R.id.action_profile:

                                break;
                        }
                        return false;
                    }
                });
    }
}
