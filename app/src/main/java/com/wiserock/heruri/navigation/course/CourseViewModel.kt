package com.wiserock.heruri.navigation.course

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.model.Course

class CourseViewModel : ViewModel() {
    val liveCourse: MutableLiveData<ArrayList<Course>> = MutableLiveData()
    fun getCourses(life: LifecycleOwner, courses: ArrayList<Course>) {
        liveCourse.value = courses
        liveCourse.observe(life, Observer {
            liveCourse.value!!.forEach { courses.add(it) }
        })
    }

}
