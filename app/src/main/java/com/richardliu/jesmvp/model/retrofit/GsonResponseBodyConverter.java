package com.richardliu.jesmvp.model.retrofit;


import com.google.gson.Gson;
import com.richardliu.jesmvp.base.bean.BaseResponse;
import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.constants.Constants;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Gson解析器 根据后台返回结果是否为10000
 * Created by Allen on 2017/6/9.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        //处理不正常返回结果的情况
        BaseResponse httpResult = gson.fromJson(response, BaseResponse.class);
        if (httpResult.getCode() == Constants.NET_CODE_SUCCESS) {
            return gson.fromJson(response, type);
        } else {
            //抛一个自定义ResultException 传入失败时候的状态码，和信息
            JesResponse jesResponse = new JesResponse();
            jesResponse.setMsg(httpResult.getMsg());
            jesResponse.setCode(httpResult.getCode());
            return (T) jesResponse;
        }
    }
}
