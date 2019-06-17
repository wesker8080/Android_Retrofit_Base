package com.elite.etl.base.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import io.reactivex.disposables.CompositeDisposable;

/***************************************
 * Author MR.ZHANG
 * Description .
 * Date:2016/6/3
 ***************************************/
public abstract class AbstractBaseActivity<T extends IView, VM extends AbstractViewMode<T>> extends AppCompatActivity implements IView {
    private final ViewModelHelper<T, VM> mViewModeHelper = new ViewModelHelper<>();
    public AbstractBaseActivity activity;
    protected CompositeDisposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.create(getVMClass());
        setContentView(tellMeLayout());
        mDisposable = new CompositeDisposable();

        activity = this;
        if (useButterknife()) {
            //ButterKnife.bind(this);
        }
        initView(savedInstanceState);
        setModelView((T) this);
    }

    public abstract int tellMeLayout();

    public abstract boolean useButterknife();

    public abstract Class<VM> getVMClass();

    public final VM getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public abstract void initView(Bundle savedInstanceState);

    public final void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModeHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModeHelper.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
        mViewModeHelper.onDestroy();
    }


    /**
     * startActivity
     */
    final public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     */
    final public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     */
    final public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     */
    final public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     */
    final public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     */
    final public void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 是否需要隐藏键盘
     *
     * @param v     控件
     * @param event 事件
     * @return false
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
