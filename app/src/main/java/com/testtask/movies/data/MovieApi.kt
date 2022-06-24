package com.testtask.movies.data

import com.testtask.movies.data.models.ListMovies
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("svc/movies/v2/reviews/all.json")
    suspend fun searchMovie(
        @Query("offset")
        offset: Int,
        @Query("order")
        sort: String,
    ): ListMovies
}