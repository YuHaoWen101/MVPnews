package com.example.ge63vr.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.ge63vr.R
import com.example.ge63vr.model.Data
import com.example.ge63vr.model.MVPContrat
import com.example.ge63vr.model.StoriesBean
import com.example.ge63vr.presenter.Presenter
import com.example.ge63vr.presenter.RecyclerviewAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MVPContrat.View {
    private var recyclerView: RecyclerView? = null
    private var presenter = Presenter(this)
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var recyclerAdapter: RecyclerviewAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var data: Data? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getNews()
        initId()
        initView()
    }

    private fun initId() {
        recyclerView = findViewById(R.id.recycler)
        swipeRefreshLayout = findViewById(R.id.base_swiperefresh)
    }

    private fun initView() {
        swipeRefreshLayout?.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        linearLayoutManager = LinearLayoutManager(this)
        swipeRefreshLayout?.setOnRefreshListener {
            presenter.refresh()
            Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout?.isRefreshing = false
        }
        recyclerView?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(linearLayoutManager!!) {
            override fun onLoadMore(currentPage: Int) {
                //加载更多数据
                Toast.makeText(this@MainActivity, "上拉加载中", Toast.LENGTH_SHORT).show()
                presenter.loadMore(data?.date)
                recyclerAdapter?.addMoreStories(data!!.stories as ArrayList<StoriesBean>, data!!.date)
            }
        })
        recyclerView?.layoutManager = linearLayoutManager
    }

    override fun onError() {
        Toast.makeText(this, "请求失败了，你是没网了咩？？", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(data: Data) {
        if (this.data == null) {
            this.data = data
            recyclerAdapter = RecyclerviewAdapter(this, data)
            recyclerView?.adapter = recyclerAdapter
        } else {
            this.data = data
        }
    }

    fun clearData() {
        data = null
    }

}