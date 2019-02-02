package com.example.ge63vr.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitUtils private constructor() {
    private var call: Call<Data>? = null
    private val api: Apiservies

    val latestBuild: Call<Data>?
        get() {
            call = api.getLatestNews()
            return call
        }

    init {
        //设置超时时间
        val builder = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder)//
                .baseUrl("https://news-at.zhihu.com/")
                .build()
        api = retrofit.create(Apiservies::class.java)
    }

    fun getBeforeBuild(date: String?): Call<Data>? {
        call = api.getBeforeNews(date)
        return call
    }

    companion object {
        val instance: RetrofitUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitUtils() }
    }
}