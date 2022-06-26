package com.testtask.movies.data

import com.testtask.movies.data.models.Movie
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networking: MovieApi
) : MoviesRepository {

    private var moviesList: List<Movie> = emptyList()

    override suspend fun searchMovies(
        offset: Int,
        sort: String
    ): List<Movie> {
        return networking.searchMovie(offset, sort).movies ?: emptyList()
    }

    fun addMoviesInList(movies: List<Movie>): List<Movie> {
        moviesList = (moviesList + movies)
        return moviesList
    }
}