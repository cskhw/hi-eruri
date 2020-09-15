package com.wiserock.heruri.navigation.course

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.model.Homework

class CourseViewModel : ViewModel() {
    var homeworkList: MutableLiveData<ArrayList<Homework>> = MutableLiveData()

}
