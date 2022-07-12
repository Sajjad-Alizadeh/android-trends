package com.sajjad.movie_project.ui.favorite.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.response.home.MoviesListResponse

class MovieEntityDiffUtils(private val oldList: List<MovieEntity>, private val newList: List<MovieEntity>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList === newList
}