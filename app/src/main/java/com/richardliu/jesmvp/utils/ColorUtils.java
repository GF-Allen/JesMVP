package com.richardliu.jesmvp.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Allen on 2017/5/23.
 */

public class ColorUtils {
    public static int generateColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256 - 50) + 50,
                random.nextInt(256 - 50) + 50, random.nextInt(256 - 50) + 50);
    }
}
