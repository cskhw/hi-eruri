package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment() {

    private lateinit var viewModel: CourseViewModel
    private var homeworkIds: HashSet<Int> = hashSetOf()
    private var homeworkList: HashSet<Homework> = hashSetOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(CourseViewModel::class.java)
        CourseAdapter.viewModel = viewModel
        viewModel.homeworkList.value = MyApp.homeworkArrayList
        view.fragment_course_recycler.adapter = CourseAdapter()
        return binding.root
    }
}