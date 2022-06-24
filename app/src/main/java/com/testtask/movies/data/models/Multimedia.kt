package com.testtask.movies.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Multimedia(
    @Json(name = "src")
    val url: String
)
