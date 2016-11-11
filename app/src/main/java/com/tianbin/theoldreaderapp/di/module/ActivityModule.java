package com.tianbin.theoldreaderapp.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.tianbin.theoldreaderapp.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    public ActivityModule(Fragment fragment) {
        mActivity = fragment.getActivity();
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
