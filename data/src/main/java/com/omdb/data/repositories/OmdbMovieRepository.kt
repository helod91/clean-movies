package com.omdb.data.repositories

import com.omdb.data.apiservice.ApiService
import com.omdb.data.mappers.MovieMapper
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.MovieSearchModel
import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.repositories.MovieRepository
import io.reactivex.Single

class OmdbMovieRepository(
    private val apiService: ApiService,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun searchMovies(title: String?): Single<MovieSearchResultModel> {
        return apiService.searchMovies(title)
            .map { movieMapper.toMovieSearchResult(it) }
    }

    override fun getMovieDetails(id: String?): Single<MovieDetailsModel> {
        return apiService.getMovieDetails(id)
            .map { movieMapper.toMovieDetails(it) }
    }
}