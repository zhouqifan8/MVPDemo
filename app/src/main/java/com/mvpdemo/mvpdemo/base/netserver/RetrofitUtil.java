package com.mvpdemo.mvpdemo.base.netserver;

import com.mvpdemo.mvpdemo.Common;
import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.module.login.LoginResponse;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitUtil {
    private static final String BaseURL = Common.UURL;
    private static ApiServer apiServer;
    private static RetrofitUtil retrofitUtil;
    private final static int DEFAULT_TIME_OUT = 60;

    public static RetrofitUtil getInstence() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }

    private RetrofitUtil() {
        throw new AssertionError();
    }

    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.addInterceptor(InterceptorUtil.Addheader());//添加公共请求参数
        if (Common.isDebug) {
            builder.addInterceptor(InterceptorUtil.UrlLog());//打印url
            builder.addInterceptor(InterceptorUtil.getHttpLoggingInterceptor());//打印日志
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BaseURL)
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }


    public static Observable<BaseResponse> Login(Map<String, String> map) {
        return apiServer.Login(map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public static Observable<BaseResponse> GetServerTime(Map<String, String> map) {
        return apiServer.GetServerTime(map)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }


}
