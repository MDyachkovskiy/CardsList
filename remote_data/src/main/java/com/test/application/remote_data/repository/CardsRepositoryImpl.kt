package com.test.application.remote_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.test.application.domain.Company
import com.test.application.remote_data.api.CardsApi
import com.test.application.remote_data.maper.toDomain
import com.test.application.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardsRepositoryImpl(
    private val cardsService: CardsApi
) : CardsRepository {
    override fun getAllCards(): Flow<PagingData<Company>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = CardsPagingSource.PAGE_SIZE),
            pagingSourceFactory = { CardsPagingSource(cardsService) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}