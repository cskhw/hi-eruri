package com.wiserock.heruri.view.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemDayBinding
import com.wiserock.heruri.navigation.planner.PlannerViewModel
import kotlinx.android.synthetic.main.item_day.view.*

class PlannerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var viewModel: PlannerViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemDayBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_day,
                parent,
                false
            )
        val dp = binding.root.resources.displayMetrics.widthPixels
        binding.root.layoutParams = LinearLayout.LayoutParams(
            dp / 7, dp / 4 + 20
        )
        return PlannerViewHolder(binding)
    }

    override fun getItemCount(): Int = itemSize
    inner class PlannerViewHolder(val binding: ItemDayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.planner = viewModel
            binding.pos = position
            binding.executePendingBindings()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PlannerViewHolder
        val view = holder.itemView
        if (position % 7 == 0) view.item_day_day.setTextColor(Color.parseColor("#FF0000"))
        if (position % 7 == 6) view.item_day_day.setTextColor(Color.parseColor("#0000FF"))
        viewHolder.bind(position)
    }
}