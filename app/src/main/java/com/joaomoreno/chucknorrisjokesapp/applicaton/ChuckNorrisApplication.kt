package com.joaomoreno.chucknorrisjokesapp.applicaton

import android.app.Application
import com.joaomoreno.chucknorrisjokesapp.network.networkModule
import com.joaomoreno.chucknorrisjokesapp.network.repositoryModule
import com.joaomoreno.chucknorrisjokesapp.utils.prefModule
import com.joaomoreno.chucknorrisjokesapp.viewmodel.jokeViewModel
import com.joaomoreno.chucknorrisjokesapp.viewmodel.searchViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChuckNorrisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ChuckNorrisApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    searchViewModelModule,
                    jokeViewModel,
                    prefModule
                )
            )
        }
    }
}