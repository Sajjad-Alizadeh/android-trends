package com.sajjad.movie_project.service

import com.sajjad.movie_project.data.request.RegisterUserRequest
import com.sajjad.movie_project.data.response.detail.MovieDetailResponse
import com.sajjad.movie_project.data.response.home.GenresListResponse
import com.sajjad.movie_project.data.response.home.MoviesListResponse
import com.sajjad.movie_project.data.response.register.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body request: RegisterUserRequest): Response<RegisterUserResponse>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMovies(@Path("genre_id") genreId: Int, @Query("page") page: Int) : Response<MoviesListResponse>

    @GET("genres")
    suspend fun getGenres() : Response<GenresListResponse>

    @GET("movies")
    suspend fun getLastMovies(@Query("page") page: Int, @Query("q") q: String?) : Response<MoviesListResponse>

    @GET("movies/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int) : Response<MovieDetailResponse>
}