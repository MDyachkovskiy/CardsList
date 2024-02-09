package com.test.application.repository

import androidx.paging.PagingData
import com.test.application.domain.Company
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun getAllCards(): Flow<PagingData<Company>>
}