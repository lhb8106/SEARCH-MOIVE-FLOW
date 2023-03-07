package com.search_movie_flow.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.BR
import com.search_movie_flow.databinding.ItemMovieListBinding
import com.search_movie_flow.domain.entity.SearchMovieEntity
import com.search_movie_flow.presentation.util.GlobalDiffCallBack

class SearchMoviePagingAdapter(
    private val movieClickListener: (SearchMovieEntity) -> Unit
) :
    PagingDataAdapter<SearchMovieEntity, SearchMoviePagingAdapter.PagingViewHolder>(GlobalDiffCallBack<SearchMovieEntity>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagingViewHolder(
            ItemMovieListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, getItem(position))
        setMovieList(holder)
    }

    private fun setMovieList(holder : PagingViewHolder) {
        val movie = getItem(holder.absoluteAdapterPosition)
        holder.binding.root.setOnClickListener {
            if (movie != null) {
                movieClickListener(movie)
            }
        }
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class PagingViewHolder(
        val binding: ItemMovieListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(value: SearchMovieEntity) {
            binding.viewModel = value
        }

    }
}

