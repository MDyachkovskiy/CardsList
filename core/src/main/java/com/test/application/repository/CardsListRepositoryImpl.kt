package com.test.application.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.domain.Company
import com.test.application.utils.CardsRemoteMediator
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class CardsListRepositoryImpl(
    private val remoteDataRepository: RemoteDataRepository,
    private val localDataRepository: LocalDataRepository,
) : CardsListRepository {

    override fun getPagingFlow(pagingConfig: PagingConfig): Flow<PagingData<Company>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = CardsRemoteMediator(remoteDataRepository, localDataRepository),
            pagingSourceFactory = { localDataRepository.pagingSourceForCompanies() }
        ).flow
    }
}