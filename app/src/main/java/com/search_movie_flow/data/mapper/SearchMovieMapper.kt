package com.search_movie_flow.data.mapper

import com.search_movie_flow.data.dto.SearchMovieDto
import com.search_movie_flow.domain.entity.SearchMovieEntity

class SearchMovieMapper {
    fun mapperToSearchMovie(searchMovieDto: SearchMovieDto.Item): SearchMovieEntity {
        return SearchMovieEntity(
            searchMovieDto.image,
            searchMovieDto.link,
            searchMovieDto.title,
            searchMovieDto.userRating
        )
    }
}