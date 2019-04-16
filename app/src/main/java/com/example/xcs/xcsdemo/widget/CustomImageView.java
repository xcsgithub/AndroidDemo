package com.example.xcs.xcsdemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author RWX
 * @time 2019-04-12.
 */
public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    private int mWidth;
    private int mHeight;

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setImageSize(int width,int height){
        this.mWidth = width;
        this.mHeight = height;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mWidth > 0 && mHeight > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            float scale = (float) mWidth / (float) mHeight;
            if (width > 0) {
                height = (int) (width / scale);
            }
            setMeasuredDimension(width, height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
