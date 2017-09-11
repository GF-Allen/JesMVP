package com.richardliu.jesmvp.module.login.model;


import com.richardliu.jesmvp.JesApp;
import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.bean.User;
import com.richardliu.jesmvp.utils.encrypt.Des;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by allen on 2017/4/12.
 */

public class LoginModel {

    private static LoginModel INSTANCE;

    private LoginModel() {
    }

    public static synchronized LoginModel getInstance() {
        if (INSTANCE == null) {
            synchronized (LoginModel.class) {
                INSTANCE = new LoginModel();
            }
        }
        return INSTANCE;
    }

    public Observable<JesResponse<User>> login(String userName, String password) {
        String encPassword = Des.EncryptAsDoNet(password, JesApp.getInstance().getDESKey());
        return JesApp.getRetrofitClient().getChinaJesApi().login(userName, encPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
