package com.testtask.movies.data

import com.testtask.movies.data.models.Movie
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networking: MovieApi
) : MoviesRepository {

    override suspend fun searchMovies(
        offset: Int,
        sort: String
    ): List<Movie> {
        return networking.searchMovie(offset, sort).movies ?: emptyList()
    }
}