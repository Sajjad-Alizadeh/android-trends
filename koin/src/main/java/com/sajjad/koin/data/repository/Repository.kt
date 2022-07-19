package com.sajjad.koin.data.repository

import com.sajjad.koin.data.UserModel
import com.sajjad.koin.retrofit.okhttp.ApiService
import com.sajjad.koin.room.MyDao

class Repository(private val dao: MyDao) {
    //local
    fun addUser(user: UserModel) = dao.addUser(user)
    fun getAllUser() = dao.getAllUser()
}