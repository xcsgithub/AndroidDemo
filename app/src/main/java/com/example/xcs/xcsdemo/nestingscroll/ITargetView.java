package com.example.xcs.xcsdemo.nestingscroll;

/**
 * Created by Xcs on 2018-03-30.
 */

public interface ITargetView {
    boolean canChildScrollUp();

    void fling(float vy);
}
