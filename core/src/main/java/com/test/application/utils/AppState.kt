package com.test.application.utils

sealed class AppState<out R> {
    object Loading : AppState<Nothing>()
    data class Success<out T>(val data: T) : AppState<T>()
    data class Error(val message: String) : AppState<Nothing>()
}