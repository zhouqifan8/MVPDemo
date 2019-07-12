package com.mvpdemo.mvpdemo.module.login;

import com.mvpdemo.mvpdemo.Common;
import com.mvpdemo.mvpdemo.base.BasePresenter;
import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.base.netserver.RetrofitUtil;
import com.mvpdemo.mvpdemo.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.functions.Action0;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private String nsername, password;

    public LoginPresenter() {

    }

    public void login() {
        nsername = mView.getUserName();
        password = mView.getPassword();
        if (nsername.isEmpty()) {
            mView.showToast("请输入账号");
            return;
        } else if (password.isEmpty()) {
            mView.showToast("请输入密码");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("appid", Common.APPid);
        map.put("appkey", Common.appkey);
        map.put("mobile", nsername);
        map.put("passwd", MD5Utils.getMd5(password));
        map.put("appos", "2");
        RetrofitUtil.Login(map).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribe(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showToast(e.getMessage());
            }

            @Override
            public void onNext(BaseResponse loginResponseBaseResponse) {
                mView.showToast(loginResponseBaseResponse.getMsg());
                if (loginResponseBaseResponse.getCode().equals("200")) {
                    mView.setData(loginResponseBaseResponse);
                }
            }
        });
    }
}
