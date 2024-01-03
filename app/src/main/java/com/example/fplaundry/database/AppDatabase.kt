package com.example.fplaundry.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fplaundry.dao.TransaksiDao
import com.example.fplaundry.dao.UserDao
import com.example.fplaundry.entity.Transaksi
import com.example.fplaundry.entity.User

@Database(
    entities = [Transaksi::class,User::class],
    version = 1,
    exportSchema = true
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun transaksidao() : TransaksiDao
    abstract fun userDao(): UserDao

    companion object{
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if(instance==null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "DatabaseLaundry")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}