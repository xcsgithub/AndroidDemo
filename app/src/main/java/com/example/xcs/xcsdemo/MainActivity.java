package com.example.xcs.xcsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xcs.xcsdemo.eventbus.EventBusTestActivity;
import com.example.xcs.xcsdemo.eventbus.MessageEvent;
import com.example.xcs.xcsdemo.recycleview.RecycleScrollToActivity;
import com.example.xcs.xcsdemo.recycleview.staggeredGrid.StaggeredLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by Xcs on 2018-03-14.
 */

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.btn_event)
    Button btnEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    public void onClick(View view){
        startActivity(new Intent(this, StaggeredLayoutActivity.class));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
      //  Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(new MessageEvent("hello ,event"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
