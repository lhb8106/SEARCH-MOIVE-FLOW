package com.search_movie_flow.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation

object BindingAdapter {

    private val EMPTY_PROFILE = com.search_movie_flow.R.drawable.rectangle_fill_gray_8dp

    @JvmStatic
    @BindingAdapter("load_movie_image")
    fun loadMovieImage(view: ImageView, url: String?) {
        with(view) {
            if (url.isNullOrEmpty()) {
                view.load(context.getDrawable(EMPTY_PROFILE)) { loadImage() }
            } else {
                view.load(url) { loadImage() }
            }
        }
    }

    private fun ImageRequest.Builder.loadImage() {
        crossfade(true)
        this.transformations(RoundedCornersTransformation(5.0f))
    }
}