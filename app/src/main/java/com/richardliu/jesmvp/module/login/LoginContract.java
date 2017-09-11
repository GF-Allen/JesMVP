package com.richardliu.jesmvp.module.login;


import com.richardliu.jesmvp.base.presenter.IBasePresenter;
import com.richardliu.jesmvp.base.view.IBaseView;

/**
 *
 * Created by allen on 2017/4/12.
 */

public class LoginContract {
    public interface View extends IBaseView {
        //登陆成功
        void loginSuccess();

        //登陆失败
        void loginFailed();

        void showUsernameError(String msg);

        void showPasswordError(String msg);
    }

    public interface Presenter<V extends IBaseView> extends IBasePresenter<V> {
        void login(String userName, String password);
    }
}
