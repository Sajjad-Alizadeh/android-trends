package com.sajjad.koin.retrofit.repo

import com.sajjad.koin.retrofit.okhttp.ApiService

class Repository (private val apiService: ApiService){
    //remote
    suspend fun getMovies() = apiService.getMovies()
}