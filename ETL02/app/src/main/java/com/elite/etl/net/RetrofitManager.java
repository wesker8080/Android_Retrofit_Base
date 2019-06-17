package com.elite.etl.net;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @Author: Wesker
 * @Date: 2019-05-22 10:10
 * @Version: 1.0
 */
public class RetrofitManager {
    private static Retrofit retrofit;
    private static volatile Request request = null;
    private RetrofitManager(){}
    public static RetrofitManager getInstance() {
        return RetrofitHolder.instance;
    }
    private static class RetrofitHolder {
        private static RetrofitManager instance = new RetrofitManager();
    }
    public void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Request.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new Retrofit2ConverterFactory())
                .build();
    }
    public static Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }
}
