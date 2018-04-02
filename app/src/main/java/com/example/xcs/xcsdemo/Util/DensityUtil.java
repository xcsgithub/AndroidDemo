package com.example.xcs.xcsdemo.Util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Xcs on 2018-03-30.
 */

public class DensityUtil {

    public static float sDensity = 0f;

    /**
     * DisplayMetrics
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 获取屏幕密度
     * @param context
     * @return
     */
    public static float getDensity(Context context){
        if (sDensity == 0f){
            sDensity = getDisplayMetrics(context).density;
        }
        return sDensity;
    }

    public static int dp2px(Context context, int dp){
        return (int)(getDensity(context) * dp + 0.5);
    }

    public static int px2dp(Context context, int px){
        return (int)(px/getDensity(context) + 0.5);
    }
}
