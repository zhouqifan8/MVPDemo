package com.mvpdemo.mvpdemo.module.main;

import com.mvpdemo.mvpdemo.Common;
import com.mvpdemo.mvpdemo.base.BasePresenter;
import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.base.netserver.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.functions.Action0;

public class MainPresenter extends BasePresenter<IMainView> {
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    public void loadData() {
        Map<String, String> map = new HashMap();
        map.put("appid", Common.APPid);
        map.put("appkey", Common.appkey);
        map.put("session_mid", "SI20190605181138S322");
        map.put("session_mtoken", "ST201906051811387jMs");
        RetrofitUtil.GetServerTime(map).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribe(new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                iMainView.setData(baseResponse);
            }
        });

    }


}
