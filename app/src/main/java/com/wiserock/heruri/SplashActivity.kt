package com.wiserock.heruri

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
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

            val homepage: Connection.Response = Jsoup.connect(loginUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent("Android")
                .execute()
            val homepageHtml = homepage.parse()

            MyApp.html = homepageHtml
            try {
                homepageHtml.select("div.main_login_find_idpw").first().text()
            } catch (e: NullPointerException) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(
                applicationContext,
                "아이디 혹은 비밀번호가 잘못되었습니다.",
                Toast.LENGTH_LONG
            )
                .show()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)

    }
}