package com.search_movie_flow.data.datasource

import com.search_movie_flow.data.dto.SearchMovieDto
import retrofit2.Response

interface SearchMovieDataSource {
    suspend fun getSearchMovieList(query : String?, display : Int, start: Int) : Response<SearchMovieDto>
}