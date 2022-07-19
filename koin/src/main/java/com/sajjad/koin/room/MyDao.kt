package com.sajjad.koin.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjad.koin.data.UserModel

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userModel: UserModel): Long

    @Query("SELECT * FROM user")
    fun getAllUser(): List<UserModel>
}