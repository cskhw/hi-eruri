package com.wiserock.heruri.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.ItemCourseBinding
import com.wiserock.heruri.navigation.course.CourseViewModel

class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCourseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_course,
            parent,
            false
        )
        binding.course = CourseViewModel()
        return ItemCourseViewHolder(binding)
    }

    inner class ItemCourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item:) {
            binding.executePendingBindings()

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = holder as ItemCourseViewHolder

        binding.bind(position)
    }

    override fun getItemCount(): Int = Course
}