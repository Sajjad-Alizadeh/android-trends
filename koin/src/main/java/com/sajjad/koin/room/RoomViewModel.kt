package com.sajjad.koin.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajjad.koin.data.repository.Repository
import com.sajjad.koin.data.UserModel
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: Repository) : ViewModel() {
    val userModelListLiveData by lazy { MutableLiveData<List<UserModel>>() }

    fun addUser(userModel: UserModel) = viewModelScope.launch {
        repository.addUser(userModel)
    }
    fun getAllUser() {
        userModelListLiveData.value = repository.getAllUser()
    }
}