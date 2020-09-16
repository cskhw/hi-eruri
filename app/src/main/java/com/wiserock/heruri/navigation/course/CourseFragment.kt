package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.wiserock.heruri.MainActivity
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.HomeworkAdapter
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
        val homeworkViewModel = HomeworkAdapter.viewModel
        val recyclerView = view.fragment_course_recycler
        MainActivity.dialog.visibility = View.VISIBLE
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.adapter = CourseAdapter()
        homeworkViewModel.homeworkList.observe(viewLifecycleOwner, Observer {
        })
        homeworkViewModel.courseList.observe(viewLifecycleOwner, Observer {
            MainActivity.dialog.visibility = View.GONE
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.adapter = CourseAdapter()
        })

        view.fragment_course_text1.setOnClickListener(setOnClickTextListener1(recyclerView))
        view.fragment_course_text2.setOnClickListener(setOnClickTextListener2(recyclerView))
        return binding.root
    }

    private fun setOnClickTextListener1(recyclerView: RecyclerView): View.OnClickListener? {
        return View.OnClickListener {
            recyclerView.adapter = HomeworkAdapter()
        }
    }

    private fun setOnClickTextListener2(recyclerView: RecyclerView): View.OnClickListener? {
        return View.OnClickListener {
            recyclerView.adapter = CourseAdapter()
        }
    }
}