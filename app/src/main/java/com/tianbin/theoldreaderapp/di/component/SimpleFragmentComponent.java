package com.tianbin.theoldreaderapp.di.component;

import com.tianbin.theoldreaderapp.PerActivity;
import com.tianbin.theoldreaderapp.di.module.BlogModule;
import com.tianbin.theoldreaderapp.ui.module.blog.SubscriptionBlogListFragment;

import dagger.Component;

/**
 * SimpleFragmentActivity Component
 * Created by tianbin on 16/11/12.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = BlogModule.class)
public interface SimpleFragmentComponent {

    void inject(SubscriptionBlogListFragment subscriptionBlogListFragment);
}
