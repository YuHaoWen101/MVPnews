package com.example.ge63vr.model;

import android.database.Observable;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("api/4/news/latest")
    Call<Data> getLatestNews();

    @GET("api/4/news/before/{date}")
    Call<Data> getBeforeNews(@Path("date") String dateString);
}
