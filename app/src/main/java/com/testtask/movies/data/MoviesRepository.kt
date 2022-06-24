package com.testtask.movies.data

import com.testtask.movies.data.models.Movie

interface MoviesRepository {
    suspend fun searchMovies(offset: Int, sort: String): List<Movie>
}