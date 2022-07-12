package com.sajjad.hilt.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.hilt.databinding.ItemMoviewBinding
import com.sajjad.hilt.retrofit.model.MoviesResponse
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemMoviewBinding

    private val differCallBack = object : DiffUtil.ItemCallback<MoviesResponse.Data>() {
        override fun areItemsTheSame(
            oldItem: MoviesResponse.Data,
            newItem: MoviesResponse.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviesResponse.Data,
            newItem: MoviesResponse.Data
        ): Boolean {
            return oldItem == newItem
        }


    }
    private val differ = AsyncListDiffer(this, differCallBack)
    fun submitList(movies: List<MoviesResponse.Data?>) {
        differ.submitList(movies)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesResponse.Data) {
            binding.apply {
                tvMoview.text = movie.title
                ivMovie.load(movie.poster) {
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMoviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}