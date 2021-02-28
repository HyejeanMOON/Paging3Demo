package com.example.paging3demo

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3demo.databinding.RecyclerItemBinding

class PersonAdapter(private val context: Context) :
    PagingDataAdapter<Person, PersonAdapter.ViewHolder>(PersonDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = getItem(position)
        holder.binding.also {
            it.textViewName.text = person?.name
            it.textViewAge.text = person?.age.toString()
            if (position % 2 == 0) {
                Glide.with(context).load(person?.photoUrl).into(it.imageView)
            } else {
                Glide.with(context).load(R.drawable.ic_studio_icon).into(it.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    class ViewHolder(val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}