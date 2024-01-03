package com.example.fplaundry.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityRegisterBinding
import com.example.fplaundry.entity.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsernameSignUp.text.toString().trim()
            val pass = binding.etPasswordSignUp.text.toString().trim()

            if (CheckValidation(username,pass)){
                database.userDao().register(User(null, username, pass))
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun CheckValidation(nama: String, pass: String): Boolean {
        if(nama.isEmpty()){
            binding.etUsernameSignUp.error = "Masukkan Username Anda"
            binding.etPasswordSignUp.requestFocus()
        } else if (pass.isEmpty()) {
            binding.etPasswordSignUp.error = "Masukkan Password Anda"
            binding.etPasswordSignUp.requestFocus()
        } else {
            binding.etPasswordSignUp.error = null
            return true
        }
        Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
        return false
    }
}