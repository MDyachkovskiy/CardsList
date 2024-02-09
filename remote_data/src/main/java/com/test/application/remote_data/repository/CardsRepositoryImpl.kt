package com.test.application.remote_data.repository

import com.test.application.domain.AllCards
import com.test.application.remote_data.api.CardsApi
import com.test.application.remote_data.maper.toDomain
import com.test.application.repository.RemoteDataRepository

class CardsRepositoryImpl(
    private val cardsService: CardsApi
) : RemoteDataRepository {

    override suspend fun fetchCards(offset: Int, limit: Int): Result<AllCards> {
        return try {
            val response = cardsService.getAllCards(mapOf("offset" to offset, "limit" to limit))
                .await()
            Result.success(
                AllCards(
                    companies = response.companies.map { it.toDomain() },
                    limit = response.limit,
                    offset = response.offset
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}