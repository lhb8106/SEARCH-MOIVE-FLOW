package com.search_movie_flow.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.BR
import com.search_movie_flow.data.dto.RecentSearchEntity
import com.search_movie_flow.databinding.ItemRecentSearchListBinding
import com.search_movie_flow.presentation.util.GlobalDiffCallBack

class RecentSearchAdapter :
    ListAdapter<RecentSearchEntity, RecentSearchAdapter.RecentSearchViewHolder>(
        GlobalDiffCallBack<RecentSearchEntity>()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val binding = ItemRecentSearchListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, getItem(position))
    }


    class RecentSearchViewHolder(
        val binding : ItemRecentSearchListBinding) : RecyclerView.ViewHolder(binding.root)

}