package com.wiserock.heruri.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiserock.heruri.R
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.databinding.FragmentCourseBinding
import com.wiserock.heruri.model.Homework
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.view.adapter.CourseAdapter
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection

class CourseFragment : Fragment() {

    private lateinit var viewModel: CourseViewModel
    private var homeworkIds: HashSet<Int> = hashSetOf()
    private var homeworkList: HashSet<Homework> = hashSetOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCourseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_course, container, false)
        viewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)
        CourseAdapter.viewModel = viewModel
        MyApp.homeworkIds.observe(viewLifecycleOwner, Observer { hashSet ->
            CourseAdapter.viewModel.homeworkList.observe(viewLifecycleOwner, Observer {
                println("it = $it")
                CourseAdapter.viewModel = CourseAdapter.viewModel
                CourseAdapter.itemSize = it.size
                println("it.size = ${it.size}")
                fragment_course_recycler.layoutManager = LinearLayoutManager(context)
                fragment_course_recycler.adapter = CourseAdapter()
            })

            hashSet.forEach {
                GlobalScope.launch(Dispatchers.IO) {
                    var done = false
                    val homeworkResponse = MyApp.getResponseWithUrl(
                        Value.BASE_URL + "mod/assign/view.php?id=$it",
                        Connection.Method.GET
                    )?.parse()
                    val course = homeworkResponse?.select("div.coursename h1")?.text()
                    val name =
                        homeworkResponse?.select("li a[title=과제]")?.text()
                    val check =
                        homeworkResponse?.select("td.cell.c1.lastcol")?.get(1)?.text()
                    if (check == "제출 완료") done = true
                    val deadline =
                        homeworkResponse?.select("tr td.cell.c1.lastcol")?.get(3)
                            ?.text()
                    homeworkList.add(
                        Homework(
                            course = course!!,
                            name = name!!,
                            done = done,
                            deadline = deadline!!
                        )
                    )
                }
            }
            val temp = arrayListOf<Homework>()
            homeworkList.forEach {
                temp.add(it)
            }
            println("temp = ${temp}")
            println("temp.size = ${temp.size}")
            CourseAdapter.viewModel.homeworkList.value = temp
        })

        val elements = MyApp.html.select(".course_link")
        val regex = "[0-9]{1,10}".toRegex()
        elements.forEach { element ->
            val id = regex.find(element.attr("href"))?.value?.toInt()
            GlobalScope.launch(Dispatchers.IO) {
                val homeworkResponse =
                    MyApp.getResponseWithUrl(
                        Value.BASE_URL + "course/view.php?id=${id}",
                        Connection.Method.GET
                    )?.parse()
                var i = 0
                withContext(Dispatchers.Main) {
                    homeworkResponse?.select("a")?.forEach {
                        val temp = it.attr("href")
                        val tempRegex =
                            """mod/assign/view.php\?id=[0-9]{1,10}""".toRegex()
                        val value = tempRegex.find(temp)?.value
                        if (value != null) {
                            val homeworkId = regex.find(value.toString())?.value?.toInt()!!
                            homeworkIds.add(homeworkId)
                        }
                    }
                    MyApp.homeworkIds.value = homeworkIds
                }

            }
        }
        return binding.root
    }
}