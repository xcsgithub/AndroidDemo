package com.example.xcs.xcsdemo.recycleview.staggeredGrid;

import android.support.v7.widget.RecyclerView;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.base.BaseActivity;
import butterknife.BindView;

/**
 * @author RWX
 * @time 2019-04-11.
 */
public class StaggeredLayoutActivity extends BaseActivity {

    @BindView(R.id.recycle)
    RecyclerView recyclerView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_recycle);
    }

    @Override
    public void initData() {

    }
}
