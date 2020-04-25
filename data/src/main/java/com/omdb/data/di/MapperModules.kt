package com.omdb.data.di

import com.omdb.data.mappers.MovieMapper
import org.koin.dsl.module

val mapperModules = module {
    single {
        MovieMapper()
    }
}