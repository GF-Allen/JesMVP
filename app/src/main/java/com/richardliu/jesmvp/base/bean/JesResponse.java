package com.richardliu.jesmvp.base.bean;

/**
 * Created by Allen on 2017/4/14.
 */

public class JesResponse<T> extends BaseResponse {
//    private int code;
//    private String msg;
    private T result;

//    public int getCode() {
//        return code;
//    }

//    public JesResponse setCode(int code) {
//        this.code = code;
//        return this;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public JesResponse setMsg(String msg) {
//        this.msg = msg;
//        return this;
//    }

    public T getResult() {
        return result;
    }

    public JesResponse setResult(T result) {
        this.result = result;
        return this;
    }
}
