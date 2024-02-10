package com.test.application.utils

sealed class DataState {
    data object Loading : DataState()
    data object Success : DataState()
    data class Error(val exception: Throwable) : DataState()
}