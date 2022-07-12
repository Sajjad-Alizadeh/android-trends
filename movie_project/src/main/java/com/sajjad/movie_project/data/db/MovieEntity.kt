package com.sajjad.movie_project.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sajjad.movie_project.common.Constants

@Entity(tableName = Constants.FAVORITE_MOVIES_TABLE_NAME)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var poster: String = "",
    var title: String = "",
    var rate: String = "",
    var country: String = "",
    var year: String = "",
)
