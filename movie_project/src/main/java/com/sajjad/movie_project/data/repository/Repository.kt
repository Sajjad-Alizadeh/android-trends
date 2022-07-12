package com.sajjad.movie_project.data.repository

import com.sajjad.movie_project.data.db.Dao
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.request.RegisterUserRequest
import com.sajjad.movie_project.service.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val dao: Dao) {
    //server
    suspend fun registerUser(request: RegisterUserRequest) = apiService.registerUser(request)
    suspend fun getTopMovies(genreId: Int, page: Int) = apiService.getTopMovies(genreId, page)
    suspend fun getGenres() = apiService.getGenres()
    suspend fun getMovies(page: Int, q: String? = null) = apiService.getLastMovies(page, q)
    suspend fun getMovieDetail(movieId: Int) = apiService.getMovieDetail(movieId)

    //database
    suspend fun addMovieToFavorite(entity: MovieEntity) = dao.addMovieToFavorite(entity)
    suspend fun removeMovieFromFavorite(id: Int) = dao.removeMovieFromFavorite(id)
    suspend fun isEntityExist(id: Int) = dao.isEntityExist(id)
    fun getFavoriteMovies() = dao.getFavoriteMovies()

}