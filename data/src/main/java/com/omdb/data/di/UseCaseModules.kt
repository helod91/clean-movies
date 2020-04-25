package com.omdb.data.di

import com.omdb.domain.usecases.GetMovieDetailsUseCase
import com.omdb.domain.usecases.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModules = module {
    factory {
        SearchMoviesUseCase(get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }
}