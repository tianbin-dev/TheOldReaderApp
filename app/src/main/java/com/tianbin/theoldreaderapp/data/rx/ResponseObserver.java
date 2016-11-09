package com.tianbin.theoldreaderapp.data.rx;

import com.tianbin.theoldreaderapp.common.wrapper.AppLog;

import rx.Subscriber;

/**
 * response observer
 * Created by tianbin on 16/11/9.
 */
public abstract class ResponseObserver<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        AppLog.d("onCompleted");
    }

    @Override
    public void onNext(T t) {
        AppLog.d("onNext");
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
}