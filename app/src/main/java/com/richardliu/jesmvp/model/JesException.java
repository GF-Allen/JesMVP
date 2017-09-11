package com.richardliu.jesmvp.model;

/**
 * Created by Allen on 2017/5/2.
 */

public class JesException extends Exception {
    private int code;

    public JesException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
