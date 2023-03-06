package com.search_movie_flow.data.datasource.remote

import com.search_movie_flow.data.api.NaverService
import com.search_movie_flow.data.dto.SearchMovieDto
import retrofit2.Response
import javax.inject.Inject

class SearchMovieDataSourceImpl @Inject constructor(
    private val naverService: NaverService
) : SearchMovieDataSource {
    override suspend fun getSearchMovieList(query: String?, page : Int): Response<SearchMovieDto> {
        return naverService.searchMovie(query, page)
    }
}