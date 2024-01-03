package com.example.fplaundry.ui

import SharedPref
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fplaundry.R
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityPaymentBinding
import com.example.fplaundry.databinding.ActivityTujuanPengantaranBinding
import com.example.fplaundry.entity.Transaksi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PaymentActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var sharedPref: SharedPref
    private var uid = 0
    private var total = 0
    private lateinit var binding: ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(applicationContext)
        sharedPref = SharedPref(this)
        uid = sharedPref.getUid()!!.toInt()

        total = intent.extras?.getInt("total")!!
        binding.textViewTotalPurchase.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(total)


        binding.buttonbayar.setOnClickListener{
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Lanjutkan Proses Pembayaran")
                .setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                    val intent = intent.extras
                    val uid = sharedPref.getUid()
                    val nama = intent?.getString("nama")
                    val nohp = intent?.getInt("nohp")
                    val alamat = intent?.getString("alamat")
                    val pengambilan = intent?.getString("pengambilan")
                    val jenislayanan = intent?.getString("jenislayanan")
                    val catatan = intent?.getString("catatan")
                    val currentTimeMillis = System.currentTimeMillis()
                    val outputFormat = "MMM dd, yyyy h:mm a"
                    val formattedDate = convertMillisToString(currentTimeMillis, outputFormat)
                    database.transaksidao().insertTransaksi(Transaksi
                        (null,uid!!.toInt(),nama,nohp,alamat,pengambilan,jenislayanan,catatan,formattedDate,total))
                    Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, ProsesActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP ; Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(i)
                }.setNegativeButton("No") { dialog: DialogInterface, which: Int ->
                    dialog.cancel()
                }.show()
        }
    }

    fun convertMillisToString(millis: Long, outputFormat: String): String {
        val dateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
        val date = Date(millis)
        return dateFormat.format(date)
    }
}