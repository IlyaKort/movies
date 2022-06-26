package com.testtask.movies.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.testtask.movies.R
import com.testtask.movies.databinding.ActivityMoviesListBinding
import com.testtask.movies.presentation.adapter.MovieAdapter
import com.testtask.movies.utils.ItemOffsetDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity(R.layout.activity_movies_list) {

    private val binding: ActivityMoviesListBinding by viewBinding(ActivityMoviesListBinding::bind)
    private val viewModel by viewModels<MoviesViewModel>()
    private var movieAdapter: MovieAdapter? = null

    private var pageOfCollections = 0
    private var sorting = "by-opening-date"
    private var isLoadingPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initList()
        bindViewModel()
        viewModel.search(pageOfCollections, sorting)
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this)

        movieAdapter = MovieAdapter()
        with(binding.movieList) {
            adapter = movieAdapter
            addItemDecoration(ItemOffsetDecoration(this@MoviesListActivity))
            layoutManager = linearLayoutManager
            setHasFixedSize(true)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val totalItemCount = linearLayoutManager.itemCount
                    val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                    if (!isLoadingPage && totalItemCount <=
                        lastVisibleItem + 2
                    ) {
                        pageOfCollections += 20
                        viewModel.search(pageOfCollections, sorting)
                    }
                }
            })
        }
    }

    private fun bindViewModel() {
        viewModel.isLoading.observe(this, ::updateLoadingState)
        viewModel.moviesList.observe(this) { movieAdapter?.items = it }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        isLoadingPage = isLoading
        if (pageOfCollections != 0) {
            binding.linearProgressBar.isVisible = isLoading
        }
    }

    override fun onBackPressed() {}

    override fun onDestroy() {
        super.onDestroy()
        movieAdapter = null
    }
}