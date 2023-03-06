package com.search_movie_flow.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_search_keyword")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    var keyword : String
)
