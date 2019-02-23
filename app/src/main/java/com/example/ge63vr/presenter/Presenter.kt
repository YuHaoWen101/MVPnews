package com.example.ge63vr.presenter


import android.os.Handler
import android.os.Looper
import com.example.ge63vr.model.Data
import com.example.ge63vr.model.MVPContrat
import com.example.ge63vr.model.Model
import com.example.ge63vr.view.MainActivity
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.launch
import retrofit2.Call


class Presenter(private val mainActivity: MainActivity) : MVPContrat.Presenter {
    private val mModel = Model()
    val UI = HandlerContext(Handler(Looper.getMainLooper()), "UI")
    operator fun get(dataCall: Call<Data>?) {
        launch {
            val databean:Data =dataCall?.execute()!!.body()
            launch(UI) {
                mainActivity.onUpdate(databean)
            }
        }

        /*dataCall?.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                mainActivity.onUpdate(response.body())
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                mainActivity.onError()
            }
        })*/
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
