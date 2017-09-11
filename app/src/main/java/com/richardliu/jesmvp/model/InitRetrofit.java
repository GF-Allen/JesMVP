package com.richardliu.jesmvp.model;


import com.richardliu.jesmvp.BuildConfig;
import com.richardliu.jesmvp.constants.Constants;
import com.richardliu.jesmvp.constants.Url;
import com.richardliu.jesmvp.model.netapi.JesApi;
import com.richardliu.jesmvp.model.retrofit.AddCookiesInterceptor;
import com.richardliu.jesmvp.model.retrofit.ResponseConverterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 初始化Retrofit
 * Created by allen on 2017/4/12.
 */

public class InitRetrofit {

    private final Retrofit client;

    public InitRetrofit() {

        client = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .addConverterFactory(ResponseConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
        newBuilder.interceptors().add(new AddCookiesInterceptor());
        //日志打印
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            newBuilder.addInterceptor(loggingInterceptor);
        }
        //设置缓存路径跟大小
        newBuilder.cache(new Cache(new File(Constants.NET_CATCH_DIR), Constants.NET_CATCH_SIZE_52428800));
        newBuilder.connectTimeout(Constants.NET_TIMEOUT_60, TimeUnit.SECONDS);
        newBuilder.readTimeout(Constants.NET_TIMEOUT_60, TimeUnit.SECONDS);
        newBuilder.writeTimeout(Constants.NET_TIMEOUT_120, TimeUnit.SECONDS);
        return newBuilder.build();
    }

    public JesApi getChinaJesApi() {
        return client.create(JesApi.class);
    }

}
