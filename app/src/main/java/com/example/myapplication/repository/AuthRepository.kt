package com.example.myapplication.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object AuthRepository {
    private val isAuthorizedMutableFlow = MutableStateFlow(false)
    val isAuthorizedFlow = isAuthorizedMutableFlow.asStateFlow()

    suspend fun signIn() {
        isAuthorizedMutableFlow.emit(true)
    }

    suspend fun logout() {
        isAuthorizedMutableFlow.emit(false)
    }
}