package com.example.fplaundry.ui

import SharedPref
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.R

class WelcomeActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        sharedPref = SharedPref(this)
        val btnNext:Button = findViewById(R.id.buttonNext)

        //event button next click
        btnNext.setOnClickListener{
            checkAuth()
        }

    }

    private fun checkAuth() {
        if (sharedPref.isLogIn()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}