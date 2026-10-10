package com.example.saporeo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.saporeo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMyProject.setOnClickListener {
            val intent = Intent(this, DetailSnackActivity::class.java)
            startActivity(intent)
        }
        binding.btnInfoSawit.setOnClickListener {
            val intent = Intent(this, SawitInfoActivity::class.java)
            startActivity(intent)
        }
    }
}