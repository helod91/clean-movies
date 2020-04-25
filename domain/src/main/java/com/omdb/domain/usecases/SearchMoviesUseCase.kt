package com.omdb.domain.usecases

import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.repositories.MovieRepository
import io.reactivex.Single

class SearchMoviesUseCase(private val moviesRepository: MovieRepository) {

    fun execute(title: String?): Single<MovieSearchResultModel> {
        return moviesRepository.searchMovies(title)
    }
}