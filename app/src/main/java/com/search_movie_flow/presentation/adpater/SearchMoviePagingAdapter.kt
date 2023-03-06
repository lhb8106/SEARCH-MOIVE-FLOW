package com.search_movie_flow.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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
        val item = getItem(position)
        holder.binding.root.setOnClickListener {
            if (item != null) {
                movieClickListener(item)
            }
        }
        if (item != null) {
            holder.bind(item)
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

