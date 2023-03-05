package com.search_movie_flow.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.search_movie_flow.databinding.ActivitySearchMainBinding
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieActivity :
    BaseActivity<ActivitySearchMainBinding>(ActivitySearchMainBinding::inflate) {

    private val viewModel: MovieViewModel by viewModels()

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
    }
}