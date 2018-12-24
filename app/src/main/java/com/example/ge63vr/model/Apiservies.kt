package com.example.ge63vr.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Apiservies {
    @GET("api/4/news/latest")
    abstract fun getLatestNews(): Call<Data>

    @GET("api/4/news/before/{date}")
    abstract fun getBeforeNews(@Path("date") dateString: String?): Call<Data>
}