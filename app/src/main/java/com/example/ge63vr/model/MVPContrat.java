package com.example.ge63vr.model;

import retrofit2.Call;

public interface MVPContrat {
    interface Presenter {
        void getNews();
        void refresh();
        void loadMore(String date);
    }

    interface View {
        void onError();

        void onUpdate(Data data);

    }

    interface ModelInterface {
        Call getLatestNews();

        Call getBeforeNews(String date);
    }
}
