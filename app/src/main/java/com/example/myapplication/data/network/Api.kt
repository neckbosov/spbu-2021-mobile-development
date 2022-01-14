package com.example.myapplication.data.network

import com.example.myapplication.data.network.request.CreateProfileRequest
import com.example.myapplication.data.network.request.RefreshAuthTokensRequest
import com.example.myapplication.data.network.request.SignInWithEmailRequest
import com.example.myapplication.data.network.response.GetUsersResponse
import com.example.myapplication.data.network.response.VerificationTokenResponse
import com.example.myapplication.data.network.response.error.*
import com.example.myapplication.entity.AuthTokens
import com.example.myapplication.entity.Post
import com.example.myapplication.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.*

interface Api {

    @GET("users?per_page=10")
    suspend fun getUsers(): GetUsersResponse

    @GET("profile")
    suspend fun getProfile(): NetworkResponse<User, Unit>

    @POST("auth/sign-in-email")
    suspend fun signInWithEmail(
        @Body request: SignInWithEmailRequest
    ): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse>

    @POST("auth/refresh-tokens")
    suspend fun refreshAuthTokens(
        @Body request: RefreshAuthTokensRequest
    ): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse>

    @POST("registration/verification-code/send")
    suspend fun sendRegistrationVerificationCode(
        @Query("email") email: String,
    ): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse>

    @POST("registration/verification-code/verify")
    suspend fun verifyRegistrationCode(
        @Query("code") code: String,
        @Query("email") email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse>

    @PUT("registration/create-profile")
    suspend fun createProfile(
        @Body request: CreateProfileRequest
    ): NetworkResponse<AuthTokens, CreateProfileErrorResponse>

    @POST("posts")
    suspend fun getPosts(): NetworkResponse<List<Post>, Unit>
}

@JsonClass(generateAdapter = true)
data class GetUsersResponse(
    @Json(name = "data") val data: List<User>
)