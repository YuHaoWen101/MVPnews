package com.example.ge63vr.model

import retrofit2.Call

interface Contract {
    interface Presenter {
        fun getNews()
        fun refresh()
        fun loadMore(date: String)
    }

    interface View {
        fun onError()

        fun onUpdate(data: Data)

    }

    interface ModelInterface {
        val latestNews: Call<*>

        fun getBeforeNews(date: String): Call<*>
    }
}