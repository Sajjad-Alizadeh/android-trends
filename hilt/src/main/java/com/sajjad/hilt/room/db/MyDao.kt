package com.sajjad.hilt.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sajjad.hilt.room.model.User

@Dao
interface MyDao {
    @Insert(onConflict = REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): MutableList<User>
}