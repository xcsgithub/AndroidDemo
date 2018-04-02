package com.example.xcs.xcsdemo.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.rx.adapter.DrunbiAdapter;
import com.example.xcs.xcsdemo.rx.api.NetWork;
import com.example.xcs.xcsdemo.rx.model.DrunbiImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Xcs on 2018-04-02.
 */

public class ElementaryFragment extends BaseFragment {

    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView griRv;

    DrunbiAdapter drunbiAdapter = new DrunbiAdapter();

    @OnCheckedChanged({R.id.searchRb1,R.id.searchRb2,R.id.searchRb3,R.id.searchRb4})
    void onTagChecked(RadioButton searchRb, boolean checked){
        if (checked){
            unSubscribe();
            drunbiAdapter.setImages(null);
            swipeRefreshLayout.setRefreshing(true);
            search(searchRb.getText().toString());
        }
    }

    private void search(String key){
        disposable = NetWork.getDrunbiApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<DrunbiImage>>() {
                    @Override
                    public void accept(List<DrunbiImage> drunbiImages) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        drunbiAdapter.setImages(drunbiImages);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elmentary,container,false);
        ButterKnife.bind(this,view);

        griRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        griRv.setAdapter(drunbiAdapter);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_elementary;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
