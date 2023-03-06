package com.search_movie_flow.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.search_movie_flow.data.api.NaverService
import com.search_movie_flow.data.dto.SearchMovieDto
import com.search_movie_flow.data.mapper.SearchMovieMapper
import com.search_movie_flow.domain.entity.SearchMovieEntity
import javax.inject.Inject

class SearchMoviePagingDataSource @Inject constructor(
    private val query: String,
    private val service: NaverService,
    private val searchMovieMapper: SearchMovieMapper
) : PagingSource<Int, SearchMovieEntity>() {

    override fun getRefreshKey(state: PagingState<Int, SearchMovieEntity>): Int? {
        // 이전에 로드한 페이지의 마지막 인덱스 값
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovieEntity> {
        return try {
            val page = params.key ?: 1
            val results = service.searchMovie(query = query, display = page, start =1 + (page - 1) * params.loadSize ).body()?.items?.map {
                searchMovieMapper.mapperToSearchMovie(it)
            } ?: emptyList()

            val nextPage =
                if (results.isEmpty()) null else page + 1
            LoadResult.Page(
                data = results, prevKey = if (page == 1) null else page - 1, nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}