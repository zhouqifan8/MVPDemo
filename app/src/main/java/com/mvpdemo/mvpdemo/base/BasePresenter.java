package com.mvpdemo.mvpdemo.base;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public V getView() {
        return mView;
    }
}
