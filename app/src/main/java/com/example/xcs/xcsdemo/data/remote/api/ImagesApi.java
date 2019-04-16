package com.example.xcs.xcsdemo.data.remote.api;

import com.example.xcs.xcsdemo.model.image.Image;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author RWX
 * @time 2019-04-12.
 */
public interface ImagesApi {

    @Headers("Authorization:Client-ID 2c8e4ccd7c1f122003e3fde392cc930dd584bf3a46df66f42e4d9f5b1d274ade")
    @GET("photos/")
    Observable<List<Image>> getImageCollections(@Query("page")int page);
}
