package com.test.application.cardslist.app

import android.app.Application
import com.test.application.cardslist.di.networkModule
import com.test.application.cardslist.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule))
        }
    }
}