package com.example.fplaundry.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fplaundry.R
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityRiwayatBinding
import com.example.fplaundry.databinding.ActivityUpdateAlamatBinding

class UpdateAlamatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateAlamatBinding
    private lateinit var database: AppDatabase
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(applicationContext)
        getData()
        binding.btnupdate.setOnClickListener {
            val alamat = binding.etAlamat.text.toString()
            database.transaksidao().updatealamat(alamat,id)
            Toast.makeText(this, "Data Alamat Berhasil Di Update", Toast.LENGTH_SHORT).show()
            val i = Intent(this, RiwayatActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP ; Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(i)
        }

    }

    fun getData(){
        val intent = intent.extras
        if (intent!=null){
            val trans = database.transaksidao().getTransaksiId(intent.getInt("id"))
            id = trans.id_transaksi!!.toInt()
        }
    }
}