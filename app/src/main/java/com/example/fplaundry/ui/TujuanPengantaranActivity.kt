package com.example.fplaundry.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityTujuanPengantaranBinding
import com.example.fplaundry.entity.Transaksi

class TujuanPengantaranActivity : AppCompatActivity() {
    private var layanan = ""
    private var total = 0
    private lateinit var binding: ActivityTujuanPengantaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTujuanPengantaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cucisetrika = intent.extras?.getString("cucisetrika")
        val setrika = intent.extras?.getString("setrika")

        if (cucisetrika!=null){
            layanan = cucisetrika.toString()
        }else{
            layanan = setrika.toString()
        }

        binding.editJenisLayanan.setText(layanan)

        binding.buttonNext.setOnClickListener {
            val totalcucisetrika = intent.extras?.getInt("totalcucisetrika")
            val totalsetrika = intent.extras?.getInt("totalsetrika")

            if (totalcucisetrika!=0){
                total = totalcucisetrika!!
            }else{
                total = totalsetrika!!
            }
            binding.editJenisLayanan.setText(layanan)

            val nama = binding.ednama.text.toString()
            val nohp = binding.editNoHp.text.toString()
            val alamat =binding.editAlamat.text.toString()
            val pengambilan = binding.editPengambilan.text.toString()
            val jenislayanan = binding.editJenisLayanan.text.toString()
            val catatan = binding.editCatatan.text.toString()

            val intent = Intent(this,PaymentActivity::class.java)
            intent.putExtra("nama",nama)
            intent.putExtra("nohp",nohp.toInt())
            intent.putExtra("alamat",alamat)
            intent.putExtra("pengambilan",pengambilan)
            intent.putExtra("jenislayanan",jenislayanan)
            intent.putExtra("catatan",catatan)
            intent.putExtra("total",total)
            startActivity(intent)
        }
    }

}