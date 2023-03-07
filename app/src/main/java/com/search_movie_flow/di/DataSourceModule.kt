package com.search_movie_flow.di

import com.search_movie_flow.data.api.SearchMovieService
import com.search_movie_flow.data.datasource.SearchMovieDataSource
import com.search_movie_flow.data.datasource.SearchMovieDataSourceImpl
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
    fun searchMovieDataSource(service: SearchMovieService) : SearchMovieDataSource =
        SearchMovieDataSourceImpl(service)

}