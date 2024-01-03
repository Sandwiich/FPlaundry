package com.example.fplaundry.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaksi(
    @PrimaryKey(autoGenerate = true)
    var id_transaksi : Int? = null,
    var uid: Int? = null,
    var nama : String? = null,
    var no_hp : Int? = null,
    var alamat : String? = null,
    var jam_jemput : String? = null,
    var jenis_layanan : String? = null,
    var catatan : String? = null,
    var date: String? = null,
    var total: Int? = null,

)
