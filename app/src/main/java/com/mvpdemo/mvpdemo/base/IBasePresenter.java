package com.mvpdemo.mvpdemo.base;

public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定View
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();

    /**
     * 获取View
     */
    V getView();
}
