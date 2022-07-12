package com.sajjad.movie_project.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sajjad.movie_project.common.BaseViewModel
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    val moviesLiveData by lazy { MutableLiveData<MutableList<MovieEntity>>() }
    val emptyLiveData by lazy { MutableLiveData<Boolean>() }

    fun getFavoriteMovies() {
        repository.getFavoriteMovies().also { list ->
            if (list.isNotEmpty()) {
                moviesLiveData.value = list
                emptyLiveData.value = false
            } else {
                emptyLiveData.value = true
            }
        }
    }
}