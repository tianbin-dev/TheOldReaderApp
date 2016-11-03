package com.tianbin.theoldreaderapp;

import android.app.Application;

/**
 * Created by tianbin on 16/11/3.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize
        mInstance = this;
    }
}
