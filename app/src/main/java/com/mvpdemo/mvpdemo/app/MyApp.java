package com.mvpdemo.mvpdemo.app;

import android.app.Application;

import com.mvpdemo.mvpdemo.base.netserver.RetrofitUtil;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitUtil.init();//初始化网络
    }
}
