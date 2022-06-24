package com.testtask.movies.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.testtask.movies.data.models.Movie

@JsonClass(generateAdapter = true)
data class ListMovies(
    @Json(name = "results")
    val movies: List<Movie>?
)
