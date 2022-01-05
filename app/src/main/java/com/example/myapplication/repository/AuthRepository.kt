package com.example.myapplication.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {
    private val isAuthorizedMutableFlow = MutableStateFlow(false)
    val isAuthorizedFlow get() = isAuthorizedMutableFlow.asStateFlow()

    suspend fun signIn(email: String, password: String) {
        isAuthorizedMutableFlow.emit(true)
    }

    suspend fun logout() {
        isAuthorizedMutableFlow.emit(false)
    }

    suspend fun signUp(
        firstname: String,
        lastname: String,
        nickname: String,
        email: String,
        password: String
    ) {
        //TODO: Get API response for email availability, change screen to email confirm
    }
}