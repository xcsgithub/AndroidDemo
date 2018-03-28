package com.example.xcs.xcsdemo.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;

/**
 * Created by Xcs on 2018-03-22.
 */

public class AnimationScrollViewGroup extends DirectScrollViewGroup {

    protected Scroller mScroller;
    public AnimationScrollViewGroup(Context context) {
        super(context);
        initScroller();
    }

    public AnimationScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller();
    }

    public AnimationScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScroller();
    }

    private void initScroller(){
        mScroller = new Scroller(getContext());
    }

    @Override
    public void move2Index(int targetIndex) {
        if (!canMove2Index(targetIndex)){
            targetIndex = 0;
        }
        mScroller.startScroll(
                getScrollX(),getScrollY(),
                targetIndex * getWidth() - getScrollX(),getScrollY()
        );
        mCurrentIndex = targetIndex;
        invalidate();
    }

    public void stopMove(){
        if (!mScroller.isFinished()){
            int currentX = mScroller.getCurrX();
            int targetIndex = (currentX + getWidth()/2) /getWidth();
            mScroller.abortAnimation();
            this.scrollTo(targetIndex * getWidth(), 0);
            mCurrentIndex = targetIndex;
        }
    }

    @Override
    public void computeScroll() {
        boolean isNotFinished = mScroller.computeScrollOffset();
        if (isNotFinished){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
