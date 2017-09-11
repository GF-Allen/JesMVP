package com.richardliu.jesmvp.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.richardliu.jesmvp.JesApp;
import com.richardliu.jesmvp.bean.User;
import com.richardliu.jesmvp.constants.Constants;

import java.util.List;

/**
 * 基本参数缓存
 * Created by allen on 2017/4/12.
 */

public class AppBaseCache {

    private final String USER_ID = "USER_ID";
    private final String USER_NAME = "USER_NAME";
    private final String REAL_NAME = "REAL_NAME";
    private final String MOBILE = "MOBILE";
    private final String EMAIL = "EMAIL";
    private final String SESSION_ID = "SESSION_ID";
    private final String TOKEN = "TOKEN";
    private final String ROLE = "ROLE";//权限控制
    private final String DEPT = "DEPT";//部门

    private static AppBaseCache sAppBaseCache;
    private static SPFHelper spfHelper;

    private String userId;
    private String userName;
    private String realName;
    private String mobile;
    private String eMail;
    private String sessionId;
    private String role;
    private String dept;

    private String token;

    private AppBaseCache(Context context) {
        spfHelper = new SPFHelper(context, Constants.SPF_NAME_USER);
    }

    public static synchronized AppBaseCache getInstance() {
        if (sAppBaseCache == null) {
            sAppBaseCache = new AppBaseCache(JesApp.getInstance());
        }
        return sAppBaseCache;
    }

    /**
     * 清空AppBaseCache
     */
    public AppBaseCache resetAppBaseCache() {
        spfHelper.clearPreference();
        sAppBaseCache = new AppBaseCache(JesApp.getInstance());
        return sAppBaseCache;
    }

    public AppBaseCache setUser(User user) {
        setUserName(user.getLoginName())
                .setToken(user.getToken())
                .setRealName(user.getName())
                .setEmail(user.getEmail())
                .setMobile(user.getPhone())
                .setDept(user.getOffice())
                .setRole(user.getRole());
        return this;
    }

    /**
     * 更改preference的操作对象，替换成指定名字的
     *
     * @param context
     * @param preferenceName
     */
    public void choicePreference(Context context, String preferenceName) {
        synchronized (this) {
            spfHelper = new SPFHelper(context, preferenceName);
        }
    }

    public String getUserId() {
        if (TextUtils.isEmpty(userId)) {
            return spfHelper.getString(USER_ID, null);
        }
        return userId;
    }

    public AppBaseCache setUserId(String userId) {
        if (spfHelper.putString(USER_ID, userId)) {
            this.userId = userId;
        }
        return this;
    }

    public String getUserName() {
        if (TextUtils.isEmpty(userName)) {
            return spfHelper.getString(USER_NAME, null);
        }
        return userName;
    }

    public AppBaseCache setUserName(String userName) {
        if (spfHelper.putString(USER_NAME, userName)) {
            this.userName = userName;
        }
        return this;
    }

    public String getSessionId() {
        if (TextUtils.isEmpty(sessionId)) {
            return spfHelper.getString(SESSION_ID, "");
        }
        return sessionId;
    }

    public AppBaseCache setSessionId(String sessionId) {
        if (spfHelper.putString(SESSION_ID, sessionId)) {
            this.sessionId = sessionId;
        }
        return this;
    }

    public String getToken() {
        if (TextUtils.isEmpty(token)) {
            return spfHelper.getString(TOKEN, "");
        }
        return token;
    }

    public AppBaseCache setToken(String token) {
        if (spfHelper.putString(TOKEN, token)) {
            this.token = token;
        }
        return this;
    }

    public String getRealName() {
        if (TextUtils.isEmpty(realName)) {
            return spfHelper.getString(REAL_NAME, null);
        }
        return realName;
    }

    public AppBaseCache setRealName(String realName) {
        if (spfHelper.putString(REAL_NAME, realName)) {
            this.realName = realName;
        }
        return this;
    }

    public String getMobile() {
        if (TextUtils.isEmpty(mobile)) {
            return spfHelper.getString(MOBILE, null);
        }
        return mobile;
    }

    public AppBaseCache setMobile(String mobile) {
        if (spfHelper.putString(MOBILE, mobile)) {
            this.mobile = mobile;
        }
        return this;
    }

    public String geteMail() {
        if (TextUtils.isEmpty(eMail)) {
            return spfHelper.getString(EMAIL, null);
        }
        return eMail;
    }

    public AppBaseCache setEmail(String eMail) {
        if (spfHelper.putString(EMAIL, eMail)) {
            this.eMail = eMail;
        }
        return this;
    }

    public String getRole() {
        if (TextUtils.isEmpty(role)) {
            return spfHelper.getString(ROLE, null);
        }
        return role;
    }

    public AppBaseCache setRole(List<User.Role> role) {
        if (role != null) {
            String json = new Gson().toJson(role);
            if (spfHelper.putString(ROLE, json)) {
                this.role = json;
            }
        }
        return this;
    }

    public String getDept() {
        if (TextUtils.isEmpty(dept)) {
            return spfHelper.getString(DEPT, null);
        }
        return dept;
    }

    public AppBaseCache setDept(String dept) {
        if (spfHelper.putString(DEPT, dept)) {
            this.dept = dept;
        }
        return this;
    }
}
