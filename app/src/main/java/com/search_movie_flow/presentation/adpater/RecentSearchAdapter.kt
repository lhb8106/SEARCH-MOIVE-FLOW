package com.search_movie_flow.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.data.dto.RecentSearchEntity
import com.search_movie_flow.databinding.ItemRecentSearchListBinding

class RecentSearchAdapter(private val recentSearchList: List<RecentSearchEntity>) :
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecentSearchViewHolder(
            ItemRecentSearchListBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(recentSearchList[position])
    }

    override fun getItemCount(): Int = recentSearchList.size

    class RecentSearchViewHolder(val binding : ItemRecentSearchListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(value: RecentSearchEntity) {
            binding.viewModel = value
        }
    }
}