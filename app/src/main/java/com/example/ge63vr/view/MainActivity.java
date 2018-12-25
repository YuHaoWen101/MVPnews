package com.example.ge63vr.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ge63vr.R;
import com.example.ge63vr.model.Data;
import com.example.ge63vr.model.MVPContrat;
import com.example.ge63vr.presenter.Presenter;
import com.example.ge63vr.presenter.RecyclerviewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MVPContrat.View {
    RecyclerView recyclerView;
    Presenter presenter = new Presenter(this);
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerviewAdapter recyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    Data data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.getNews();
        initId();
        initView();
    }
    private void initId(){
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout = findViewById(R.id.base_swiperefresh);
    }
    private void initView(){
            swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
            linearLayoutManager = new LinearLayoutManager(this);
            swipeRefreshLayout.setOnRefreshListener(() -> {
                presenter.refresh();
                Toast.makeText(this,"刷新成功",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            });
            recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int currentPage) {
                    //加载更多数据
                    Toast.makeText(MainActivity.this,"上拉加载中",Toast.LENGTH_SHORT).show();
                    presenter.loadMore(data.getDate());
                    recyclerAdapter.addMoreStories((ArrayList<Data.StoriesBean>) data.getStories(),data.getDate());
                }
            });
            recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onError() {
        Toast.makeText(this,"请求失败了，你是没网了咩？？",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate(Data data) {
        if (this.data == null) {
            this.data = data;
            recyclerAdapter = new RecyclerviewAdapter(this, data);
            recyclerView.setAdapter(recyclerAdapter);
        }else{
            this.data = data;
        }
    }
    public void clearData(){
        data = null;
}

}
