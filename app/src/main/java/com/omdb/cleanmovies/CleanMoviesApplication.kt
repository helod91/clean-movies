package com.omdb.cleanmovies

import android.app.Application
import com.omdb.cleanmovies.di.viewModelModules
import com.omdb.data.di.*
import org.koin.core.context.startKoin

class CleanMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(
                apiModules,
                mapperModules,
                networkModules,
                repositoryModules,
                useCaseModules,
                viewModelModules
            ))
        }
    }
}