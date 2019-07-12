package com.mvpdemo.mvpdemo.base.netserver;

import android.util.Log;

import com.mvpdemo.mvpdemo.Common;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

public class InterceptorUtil {


    /**
     * 增加header,添加公共请求参数
     */

    public static final Interceptor Addheader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("appid", Common.APPid)
                        .addHeader("appkey", Common.appkey)
                        .build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 打印url
     */

    public static final Interceptor UrlLog() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request();
                Buffer buffer = new Buffer();
                if (request.body() != null) {
                    request.body().writeTo(buffer);
                } else {
                    Logger.d("RxRetrofit", "request.body()==null");
                }
                //打印url
                Logger.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(), buffer) : ""));
                final Response response = chain.proceed(request);
                return response;
            }
        };

    }

    private static final String _parseParams(RequestBody body, Buffer buffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(buffer.readUtf8(), "UTF-8");
        }
        return "null";
    }

    /**
     * 日志输出
     */
    public static final HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("RxRetrofit", "Retrofit====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
