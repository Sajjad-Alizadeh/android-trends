package com.sajjad.hilt.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjad.hilt.room.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = ""
)
