package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wiserock.heruri.R
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.model.Course
import com.wiserock.heruri.utils.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Connection

class CourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var courses: ArrayList<Course> = arrayListOf()
        courseViewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        binding.course = courseViewModel

        val elements = MyApp.html.select(".course_link")
        println("elements = ${elements}")
        val regex = "[0-9]{1,10}".toRegex()


        elements.forEach {
            val id = regex.find(it.attr("href"))?.value?.toInt()
            GlobalScope.launch(Dispatchers.IO) {
                val temp =
                    MyApp.getResponseWithUrl(
                        Value.BASE_URL + "course/view.php?id=41648",
                        Connection.Method.GET
                    )
                val parse = temp?.parse()
                val homework = parse?.select("h3.sectionname")
                println("courseRegex = ${homework}")
            }

//            courses.add(
//                Course(
//                    id = id,
//                    name = it.select("div.course-title h3").text(),
//                    href = it.attr("href"),
//                    professor = it.select(".prof").text(),
//                    homework = ,
//                    videos =
//                )
//            )
        }

        return binding.root
    }


}