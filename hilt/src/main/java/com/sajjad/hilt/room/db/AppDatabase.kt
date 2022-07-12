package com.sajjad.hilt.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sajjad.hilt.room.model.User
import com.sajjad.hilt.room.DATABASE_VERSION


@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): MyDao
}