package com.elite.etl.viewmodel;

import android.util.Log;

import com.elite.etl.MainActivity;
import com.elite.etl.base.BaseVM;
import com.elite.etl.net.ResponseTransformer;
import com.elite.etl.net.RetrofitManager;
import com.elite.etl.net.exception.ApiException;

import io.reactivex.disposables.Disposable;

/**
 * @Author: Wesker
 * @Date: 2019-05-21 16:03
 * @Version: 1.0
 */
public class MainVM extends BaseVM<MainActivity> {
    public void getNews() {
        Disposable disposable = RetrofitManager.getRequest()
                .getNews("top", "17eb0f5a18fb991e8df45b310ee5f6c2")
                .compose(ResponseTransformer.handleResult())
                .compose(applySchedulers())
                .subscribe(onNext -> {
                    Log.d("wesker", " onNext : " + onNext.toString());
                    getView().showResult(onNext);
                }, onError -> {
                    // 处理异常
                    if (onError instanceof ApiException) {
                        ApiException apiException = (ApiException)onError;
                        Log.d("wesker", " onError : " + apiException.getDisplayMessage());
                    }
                });
        getView().getCompositeDisposable().add(disposable);
    }

}
