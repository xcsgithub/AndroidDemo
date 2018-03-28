package com.example.xcs.xcsdemo.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/**
 * Created by Xcs on 2018-03-22.
 */

public class TouchScrollViewGroup extends AnimationScrollViewGroup {

    private VelocityTracker mVelocityTracker;
    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;

    private float mLastMotionX;
    private int mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    private static final int VELOCITY_MIN = 600;

    public TouchScrollViewGroup(Context context) {
        super(context);
    }

    public TouchScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        if ((action == MotionEvent.ACTION_MOVE) && (mTouchState != TOUCH_STATE_REST)){
            return true;
        }

        final float x = ev.getX();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST : TOUCH_STATE_SROLLING;
                break;
            case MotionEvent.ACTION_MOVE:
                final int xDiff = (int) Math.abs(mLastMotionX - x);
                if (xDiff > mTouchSlop){
                    mTouchState = TOUCH_STATE_SROLLING;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return mTouchState != TOUCH_STATE_REST;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
           super.onTouchEvent(event);
        if (mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        final float eventX = event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            if(!mScroller.isFinished()){
                mScroller.abortAnimation();
            }
            mLastMotionX = eventX;
            break;
            case MotionEvent.ACTION_MOVE:
                //手指移动的距离
                int deltaX = (int)(eventX - mLastMotionX);
                //滚动内容，
                int targetScrollX = getScrollX() - deltaX;
                if (targetScrollX >= 0 &&
                        targetScrollX <= getWidth() * (CHILD_NUMBER -1)){
                    scrollTo(targetScrollX, 0);
                }
                mLastMotionX = eventX;
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float velocityX = mVelocityTracker.getXVelocity();
                if (velocityX > VELOCITY_MIN && canMove2Index(getCurrentIndex() -1)){
                    move2Index(getCurrentIndex() -1);
                }else if (velocityX < -VELOCITY_MIN && canMove2Index(getCurrentIndex() +1)){
                    move2Index(getCurrentIndex() +1);
                }else {
                    int targetIndex = (getScrollX() + getWidth() / 2)/ getWidth();
                    move2Index(targetIndex);
                }
                if (mVelocityTracker != null){
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                mTouchState = TOUCH_STATE_REST;
                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST;
                break;
        }
        return true;
    }
}
