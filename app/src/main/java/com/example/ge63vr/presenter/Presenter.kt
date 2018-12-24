package com.example.ge63vr.presenter

import com.example.ge63vr.model.Data
import com.example.ge63vr.model.MVPContrat
import com.example.ge63vr.model.Model
import com.example.ge63vr.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter(private val mainActivity: MainActivity) : MVPContrat.Presenter {
    private val mModel = Model()

    operator fun get(dataCall: Call<Data>?) {
        dataCall?.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                mainActivity.onUpdate(response.body())
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                mainActivity.onError()
            }
        })
    }

    override fun getNews() {
        get(mModel.latestNews)
    }

    override fun refresh() {
        mainActivity.clearData()
        mModel.latestNews
    }

    override fun loadMore(date: String?) {
        get(mModel.getBeforeNews(date))
    }

}