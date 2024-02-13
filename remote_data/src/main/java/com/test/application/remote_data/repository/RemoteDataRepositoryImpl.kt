package com.test.application.remote_data.repository

import android.util.Log
import com.test.application.domain.AllCards
import com.test.application.remote_data.api.CardsApi
import com.test.application.remote_data.maper.toDomain
import com.test.application.repository.RemoteDataRepository

class RemoteDataRepositoryImpl(
    private val cardsService: CardsApi
) : RemoteDataRepository {

    override suspend fun fetchCards(offset: Int, limit: Int): Result<AllCards> {
        return try {
            Log.d("@@@", "Fetching cards with offset: $offset, limit: $limit")
            val response = cardsService.getAllCards(mapOf("offset" to offset, "limit" to limit))

            Log.d("@@@", "Fetched ${response.companies.size} companies from the server")
            Result.success(
                AllCards(
                    companies = response.companies.map { it.toDomain() },
                    limit = response.limit,
                    offset = response.offset
                )
            )
        } catch (e: Exception) {
            Log.e("@@@", "Error fetching cards", e)
            Result.failure(e)
        }
    }
}