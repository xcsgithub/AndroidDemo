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
import com.example.xcs.xcsdemo.widget.MyDeleteListAdapter;
import com.example.xcs.xcsdemo.widget.MyDeleteListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Xcs on 2018-03-14.
 */

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.btn_event)
    Button btnEvent;

    private MyDeleteListView listView;

    private List<String> contentList = new ArrayList<>();

    private MyDeleteListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initList();
        listView = findViewById(R.id.my_list);
        listView.setOnDeleteListener(new MyDeleteListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        adapter =  new MyDeleteListAdapter(this,0,contentList);
        listView.setAdapter(adapter);
    }

    public void onClick(View view){
        startActivity(new Intent(this, StaggeredLayoutActivity.class));

    }
    private void initList() {
        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
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
