package com.search_movie_flow.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.search_movie_flow.databinding.ActivitySearchMainBinding
import com.search_movie_flow.presentation.adpater.SearchMoviePagingAdapter
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMovieActivity :
    BaseActivity<ActivitySearchMainBinding>(ActivitySearchMainBinding::inflate) {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var searchMovieRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener()
    }


    private fun clickListener() {
        binding.tvSearch.setOnClickListener { searchMovieNetwork() }
    }

    private fun searchMovieNetwork() {
        val searchContent = binding.editText.text.toString()
        viewModel.searchMovieList(searchContent)
        searchMovieRecyclerView = binding.rvMovieList
        searchMovieRecyclerView.adapter = SearchMoviePagingAdapter()

        viewModel.searchMovie.flowWithLifecycle(lifecycle)
            .onEach {
                (searchMovieRecyclerView.adapter as SearchMoviePagingAdapter).submitData(it ?: PagingData.empty())
            }
            .launchIn(lifecycleScope)

    }
}