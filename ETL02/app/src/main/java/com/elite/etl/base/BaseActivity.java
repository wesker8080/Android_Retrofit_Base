package com.elite.etl.base;


import android.app.ProgressDialog;
import android.os.Bundle;

import com.elite.etl.base.mvvm.AbstractBaseActivity;
import com.elite.etl.base.mvvm.AbstractViewMode;
import com.elite.etl.base.mvvm.IView;

import io.reactivex.disposables.CompositeDisposable;


/***************************************
 * Author MR.ZHANG
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class BaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseActivity<T, VM> {

    @Override
    public boolean useButterknife() {
        return true;
    }


    protected ProgressDialog progressDialog;

    @Override
    public void initView(Bundle savedInstanceState) {
    }


    /**
     * 重新登录
     */
    private void reLogin() {


    }

    /**
     * 退出登录
     */
    protected void logout() {

    }

    public void showDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public void dissMissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public CompositeDisposable getCompositeDisposable() {
        return mDisposable;
    }
}
