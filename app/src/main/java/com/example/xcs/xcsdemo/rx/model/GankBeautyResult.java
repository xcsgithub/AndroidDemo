package com.example.xcs.xcsdemo.rx.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Xcs on 2018-04-08.
 */

public class GankBeautyResult {
    public boolean error;
    public @SerializedName("results") List<GankBeauty> beauties;
}
