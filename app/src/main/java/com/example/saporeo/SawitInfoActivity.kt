package com.example.saporeo

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.saporeo.databinding.ActivitySawitInfoBinding

class SawitInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySawitInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySawitInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Konfigurasi Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Portal Info Sawit"
            subtitle = "Kemitraan & Perkebunan Toke Sawit"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 2. Konfigurasi WebView
        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl("https://www.infosawit.com/")
        }

        // 3. Efek Toolbar hide/show saat di-scroll
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true)
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)
            }
        }

        // 4. Penanganan Back Button Modern
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    // 5. Aksi Tombol Panah Back di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}