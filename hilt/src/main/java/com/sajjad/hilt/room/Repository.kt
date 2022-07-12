package com.sajjad.hilt.room

import com.sajjad.hilt.room.model.User
import com.sajjad.hilt.room.db.MyDao
import javax.inject.Inject
import javax.inject.Singleton

class Repository @Inject constructor(private val dao: MyDao) {
    fun addUser(user: User) = dao.addUser(user)
    fun getUsers() = dao.getAllUsers()
}