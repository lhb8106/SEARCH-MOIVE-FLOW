package com.search_movie_flow.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.search_movie_flow.data.dto.RecentSearchEntity

@Dao
interface RecentSearchDao {
    @Insert
    suspend fun insertKeyword(keyword : RecentSearchEntity)

    @Query("SELECT * FROM local_search_keyword ORDER BY id DESC LIMIT 10")
    suspend fun getKeywordList() : List<RecentSearchEntity>

}