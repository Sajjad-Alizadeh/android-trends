package com.sajjad.hilt.retrofit

import com.sajjad.hilt.retrofit.model.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Single<MoviesResponse>
}