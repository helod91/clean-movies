package com.omdb.domain.models

data class MovieDetailsModel(
    val title: String? = null,
    val releaseDate: String? = null,
    val imageUrl: String? = null,
    val runtime: String? = null,
    val genre: String? = null,
    val writers: String? = null,
    val directors: String? = null,
    val actors: String? = null,
    val plot: String? = null
)