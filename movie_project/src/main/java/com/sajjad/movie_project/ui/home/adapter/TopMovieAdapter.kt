package com.sajjad.movie_project.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.movie_project.data.response.home.MoviesListResponse.Movie
import com.sajjad.movie_project.databinding.ItemTopMovieBinding
import javax.inject.Inject

class TopMovieAdapter @Inject constructor() : RecyclerView.Adapter<TopMovieAdapter.ViewHolder>() {
    private lateinit var binding: ItemTopMovieBinding
    private val differCallBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return if (differ.currentList.isEmpty())
            0
        else 5
    }

    fun submitList(newList: List<Movie>) {
        differ.submitList(newList)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                ivMovie.load(movie.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                tvMovieName.text = movie.title
                tvMovieInfo.text = "${movie.imdbRating} | ${movie.country} | ${movie.year}"
            }
        }
    }

}