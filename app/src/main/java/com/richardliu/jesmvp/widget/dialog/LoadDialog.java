package com.richardliu.jesmvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.richardliu.jesmvp.R;
import com.richardliu.jesmvp.widget.loadmore.SwipeProgressBar;


/**
 * Created by Allen on 2017/4/22.
 */

public class LoadDialog extends Dialog {
    public LoadDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉头
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setContentView(R.layout.dialog_loading);

        SwipeProgressBar bar = (SwipeProgressBar) findViewById(R.id.SwipeProgressBar);
        bar.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
