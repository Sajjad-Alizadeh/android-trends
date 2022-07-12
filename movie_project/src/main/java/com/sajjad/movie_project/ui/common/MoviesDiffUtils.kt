package com.sajjad.movie_project.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.sajjad.movie_project.data.response.home.MoviesListResponse

class MoviesDiffUtils(private val oldList: List<MoviesListResponse.Movie>, private val newList: List<MoviesListResponse.Movie>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList === newList
}