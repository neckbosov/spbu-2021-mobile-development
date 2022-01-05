package com.example.myapplication.ui

import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class MainViewModel : BaseViewModel() {
    val isAuthorizedFlow: Flow<Boolean> = AuthRepository.isAuthorizedFlow
}