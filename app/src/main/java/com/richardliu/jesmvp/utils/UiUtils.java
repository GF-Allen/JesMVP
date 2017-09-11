package com.richardliu.jesmvp.utils;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by allen on 2017/4/12.
 */

public class UiUtils {
    /**
     * 获取状态栏的高度
     *
     * @param context 上下文对象
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            throw new NullPointerException();
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取Activity的根布局的View
     *
     * @param context 当前Activity
     * @return 返回根布局的View
     */
    public static View getRootView(Activity context) {
        return ((ViewGroup) (context.findViewById(android.R.id.content)));
    }

    /**
     * 获取View的高度
     *
     * @param context
     * @param view
     * @return
     */
    public static ViewGroup.LayoutParams getViewHeight(Context context, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = getStatusBarHeight(context);
        return layoutParams;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽高
     */
    public static DisplayMetrics getScreen(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        try {
            activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        } catch (Exception e) {
            return outMetrics;
        }
        return outMetrics;
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    /**
     * 添加过渡动画，ViewGroup增加和删除View
     *
     * @param view
     */
    public static void addTransitionAnim(ViewGroup view) {
        LayoutTransition transition = new LayoutTransition();
        transition.setDuration(LayoutTransition.APPEARING, 200);
        transition.setStartDelay(LayoutTransition.APPEARING, 0);
        transition.setAnimator(LayoutTransition.APPEARING, transition.getAnimator(LayoutTransition.APPEARING));
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 0);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
        transition.setStartDelay(LayoutTransition.DISAPPEARING, 0);
        transition.setAnimator(LayoutTransition.DISAPPEARING, transition.getAnimator(LayoutTransition.DISAPPEARING));
        transition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
        view.setLayoutTransition(transition);
    }

}
