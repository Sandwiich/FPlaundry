package com.example.fplaundry.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fplaundry.entity.Transaksi

@Dao
interface TransaksiDao {
    @Query("SELECT * FROM Transaksi ORDER BY id_transaksi ASC")
    fun getTransaksi(): List<Transaksi>

    @Query("SELECT * FROM Transaksi  WHERE uid = :userid")
    fun getTransaction(userid : Int): List<Transaksi>

    @Query("SELECT * FROM Transaksi  WHERE id_transaksi = :idtrans")
    fun getTransaksiId(idtrans : Int): Transaksi

    @Query("UPDATE Transaksi SET alamat = :alamat WHERE id_transaksi = :idtrans")
    fun updatealamat(alamat:String, idtrans: Int)

    @Insert
    fun insertTransaksi(transaksi: Transaksi)

    @Update
    fun updateTransaksi(transaksi: Transaksi)

    @Delete
    fun deleteTransaksi(transaksi: Transaksi)


}