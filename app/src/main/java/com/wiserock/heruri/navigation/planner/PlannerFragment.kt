package com.wiserock.heruri.navigation.planner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentPlannerBinding
import com.wiserock.heruri.utils.classes.Planner
import com.wiserock.heruri.view.adapter.PlannerAdapter
import com.wiserock.heruri.view.adapter.PlannerViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_planner.view.*
import java.util.*

class PlannerFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_planner, container, false)
        val view = binding.root
        val viewModel = ViewModelProvider(this).get(PlannerViewModel::class.java)
        Planner.selectedCalendar = Calendar.getInstance()
        Planner.currentCalendar = Calendar.getInstance()
        PlannerAdapter.viewModel = viewModel
        val viewPager = view.fragment_planner_viewPager
        val year = Planner.selectedCalendar.get(Calendar.YEAR)
        val month = Planner.selectedCalendar.get(Calendar.MONTH)
        viewModel.year.value = year.toString()
        viewModel.month.value = month.toString()
        val todayText = "${viewModel.year.value}년 ${viewModel.month.value + 1}월"
        println("viewModel.month.value = ${viewModel.month.value}")
        viewModel.month.observe(viewLifecycleOwner, Observer {
            view.fragment_planner_date.text =
                "${viewModel.year.value}년 ${(viewModel.month.value!!.toInt() + 1).toString()}월"
        })
        view.fragment_planner_date.text = todayText
        viewPager.adapter = PlannerViewPagerAdapter(childFragmentManager, 3)
        viewPager.currentItem = 1
        return view
    }
}