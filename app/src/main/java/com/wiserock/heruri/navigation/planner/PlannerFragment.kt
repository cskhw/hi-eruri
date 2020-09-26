package com.wiserock.heruri.navigation.planner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.wiserock.heruri.R
import com.wiserock.heruri.databinding.FragmentPlannerBinding
import com.wiserock.heruri.utils.Planner
import com.wiserock.heruri.utils.Planner.selectedCalendar
import com.wiserock.heruri.view.adapter.PlannerAdapter
import com.wiserock.heruri.view.adapter.PlannerViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_planner.view.*
import java.util.*

class PlannerFragment : Fragment() {
    var index = 0
    lateinit var mViewPagerAdapter: FragmentStatePagerAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_planner, container, false)
        val view = binding.root
        val viewPager = view.fragment_planner_viewPager
        val viewModel = ViewModelProvider(this).get(PlannerViewModel::class.java)
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        mViewPagerAdapter = PlannerViewPagerAdapter(childFragmentManager, 100)
        selectedCalendar = Calendar.getInstance()
        Planner.currentCalendar = Calendar.getInstance()
        PlannerAdapter.viewModel = viewModel
        viewPager.offscreenPageLimit = 3
        println("month = ${month}")
        viewModel.year.value = year.toString()
        viewModel.month.value = month.toString()
        val todayText = "${viewModel.year.value}년 ${viewModel.month.value + 1}월"
        view.fragment_planner_date.text = todayText
        println("viewModel.month.value = ${viewModel.month.value}")
        viewModel.month.observe(viewLifecycleOwner, Observer {
            view.fragment_planner_date.text =
                "${viewModel.year.value}년 ${(viewModel.month.value!!.toInt() + 1)}월"
        })
        viewPager.adapter = mViewPagerAdapter
        viewPager.currentItem = 49
        index = 49
        viewPager.addOnPageChangeListener(onPageChangeListener(viewModel))
        println("fragment1")
        return view
    }

    private fun onPageChangeListener(
        viewModel: PlannerViewModel
    ): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                println("position = ${position}")
                when {
                    position < index -> {
                        println("calendar.get(Calendar.MONTH) = ${selectedCalendar.get(Calendar.MONTH)}")
                        println("month -1 ")
                        selectedCalendar.set(
                            Calendar.MONTH,
                            selectedCalendar.get(Calendar.MONTH) - 1
                        )
                        viewModel.month.value = selectedCalendar.get(Calendar.MONTH).toString()
                        Planner.setDays(viewModel)
                        index = position
                        refresh()
                    }
                    position > index -> {
                        println("month + 1")
                        selectedCalendar.set(
                            Calendar.MONTH,
                            selectedCalendar.get(Calendar.MONTH) + 1
                        )
                        viewModel.month.value = selectedCalendar.get(Calendar.MONTH).toString()
                        Planner.setDays(viewModel)
                        index = position
                        refresh()
                    }
                    else -> {
                        println("month")
                        selectedCalendar.set(Calendar.MONTH, selectedCalendar.get(Calendar.MONTH))
                        viewModel.month.value = selectedCalendar.get(Calendar.MONTH).toString()
                        Planner.setDays(viewModel)
                        index = position
                        refresh()
                    }
                }
            }

            fun refresh() {
                mViewPagerAdapter.notifyDataSetChanged()
            }
        }
    }
}