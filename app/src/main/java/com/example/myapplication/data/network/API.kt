package com.example.myapplication.data.network

import com.example.myapplication.data.network.response.GetUsersResponse
import retrofit2.http.GET

interface API {
    @GET("users?per_page=10")
    suspend fun getUsers(): GetUsersResponse
}

