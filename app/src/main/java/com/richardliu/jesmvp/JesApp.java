package com.richardliu.jesmvp;

import android.app.Application;
import android.content.Intent;

import com.richardliu.jesmvp.constants.Constants;
import com.richardliu.jesmvp.model.InitRetrofit;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by allen on 2017/4/11.
 */

public class JesApp extends Application {

    private static JesApp sJesApp;
    private static InitRetrofit sInitRetrofit;

    public static JesApp getInstance() {
        return sJesApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        sJesApp = this;

        //日志打印
        KLog.init(BuildConfig.DEBUG, Constants.PROJECT_NAME);
    }

    public synchronized static InitRetrofit getRetrofitClient() {
        if (sInitRetrofit == null) {
            sInitRetrofit = new InitRetrofit();
        }
        return sInitRetrofit;
    }

    /**
     * 重建Retrofit
     */
    public synchronized static void resetRetrofitClient() {
        sInitRetrofit = new InitRetrofit();
    }

    /**
     * 跳转登录页
     */
    public void jumpLoginActivity() {
        Intent intent = new Intent(Constants.LOGON_ACTION);
        sendBroadcast(intent);
    }

    /**
     * 加密秘钥
     * @return
     */
    public String getDESKey() {
        return getString(R.string.DES_KEY);
    }
}
