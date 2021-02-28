package com.example.paging3demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.paging3demo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val adapter = PersonAdapter(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.personList.collectLatest {
                adapter.submitData(it)
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener {state ->
            when(state.refresh){
                is LoadState.Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                }
                is LoadState.NotLoading -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is LoadState.Error -> {
                    // show an error dialog.
                }
            }
        }

    }
}