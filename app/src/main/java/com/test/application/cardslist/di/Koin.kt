package com.test.application.cardslist.di

import androidx.room.Room
import com.test.application.cards_list.view_model.CardsViewModel
import com.test.application.cardslist.utils.BASE_URL
import com.test.application.local_data.database.LocalDatabase
import com.test.application.local_data.repository.LocalDataRepositoryImpl
import com.test.application.remote_data.api.CardsApi
import com.test.application.remote_data.repository.CardsRepositoryImpl
import com.test.application.repository.LocalDataRepository
import com.test.application.repository.RemoteDataRepository
import org.koin.androidx.viewmodel.dsl.viewModel
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

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            LocalDatabase::class.java, "app_database_name"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<LocalDatabase>().companyDao() }
}

val repositoryModule = module {
    single<RemoteDataRepository> { CardsRepositoryImpl(cardsService = get()) }
    single<LocalDataRepository> { LocalDataRepositoryImpl(companyDao = get()) }
}

val viewModelModule = module {
    viewModel { CardsViewModel(localDataRepository = get(), remoteDataRepository = get()) }
}