package com.search_movie_flow.data.datasource.remote

import com.search_movie_flow.data.dto.SearchMovieDto
import retrofit2.Response

interface SearchMovieDataSource {
    suspend fun getSearchMovieList(query : String?) : Response<SearchMovieDto>
}