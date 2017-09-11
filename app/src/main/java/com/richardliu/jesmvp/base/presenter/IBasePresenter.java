package com.richardliu.jesmvp.base.presenter;


import com.richardliu.jesmvp.base.view.IBaseView;

/**
 *
 * Created by allen on 2017/4/11.
 */
public interface IBasePresenter<V extends IBaseView> {
    /**
     * 绑定View
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();

    void showErrorMessage(String error);
}
