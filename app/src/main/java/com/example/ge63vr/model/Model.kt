package com.example.ge63vr.model

import retrofit2.Call

class Model : MVPContrat.ModelInterface {
    override fun getLatestNews(): Call<Data>? {
        return RetrofitUtils.instance?.latestBuild
    }

    override fun getBeforeNews(date: String?): Call<Data>? {
        return RetrofitUtils.instance?.getBeforeBuild(date)
    }
}