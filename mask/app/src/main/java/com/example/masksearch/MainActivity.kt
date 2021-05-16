package com.example.masksearch

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeAdapter = StoreAdapter()

        findViewById<RecyclerView>(R.id.rv_store).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = storeAdapter
        }

        viewModel.apply {
            itemLivaData.observe(this@MainActivity, Observer {
                storeAdapter.updateItems(it)
            })
            loadingLiveData.observe(this@MainActivity, Observer { isLoading ->
                    findViewById<View>(R.id.progressBar).visibility = if(isLoading) View.VISIBLE else View.GONE
            })
        }
    }
}