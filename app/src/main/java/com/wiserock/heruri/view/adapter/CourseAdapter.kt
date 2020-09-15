package com.wiserock.heruri.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemCourseBinding
import com.wiserock.heruri.navigation.course.CourseViewModel
import kotlinx.android.synthetic.main.item_course.view.*


class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        var itemSize = 0
        lateinit var viewModel: CourseViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCourseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_course,
            parent,
            false
        )
        binding.course = viewModel
        return ItemCourseViewHolder(binding)
    }

    inner class ItemCourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.pos = position
            binding.executePendingBindings()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val binding = holder as ItemCourseViewHolder
        binding.bind(position)
        try {
            if (viewModel.homeworkList.value?.get(position)?.done!!) {
                view.setBackgroundResource(R.color.white)
                view.item_course_text1.setTextColor(R.color.black)
                view.item_course_text2.setTextColor(R.color.black)
                view.item_course_text3.setTextColor(R.color.black)
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            println("리스트 초기화 중...")
        }
    }

    override fun getItemCount(): Int = itemSize

}