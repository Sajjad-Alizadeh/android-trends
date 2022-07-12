package com.sajjad.movie_project.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sajjad.movie_project.common.BaseViewModel
import com.sajjad.movie_project.data.db.MovieEntity
import com.sajjad.movie_project.data.repository.Repository
import com.sajjad.movie_project.data.response.detail.MovieDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    val loadingLivedata by lazy { MutableLiveData<Boolean>() }
    val detailLivedata by lazy { MutableLiveData<MovieDetailResponse>() }

    fun getMovieDetail(id: Int) = viewModelScope.launch {
        loadingLivedata.value = true
        repository.getMovieDetail(id).also { response ->
            if (response.isSuccessful) {
                detailLivedata.value = response.body()
            }
            loadingLivedata.value = false
        }
    }

    suspend fun isEntityExists(id: Int) = withContext(viewModelScope.coroutineContext) {
        repository.isEntityExist(id)
    }

    val isFavorite by lazy { MutableLiveData<Boolean>() }

    fun handleFavoriteMovie(entity: MovieEntity) = viewModelScope.launch {
        if (repository.isEntityExist(entity.id)) {
            //Remove entity from favorite list
            repository.removeMovieFromFavorite(entity.id)
            isFavorite.value = false
        } else {
            //Add entity to favorite list
            repository.addMovieToFavorite(entity)
            isFavorite.value = true
        }
    }
}