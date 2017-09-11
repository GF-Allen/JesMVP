package com.richardliu.jesmvp.constants;

import android.os.Environment;

import java.io.File;

/**
 * Created by allen on 2017/4/12.
 */

public class Constants {

    public static final String PROJECT_NAME = "JesMVP";

    /**
     * 路径
     */
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + PROJECT_NAME + File.separator + "clue" + File.separator;

    /**
     * 网络缓存路径
     */
    public static final String NET_CATCH_DIR = BASE_PATH + File.separator + ".netcatch";

    /**
     * 数字常量把值包含在名字之中
     */
    public static final int NET_TIMEOUT_30 = 30;
    public static final int NET_TIMEOUT_60 = 60;
    public static final int NET_TIMEOUT_120 = 120;
    public static final int NET_TIMEOUT_600 = 600;

    /**
     * 网络缓存大小
     */
    public static final int NET_CATCH_SIZE_52428800 = 52428800;

    /**
     *常量
     */
    public static final String SESSION_ID = "Cookie";
    public static final String TOKEN = "token";

    public static final String LOGON_ACTION = "JESMVP_LOGIN_ACTION";

    public static final String SPF_NAME_USER = "UserInfo";
    public static final String SPF_NAME_COMMON = PROJECT_NAME;

    /**
     * 网络状态码
     */
    public static final int NET_CODE_SUCCESS = 10000;
    //重新登录
    public static final int NET_CODE_RE_LOGIN = 10089;
    //500
    public static final int NET_CODE_INTERNAL_ERROR = 10500;


}
