package com.richardliu.jesmvp.model.retrofit;

import android.text.TextUtils;

import com.richardliu.jesmvp.model.AppBaseCache;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加token
 * Created by Allen on 2017/4/17.
 */

public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String token = AppBaseCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("token", token);
        }
        return chain.proceed(builder.build());
    }

}
