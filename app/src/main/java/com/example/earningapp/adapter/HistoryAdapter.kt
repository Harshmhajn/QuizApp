package com.example.earningapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.databinding.HistoryitemBinding
import com.example.earningapp.modelClass.HistorymodelClass

class HistoryAdapter(var listHistory:ArrayList<HistorymodelClass>) : RecyclerView.Adapter<HistoryAdapter.HistoryCoinViewHolder>() {
    class HistoryCoinViewHolder(var binding: HistoryitemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCoinViewHolder {
         return HistoryCoinViewHolder(HistoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = listHistory.size

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {
        holder.binding.time.text = listHistory[position].timeAndDate
        holder.binding.HistoryCoin.text = listHistory[position].coin
    }
}