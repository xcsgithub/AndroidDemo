package com.example.xcs.xcsdemo.scroller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.xcs.xcsdemo.R;

/**
 * Created by Xcs on 2018-03-22.
 */

public class ScrollerActivity extends Activity {
    DirectScrollViewGroup viewGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        viewGroup = findViewById(R.id.custom);
    }

    public void move2Index(View view){
        viewGroup.move2Index(viewGroup.getCurrentIndex() + 1);
    }
}
