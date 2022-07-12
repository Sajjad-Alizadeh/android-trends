package com.sajjad.hilt.retrofit.repository

import com.sajjad.hilt.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRepository @Inject constructor(private val apiService: ApiService) {
    fun getMovies() = apiService.getMovies()
}