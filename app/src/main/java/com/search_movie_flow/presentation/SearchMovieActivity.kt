package com.search_movie_flow.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.data.local.RecentSearchDatabase
import com.search_movie_flow.data.dto.RecentSearchDto
import com.search_movie_flow.databinding.ActivitySearchMainBinding
import com.search_movie_flow.presentation.adpater.SearchMoviePagingAdapter
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMovieActivity :
    BaseActivity<ActivitySearchMainBinding>(ActivitySearchMainBinding::inflate) {

    private var localDatabase: RecentSearchDatabase? = null
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var searchMovieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener()
        registerRecentSearch()
    }

    override fun onResume() {
        super.onResume()
        setRecentSearchKeyword()
    }

    private fun clickListener() {
        localDatabase = RecentSearchDatabase.getInstance(this)
        binding.tvSearch.setOnClickListener {
            searchMovieNetwork(binding.etSearch.text.toString())
            lifecycleScope.launch {
                val inputText = binding.etSearch.text.toString()
                val newSearchKeyword = RecentSearchDto(keyword = inputText)
                localDatabase?.recentSearchDao()?.insertKeyword(newSearchKeyword)
            }
        }
    }

    private fun searchMovieNetwork(searchContent : String) {
        viewModel.searchMovieList(searchContent)
        searchMovieRecyclerView = binding.rvMovieList
        viewModel.searchMovie.flowWithLifecycle(lifecycle)
            .onEach {
                if (it != null) {
                    searchMovieRecyclerView.adapter = SearchMoviePagingAdapter {
                        Intent(this, WebViewActivity::class.java).apply {
                            putExtra("url", it.link)
                            startActivity(this)
                        }
                    }
                }
                (searchMovieRecyclerView.adapter as SearchMoviePagingAdapter).submitData(
                    it ?: PagingData.empty()
                )
            }
            .launchIn(lifecycleScope)
    }

    private fun registerRecentSearch() {
        binding.tvRecentSearch.setOnClickListener {
            startActivity(Intent(this, RecentSearchActivity::class.java))
            finish()
        }
    }

    private fun setRecentSearchKeyword() {
        val keyword = intent.getStringExtra("keyword")
        if(keyword != null) {
            searchMovieNetwork(keyword)
            binding.etSearch.setText(keyword)
        }
    }
}