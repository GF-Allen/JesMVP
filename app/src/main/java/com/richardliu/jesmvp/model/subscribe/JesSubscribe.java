package com.richardliu.jesmvp.model.subscribe;


import com.richardliu.jesmvp.JesApp;
import com.richardliu.jesmvp.base.view.IBaseView;
import com.richardliu.jesmvp.constants.Constants;
import com.richardliu.jesmvp.model.JesException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 通用Subscribe
 * 统一处理异常
 * Created by Allen on 2017/4/14.
 */

public abstract class JesSubscribe<T> extends Subscriber<T> {

    private IBaseView mView;
    private boolean showLoading = true;//显示loading框

    public JesSubscribe(IBaseView view) {
        this.mView = view;
    }

    public JesSubscribe setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        return this;
    }

    @Override
    public void onStart() {
        if (showLoading) {
            mView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        if (showLoading) {
            mView.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        mView.hideLoading();
        JesException exception;
        if (e instanceof SocketTimeoutException) {
            exception = new JesException("连接超时", 100020);
        } else if (e instanceof ConnectException) {
            exception = new JesException("网络中断，请检查您的网络状态", 100021);
        } else if (e instanceof JesException) {
            exception = (JesException) e;
            handleJesException(exception);
            _onError((JesException) e);
        } else {
            exception = new JesException(e.getMessage(), 100050);
        }
        mView.showError(exception);
    }

    private void handleJesException(JesException exception) {
        switch (exception.getCode()){
            case Constants.NET_CODE_RE_LOGIN:
                mView.showMessage("登录过期，请重新登录");
                JesApp.getInstance().jumpLoginActivity();
                break;
        }
    }

    @Override
    public void onNext(T result) {
        _onSuccess(result);
    }

    public abstract void _onSuccess(T t);

    public void _onError(JesException e) {

    }

}
