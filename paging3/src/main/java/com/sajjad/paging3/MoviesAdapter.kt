package com.sajjad.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.paging3.data.MoviesResponse.*
import com.sajjad.paging3.databinding.ItemMovieBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor() :
    PagingDataAdapter<Data, MoviesAdapter.ViewHolder>(diffCallBack) {
    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Data) {
            binding.apply {
                ivMovie.load(movie.poster) {
                    crossfade(true)
                    crossfade(500)
                }
                tvMovie.text = movie.title
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean = oldItem == newItem
        }
    }
}