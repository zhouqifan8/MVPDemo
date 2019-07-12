package com.mvpdemo.mvpdemo.base.netserver;

import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.module.login.LoginResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiServer {

    @FormUrlEncoded
    @POST("user/session/get")
    Observable<BaseResponse> Login(@FieldMap Map<String, String> paramsMap);

    @FormUrlEncoded
    @POST("communal/nowDate/get")
    Observable<BaseResponse> GetServerTime(@FieldMap Map<String, String> parmsMap);
}
