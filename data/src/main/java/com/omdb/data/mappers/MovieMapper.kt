package com.omdb.data.mappers

import com.omdb.data.models.MovieDetailsResponseModel
import com.omdb.data.models.MovieModel
import com.omdb.data.models.MovieSearchResponseModel
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.MovieSearchModel
import com.omdb.domain.models.MovieSearchResultModel

class MovieMapper {

    fun toMovieSearchResult(movieSearchResponseModel: MovieSearchResponseModel): MovieSearchResultModel {
        return MovieSearchResultModel(
            movieSearchResponseModel.movies?.map { toMovieSearch(it) },
            movieSearchResponseModel.error,
            movieSearchResponseModel.response
        )
    }

    fun toMovieDetails(movieDetailsModelServer: MovieDetailsResponseModel): MovieDetailsModel {
        return MovieDetailsModel(
            title = movieDetailsModelServer.title,
            releaseDate = movieDetailsModelServer.released,
            imageUrl = movieDetailsModelServer.poster,
            runtime = movieDetailsModelServer.runtime,
            genre = movieDetailsModelServer.genre,
            writers = movieDetailsModelServer.writer,
            directors = movieDetailsModelServer.director,
            actors = movieDetailsModelServer.actors,
            plot = movieDetailsModelServer.plot
        )
    }

    private fun toMovieSearch(movieModelServer: MovieModel): MovieSearchModel {
        return MovieSearchModel(
            id = movieModelServer.imdbID,
            title = movieModelServer.title,
            type = movieModelServer.type,
            releaseYear = movieModelServer.year,
            thumbUrl = movieModelServer.poster
        )
    }
}