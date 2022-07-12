package com.sajjad.movie_project.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sajjad.movie_project.common.BaseViewModel
import com.sajjad.movie_project.data.repository.Repository
import com.sajjad.movie_project.data.response.home.MoviesListResponse.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    val moviesLiveMovie by lazy { MutableLiveData<List<Movie>>() }
    val loadingLiveData by lazy { MutableLiveData<Boolean>() }
    val emptyLiveData by lazy { MutableLiveData<Boolean>() }

    fun searchMovie(q: String) = viewModelScope.launch {
        loadingLiveData.value = true
        repository.getMovies(1, q).let { response ->
            if (response.isSuccessful) {
                if (response.body()?.data!!.isNotEmpty()) {
                    moviesLiveMovie.value = response.body()!!.data
                    emptyLiveData.value = false
                } else {
                    emptyLiveData.value = true
                }
            }
            loadingLiveData.value = false
        }
    }
}