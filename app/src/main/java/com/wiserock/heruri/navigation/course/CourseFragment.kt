package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment() {

    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        val view = binding.root
        CourseAdapter.viewModel.homeworkList.value = MyApp.homeworkArrayList
        view.fragment_course_recycler.adapter = CourseAdapter()
        view.fragment_course_text1.setOnClickListener(setOnClickTextListener1())
        view.fragment_course_text1.setOnClickListener(setOnClickTextListener2())
        return binding.root
    }

    private fun setOnClickTextListener1(): View.OnClickListener? {
        return View.OnClickListener {
            
        }
    }

    private fun setOnClickTextListener2(): View.OnClickListener? {
        return View.OnClickListener {

        }
    }
}