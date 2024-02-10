package com.test.application.remote_data.api

import com.test.application.remote_data.dto.AllCardsDTO
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CardsApi {
    @POST("getAllCompaniesIdeal")
    @Headers("TOKEN: 123")
    suspend fun getAllCards(
        @Body offset: Map<String, Int>
    ): AllCardsDTO
}