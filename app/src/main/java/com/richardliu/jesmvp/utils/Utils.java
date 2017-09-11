package com.richardliu.jesmvp.utils;

import android.text.TextUtils;

import com.richardliu.jesmvp.model.AppBaseCache;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Allen on 2017/5/31.
 */

public class Utils {
    /**
     * 处理金额
     * @param num
     * @return
     */
    public static String handPrice(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }

    /**
     * 字符串为空时的默认值
     * @param str
     * @param defaultStr
     * @return
     */
    public static String isEmptyDefaultForString(String str, String defaultStr) {
        return TextUtils.isEmpty(str) ? defaultStr : str;
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Matcher m;
        boolean b;
        Pattern p1 = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$");  // 验证带区号的
        Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 邮件验证
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }

    /**
     * 金额验证
     */
    public static boolean isPrice(String str){
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        return match.matches();
    }

    /**
     * 页面权限
     * 是否拥有权限
     *
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {
        if (!TextUtils.isEmpty(AppBaseCache.getInstance().getRole())) {
            return AppBaseCache.getInstance().getRole().contains(role);
        }
        return false;
    }
}
