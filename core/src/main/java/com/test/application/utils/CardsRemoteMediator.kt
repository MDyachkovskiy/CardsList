package com.test.application.utils

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.test.application.domain.Company
import com.test.application.repository.LocalDataRepository
import com.test.application.repository.RemoteDataRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class CardsRemoteMediator(
    private val remoteData: RemoteDataRepository,
    private val localData: LocalDataRepository,
    private val dataStateFlow: MutableStateFlow<DataState>
) : RemoteMediator<Int, Company>() {

    private var currentOffset = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Company>
    ): MediatorResult {
        Log.d("@@@", "RemoteMediator load started: loadType=$loadType")

        val pageSize = state.config.pageSize

        val offset = when (loadType) {
            LoadType.REFRESH -> {
                currentOffset = 0
                Log.d("@@@", "Refreshing data.")
                currentOffset
            }
            LoadType.PREPEND -> {
                Log.d("@@@", "Prepending data, no operation needed.")
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                Log.d("@@@", "Appending data after lastItemIndex: $currentOffset")
                val lastItemIndex = state.lastItemOrNull()?.let { currentOffset + pageSize }
                    ?: return MediatorResult.Success(endOfPaginationReached = true) // Если нет элементов, значит достигнут конец
                lastItemIndex
            }
        }

        return try {
            Log.d("@@@", "Fetching cards from remote: offset=$offset, pageSize=$pageSize")
            val response = remoteData.fetchCards(offset, pageSize)

            response.fold(
                onSuccess = { allCards ->
                    Log.d("@@@", "Fetched ${allCards.companies.size} cards from remote")
                    if(allCards.companies.isNotEmpty()) {

                        coroutineScope {
                            launch {
                                if (loadType == LoadType.REFRESH) {
                                    Log.d("@@@", "Clearing all data in database because of refresh.")
                                    localData.clearAll()
                                }
                            }.join()

                            launch {
                                Log.d("@@@", "Inserting ${allCards.companies.size} fetched companies into database.")
                                localData.insertAll(allCards.companies)
                            }.join()
                        }
                    }

                    currentOffset += allCards.companies.size
                    dataStateFlow.emit(DataState.Success)
                    Log.d("@@@", "Successfully inserted data into database. EndOfPaginationReached: ${allCards.companies.size < pageSize}")
                    MediatorResult.Success(endOfPaginationReached = allCards.companies.size < pageSize)
                },
                onFailure = { exception ->
                    Log.e("@@@", "Fetch failed: ${exception.message}", exception)
                    dataStateFlow.emit(DataState.Error(exception))
                    MediatorResult.Error(exception)
                }
            )
        } catch (exception: Exception) {
            dataStateFlow.emit(DataState.Error(exception))
            Log.e("@@@", "Error in RemoteMediator", exception)
            MediatorResult.Error(exception)
        }
    }
}