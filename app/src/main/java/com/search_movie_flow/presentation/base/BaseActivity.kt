package com.search_movie_flow.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(
    private val inflate : (LayoutInflater) -> T
) : AppCompatActivity() {
    private val _binding: T? by lazy { inflate.invoke(layoutInflater) }
    val binding get() = _binding ?: throw NullPointerException("Binding is Null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }
}