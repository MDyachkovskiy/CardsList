package com.test.application.cardslist.di

import com.test.application.cardslist.utils.BASE_URL
import com.test.application.remote_data.api.CardsApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(CardsApi::class.java)
    }
}