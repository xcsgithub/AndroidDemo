package com.example.xcs.xcsdemo.Util;

import com.example.xcs.xcsdemo.rx.model.GankBeauty;
import com.example.xcs.xcsdemo.rx.model.GankBeautyResult;
import com.example.xcs.xcsdemo.rx.model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by Xcs on 2018-04-08.
 */

public class GankBeautyResultToItemsMapper implements Function<GankBeautyResult,List<Item>> {

    private static GankBeautyResultToItemsMapper INSTANCE = new GankBeautyResultToItemsMapper();

    private GankBeautyResultToItemsMapper(){}

    public static GankBeautyResultToItemsMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public List<Item> apply(GankBeautyResult gankBeautyResult) throws Exception {
        List<GankBeauty> gankBeauties = gankBeautyResult.beauties;
        List<Item> items = new ArrayList<>(gankBeauties.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outPutFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (GankBeauty gankBeauty : gankBeauties){
            Item item = new Item();
            try {
                Date date = inputFormat.parse(gankBeauty.createdAt);
                item.description = outPutFormat.format(date);
            } catch (ParseException e){
                e.printStackTrace();
                item.description = "unknown date";
            }
            item.imageUrl = gankBeauty.url;
            items.add(item);
        }
        return items;
    }
}
