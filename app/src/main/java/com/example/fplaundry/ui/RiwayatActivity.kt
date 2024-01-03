package com.example.fplaundry.ui

import SharedPref
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fplaundry.R
import com.example.fplaundry.adapter.RiwayatAdapter
import com.example.fplaundry.database.AppDatabase
import com.example.fplaundry.databinding.ActivityRiwayatBinding
import com.example.fplaundry.entity.Transaksi

class RiwayatActivity : AppCompatActivity() , RiwayatAdapter.RiwayatItemClickInterface {
    private lateinit var binding: ActivityRiwayatBinding
    private lateinit var database: AppDatabase
    private lateinit var sharedPref: SharedPref
    private lateinit var riwayatAdapter: RiwayatAdapter
    private var uid = 0
    private var listhistory = mutableListOf<Transaksi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(applicationContext)
        sharedPref = SharedPref(this)
        uid = sharedPref.getUid()!!.toInt()

        riwayatAdapter = RiwayatAdapter(listhistory,this)
        binding.rvHistory.apply {
            getData()
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = riwayatAdapter
            riwayatAdapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    fun getData(){
        listhistory.clear()
        listhistory.addAll(database.transaksidao().getTransaction(uid))
        riwayatAdapter.notifyDataSetChanged()
    }

    override fun onDelete(position: Int) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Confirmation Delete")
            .setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
                database.transaksidao().deleteTransaksi(listhistory[position])
                getData()
            }.setNegativeButton("No") { dialog: DialogInterface, which: Int ->
                dialog.cancel()
            }.show()
    }

    override fun onUpdate(position: Int) {
        val intent = Intent(this, UpdateAlamatActivity::class.java)
        intent.putExtra("id", listhistory[position].id_transaksi)
        startActivity(intent)
    }
}