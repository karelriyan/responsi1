package com.example.responsi1mobileh1d023085.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023085.data.model.PlayerResponse
import com.example.responsi1mobileh1d023085.databinding.ItemPlayerBinding

class PlayerAdapter(
    private var items: List<PlayerResponse>,
    private val onItemClick: (PlayerResponse) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.VH>() {

    inner class VH(val b: ItemPlayerBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(h: VH, i: Int) {
        val p = items[i]
        h.b.root.setCardBackgroundColor(positionColor(p.position))
        h.b.root.setOnClickListener { onItemClick(p) }
        h.b.tvName.text = p.name
        h.b.tvNation.text = p.nationality
    }

    override fun getItemCount() = items.size

    fun setData(list: List<PlayerResponse>) {
        items = list
        notifyDataSetChanged()
    }

    private fun positionColor(pos: String?) = when (pos?.lowercase()) {
        "goalkeeper" -> Color.YELLOW
        "defender" -> Color.parseColor("#2196F3")
        "midfielder" -> Color.parseColor("#4CAF50")
        "offence", "forward", "attacker" -> Color.parseColor("#F44336")
        else -> Color.LTGRAY
    }
}
