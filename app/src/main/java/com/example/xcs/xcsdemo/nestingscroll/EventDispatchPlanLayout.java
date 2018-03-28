package com.example.xcs.xcsdemo.nestingscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Xcs on 2018-03-28.
 */

public class EventDispatchPlanLayout extends ViewGroup {
    private static final String TAG = "EventDispatchPlanLayout";
    private static final int INVALID_POINTER = -1;

    private int mHeaderViewId = 0;

    public EventDispatchPlanLayout(Context context) {
        super(context);
    }

    public EventDispatchPlanLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventDispatchPlanLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
