package com.richardliu.jesmvp.base.bean;

/**
 * Created by Allen on 2017/6/9.
 */

public class BaseResponse {
    protected int total;//加载更多使用的页数
    protected int code;
    protected String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
