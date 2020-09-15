package com.wiserock.heruri

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.utils.AppPreferenceManager
import com.wiserock.heruri.utils.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Connection
import org.jsoup.Jsoup

class SplashActivity : AppCompatActivity() {

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
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}