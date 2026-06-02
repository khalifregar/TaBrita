package com.tabrita.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabrita.data.auth.AppUser
import com.tabrita.data.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<AppUser?>(null)
    val currentUser: StateFlow<AppUser?> = _currentUser.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        refreshUser()
    }

    fun refreshUser() {
        viewModelScope.launch {
            _isLoading.value = true
            _currentUser.value = authRepository.getCurrentAppUser()
            _isLoading.value = false
        }
    }

    fun signInWithGoogle(idToken: String, onSuccess: (AppUser) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = authRepository.signInWithGoogle(idToken)
            _isLoading.value = false
            result.onSuccess { user ->
                _currentUser.value = user
                onSuccess(user)
            }.onFailure { e ->
                onError(e.message ?: "Login gagal")
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _currentUser.value = null
    }
}
