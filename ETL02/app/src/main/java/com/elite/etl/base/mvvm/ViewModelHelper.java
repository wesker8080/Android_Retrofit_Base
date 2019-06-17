package com.elite.etl.base.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/***************************************
 * Author MR.ZHANG
 * Description .
 * Date:2016/6/3
 ***************************************/
public class ViewModelHelper<T extends IView, VM extends AbstractViewMode<T>> {
    private VM mViewModel;

    public void create(@Nullable Class<VM> vmClass) {
        if (vmClass == null) {
            mViewModel = null;
            return;
        }
        try {
            mViewModel = vmClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setView(@NonNull T t) {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onBindView(t);
    }

    @NonNull
    public VM getViewModel() {
        if (null == mViewModel) {
            throw new IllegalStateException("ViewModel is not ready. Are you calling this method before Activity/Fragment onCreate?");
        }
        return mViewModel;
    }
    public void onCreate(Bundle savedInstanceState) {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onCreate(savedInstanceState);
    }

    public void onStart() {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onStart();
    }

    public void onResume() {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onResume();
    }

    public void onPause() {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onPause();
    }

    public void onStop() {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onStop();
    }

    public void onDestroy() {
        if (mViewModel == null) {
            //no viewModel for this activity
            return;
        }
        mViewModel.onDestroy();
    }
}
