package com.richardliu.jesmvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.richardliu.jesmvp.constants.Constants;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * SharedPreferences帮助类
 * Created by allen on 2017/4/12.
 */

public class SPFHelper {
    //各类型的默认值
    private final Float DEFAULT_FLOAT = 0.0f;
    private final Integer DEFAULT_INTEGER = 0;
    private final Boolean DEFAULT_BOOLEAN = false;
    private final Long DEFAULT_LONG = 0L;
    private final String DEFAULT_STRING = "";

    private SharedPreferences preferences = null;

    /**
     * 获取Preference操作对象
     *
     * @param context
     * @param preferenceName 如果为NULL，则为默认，否则就是改preference的name
     */
    public SPFHelper(Context context, String preferenceName) {
        if (TextUtils.isEmpty(preferenceName)) {
            this.preferences = context.getSharedPreferences(Constants.SPF_NAME_COMMON, Context.MODE_PRIVATE);
        } else {
            this.preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        }
    }

    @NonNull
    public Integer getInt(@NonNull String key, Integer defaultValue) {
        if (preferences == null) {
            return DEFAULT_INTEGER;
        }
        return preferences.getInt(key, defaultValue);
    }

    @NonNull
    public Float getFloat(@NonNull String key, Float defaultValue) {
        if (preferences == null) {
            return DEFAULT_FLOAT;
        }
        return preferences.getFloat(key, defaultValue);
    }

    @NonNull
    public Long getLong(@NonNull String key, Long defaultValue) {
        if (preferences == null) {
            return DEFAULT_LONG;
        }
        return preferences.getLong(key, defaultValue);
    }


    @NonNull
    public Boolean getBoolean(@NonNull String key, Boolean defaultValue) {
        if (preferences == null) {
            return DEFAULT_BOOLEAN;
        }
        return preferences.getBoolean(key, defaultValue);
    }


    @NonNull
    public String getString(@NonNull String key, String defaultValue) {
        if (preferences == null) {
            return DEFAULT_STRING;
        }
        return preferences.getString(key, defaultValue);
    }

    @Nullable
    public Set<String> getStringSet(@NonNull String key, Set<String> defaultValue) {
        if (preferences == null) {
            return null;
        }
        return preferences.getStringSet(key, defaultValue);
    }

    @Nullable
    public <T> T getObject(@NonNull String key, @NonNull Class<T> classOfT) {
        if (preferences == null || preferences.getString(key, null) == null) {
            return null;
        }
        return new Gson().fromJson(preferences.getString(key, null), classOfT);
    }

    @Nullable
    public <T> T getObject(@NonNull String key, @NonNull Type type) {
        if (preferences == null || preferences.getString(key, null) == null) {
            return null;
        }
        return new Gson().fromJson(preferences.getString(key, null), type);
    }

    public boolean putInt(@NonNull String key, Integer value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putInt(key, value).commit();
    }

    public boolean putFloat(@NonNull String key, Float value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putFloat(key, value).commit();
    }

    public boolean putLong(@NonNull String key, Long value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putLong(key, value).commit();
    }

    public boolean putBoolean(@NonNull String key, Boolean value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putBoolean(key, value).commit();
    }

    public boolean putString(@NonNull String key, String value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putString(key, value).commit();
    }

    public boolean putStringSet(@NonNull String key, Set<String> value) {
        if (preferences == null) {
            return false;
        }
        SharedPreferences.Editor editor = preferences.edit();
        return editor.putStringSet(key, value).commit();
    }

    public <T> boolean putObject(@NonNull String key, @NonNull T object) {
        String value = new Gson().toJson(object);
        return putString(key, value);
    }

    /**
     * 清除
     */
    public void clearPreference() {
        preferences.edit().clear().apply();
    }
}
