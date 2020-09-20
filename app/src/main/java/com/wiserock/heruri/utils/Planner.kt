package com.wiserock.heruri.utils

import com.wiserock.heruri.model.Day
import com.wiserock.heruri.navigation.planner.PlannerViewModel
import com.wiserock.heruri.view.adapter.PlannerAdapter
import java.util.*

object Planner {
    var currentCalendar: Calendar = Calendar.getInstance()
    var selectedCalendar: Calendar = Calendar.getInstance()


    private fun getStartDayOfMonth(selectedCalendar: Calendar): Int {
        selectedCalendar.set(Calendar.DAY_OF_MONTH, 1)
        return selectedCalendar.get(Calendar.DAY_OF_WEEK)
    }

    private fun getMonthOfDays(selectedCalendar: Calendar): Int {
        return selectedCalendar.getMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getCurrentDay() {
        selectedCalendar =
            currentCalendar
    }


    fun setDays(viewModel: PlannerViewModel) {
        println("selectedCalendar.month = ${selectedCalendar.get(Calendar.MONTH)}")
        val days = getMonthOfDays(selectedCalendar)
        val startDay = getStartDayOfMonth(selectedCalendar) - 1
        println("days = ${days}")
        println("startDay = ${startDay}")
        PlannerAdapter.itemSize = days + startDay
        val temp: ArrayList<Day> = arrayListOf()
        for (i in 0 until startDay) temp.add(Day(day = " "))
        for (i in startDay..days + startDay) temp.add(Day(day = (i - startDay + 1).toString()))
        println("1")
        viewModel.dayArrayList.value = temp
        println("viewModel.dayArrayList.value = ${viewModel.dayArrayList.value}")
    }
}