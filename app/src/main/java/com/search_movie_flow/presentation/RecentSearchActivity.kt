package com.search_movie_flow.presentation

import android.os.Bundle
import com.search_movie_flow.databinding.ActivityRecentSearchBinding
import com.search_movie_flow.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity : BaseActivity<ActivityRecentSearchBinding>(ActivityRecentSearchBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}