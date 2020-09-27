package com.wiserock.heruri.navigation.planner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentPlanner2Binding
import com.wiserock.heruri.repository.PlannerRepo
import com.wiserock.heruri.utils.Planner
import com.wiserock.heruri.view.adapter.DayAdapter
import com.wiserock.heruri.view.adapter.PlanAdapter
import kotlinx.android.synthetic.main.fragment_planner2.view.*

class Planner2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlanner2Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_planner2, container, false)
        val viewModel = ViewModelProvider(this).get(PlannerViewModel::class.java)
        DayAdapter.dayAdapterViewModel = viewModel
        PlanAdapter.planAdapterViewModel = viewModel
        val plannerRepo = PlannerRepo()
        plannerRepo.getPlan()
        val view = binding.root
        val recycler = view.fragment2_planner_recycler
        recycler.layoutManager = StaggeredGridLayoutManager(7, 1)
        Planner.setDays(viewModel)
        println("fragment2")
        viewModel.dayArrayList.observe(viewLifecycleOwner, Observer {
            recycler.adapter?.notifyDataSetChanged()
            recycler.adapter = DayAdapter(viewLifecycleOwner)
        })
        return view
    }
}