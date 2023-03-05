package com.search_movie_flow.data.api

import com.search_movie_flow.data.dto.SearchMovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {
    @GET("v1/search/movie.json")
    suspend fun searchMovie(
        @Query("query") query: String?,
    ): Response<SearchMovieDto>
}