package com.search_movie_flow.data.repository

import android.util.Log
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
    private val searchMovieDataSource: SearchMovieDataSource
)  : SearchMovieRepository {
    override suspend fun getSearchMovieList(query: String?): Flow<List<SearchMovieEntity>> = flow {
        val searchMovieList = searchMovieDataSource.getSearchMovieList(query).body()?.items
        Log.e("TEST", "${searchMovieList.toString()}")
        if (searchMovieList != null) {
            emit(searchMovieList.map { searchMovieMapper.mapperToSearchMovie(it) })
        }
    }.flowOn(Dispatchers.IO)
}