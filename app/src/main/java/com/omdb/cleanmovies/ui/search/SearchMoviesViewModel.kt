package com.omdb.cleanmovies.ui.search

import androidx.lifecycle.MutableLiveData
import com.omdb.cleanmovies.common.BaseViewModel
import com.omdb.cleanmovies.common.subscribeWithAndroidOperators
import com.omdb.cleanmovies.models.Data
import com.omdb.domain.models.MovieSearchModel
import com.omdb.domain.usecases.SearchMoviesUseCase

class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : BaseViewModel() {

    val searchMoviesResult = MutableLiveData<Data<List<MovieSearchModel>?>>()

    fun searchMovie(title: String?) {
        searchMoviesResult.value = Data.loading()

        searchMoviesUseCase.execute(title)
            .subscribeWithAndroidOperators(
                { result ->
                    if (result.success)
                        searchMoviesResult.value = Data.success(result.movies)
                    else
                        searchMoviesResult.value = Data.error(Throwable(result.error))
                },
                {
                    searchMoviesResult.value = Data.error(it)
                }
            ).let { addDisposable(it) }
    }
}