package com.test.application.cards_list.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.test.application.repository.LocalDataRepository
import com.test.application.repository.RemoteDataRepository
import com.test.application.utils.CardsRemoteMediator
import com.test.application.utils.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardsViewModel(
    private val localDataRepository: LocalDataRepository,
    remoteDataRepository: RemoteDataRepository
) : ViewModel() {

    private val _dataState = MutableStateFlow<DataState>(DataState.Loading)
    val dataState: StateFlow<DataState> = _dataState.asStateFlow()

    private val pagingConfig = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    @ExperimentalPagingApi
    val cardsFlow = Pager(
        config = pagingConfig,
        remoteMediator = CardsRemoteMediator(remoteDataRepository, localDataRepository, _dataState)
    ) {
        Log.d("@@@", "CardsViewModel Creating paging source")
        localDataRepository.pagingSourceForCompanies().also {
            Log.d("@@@", "CardsViewModel Paging source created")
        }
    }.flow.cachedIn(viewModelScope).also {
        Log.d("@@@", "CardsViewModel Flow cached in viewModelScope")
    }
}