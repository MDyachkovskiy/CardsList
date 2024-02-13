package com.test.application.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.application.domain.Company
import kotlinx.coroutines.flow.Flow

interface CardsListRepository {
    fun getPagingFlow(
        pagingConfig: PagingConfig = PagingConfig(pageSize = 5)
    ): Flow<PagingData<Company>>
}