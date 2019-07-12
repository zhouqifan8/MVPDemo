package com.mvpdemo.mvpdemo.module.login;

import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.base.IBaseView;

public interface ILoginView extends IBaseView {
    String getUserName();

    String getPassword();

    void setData(BaseResponse loginResponse);

}
