package com.sajjad.paging3.data.repo

import com.sajjad.paging3.service.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovie(page: Int) = apiService.getMovie(page)
}