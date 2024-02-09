package com.test.application.base

import android.content.Context
import com.test.application.core.R
import com.test.application.utils.AppState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository<T>(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun getSuccessResult(): T

    fun getResult(): Flow<AppState<T>> = flow {
        emit(AppState.Loading)
        try {
            emit(AppState.Success(getSuccessResult()))
        } catch (t: Throwable) {
            emit(AppState.Error(context.getString(R.string.failed_loading)))
        }
    }.flowOn(ioDispatcher)
}