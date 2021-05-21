package com.example.taskapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import static android.content.Context.WINDOW_SERVICE;

public class ScreenUtils {
    public static long getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public static long getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }
}