package com.mvpdemo.mvpdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mvpdemo.mvpdemo.R;
import com.mvpdemo.mvpdemo.widget.EmptyLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends Activity implements IBaseView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initViews();
        loadData();
    }

    protected abstract int attachLayoutRes();

    protected abstract P createPresenter();

    protected abstract void initViews();

    protected abstract void loadData();

    /**
     * 加载动画
     */
    @Override
    public void showLoading() {

    }

    /**
     * 隐藏动画、隐藏Error布局
     */
    @Override
    public void hide() {

    }

    /**
     * 无网络时 加载布局
     */
    @Override
    public void noNetError() {

    }

    /**
     * 服务器错误时 加载布局
     */
    @Override
    public void serverError() {

    }

    private Toast mToast;

    @Override
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
