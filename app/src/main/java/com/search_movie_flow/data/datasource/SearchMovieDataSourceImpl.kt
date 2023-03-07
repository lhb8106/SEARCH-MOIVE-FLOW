package com.search_movie_flow.data.datasource

import com.search_movie_flow.data.api.SearchMovieService
import com.search_movie_flow.data.dto.SearchMovieDto
import retrofit2.Response
import javax.inject.Inject

class SearchMovieDataSourceImpl @Inject constructor(
    private val service: SearchMovieService
) : SearchMovieDataSource {
    override suspend fun getSearchMovieList(query: String?, dispaly : Int, start: Int ): Response<SearchMovieDto> {
        return service.searchMovie(query, dispaly, start)
    }
}