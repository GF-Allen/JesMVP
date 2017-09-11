package com.richardliu.jesmvp.base.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.richardliu.jesmvp.model.JesException;


/**
 * Created by allen on 2017/4/12.
 */

public interface IBaseView {

    void showMessage(@NonNull String message);

    void showMessage(@StringRes int messageId);

    void showLoading();

    void hideLoading();

    void showError(JesException e);

    /**
     * 获得Context
     */
    Context getContext();
}
