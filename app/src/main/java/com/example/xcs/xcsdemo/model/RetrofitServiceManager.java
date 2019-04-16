package com.example.xcs.xcsdemo.model;

import com.example.xcs.xcsdemo.base.Constants;
import com.example.xcs.xcsdemo.data.remote.api.ImagesApi;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author RWX
 * @time 2018-10-30.
 */

public class RetrofitServiceManager {

    private static final int DEFAULT_TIME_OUT = 30;//超时时间 30s
    private static final int DEFAULT_READ_TIME_OUT = 30;

    private static OkHttpClient.Builder okHttpClientBuilder;
    private static Retrofit.Builder builder;
    private static Retrofit retrofit;

    private RetrofitServiceManager(String baseUrl) {
        okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);
        okHttpClientBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        okHttpClientBuilder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        okHttpClientBuilder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientBuilder.build());
        retrofit = builder.build();
    }

    private static class ImagesApiHolder{
        private static final ImagesApi INSTANCE = new RetrofitServiceManager(Constants.IMAGE_API).create(ImagesApi.class);
    }

    public static ImagesApi getImageApiService(){
        return ImagesApiHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
