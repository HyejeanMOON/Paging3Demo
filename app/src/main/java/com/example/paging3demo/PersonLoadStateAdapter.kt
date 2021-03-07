package com.example.paging3demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.LoadStateAdapter
import com.example.paging3demo.databinding.LoadStateFooterViewItemBinding

class PersonLoadStateAdapter(private val context: Context) :
    LoadStateAdapter<PersonLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        when(loadState){
            is LoadState.Loading -> {
                holder.binding.progressBar.visibility = View.VISIBLE
                holder.binding.errorMsg.visibility = View.INVISIBLE
                holder.binding.retryButton.visibility = View.INVISIBLE
            }
            is LoadState.NotLoading -> {
                holder.binding.progressBar.visibility = View.INVISIBLE
                holder.binding.errorMsg.visibility = View.INVISIBLE
                holder.binding.retryButton.visibility = View.INVISIBLE
            }
            is LoadState.Error -> {
                holder.binding.progressBar.visibility = View.INVISIBLE
                holder.binding.errorMsg.visibility = View.VISIBLE
                holder.binding.retryButton.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            LoadStateFooterViewItemBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    class ViewHolder(val binding: LoadStateFooterViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}