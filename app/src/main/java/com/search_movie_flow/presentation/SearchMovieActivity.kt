package com.search_movie_flow.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.data.datasource.local.LocalDatabase
import com.search_movie_flow.data.dto.RecentSearchEntity
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

    private var localDatabase: LocalDatabase? = null
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var searchMovieRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener()
        registerRecentSearch()
    }


    private fun clickListener() {
        localDatabase = LocalDatabase.getInstance(this)
        binding.tvSearch.setOnClickListener {
            searchMovieNetwork()
            lifecycleScope.launch {
                val inputText = binding.editText.text.toString()
                val newSearchKeyword = RecentSearchEntity(keyword = inputText.trim())
                localDatabase?.recentSearchDao()?.insertKeyword(newSearchKeyword)
            }

        }
    }

    private fun searchMovieNetwork() {
        val searchContent = binding.editText.text.toString()
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
            /*lifecycleScope.launch {
                localDatabase?.recentSearchDao()?.getKeywordList()?.let { keywordList ->
                    Log.e("Test ", keywordList.toString())
                }
            }*/
            startActivity(Intent(this, RecentSearchActivity::class.java))
        }
    }
}