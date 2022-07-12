package com.sajjad.movie_project.data.response.home


import com.google.gson.annotations.SerializedName
import com.sajjad.movie_project.data.response.home.GenresListResponse.GenresListResponseItem

class GenresListResponse : ArrayList<GenresListResponseItem>() {
    data class GenresListResponseItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}