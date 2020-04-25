package com.omdb.data.models

import com.google.gson.annotations.SerializedName

data class MovieSearchResponseModel(
    @SerializedName("Search") val movies : List<MovieModel>?,
    @SerializedName("totalResults") val totalResults : Int?,
    @SerializedName("Error") val error: String?,
    @SerializedName("Response") val response : Boolean
)