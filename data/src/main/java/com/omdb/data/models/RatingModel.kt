package com.omdb.data.models

import com.google.gson.annotations.SerializedName

data class RatingModel (
	@SerializedName("Source") val source : String,
	@SerializedName("Value") val value : String
)