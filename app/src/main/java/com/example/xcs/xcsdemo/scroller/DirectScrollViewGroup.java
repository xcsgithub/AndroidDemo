package com.example.xcs.xcsdemo.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xcs.xcsdemo.R;

/**
 * Created by Xcs on 2018-03-22.
 */

public class DirectScrollViewGroup extends ViewGroup {
    public static final int CHILD_NUMBER = 6;
    protected int mCurrentIndex = 0;
    public DirectScrollViewGroup(Context context) {
        super(context);
        init();
    }

    public DirectScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DirectScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
         for (int i = 0 ; i< CHILD_NUMBER; i++){
             TextView textView = new TextView(getContext());
             int color = 0xff6666cc;
             switch (i % 3 ){
                 case 0:
                     color = 0xffcc6666;
                     break;
                 case 1:
                     color = 0xffcc6666;
                     break;
                 case 2:
                     color = 0xff6666cc;
                     break;
             }
             textView.setBackgroundColor(color);
             textView.setText("页面" + i);
             textView.setGravity(Gravity.CENTER);
             textView.setTextColor(getResources().getColor(R.color.colorAccent));

             addView(textView);
         }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        for (int i = 0; i< getChildCount(); i++){
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i< getChildCount(); i++){
            View childView = getChildAt(i);
            childView.layout(getWidth() * i, 0, getWidth() * (i +1),b -t);
        }
    }

    public void move2Index(int targetIndex){
        if (!canMove2Index(targetIndex)){
           targetIndex = 0;
        }

        scrollTo(targetIndex * getWidth(),getScrollY());
        mCurrentIndex = targetIndex;
        invalidate();
    }

    public boolean canMove2Index(int targetIndex){
        return targetIndex >=0 && targetIndex < CHILD_NUMBER;
    }

    public int getCurrentIndex(){
        return mCurrentIndex;
    }
}
