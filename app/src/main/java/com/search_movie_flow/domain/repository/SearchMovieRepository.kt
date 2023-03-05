package com.search_movie_flow.domain.repository

import com.search_movie_flow.domain.entity.SearchMovieEntity
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {
    suspend fun getSearchMovieList(query : String?) : Flow<List<SearchMovieEntity>>
}