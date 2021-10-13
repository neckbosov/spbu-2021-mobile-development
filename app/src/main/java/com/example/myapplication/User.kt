package com.example.myapplication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar")
    val avatarUrl: String,

    @Json(name = "first_name")
    val name: String,

    @Json(name = "email")
    val email: String
)