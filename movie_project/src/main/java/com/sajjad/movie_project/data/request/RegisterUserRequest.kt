package com.sajjad.movie_project.data.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("name")
    var name: String = ""
)
