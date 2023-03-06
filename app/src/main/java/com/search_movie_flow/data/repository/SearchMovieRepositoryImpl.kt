package com.search_movie_flow.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.search_movie_flow.data.api.NaverService
import com.search_movie_flow.data.datasource.SearchMoviePagingDataSource
import com.search_movie_flow.data.datasource.remote.SearchMovieDataSource
import com.search_movie_flow.data.mapper.SearchMovieMapper
import com.search_movie_flow.domain.entity.SearchMovieEntity
import com.search_movie_flow.domain.repository.SearchMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class SearchMovieRepositoryImpl @Inject constructor(
    private val searchMovieMapper: SearchMovieMapper,
    private val searchMovieDataSource: SearchMovieDataSource,
    private val service: NaverService,
)  : SearchMovieRepository {
    override fun getSearchMovieList(query: String?): Flow<PagingData<SearchMovieEntity>> {
        //return null
/*       val searchMovieList = searchMovieDataSource.getSearchMovieList(query).body()?.items
        if (searchMovieList != null) {
            emit(searchMovieList.map { searchMovieMapper.mapperToSearchMovie(it) })
        }*/
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchMoviePagingDataSource(query = query ?: "", service = service, searchMovieMapper = searchMovieMapper) }
        ).flow

    }
    //.flowOn(Dispatchers.IO)
    /*
    fun fetchMovieList() : Flow<PagingData<SearchMovieEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchMoviePagingDataSource(query = query, service = service, searchMovieMapper = searchMovieMapper) }
        ).flow
    }
     */
}