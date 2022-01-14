package com.example.myapplication.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.myapplication.entity.User
import com.example.myapplication.interactor.AuthInteractor
import com.example.myapplication.interactor.UsersInteractor
import com.example.myapplication.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    private val userViewStateMutable: MutableStateFlow<UserViewState> =
        MutableStateFlow(UserViewState.Loading)
    val userViewState: Flow<UserViewState> get() = userViewStateMutable.asStateFlow()
    fun loadProfile() {
        viewModelScope.launch {
            userViewStateMutable.emit(UserViewState.Loading)
            Timber.d("loading profile...")
            delay(1000)
            when (val response = usersInteractor.loadProfile()) {
                is NetworkResponse.Success -> {
                    userViewStateMutable.emit(UserViewState.Profile(response.body))
                }
                else -> {
                    Timber.e("failed to load profile")
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                authInteractor.logout()
            } catch (error: Throwable) {
                Timber.e(error)
            }
        }
    }

    sealed class UserViewState {
        object Loading : UserViewState()
        data class Profile(val data: User) : UserViewState()
    }
}
