package com.test.application.repository

import androidx.paging.PagingSource
import com.test.application.domain.Company

interface LocalDataRepository {
    suspend fun clearAll()
    suspend fun insertAll(companies: List<Company>)
    fun pagingSourceForCompanies(): PagingSource<Int, Company>
}