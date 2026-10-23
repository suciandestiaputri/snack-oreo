package com.example.saporeo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saporeo.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil email dari intent sebelumnya dan kunci field email
        val gmail = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        binding.etEmail.setText(gmail)

        binding.btnRegisterSubmit.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validasi: Semua field wajib diisi
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showError("Semua field wajib diisi!")
                return@setOnClickListener
            }

            // Validasi: Password minimal 6 karakter
            if (password.length < 6) {
                showError("Password minimal harus 6 karakter!")
                return@setOnClickListener
            }

            // Validasi: Username tidak boleh mengandung spasi
            if (username.contains(" ")) {
                showError("Username tidak boleh mengandung spasi!")
                return@setOnClickListener
            }

            // Simpan data ke SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit().apply {
                putString("reg_name", name)
                putString("reg_email", email)
                putString("reg_username", username)
                putString("reg_password", password)
                apply()
            }

            // Tampilkan dialog informasi berhasil & kembali ke Login
            MaterialAlertDialogBuilder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Akun Anda telah berhasil didaftarkan. Silakan login.")
                .setPositiveButton("OK") { _, _ ->
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                .show()
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