package com.example.fplaundry.ui

import SharedPref
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.R
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbout)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        database = AppDatabase.getInstance(applicationContext)
        sharedPref = SharedPref(this)
        binding.tvUser.text = sharedPref.getUsername().toString()

        binding.cucisetrika.setOnClickListener {
            startActivity(Intent(this, CuciSetrikaActivity::class.java))
        }

        binding.setrika.setOnClickListener {
            startActivity(Intent(this, SetrikaActivity::class.java))
        }

        binding.history.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnLogOut -> {
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Peringatan !!! ")
                    .setMessage("Apakah Anda Ingin Log Out ? ")
                    .setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                        sharedPref.isLogOut()
                        Toast.makeText(this, "Log Out Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }.setNegativeButton("No") { dialog: DialogInterface, which: Int ->
                        dialog.cancel()
                    }.show()
            }
        }
        return true
    }

}