package com.example.fplaundry.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fplaundry.databinding.ItemHistoryBinding
import com.example.fplaundry.entity.Transaksi
import com.example.fplaundry.ui.RiwayatActivity
import java.text.NumberFormat
import java.util.Locale

class RiwayatAdapter(private var listhistory: List<Transaksi>, private val riwayatItemClickInterface: RiwayatActivity) : RecyclerView.Adapter<RiwayatAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    interface RiwayatItemClickInterface {
        fun onDelete(position: Int)
        fun onUpdate(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatAdapter.HistoryViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatAdapter.HistoryViewHolder, position: Int) {
        val total = listhistory[position].total
        holder.binding.tvnama.text = listhistory[position].nama.toString()
        holder.binding.tvdate.text = listhistory[position].date.toString()
        holder.binding.tvalamat.text = listhistory[position].alamat.toString()
        holder.binding.tvtotal.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(total)
        holder.binding.ivedit.setOnClickListener {
            riwayatItemClickInterface.onUpdate(position)
        }
        holder.binding.ivdelete.setOnClickListener {
            riwayatItemClickInterface.onDelete(position)
        }
    }

    override fun getItemCount(): Int {
        return listhistory.size
    }
}