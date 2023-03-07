package com.search_movie_flow

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SearchMovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
