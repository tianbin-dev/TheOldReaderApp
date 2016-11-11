package com.tianbin.theoldreaderapp;

import android.app.Application;
import android.content.Context;

import com.tianbin.theoldreaderapp.di.component.ApplicationComponent;
import com.tianbin.theoldreaderapp.di.component.DaggerApplicationComponent;
import com.tianbin.theoldreaderapp.di.module.ApplicationModule;

/**
 * application
 * Created by tianbin on 16/11/3.
 */
public class MyApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
