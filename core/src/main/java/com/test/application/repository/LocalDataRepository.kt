package com.test.application.repository

import com.test.application.domain.Company

interface LocalDataRepository {
    suspend fun clearAll()
    suspend fun insertAll(companies: List<Company>)
}