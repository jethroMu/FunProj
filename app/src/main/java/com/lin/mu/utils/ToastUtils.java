package com.lin.mu.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Toast类，只能在主线程调用
 *
 */
public class ToastUtils {
    public static void showShortToast(Context context, CharSequence msg) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, int resId) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, CharSequence msg) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, int resId) {
        if (context == null) return;
        Toast.makeText(context.getApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }
}
