package com.wiserock.heruri.navigation.planner

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiserock.heruri.model.day.Day
import com.wiserock.template.model.AppDatabase

class PlannerViewModel : ViewModel() {
    val dayArrayList: MutableLiveData<ArrayList<Day>> = MutableLiveData()
    val year: MutableLiveData<String> = MutableLiveData()
    val month: MutableLiveData<String> = MutableLiveData()

    fun onClickDayButton(context: Context, day: Day) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder
            .setTitle("일정")
            .create()
        val appDatabase: AppDatabase = AppDatabase.getInstance(context)
        val dayDAO = appDatabase.dayDAO()
        val plans = dayDAO.getAllPlan()
    }
}