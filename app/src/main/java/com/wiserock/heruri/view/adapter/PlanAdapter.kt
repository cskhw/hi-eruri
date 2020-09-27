package com.wiserock.heruri.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemPlanBinding
import com.wiserock.heruri.navigation.planner.PlannerViewModel
import kotlinx.android.synthetic.main.item_plan.view.*

class PlanAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val position: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var planAdapterViewModel: PlannerViewModel
    }

    // 오늘은 여기까지 포지션 값을 이용해서 데이에서 값 가져와서 리사이클러 뷰에 보여주기 해야됨
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemPlanBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_plan,
            parent,
            false
        )

        return if () else
    }

    inner class PlanViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.pos = position
            binding.plan = planAdapterViewModel
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = itemSize

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as PlanViewHolder
        val view = holder.itemView
        view.item_plan_title


    }
}