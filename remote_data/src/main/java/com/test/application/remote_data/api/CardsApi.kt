package com.test.application.remote_data.api

import com.test.application.remote_data.dto.AllCardsDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CardsApi {
    @POST("getAllCompanies")
    @Headers("TOKEN: 123")
    fun getAllCards(
        @Body offset: Map<String, Int>
    ): Deferred<AllCardsDTO>
}