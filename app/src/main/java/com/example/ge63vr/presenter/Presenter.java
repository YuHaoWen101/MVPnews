package com.example.ge63vr.presenter;

import com.example.ge63vr.model.Data;
import com.example.ge63vr.model.MVPContrat;
import com.example.ge63vr.model.Model;
import com.example.ge63vr.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements MVPContrat.Presenter {
    private MainActivity mainActivity;
    private Model mModel= new Model();

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void get(Call<Data> dataCall) {
        dataCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                mainActivity.onUpdate(response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                mainActivity.onError();
            }
        });
    }

    @Override
    public void getNews() {
        get(mModel.getLatestNews());
    }

    @Override
    public void refresh() {
    mainActivity.clearData();
    mModel.getLatestNews();
    }

    @Override
    public void loadMore(String date) {
        get(mModel.getBeforeNews(date));

    }

}
