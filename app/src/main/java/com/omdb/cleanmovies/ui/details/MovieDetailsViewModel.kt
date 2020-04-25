package com.omdb.cleanmovies.ui.details

import androidx.lifecycle.MutableLiveData
import com.omdb.cleanmovies.common.BaseViewModel
import com.omdb.cleanmovies.models.Data
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.usecases.GetMovieDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    val movieDetailsResult = MutableLiveData<Data<MovieDetailsModel>>()

    fun getMovieDetails(id: String?) {
        movieDetailsResult.value = Data.loading()

        movieDetailsUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    movieDetailsResult.value = Data.success(it)
                },
                {
                    movieDetailsResult.value = Data.error(it)
                }
            ).let { addDisposable(it) }
    }
}