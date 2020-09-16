package com.wiserock.heruri

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.navigation.course.CourseViewModel
import com.wiserock.heruri.utils.AppPreferenceManager
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.utils.interfaces.LoadCourse
import com.wiserock.heruri.utils.interfaces.LoadHomework
import com.wiserock.heruri.utils.interfaces.LoadNotification
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.HomeworkAdapter
import com.wiserock.heruri.view.adapter.NotificationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Connection
import org.jsoup.Jsoup

class SplashActivity : AppCompatActivity(), LoadHomework, LoadCourse, LoadNotification {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preference = AppPreferenceManager

        val username = preference.getString(applicationContext, "username")
        val password = preference.getString(applicationContext, "password")

        val loginUrl = Value.BASE_URL + "login/index.php"

        val formData: HashMap<String, String> = hashMapOf()

        try {
            formData["username"] = username!!
            formData["password"] = password!!
        } catch (e: Exception) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        GlobalScope.launch(Dispatchers.IO) {
            MyApp.index = Jsoup.connect(loginUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent("Android")
                .execute()
            val homepageHtml = MyApp.index.parse()
            MyApp.html = homepageHtml
            MyApp.cookies = MyApp.index.cookies()

            try {
                homepageHtml.select("div.main_login_find_idpw").first().text()
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            } catch (e: NullPointerException) {
                withContext(Dispatchers.Main) {
                    MyApp.loading.observe(this@SplashActivity, Observer {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    })
                    val viewModel =
                        ViewModelProvider(this@SplashActivity).get(CourseViewModel::class.java)
                    HomeworkAdapter.viewModel = viewModel
                    CourseAdapter.viewModel = viewModel
                    NotificationAdapter.viewModel = viewModel
                    loadCourse()
                    loadHomework(this@SplashActivity)
                    loadNotification()
                }
            }
        }
    }
}
