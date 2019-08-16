package com.example.xcs.xcsdemo.callback;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.base.BaseActivity;

/**
 * @author RWX
 * @time 2019-05-21.
 */
public class FirstActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_first);
    }

    @Override
    public void initData() {

    }

    public void onButtonClick(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }
}
