package com.omdb.domain.models

class MovieSearchResultModel(
    val movies: List<MovieSearchModel>?,
    val error: String?,
    val success: Boolean
)