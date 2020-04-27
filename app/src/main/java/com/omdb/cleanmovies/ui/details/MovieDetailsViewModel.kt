package com.omdb.cleanmovies.ui.details

import androidx.lifecycle.MutableLiveData
import com.omdb.cleanmovies.common.BaseViewModel
import com.omdb.cleanmovies.common.subscribeWithAndroidOperators
import com.omdb.cleanmovies.models.Data
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.usecases.GetMovieDetailsUseCase

class MovieDetailsViewModel(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    val movieDetailsResult = MutableLiveData<Data<MovieDetailsModel>>()

    fun getMovieDetails(id: String?) {
        movieDetailsResult.value = Data.loading()

        movieDetailsUseCase.execute(id)
            .subscribeWithAndroidOperators(
                {
                    movieDetailsResult.value = Data.success(it)
                },
                {
                    movieDetailsResult.value = Data.error(it)
                }
            ).let { addDisposable(it) }
    }
}