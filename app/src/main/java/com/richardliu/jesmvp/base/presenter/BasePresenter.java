package com.richardliu.jesmvp.base.presenter;

import android.support.annotation.NonNull;

import com.richardliu.jesmvp.base.view.IBaseView;

import rx.subscriptions.CompositeSubscription;

/**
 * 辅助presenter类，实现一些通用的方法（不涉及加载数据）
 */

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;
    //包装所有的订阅者，便于做生命周期的管理
    protected CompositeSubscription mSubscriptions;

    protected BasePresenter() {
        mSubscriptions = new CompositeSubscription();
    }

    protected int page = 1; //当前页
    protected int total = 1; //总页数

    /**
     * 分页加载初始化页数等
     */
    public void initPageAndTotal() {
        this.page = 1;
        this.total = 1;
    }

    @Override
    public void attachView(@NonNull V view) {
        this.mView = view;
    }

    @Override
    public void showErrorMessage(String error) {
        mView.showMessage(error);
    }

    @Override
    public void detachView() {
        unSubscribe();
        this.mView = null;
    }

    public void unSubscribe() {
        mSubscriptions.unsubscribe();
    }
}
