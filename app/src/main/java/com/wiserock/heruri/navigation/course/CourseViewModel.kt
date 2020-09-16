package com.wiserock.heruri.navigation.course

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.model.Course
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.Notification

class CourseViewModel : ViewModel() {
    var homeworkList: MutableLiveData<ArrayList<Homework>> = MutableLiveData()
    var courseList: MutableLiveData<ArrayList<Course>> = MutableLiveData()
    var notificationList: MutableLiveData<ArrayList<Notification>> = MutableLiveData()
}
