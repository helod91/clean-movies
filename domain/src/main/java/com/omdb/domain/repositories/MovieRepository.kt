package com.omdb.domain.repositories

import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.MovieSearchResultModel
import io.reactivex.Single

interface MovieRepository {

    fun searchMovies(title: String?): Single<MovieSearchResultModel>

    fun getMovieDetails(id: String?): Single<MovieDetailsModel>
}