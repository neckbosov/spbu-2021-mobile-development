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

class MockApi : Api {
    private val mockUser = User(
        id = 1,
        firstName = "Nikita",
        lastName = "Bosov",
        userName = "neckbosov",
        avatarUrl = "https://img-fotki.yandex.ru/get/237002/17091561.11f/0_22882b_e60fbcb2_XXL",
        groupName = "MKN",
        age = "21"
    )

    override suspend fun getUsers(): GetUsersResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): NetworkResponse<User, Unit> {
        return NetworkResponse.Success(mockUser, code = 200)
    }

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return NetworkResponse.Success(
            AuthTokens(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                accessTokenExpiration = 1640871771000,
                refreshTokenExpiration = 1640871771000,
            ),
            code = 200
        )
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): NetworkResponse<List<Post>, Unit> {
        TODO("Not yet implemented")
    }
}