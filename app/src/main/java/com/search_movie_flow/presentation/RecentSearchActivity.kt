package com.search_movie_flow.presentation

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.search_movie_flow.data.local.RecentSearchDatabase
import com.search_movie_flow.databinding.ActivityRecentSearchBinding
import com.search_movie_flow.presentation.adpater.RecentSearchAdapter
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentSearchActivity :
    BaseActivity<ActivityRecentSearchBinding>(ActivityRecentSearchBinding::inflate) {

    private var localDatabase: RecentSearchDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSearchKeywordAdapter()
        backBtnClickListener()
        clearAllClickerListener()
    }

    private fun initSearchKeywordAdapter() {
        localDatabase = RecentSearchDatabase.getInstance(this)
        lifecycleScope.launch {
            localDatabase?.recentSearchDao()?.getKeywordList()?.let { keywordList ->
                if (keywordList.isNotEmpty()) {
                    val recentSearchAdapter = RecentSearchAdapter {
                        Intent(this@RecentSearchActivity, SearchMovieActivity::class.java).apply {
                            putExtra("keyword", it.keyword)
                            startActivity(this)
                            finish()
                        }
                    }
                    binding.rvRecentSearch.adapter = recentSearchAdapter
                    (binding.rvRecentSearch.adapter as RecentSearchAdapter).submitList(keywordList)
                }
            }
        }
    }

    private fun backBtnClickListener() {
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this@RecentSearchActivity, SearchMovieActivity::class.java))
            finish()
        }
    }

    private fun clearAllClickerListener() {
        binding.tvDeleteAll.setOnClickListener {
            lifecycleScope.launch {
                localDatabase?.clearAllTables()
                localDatabase?.recentSearchDao()?.getKeywordList().let {
                    (binding.rvRecentSearch.adapter as RecentSearchAdapter).submitList(it)
                }
            }
        }
    }
}