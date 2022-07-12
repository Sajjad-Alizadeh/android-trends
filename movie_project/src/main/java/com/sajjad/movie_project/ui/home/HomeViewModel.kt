package com.sajjad.movie_project.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sajjad.movie_project.common.BaseViewModel
import com.sajjad.movie_project.data.repository.Repository
import com.sajjad.movie_project.data.response.home.GenresListResponse
import com.sajjad.movie_project.data.response.home.MoviesListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val progressLiveData = MutableLiveData<Boolean>()
    val topMoviesLiveData = MutableLiveData<MoviesListResponse>()
    val lastMoviesLiveData = MutableLiveData<MoviesListResponse>()
    val genresLiveData = MutableLiveData<GenresListResponse>()

    fun getTopMovies(genreId: Int, page: Int) = viewModelScope.launch {
        progressLiveData.value = true
        repository.getTopMovies(genreId, page).also { response ->
            if (response.isSuccessful) {
                topMoviesLiveData.value = response.body()
            }
            progressLiveData.value = false
        }
    }

    fun getGenres() = viewModelScope.launch {
        progressLiveData.value = true
        repository.getGenres().also { response ->
            if (response.isSuccessful) {
                genresLiveData.value = response.body()
            }
            progressLiveData.value = false
        }
    }

    fun getLastMovies(page: Int) = viewModelScope.launch {
        progressLiveData.value = true
        repository.getMovies(page).also { response ->
            if (response.isSuccessful) {
                lastMoviesLiveData.value = response.body()
            }
            progressLiveData.value = false
        }
    }

}