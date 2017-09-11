package com.richardliu.jesmvp.constants;


import com.richardliu.jesmvp.BuildConfig;

/**
 *
 * 统一接口目录地址
 * Created by allen on 2017/4/12.
 */

public class Url {
    public static final String BASE_URL_DEBUG = "测试地址";

    public static final String BASE_URL_PUBLIC = "生产环境地址";

    public static String BASE_URL;


    static {
        if (BuildConfig.DEBUG) {
            BASE_URL = BASE_URL_DEBUG;
        } else {
            BASE_URL = BASE_URL_PUBLIC;
        }
    }

}
