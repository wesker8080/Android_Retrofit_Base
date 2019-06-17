package com.elite.etl;

import android.app.Application;
import android.content.Context;

import com.elite.etl.net.RetrofitManager;

import java.util.Objects;

/**
 * @Author: Wesker
 * @Date: 2019-05-21 14:45
 * @Version: 1.0
 */
public class EtlApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init();
        context = getApplicationContext();
    }
    public static Context getContext() {
        if (Objects.isNull(context)) {
            throw new NullPointerException();
        }
        return context;
    }
}
