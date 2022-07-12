package com.sajjad.movie_project.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.movie_project.data.response.home.MoviesListResponse.Movie
import com.sajjad.movie_project.databinding.ItemMovieBinding
import javax.inject.Inject

class MoviesAdapter @Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding
    private var movieList = emptyList<Movie>()
    private var onItemClickListener: ((Movie) -> Unit) ?= null
    fun onItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = movieList.size

    fun submitList(newList: List<Movie>) {
        val movieDiffUtil = MoviesDiffUtils(movieList, newList)
        val diffUtils = DiffUtil.calculateDiff(movieDiffUtil)
        movieList = newList
        diffUtils.dispatchUpdatesTo(this)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                ivLastMovieImage.load(movie.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                tvTitle.text = movie.title
                tvRating.text = movie.imdbRating
                tvCountry.text = movie.country
                tvYears.text = movie.year
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(movie)
                    }
                }
            }
        }
    }

}