package com.elite.etl.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.elite.etl.base.mvvm.AbstractBaseFragment;
import com.elite.etl.base.mvvm.AbstractViewMode;
import com.elite.etl.base.mvvm.IView;


/***************************************
 * Author MR.ZHANG
 * Description .
 * Date:2016/6/13
 ***************************************/
public abstract class BaseFragment<T extends IView, VM extends AbstractViewMode<T>> extends AbstractBaseFragment<T, VM> {
    private Context context;
    private Activity activity;


    protected ProgressDialog progressDialog;

    @Override
    public boolean useButterknife() {
        return true;
    }

    @Override
    protected void injectView(View mRootView) {
        //ButterKnife.bind(this, mRootView);
        context = getActivity();
        activity = getActivity();
        progressDialog = new ProgressDialog(activity);
    }

    /**
     * 重新登录
     */
    private void reLogin() {
        //readyGo(DialogQuitActivity.class);
    }

    public void showDialog(){
        if(progressDialog != null) {
            progressDialog.show();
        }
    }
    public void dissMissDialog(){
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
