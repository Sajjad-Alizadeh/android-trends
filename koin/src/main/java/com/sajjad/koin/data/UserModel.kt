package com.sajjad.koin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var fullName: String = "",
    var age: Int = 0
)
