package com.example.xcs.xcsdemo.canvas;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xcs on 2018-02-27.
 */

public class CanvasTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        List<UserData> userData = new ArrayList<>();
        for (int i = 0 ;i < 5; i++){
            UserData data = new UserData();
            data.setName("数据" + i);
            data.setValue((i + 1) * 10);
            userData.add(data);
        }

        PieView pieView = findViewById(R.id.pieView);
        pieView.setData(userData);

    }

    public void onClick(View view){
        EventBus.getDefault().post(new MessageEvent("hello ,event"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
