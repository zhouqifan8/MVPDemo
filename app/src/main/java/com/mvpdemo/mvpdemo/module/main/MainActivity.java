package com.mvpdemo.mvpdemo.module.main;

import android.util.Log;

import com.mvpdemo.mvpdemo.R;
import com.mvpdemo.mvpdemo.base.BaseActivity;
import com.mvpdemo.mvpdemo.base.BaseResponse;

public class MainActivity extends BaseActivity implements IMainView {
    private MainPresenter mainPresenter;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    protected void loadData() {
        mainPresenter.loadData();
    }

    @Override
    public void setData(BaseResponse baseResponse) {
        Log.d("99999999", baseResponse.getMsg() + "");
    }
}
