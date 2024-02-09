package com.test.application.cards_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.test.application.repository.LocalDataRepository
import com.test.application.repository.RemoteDataRepository
import com.test.application.utils.CardsRemoteMediator

class CardsViewModel(
    private val localDataRepository: LocalDataRepository,
    remoteDataRepository: RemoteDataRepository
) : ViewModel() {

    private val pagingConfig = PagingConfig(
        pageSize = 5,
        enablePlaceholders = false,
        initialLoadSize = 10
    )

    @ExperimentalPagingApi
    val cardsFlow = Pager(
        config = pagingConfig,
        remoteMediator = CardsRemoteMediator(remoteDataRepository, localDataRepository)
    ) {
        localDataRepository.pagingSourceForCompanies()
    }.flow.cachedIn(viewModelScope)
}