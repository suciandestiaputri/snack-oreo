package com.example.saporeo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.saporeo.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 1. Tombol untuk mengarahkan ke halaman input email registrasi
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, RegisterEmailActivity::class.java)
            startActivity(intent)
        }

        // 2. Logika Tombol Login
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Ambil data akun yang tersimpan dari SharedPreferences (hasil registrasi)
            val savedUsername = sharedPref.getString("reg_username", null)
            val savedPassword = sharedPref.getString("reg_password", null)

            // Kondisi 1: Akun bawaan (hardcoded / ketentuan lama: username == password)
            val isHardcodedLogin = (username.isNotEmpty() && username == password)

            // Kondisi 2: Akun sesuai dengan data SharedPreferences dari registrasi baru
            val isRegisteredLogin = (username == savedUsername && password == savedPassword && !username.isNullOrEmpty())

            // Jika salah satu kondisi terpenuhi, login berhasil
            if (isHardcodedLogin || isRegisteredLogin) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika gagal, tampilkan MaterialAlertDialog / AlertDialog error
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah!")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }
    }
}