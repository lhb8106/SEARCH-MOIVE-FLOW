package com.search_movie_flow.domain.repository

import androidx.paging.PagingData
import com.search_movie_flow.domain.entity.SearchMovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {
    fun getSearchMovieList(query : String?) : Flow<PagingData<SearchMovieEntity>>
}