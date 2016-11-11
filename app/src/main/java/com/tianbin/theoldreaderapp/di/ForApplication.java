package com.tianbin.theoldreaderapp.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * des
 * Created by tianbin on 16/11/11.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {
}
