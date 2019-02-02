package com.example.ge63vr.view

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.example.ge63vr.R
import java.util.*

class StoryDetailActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    internal var id: Int = 0
    internal lateinit var instr: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)
        val intent = intent
        id = intent.getIntExtra("id", 9702689)
        instr = id.toString()
        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)
        webView = findViewById(R.id.base_web)
        //webView.loadUrl("http://news-at.zhihu.com//css//news_qa.auto.css?v=4b3e3");
        webView.loadUrl("http://daily.zhihu.com/story/$instr")

    }

}