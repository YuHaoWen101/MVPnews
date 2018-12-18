package com.example.ge63vr.model;

import org.reactivestreams.Subscriber;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static final int DEFAULT_TIMEOUT = 5;
    private static RetrofitUtils retrofitUtils;
    private Call<Data> call;
    private Api api;
    private RetrofitUtils() {
        //设置超时时间
        OkHttpClient builder = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder)//
                .baseUrl("https://news-at.zhihu.com/")
                .build();
        api = retrofit.create(Api.class);
    }


    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public Call<Data> getLatestBuild() {
        call = api.getLatestNews();
        return call;
    }

    public Call<Data> getBeforeBuild(String date) {
        call = api.getBeforeNews(date);
        return call;
    }
}