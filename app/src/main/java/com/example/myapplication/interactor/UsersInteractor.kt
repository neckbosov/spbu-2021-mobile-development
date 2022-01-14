package com.example.myapplication.interactor

import com.example.myapplication.entity.User
import com.example.myapplication.repository.UsersRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun loadProfile(): NetworkResponse<User, Unit> =
        usersRepository.getProfile()
}