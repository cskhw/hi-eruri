package com.wiserock.heruri.utils.interfaces

import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.Course
import com.wiserock.heruri.utils.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection

interface LoadCourse {
    fun loadCourse() {
        val elements = MyApp.html.select(".course_link")
        val regex = "[0-9]{1,10}".toRegex()
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                elements.forEach { element ->
                    val id = regex.find(element.attr("href"))?.value?.toInt()
                    val courseResponse =
                        MyApp.getResponseWithUrl(
                            Value.BASE_URL + "report/ubcompletion/user_progress_a.php?id=${id}",
                            Connection.Method.GET
                        )?.parse()
                    var elements1: ArrayList<String> = arrayListOf()
                    courseResponse?.select("td.text-left")?.forEach {
                        elements1.add(it.text())
                    }
                    var elements2: ArrayList<String> = arrayListOf()
                    var hrefs: ArrayList<String> = arrayListOf()
                    courseResponse?.select("tbody tr td[class=text-center]")?.forEach {
                        if (it.text() == "O") elements2.add(it.text())
                        val temp = it.select("a")
                        println("temp = ${temp}")

                        //여기까지 작업함
                    }
                    var elements3: ArrayList<String> = arrayListOf()
                    courseResponse?.select("tbody tr td[rowspan=2]")?.forEach {
                        if (it.text() == "O") elements3.add(it.text())
                    }
                    val element2Size = elements2.size - elements3.size
                    println("element2Size = ${element2Size}")
                    try {
                        for (i in 2..element2Size + 2) {
                            MyApp.course.add(
                                Course(
                                    id = id,
                                    name = elements1[i],
                                    href = "",
                                    professor = ""
                                )
                            )
                        }
                    } catch (e: Exception) {
                        println("안녕하신가")
                    }
                }
            }
        }
    }
}