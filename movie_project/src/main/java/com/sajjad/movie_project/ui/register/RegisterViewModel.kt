package com.sajjad.movie_project.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sajjad.movie_project.common.BaseViewModel
import com.sajjad.movie_project.data.repository.Repository
import com.sajjad.movie_project.data.request.RegisterUserRequest
import com.sajjad.movie_project.data.response.register.RegisterUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    val registerUserLiveData = MutableLiveData<RegisterUserResponse>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun registerUser(request: RegisterUserRequest) =
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            val response = repository.registerUser(request)
            if (response.isSuccessful) {
                registerUserLiveData.postValue(response.body())
            }
            loadingLiveData.postValue(false)

        }
}