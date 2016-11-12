package com.tianbin.theoldreaderapp.di.module;

import com.tianbin.theoldreaderapp.data.api.BlogApi;
import com.tianbin.theoldreaderapp.data.net.BlogDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * class des
 * Created by tianbin on 16/11/11.
 */
@Module
public class BlogModule {

    @Provides
    BlogApi provideBlogApi(BlogDataSource blogDataSource) {
        return blogDataSource;
    }

}
