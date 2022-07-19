package com.sajjad.koin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sajjad.koin.data.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getDao(): MyDao
}