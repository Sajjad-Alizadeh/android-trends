package com.sajjad.movie_project.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): Dao
}