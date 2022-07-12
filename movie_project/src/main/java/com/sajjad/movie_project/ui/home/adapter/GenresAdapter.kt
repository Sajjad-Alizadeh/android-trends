package com.sajjad.movie_project.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.movie_project.data.response.home.GenresListResponse.GenresListResponseItem
import com.sajjad.movie_project.databinding.ItemGenreBinding
import javax.inject.Inject

class GenresAdapter @Inject constructor() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private lateinit var binding: ItemGenreBinding
    private val differCallBack = object : DiffUtil.ItemCallback<GenresListResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GenresListResponseItem,
            newItem: GenresListResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GenresListResponseItem,
            newItem: GenresListResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(newList: List<GenresListResponseItem>) {
        differ.submitList(newList)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenresListResponseItem) {
            binding.apply {
                tvGenreName.text = genre.name
            }
        }
    }

}