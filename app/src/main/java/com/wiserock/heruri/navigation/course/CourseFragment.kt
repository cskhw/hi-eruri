package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.view.adapter.CourseAdapter
import kotlinx.android.synthetic.main.fragment_course.*

class CourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    private val viewModel = CourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseViewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)

        binding.course = viewModel

        val recycler = fragment_course_recycler
        recycler.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler.adapter = CourseAdapter()
        return binding.root
    }
}