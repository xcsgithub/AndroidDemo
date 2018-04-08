package com.example.xcs.xcsdemo.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.Util.GankBeautyResultToItemsMapper;
import com.example.xcs.xcsdemo.rx.adapter.ItemListAdapter;
import com.example.xcs.xcsdemo.rx.api.NetWork;
import com.example.xcs.xcsdemo.rx.model.DrunbiImage;
import com.example.xcs.xcsdemo.rx.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Xcs on 2018-04-08.
 */

public class ZipFragment extends BaseFragment {

    @BindView(R.id.gridRv)
    RecyclerView gridRv;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    ItemListAdapter adapter = new ItemListAdapter();

    @OnClick(R.id.zipLoadBt)
    void load(){
        swipeRefreshLayout.setRefreshing(true);
        unSubscribe();
        disposable = Observable.zip(NetWork.getGankApi().getBeauties(200, 1).map(GankBeautyResultToItemsMapper.getInstance()),
                NetWork.getDrunbiApi().search("装逼"),
                new BiFunction<List<Item>, List<DrunbiImage>, List<Item>>() {
                    @Override
                    public List<Item> apply(List<Item> items, List<DrunbiImage> drunbiImages) throws Exception {
                        List<Item> gankItems = new ArrayList<>();
                        for (int i = 0; i< items.size() / 2 && i < drunbiImages.size(); i++){
                            gankItems.add(items.get(i * 2));
                            gankItems.add(items.get(i * 2 + 1));
                            Item zhuangbiItem = new Item();
                            DrunbiImage drunbiImage = drunbiImages.get(i);
                            zhuangbiItem.description = drunbiImage.description;
                            zhuangbiItem.imageUrl = drunbiImage.image_url;
                            gankItems.add(zhuangbiItem);
                        }
                        return gankItems;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.setItems(items);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip,container,false);
        ButterKnife.bind(this,view);

        gridRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    protected int getDialogRes() {
        return 0;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_zip;
    }
}
