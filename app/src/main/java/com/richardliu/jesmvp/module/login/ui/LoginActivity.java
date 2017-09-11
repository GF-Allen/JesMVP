package com.richardliu.jesmvp.module.login.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.richardliu.jesmvp.MainActivity;
import com.richardliu.jesmvp.R;
import com.richardliu.jesmvp.base.view.BaseActivity;
import com.richardliu.jesmvp.model.SPFHelper;
import com.richardliu.jesmvp.module.login.LoginContract;
import com.richardliu.jesmvp.module.login.presenter.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginPresenter mLoginPresenter;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createView(R.layout.activity_login);
        hideToolBar();
        String loginName = new SPFHelper(this, "").getString("loginName", "");
        mEtUsername.setText(loginName);
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);
    }

    @Override
    public void loginSuccess() {
        showToastMessage("登录成功");
        startActivityNoValue(this, MainActivity.class);
        finish();
    }

    @Override
    public void loginFailed() {
        showToastMessage("登录失败");
    }

    @Override
    public void showUsernameError(String msg) {
        mEtUsername.requestFocus();
        showMessage(msg);
    }

    @Override
    public void showPasswordError(String msg) {
        mEtPassword.requestFocus();
        showMessage(msg);
    }

    public void login(View view) {
        hideKeyboard();
        String userName = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        mLoginPresenter.login(userName, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }
}
