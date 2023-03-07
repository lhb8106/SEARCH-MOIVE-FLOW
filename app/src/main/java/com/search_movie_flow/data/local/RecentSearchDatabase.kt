package com.search_movie_flow.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.search_movie_flow.data.dto.RecentSearchDto

@Database(entities = [RecentSearchDto::class], version = 1)
abstract class RecentSearchDatabase : RoomDatabase() {
    abstract fun recentSearchDao() : RecentSearchDao

    companion object{
        private const val dbName = "localDb"
        private var INSTANCE : RecentSearchDatabase?= null

        fun getInstance(context : Context) : RecentSearchDatabase? {
            if(INSTANCE == null) {
                synchronized(RecentSearchDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    RecentSearchDatabase::class.java, dbName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}