package com.testtask.movies.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "date_updated")
    val id: String,
    @Json(name = "display_title")
    val title: String,
    @Json(name = "summary_short")
    val summaryShort: String,
    @Json(name = "multimedia")
    val url: Multimedia
)