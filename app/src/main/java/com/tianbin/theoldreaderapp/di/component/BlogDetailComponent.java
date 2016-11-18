package com.tianbin.theoldreaderapp.di.component;

import com.tianbin.theoldreaderapp.PerActivity;
import com.tianbin.theoldreaderapp.di.module.BlogModule;
import com.tianbin.theoldreaderapp.ui.module.blog.BlogDetailActivity;

import dagger.Component;

/**
 * BlogDetailComponent
 * Created by tianbin on 16/11/18.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {BlogModule.class})
public interface BlogDetailComponent {
    void inject(BlogDetailActivity blogDetailActivity);
}
