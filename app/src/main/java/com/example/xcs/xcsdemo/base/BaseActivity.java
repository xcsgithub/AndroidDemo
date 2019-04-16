package com.example.xcs.xcsdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author RWX
 * @time 2019-04-11.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }
    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
