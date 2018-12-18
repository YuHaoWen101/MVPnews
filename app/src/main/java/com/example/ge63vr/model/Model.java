package com.example.ge63vr.model;


import retrofit2.Call;

public class Model implements MVPContrat.ModelInterface{
    @Override
    public Call getLatestNews() {
    Call<Data> build = RetrofitUtils.getInstance().getLatestBuild();
    return build;
    }

    @Override
    public Call getBeforeNews(String date) {
    Call<Data> build = RetrofitUtils.getInstance().getBeforeBuild(date);
    return build;
    }
}
