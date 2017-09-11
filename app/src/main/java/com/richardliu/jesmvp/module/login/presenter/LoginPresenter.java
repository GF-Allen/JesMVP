package com.richardliu.jesmvp.module.login.presenter;

import android.text.TextUtils;

import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.base.presenter.BasePresenter;
import com.richardliu.jesmvp.bean.User;
import com.richardliu.jesmvp.model.AppBaseCache;
import com.richardliu.jesmvp.model.JesException;
import com.richardliu.jesmvp.model.SPFHelper;
import com.richardliu.jesmvp.model.subscribe.JesSubscribe;
import com.richardliu.jesmvp.module.login.contract.LoginContract;
import com.richardliu.jesmvp.module.login.model.LoginModel;

import rx.Subscription;

/**
 * Created by allen on 2017/4/12.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {

    private LoginModel mModel;

    public LoginPresenter() {
        mModel = LoginModel.getInstance();
    }

    @Override
    public void login(final String userName, final String password) {
        if (!verifyAccount(userName, password)) {
            return;
        }
        mView.showPasswordError("");
        Subscription mSubscribe = mModel.login(userName, password)
                .subscribe(new JesSubscribe<User>(mView) {
                    @Override
                    public void _onSuccess(JesResponse<User> result) {
                        AppBaseCache.getInstance().setUser(result.getResult());
                        SPFHelper spfHelper = new SPFHelper(mView.getContext(), "");
                        spfHelper.putString("loginName", userName);
                        mView.loginSuccess();
                    }

                    @Override
                    public void _onError(JesException e) {
                        mView.showMessage(e.getMessage());
                    }
                });
        mSubscriptions.add(mSubscribe);
    }

    /**
     * 简单验证下账户
     *
     * @param userName
     * @param password
     */
    private boolean verifyAccount(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            mView.showUsernameError("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            mView.showPasswordError("密码不能为空");
            return false;
        }
        return true;
    }
}
