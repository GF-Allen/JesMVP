package com.richardliu.jesmvp.model.netapi;

import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 通用接口
 * Created by Allen on 2017/4/14.
 */

public interface JesApi {

    @FormUrlEncoded
    @POST("/login")
    Observable<JesResponse<User>> login(@Field("username") String userName, @Field("password") String password);

}
