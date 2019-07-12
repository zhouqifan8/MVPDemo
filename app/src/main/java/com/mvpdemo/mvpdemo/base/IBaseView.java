package com.mvpdemo.mvpdemo.base;

public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载动画
     */
    void hide();

    /**
     * 无网络错误
     */
    void noNetError();


    /**
     * 服务器错误
     */
    void serverError();

    /**
     * Toast
     */
    void showToast(String msg);
}
