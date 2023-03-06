package com.search_movie_flow.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.search_movie_flow.data.dto.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun recentSearchDao() : RecentSearchDao

    companion object{
        private const val dbName = "localDb"
        private var INSTANCE : LocalDatabase?= null

        fun getInstance(context : Context) : LocalDatabase? {
            if(INSTANCE == null) {
                synchronized(LocalDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LocalDatabase::class.java, dbName)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}