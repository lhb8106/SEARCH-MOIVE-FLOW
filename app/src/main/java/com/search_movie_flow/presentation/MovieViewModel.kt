package com.search_movie_flow.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.search_movie_flow.domain.entity.SearchMovieEntity
import com.search_movie_flow.domain.repository.SearchMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val searchMovieRepository: SearchMovieRepository
) : ViewModel() {
    private val _searchMovie = MutableStateFlow<PagingData<SearchMovieEntity>?>(null)
    val searchMovie get() = _searchMovie.asStateFlow()

    //영화 검색 api
    fun searchMovieList(query: String?) {
        viewModelScope.launch {
            searchMovieRepository.getSearchMovieList(query)
                .catch {
                    Log.e("실패", "${it.printStackTrace()}")
                }
                .collectLatest {
                    _searchMovie.value = it
                    Log.e("성공", "${it}")
                }
        }
    }

}