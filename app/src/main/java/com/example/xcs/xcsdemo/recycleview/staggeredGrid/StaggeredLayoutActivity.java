package com.example.xcs.xcsdemo.recycleview.staggeredGrid;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.base.BaseActivity;
import com.example.xcs.xcsdemo.model.RetrofitServiceManager;
import com.example.xcs.xcsdemo.model.image.Image;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author RWX
 * @time 2019-04-11.
 */
public class StaggeredLayoutActivity extends BaseActivity {

    private int page =1;

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    StaggeredAdapter mAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);
            mAdapter = new StaggeredAdapter(this,null);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

        RetrofitServiceManager.getImageApiService().getImageCollections(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Image>>() {
                    @Override
                    public void onNext(List<Image> images) {
                      mAdapter.setImageList(images);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadImages(){

    }
}
