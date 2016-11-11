package com.tianbin.theoldreaderapp.di.component;

import com.tianbin.theoldreaderapp.PerActivity;
import com.tianbin.theoldreaderapp.di.module.AccountModule;
import com.tianbin.theoldreaderapp.di.module.ActivityModule;
import com.tianbin.theoldreaderapp.ui.module.main.LoginActivity;

import dagger.Component;

/**
 * class des
 * Created by tianbin on 16/11/11.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, AccountModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}
