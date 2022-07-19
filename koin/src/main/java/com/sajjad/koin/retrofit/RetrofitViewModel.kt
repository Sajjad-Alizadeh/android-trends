package com.sajjad.koin.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjad.koin.data.response.MoviesResponse
import com.sajjad.koin.retrofit.repo.Repository
import kotlinx.coroutines.launch

class RetrofitViewModel(private val repository: Repository) : ViewModel() {
    val movieLiveData = MutableLiveData<MoviesResponse>()

    fun getMovies() = viewModelScope.launch {
        val response = repository.getMovies()
        if (response.isSuccessful) {
            movieLiveData.value = response.body()
        }
    }
}