package com.search_movie_flow.presentation.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.BR
import com.search_movie_flow.data.dto.RecentSearchDto
import com.search_movie_flow.databinding.ItemRecentSearchListBinding
import com.search_movie_flow.presentation.util.GlobalDiffCallBack

class RecentSearchAdapter(
    private val keywordClickListener: (RecentSearchDto) -> Unit
) :
    ListAdapter<RecentSearchDto, RecentSearchAdapter.RecentSearchViewHolder>(
        GlobalDiffCallBack<RecentSearchDto>()
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
        setRecentSearchList(holder)
    }

    private fun setRecentSearchList(holder: RecentSearchViewHolder) {
        val keyword = getItem(holder.absoluteAdapterPosition)
        holder.binding.root.setOnClickListener {
            if (keyword != null) {
                keywordClickListener(keyword)
            }
        }
    }


    class RecentSearchViewHolder(
        val binding: ItemRecentSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root)

}