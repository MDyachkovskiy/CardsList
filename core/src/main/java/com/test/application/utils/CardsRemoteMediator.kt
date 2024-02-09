package com.test.application.utils

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.test.application.domain.Company
import com.test.application.repository.LocalDataRepository
import com.test.application.repository.RemoteDataRepository

@ExperimentalPagingApi
class CardsRemoteMediator(
    private val remoteData: RemoteDataRepository,
    private val localData: LocalDataRepository
) : RemoteMediator<Int, Company>() {

    private var currentOffset = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Company>
    ): MediatorResult {

        val pageSize = state.config.pageSize

        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItemIndex = state.lastItemOrNull()?.let {
                    currentOffset + pageSize } ?: return MediatorResult.Success(
                    endOfPaginationReached = true)
                lastItemIndex
            }
        }

        return try {
            val response = remoteData.fetchCards(offset, pageSize)
            response.fold(
                onSuccess = { allCards ->
                    if (loadType == LoadType.REFRESH) {
                        localData.clearAll()
                    }
                    localData.insertAll(allCards.companies)
                    currentOffset += allCards.companies.size
                    MediatorResult.Success(endOfPaginationReached = allCards.companies.size < pageSize)
                },
                onFailure = { exception ->
                    MediatorResult.Error(exception)
                }
            )
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}