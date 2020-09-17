package com.wiserock.heruri.navigation.course

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wiserock.heruri.model.Course
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.model.Notification

class CourseViewModel : ViewModel() {
    var homeworkList: MutableLiveData<ArrayList<Homework>> = MutableLiveData()
    var courseList: MutableLiveData<ArrayList<Course>> = MutableLiveData()
    var notificationList: MutableLiveData<ArrayList<Notification>> = MutableLiveData()

    fun setOnClickCourseListener(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    }
}
