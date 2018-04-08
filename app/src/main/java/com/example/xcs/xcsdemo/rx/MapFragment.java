package com.example.xcs.xcsdemo.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.Util.GankBeautyResultToItemsMapper;
import com.example.xcs.xcsdemo.rx.adapter.ItemListAdapter;
import com.example.xcs.xcsdemo.rx.api.NetWork;
import com.example.xcs.xcsdemo.rx.model.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Xcs on 2018-04-08.
 */

public class MapFragment extends BaseFragment{

    private int page = 0;
    @BindView(R.id.pageTv)
    TextView pageTve;
    @BindView(R.id.previousPageBt)
    Button previousPageBt;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    ItemListAdapter adapter = new ItemListAdapter();

    @OnClick(R.id.previousPageBt)
    void previousPage(){
        loadPage(--page);
        if (page == 1){
            previousPageBt.setEnabled(false);
        }
    }
    @OnClick(R.id.nextPageBt)
    void nextPage(){
        loadPage(++ page);
        if (page == 2){
            previousPageBt.setEnabled(true);
        }
    }

    private void loadPage(int page) {
        swipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        disposable = NetWork.getGankApi()
                .getBeauties(10,page)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        pageTve.setText(getString(R.string.page_with_number, MapFragment.this.page));
                        adapter.setItems(items);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),R.string.loading_failed,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this,view);

        gridRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return R.layout.dialog_map;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }
}
