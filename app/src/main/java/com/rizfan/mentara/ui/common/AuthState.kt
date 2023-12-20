package com.rizfan.mentara.ui.common

sealed class AuthState<out T: Any?> {

    object Unauthorized : AuthState<Nothing>()
    object Loading : AuthState<Nothing>()
    data class Success<out T: Any>(val data: T) : AuthState<T>()
    data class Error(val errorMessage: String) : AuthState<Nothing>()
}
