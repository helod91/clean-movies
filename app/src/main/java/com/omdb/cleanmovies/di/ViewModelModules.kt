package com.omdb.cleanmovies.di

import com.omdb.cleanmovies.ui.details.MovieDetailsViewModel
import com.omdb.cleanmovies.ui.search.SearchMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel { SearchMoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}