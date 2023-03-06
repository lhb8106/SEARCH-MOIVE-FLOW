package com.search_movie_flow.presentation

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.search_movie_flow.data.datasource.local.LocalDatabase
import com.search_movie_flow.databinding.ActivityRecentSearchBinding
import com.search_movie_flow.presentation.adpater.RecentSearchAdapter
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentSearchActivity : BaseActivity<ActivityRecentSearchBinding>(ActivityRecentSearchBinding::inflate) {

    private var localDatabase: LocalDatabase? = null
    private lateinit var recentSearchAdapter : RecentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSearchKeywordAdapter()
    }

    private fun initSearchKeywordAdapter() {
        localDatabase = LocalDatabase.getInstance(this)
        lifecycleScope.launch {
            localDatabase?.recentSearchDao()?.getKeywordList().let {
                recentSearchAdapter = RecentSearchAdapter(it ?: emptyList())
                binding.rvRecentSearch.adapter = recentSearchAdapter
            }
        }
    }
}