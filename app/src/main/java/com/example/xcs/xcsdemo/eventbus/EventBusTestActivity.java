package com.example.xcs.xcsdemo.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.xcs.xcsdemo.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Xcs on 2018-02-27.
 */

public class EventBusTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_scroll);

    }

    public void onClick(View view){
        EventBus.getDefault().post(new MessageEvent("hello ,event"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
