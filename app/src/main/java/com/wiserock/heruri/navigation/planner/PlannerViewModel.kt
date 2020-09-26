package com.wiserock.heruri.navigation.planner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.model.day.Day

class PlannerViewModel : ViewModel() {
    val dayArrayList: MutableLiveData<ArrayList<Day>> = MutableLiveData()
    val year: MutableLiveData<String> = MutableLiveData()
    val month: MutableLiveData<String> = MutableLiveData()
}