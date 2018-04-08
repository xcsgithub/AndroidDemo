package com.example.xcs.xcsdemo.rx.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Xcs on 2018-04-02.
 */

public class NetWork {
    private static DrunbiApi drunbiApi;
    private static GankApi gankApi;
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    public static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    public static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public static DrunbiApi getDrunbiApi(){
        if (drunbiApi == null){
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(httpLoggingInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            drunbiApi = retrofit.create(DrunbiApi.class);
        }
        return drunbiApi;
    }

    public static GankApi getGankApi(){
        if (gankApi == null) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(httpLoggingInterceptor);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }
}
