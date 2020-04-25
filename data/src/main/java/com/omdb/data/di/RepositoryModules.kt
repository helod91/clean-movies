package com.omdb.data.di

import com.omdb.data.repositories.OmdbMovieRepository
import com.omdb.domain.repositories.MovieRepository
import org.koin.dsl.module

val repositoryModules = module {
    single<MovieRepository> {
        OmdbMovieRepository(get(), get())
    }
}