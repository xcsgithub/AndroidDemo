package com.example.xcs.xcsdemo.rx.api;

import com.example.xcs.xcsdemo.rx.model.DrunbiImage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Xcs on 2018-04-02.
 */

public interface DrunbiApi {
    @GET("search")
    Observable<List<DrunbiImage>> search(@Query("q") String query);
}
