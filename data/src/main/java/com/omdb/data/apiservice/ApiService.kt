package com.omdb.data.apiservice

import com.omdb.data.models.MovieDetailsResponseModel
import com.omdb.data.models.MovieSearchResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val API_KEY = "411f9106"
    }

    @GET("/")
    fun searchMovies(@Query("s") title: String?, @Query("apikey") apiKey: String = API_KEY): Single<MovieSearchResponseModel>

    @GET("/")
    fun getMovieDetails(@Query("i") id: String?, @Query("plot") plot: String = "full", @Query("apikey") apiKey: String = API_KEY): Single<MovieDetailsResponseModel>
}