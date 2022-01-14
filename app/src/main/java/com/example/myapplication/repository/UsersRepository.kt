package com.example.myapplication.repository

import com.example.myapplication.data.network.Api
import com.example.myapplication.di.IoCoroutineDispatcher
import com.example.myapplication.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    private val apiLazy: Lazy<Api>,
    @IoCoroutineDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    private val api by lazy { apiLazy.get() }
    suspend fun getProfile(): NetworkResponse<User, Unit> =
        api.getProfile()
}