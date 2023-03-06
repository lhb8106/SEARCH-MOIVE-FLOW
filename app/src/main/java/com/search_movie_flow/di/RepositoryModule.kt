package com.search_movie_flow.di

import com.search_movie_flow.data.repository.SearchMovieRepositoryImpl
import com.search_movie_flow.domain.repository.SearchMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchMovieRepository(
        repository: SearchMovieRepositoryImpl
    ) : SearchMovieRepository = repository
}