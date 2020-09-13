package com.wiserock.heruri.navigation.course

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.utils.MyApp
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class CourseViewModel : ViewModel() {
    val liveCourse: MutableLiveData<Elements> = MutableLiveData()
    var courses: ArrayList<Element> = arrayListOf()

    fun getCourses(life: LifecycleOwner) {
        liveCourse.value = MyApp.html.select(".course_link")
        liveCourse.observe(life, Observer {
            liveCourse.value!!.forEach { courses.add(it) }
        })
    }
}
