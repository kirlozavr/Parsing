package com.example.parsing.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.parsing.ParsingApplication
import com.example.parsing.R
import com.example.parsing.adapter.ImagesAdapter
import com.example.parsing.viewmodel.MainViewModel
import com.example.parsing.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var adapter: ImagesAdapter

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (application as ParsingApplication).component
    }

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        showImages()
    }

    private fun initView(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        refreshLayout = findViewById(R.id.refresh)
        refreshLayout.setOnRefreshListener {
            showImages()
            refreshLayout.isRefreshing = false
        }
    }

    private fun showImages(){
        viewModel.requestImages()
        viewModel.getImages().observe(this) {
            adapter.clearList()
            adapter.setList(it.toMutableList())
        }
    }
}