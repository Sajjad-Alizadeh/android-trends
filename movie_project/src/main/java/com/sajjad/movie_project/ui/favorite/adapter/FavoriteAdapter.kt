package com.sajjad.movie_project.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.response.home.MoviesListResponse.Movie
import com.sajjad.movie_project.databinding.ItemMovieBinding
import javax.inject.Inject

class FavoriteAdapter @Inject constructor() : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding
    private var movieList = emptyList<MovieEntity>()
    private var onItemClickListener: ((MovieEntity) -> Unit) ?= null
    fun onItemClickListener(listener: (MovieEntity) -> Unit) {
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

    fun submitList(newList: List<MovieEntity>) {
        val movieDiffUtil = MovieEntityDiffUtils(movieList, newList)
        val diffUtils = DiffUtil.calculateDiff(movieDiffUtil)
        movieList = newList
        diffUtils.dispatchUpdatesTo(this)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.apply {
                ivLastMovieImage.load(movie.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                tvTitle.text = movie.title
                tvRating.text = movie.rate
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