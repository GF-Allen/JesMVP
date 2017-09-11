package com.richardliu.jesmvp.model.subscribe;


import com.richardliu.jesmvp.JesApp;
import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.base.view.IBaseView;
import com.richardliu.jesmvp.constants.Constants;
import com.richardliu.jesmvp.model.JesException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 返回 result为空的Subscribe
 * Created by Allen on 2017/5/2.
 */

public abstract class JesEmptySubscribe extends Subscriber<JesResponse> {

    private IBaseView mView;

    public JesEmptySubscribe(IBaseView view) {
        this.mView = view;
    }

    @Override
    public void onStart() {
        mView.showLoading();
    }

    @Override
    public void onCompleted() {
        mView.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        mView.hideLoading();
        JesException exception;
        if (e instanceof SocketTimeoutException) {
//            mView.showMessage("连接超时");
            exception = new JesException("连接超时", 100020);
        } else if (e instanceof ConnectException) {
            exception = new JesException("网络中断，请检查您的网络状态", 100021);
//            mView.showMessage("网络中断，请检查您的网络状态");
        } else if (e instanceof JesException) {
//            mView.showMessage(e.getMessage());
            exception = (JesException) e;
            _onError((JesException) e);
        } else {
            exception = new JesException(e.getMessage(), 100050);
//            mView.showMessage(e.getMessage());
        }
        mView.showError(exception);
    }

    @Override
    public void onNext(JesResponse result) {

        switch (result.getCode()) {
            case Constants.NET_CODE_SUCCESS:
                _onSuccess(result);
                break;
            case Constants.NET_CODE_INTERNAL_ERROR:
                mView.showMessage(result.getMsg());
                break;
            case Constants.NET_CODE_RE_LOGIN:
                mView.showMessage("登录过期，请重新登录");
                JesApp.getInstance().jumpLoginActivity();
                break;
            default:
                onError(new JesException(result.getMsg(), result.getCode()));
                break;
        }
    }

    public abstract void _onSuccess(JesResponse result);

    public void _onError(JesException e) {

    }

}
