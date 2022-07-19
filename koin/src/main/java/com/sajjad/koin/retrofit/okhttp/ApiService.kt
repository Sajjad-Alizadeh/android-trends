package com.sajjad.koin.retrofit.okhttp

import com.sajjad.koin.data.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    suspend fun getMovies(): Response<MoviesResponse>
}