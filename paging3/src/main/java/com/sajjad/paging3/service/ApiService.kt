package com.sajjad.paging3.service

import com.sajjad.paging3.data.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movies")
    suspend fun getMovie(@Query("page") page: Int): Response<MoviesResponse>
}