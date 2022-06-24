package com.testtask.movies.di

import com.testtask.movies.data.MoviesRepository
import com.testtask.movies.data.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun providesRepository(impl: MoviesRepositoryImpl): MoviesRepository
}