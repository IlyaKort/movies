package com.testtask.movies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.movies.data.models.Movie
import com.testtask.movies.data.MoviesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepositoryImpl
) : ViewModel() {

    private val moviesListLiveData = MutableLiveData<List<Movie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val moviesList: LiveData<List<Movie>>
        get() = moviesListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(offset: Int, sort: String) {
        viewModelScope.launch {
            try {
                isLoadingLiveData.postValue(true)
                val movies = repository.addMoviesInList(repository.searchMovies(offset, sort))
                moviesListLiveData.postValue(movies)
            } catch (t: Throwable) {
                Log.e("MoviesViewModel", "error", t)
                moviesListLiveData.postValue(emptyList())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}