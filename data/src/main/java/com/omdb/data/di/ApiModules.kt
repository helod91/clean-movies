package com.omdb.data.di

import com.omdb.data.apiservice.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModules = module {
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}