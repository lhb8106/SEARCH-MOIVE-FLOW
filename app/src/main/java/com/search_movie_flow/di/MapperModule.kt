package com.search_movie_flow.di

import com.search_movie_flow.data.mapper.SearchMovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun searchMovieMapper() : SearchMovieMapper = SearchMovieMapper()
}