package com.example.saporeo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saporeo.databinding.ActivityDetailSnackBinding

class DetailSnackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSnackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Konfigurasi teks judul pada Toolbar
        binding.toolbarDetail.title = "Detail Snack"
        binding.toolbarDetail.subtitle = "Katalog Produk Oreo"

        // Menampilkan ikon panah back bawaan Android
        binding.toolbarDetail.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        // Memberikan aksi klik pada tombol back untuk kembali ke MainActivity
        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }
    }
}