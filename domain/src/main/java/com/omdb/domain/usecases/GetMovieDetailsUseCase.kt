package com.omdb.domain.usecases

import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.repositories.MovieRepository
import io.reactivex.Single

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {

    fun execute(id: String?): Single<MovieDetailsModel> {
        return movieRepository.getMovieDetails(id)
    }
}