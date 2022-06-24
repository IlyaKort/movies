package com.testtask.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.testtask.movies.R
import com.testtask.movies.data.models.Movie
import com.testtask.movies.databinding.ItemMovieBinding

class MovieAdapterDelegate :
    AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.Holder>() {

    override fun isForViewType(
        item: Movie,
        items: MutableList<Movie>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val itemBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(item: Movie, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        private val itemBinding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(movie: Movie) {

            itemBinding.titleTextView.text = movie.title
            itemBinding.summaryShortTextView.text = movie.summaryShort

            Glide.with(itemView)
                .load(movie.url.url)
                .placeholder(R.drawable.ic_wallpaper)
                .into(itemBinding.posterImageView)
        }
    }
}