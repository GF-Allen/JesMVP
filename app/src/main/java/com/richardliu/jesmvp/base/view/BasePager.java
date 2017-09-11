package com.richardliu.jesmvp.base.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.richardliu.jesmvp.BuildConfig;
import com.richardliu.jesmvp.model.JesException;
import com.richardliu.jesmvp.utils.ToastUtils;
import com.richardliu.jesmvp.widget.dialog.LoadDialog;

import butterknife.ButterKnife;

/**
 * Created by Allen on 2017/7/21.
 */

public abstract class BasePager extends LinearLayout implements IBaseView {

    protected Context mContext;

    private LoadDialog mLoadDialog;

    public BasePager(Context context) {
        this(context,null);
    }

    public BasePager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BasePager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public View createView(@LayoutRes int layoutId) {
        View view = View.inflate(mContext, layoutId, this);
        ButterKnife.bind(this, view);
        return view;
    }

    public abstract void init();

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShortToast(message);
    }

    @Override
    public void showMessage(@StringRes int messageId) {
        ToastUtils.showShortToast(messageId);
    }

    @Override
    public void showLoading() {
        if (mLoadDialog == null) {
            mLoadDialog = new LoadDialog(mContext);
        }
        mLoadDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadDialog != null) {
            mLoadDialog.cancel();
        }
    }

    @Override
    public void showError(JesException e) {
        if (BuildConfig.DEBUG) {
            showMessage(e.getMessage());
        }else {
            showMessage("出现异常");
        }
    }
}
