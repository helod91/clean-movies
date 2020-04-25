package com.omdb.domain.models

data class MovieSearchModel(
    val id: String,
    val title: String? = null,
    val type: String? = null,
    val releaseYear: String? = null,
    val thumbUrl: String? = null
)