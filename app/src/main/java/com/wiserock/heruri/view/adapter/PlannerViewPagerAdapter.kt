package com.wiserock.heruri.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.wiserock.heruri.navigation.planner.Planner2Fragment

class PlannerViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val size: Int
) : FragmentStatePagerAdapter(fragmentManager, size) {
    override fun getCount(): Int = size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Planner2Fragment()
            1 -> Planner2Fragment()
            2 -> Planner2Fragment()
            else -> Planner2Fragment()
        }
    }
}