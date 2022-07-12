package com.sajjad.movie_project.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sajjad.movie_project.common.Constants

@Dao
interface Dao {
    @Insert
    suspend fun addMovieToFavorite(movie: MovieEntity)

    //no need to used suspend keyword when return type is kind of list
    @Query("SELECT * FROM ${Constants.FAVORITE_MOVIES_TABLE_NAME}")
    fun getFavoriteMovies(): MutableList<MovieEntity>

    @Query("DELETE FROM ${Constants.FAVORITE_MOVIES_TABLE_NAME} WHERE id =:id")
    suspend fun removeMovieFromFavorite(id: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.FAVORITE_MOVIES_TABLE_NAME} WHERE id =:id)")
    suspend fun isEntityExist(id: Int): Boolean
}

