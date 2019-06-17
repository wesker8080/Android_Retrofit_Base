package com.elite.etl.base.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/***************************************
 * Author MR.ZHANG
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class AbstractViewMode<T extends IView> {
    private WeakReference<T> mView;

    public void onBindView(@NonNull T view) {
        mView = new WeakReference<T>(view);
    }

    public T getView() {
//        if (mView.get() == null) {
//            throw new NullPointerException("the view must be created first!");
//        }
        return mView.get();
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }
    protected  <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
