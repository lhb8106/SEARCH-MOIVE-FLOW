package com.search_movie_flow.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.search_movie_flow.data.api.SearchMovieService
import com.search_movie_flow.data.mapper.SearchMovieMapper
import com.search_movie_flow.domain.entity.SearchMovieEntity
import javax.inject.Inject

class SearchMoviePagingDataSource @Inject constructor(
    private val query: String,
    private val service: SearchMovieService,
    private val searchMovieMapper: SearchMovieMapper
) : PagingSource<Int, SearchMovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, SearchMovieEntity>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovieEntity> {
        return try {
            val page = params.key ?: 1
            val results = service.searchMovie(
                query = query,
                display = params.loadSize,
                start = (page - 1) * params.loadSize + 1
            )
                .body()?.items?.map {
                    searchMovieMapper.mapperToSearchMovie(it)
                } ?: emptyList()

            val nextPage = if (results.size < params.loadSize) {
                null
            } else {
                page + 1
            }

            LoadResult.Page(
                data = results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}