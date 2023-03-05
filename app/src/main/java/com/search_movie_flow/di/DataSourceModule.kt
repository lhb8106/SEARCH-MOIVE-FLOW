package com.search_movie_flow.di

import com.search_movie_flow.data.api.NaverService
import com.search_movie_flow.data.datasource.remote.SearchMovieDataSource
import com.search_movie_flow.data.datasource.remote.SearchMovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun searchMovieDataSource(naverService: NaverService) : SearchMovieDataSource =
        SearchMovieDataSourceImpl(naverService)

}