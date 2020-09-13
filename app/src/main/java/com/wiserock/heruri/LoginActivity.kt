package com.wiserock.heruri

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.wiserock.heruri.api.Api
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.SignIn
import com.wiserock.heruri.utils.MyApp
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.FormElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.webkit.JavascriptInterface as JavascriptInterface

const val BASE_URL = "https://eruri.kangwon.ac.kr/"

class LoginActivity : AppCompatActivity() {
    var username: String = ""
    var password: String = ""
    val userAgent =
        "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"
    lateinit var source: String

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Api.update(Value.BASE_URL)

        val loginUrl = Value.BASE_URL + "login/index.php"
        GlobalScope.launch(Dispatchers.IO) {
            val loginForm: Connection.Response = Jsoup
                .connect(loginUrl)
                .method(Connection.Method.GET)
                .userAgent(userAgent)
                .execute()

            val usernameTag: FormElement =
                loginForm.parse().getElementsByTag("username").first() as FormElement
            val passwordTag: FormElement =
                loginForm.parse().getElementsByTag("password").first() as FormElement
            val loginButton: FormElement =
                loginForm.parse().getElementsByTag("main_login_btn").first() as FormElement
            usernameTag.`val`(username)
            passwordTag.`val`(password)

            println("loginButton.submt() = ${loginButton.submit()}")


            val formData: HashMap<String, String> = hashMapOf()

            formData["username"] = username
            formData["password"] = password

            val homepage: Connection.Response = Jsoup.connect(loginUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent("Android")
                .execute()
        }

//        val webView = activity_login_webView
//        webView.settings.javaScriptEnabled = true
//        webView.addJavascriptInterface(MyJavascriptInterface(), "Android")
//
//        // 태그 찾기
//        // 값 입력
//        // 보내기
//        webView.webViewClient = object : WebViewClient() {
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                view?.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('body')[0].innerHTML);")
//            }
//
//        }
//        webView.loadUrl("https://eruri.kangwon.ac.kr")


        activity_login_button.setOnClickListener {
            username = activity_login_username.text.toString()
            password = activity_login_password.text.toString()


        }
    }

    inner class MyJavascriptInterface {
        @JavascriptInterface
        fun getHtml(html: String) {
            source = html
        }

        @JavascriptInterface
        fun updateKeyword(keyword: String) {

        }
    }
}
