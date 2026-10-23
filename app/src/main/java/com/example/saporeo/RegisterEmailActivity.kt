package com.example.saporeo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saporeo.databinding.ActivityRegisterEmailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val email = binding.etEmailGmail.text.toString().trim()

            // Validasi 1: Tidak boleh kosong
            if (email.isEmpty()) {
                showError("Email tidak boleh kosong!")
                return@setOnClickListener
            }

            // Validasi 2: Harus menggunakan domain @gmail.com
            if (!email.endsWith("@gmail.com")) {
                showError("Email harus menggunakan domain @gmail.com!")
                return@setOnClickListener
            }

            // Jika valid, teruskan ke RegisterActivity dengan membawa data email
            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra("EXTRA_EMAIL", email)
            }
            startActivity(intent)
        }
    }

    private fun showError(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Validasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}