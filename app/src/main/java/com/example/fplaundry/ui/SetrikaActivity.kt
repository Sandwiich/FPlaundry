package com.example.fplaundry.ui

import SharedPref
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.R
import com.example.fplaundry.databinding.ActivityCuciSetrikaBinding
import com.example.fplaundry.databinding.ActivitySetrikaBinding
import java.text.NumberFormat
import java.util.Locale

class SetrikaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetrikaBinding
    private lateinit var sharedPref: SharedPref
    private var jumlahkaos = 1
    private var jumlahjean = 1
    private var jumlahjaket = 1
    private var jumlahkarpet = 1
    private var totalhargakaos = 800
    private var totalhargajean = 1000
    private var totalhargajaket = 1000
    private var totalhargakarpet = 25000
    private var totalsemua = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetrikaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = SharedPref(applicationContext)

        binding.bttambahkaos.setOnClickListener {
            jumlahkaos++
            binding.textqtykaos.text = jumlahkaos.toString()
            totalhargakaos = 800*jumlahkaos
            binding.tvhargakaos.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargakaos)
        }

        binding.btkurangkaos.setOnClickListener {
            if(jumlahkaos>=1){
                jumlahkaos--
                binding.textqtykaos.text = jumlahkaos.toString()
                totalhargakaos = 800*jumlahkaos
                binding.tvhargakaos.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargakaos)
            }
        }

        binding.bttambahjean.setOnClickListener {
            jumlahjean++
            binding.textqtyjeans.text = jumlahjean.toString()
            totalhargajean = 1000*jumlahjean
            binding.tvhargajean.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargajean)
        }

        binding.btkurangjean.setOnClickListener {
            if(jumlahjean>=1){
                jumlahjean--
                binding.textqtyjeans.text = jumlahjean.toString()
                totalhargajean = 1000*jumlahjean
                binding.tvhargajean.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargajean)
            }
        }

        binding.bttambahjaket.setOnClickListener {
            jumlahjaket++
            binding.textqtyjaket.text = jumlahjaket.toString()
            totalhargajaket = 1000*jumlahjaket
            binding.tvhargajaket.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargajaket)
        }

        binding.btkurangjaket.setOnClickListener {
            if(jumlahjaket>=1){
                jumlahjaket--
                binding.textqtyjaket.text = jumlahjaket.toString()
                totalhargajaket = 1000*jumlahjaket
                binding.tvhargajaket.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargajaket)
            }
        }

        binding.bttambahkarpet.setOnClickListener {
            jumlahkarpet++
            binding.textqtykarpet.text = jumlahkarpet.toString()
            totalhargakarpet = 25000*jumlahkarpet
            binding.tvhargakarpet.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargakarpet)
        }

        binding.btkurangkarpet.setOnClickListener {
            if(jumlahkarpet>=1){
                jumlahkarpet--
                binding.textqtykarpet.text = jumlahkarpet.toString()
                totalhargakarpet = 25000*jumlahkarpet
                binding.tvhargakarpet.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(totalhargakarpet)
            }
        }

        binding.buttonNextSetrika.setOnClickListener {
            totalsemua = totalhargajaket+totalhargajean+totalhargakaos+totalhargakarpet
            val intent = Intent(this,TujuanPengantaranActivity::class.java)
            intent.putExtra("totalsetrika",totalsemua)
            intent.putExtra("setrika","Setrika")
            startActivity(intent)
        }
    }
}