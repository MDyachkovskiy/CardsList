package com.test.application.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.application.utils.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(
    private val repository: BaseRepository<T>,
    private val id: Any? = null
) : ViewModel() {

    private val _stateFlow = MutableStateFlow<AppState<T>>(AppState.Loading)
    val stateFlow: StateFlow<AppState<T>>
        get() = _stateFlow

    fun refresh() {
        viewModelScope.launch {
            repository.getResult().collect {
                _stateFlow.tryEmit(it)
            }
        }
    }
}