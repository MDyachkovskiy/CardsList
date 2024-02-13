package com.test.application.repository


import com.test.application.domain.AllCards


interface RemoteDataRepository {
    suspend fun fetchCards(offset: Int, limit: Int): Result<AllCards>
}