package com.example.ge63vr.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.example.ge63vr.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class StoryDetailActivity extends AppCompatActivity {
    private WebView webView;
    int id;
    String instr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 9702689);
        instr = String.valueOf(id);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        webView = findViewById(R.id.base_web);
        //webView.loadUrl("http://news-at.zhihu.com//css//news_qa.auto.css?v=4b3e3");
        webView.loadUrl("http://daily.zhihu.com/story/" + instr);
    }

}
