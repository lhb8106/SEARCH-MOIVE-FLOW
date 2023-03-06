package com.search_movie_flow.presentation

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.search_movie_flow.data.datasource.local.LocalDatabase
import com.search_movie_flow.databinding.ActivityRecentSearchBinding
import com.search_movie_flow.presentation.adpater.RecentSearchAdapter
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentSearchActivity : BaseActivity<ActivityRecentSearchBinding>(ActivityRecentSearchBinding::inflate) {

    private var localDatabase: LocalDatabase? = null
    private lateinit var recentSearchAdapter : RecentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSearchKeywordAdapter()
        backBtnClickListener()
        clearAllClickerListener()
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

    private fun backBtnClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun clearAllClickerListener() {
        binding.tvDeleteAll.setOnClickListener {
            GlobalScope.launch {
                localDatabase?.clearAllTables()
            }
        }
    }
}