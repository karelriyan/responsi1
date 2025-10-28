package com.example.responsi1mobileh1d023085.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023085.data.model.CoachResponse
import com.example.responsi1mobileh1d023085.databinding.ItemCoachBinding

class CoachAdapter(
    private var items: List<CoachResponse>,
    private val onItemClick: (CoachResponse) -> Unit = {}
) : RecyclerView.Adapter<CoachAdapter.VH>() {

    inner class VH(val b: ItemCoachBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemCoachBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = items[position]
        holder.b.tvCoachName.text = c.name ?: "-"
        holder.b.tvCoachNation.text = c.nationality ?: "-"
        holder.b.tvCoachDob.text = c.dateOfBirth ?: "-"
        holder.b.root.setOnClickListener { onItemClick(c) }
    }

    override fun getItemCount(): Int = items.size

    fun setData(list: List<CoachResponse>) {
        items = list
        notifyDataSetChanged()
    }
}
