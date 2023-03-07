package com.search_movie_flow.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.search_movie_flow.databinding.ActivityWebViewBinding
import com.search_movie_flow.presentation.base.BaseActivity
import com.search_movie_flow.presentation.base.BaseWebChromeClient


class WebViewActivity : BaseActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate) {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUrlFromIntent()
        initWebView()
        initToolbar()

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.wvContent.canGoBack()) {
            binding.wvContent.goBack()
            true
        } else return super.onKeyDown(keyCode, event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else return super.onOptionsItemSelected(item)
    }

    private fun loadUrlFromIntent() {
        this.url = intent.getStringExtra("url")
    }

    private fun initWebView() {
        val customWebChromeClient = BaseWebChromeClient()
        customWebChromeClient.titleData.observe(this) {
            supportActionBar?.title = customWebChromeClient.titleData.value
        }

        with(binding.wvContent) {
            with(settings) {
                loadWithOverviewMode = true
                useWideViewPort = true
                setSupportZoom(true)
                builtInZoomControls = false
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
                domStorageEnabled = true
            }

            webViewClient = BaseWebViewClient()
            webChromeClient = customWebChromeClient
        }
        if (url != null) binding.wvContent.loadUrl(url!!)
    }


    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

class BaseWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (view != null && url != null)
            view.loadUrl(url)
        return true
    }
}