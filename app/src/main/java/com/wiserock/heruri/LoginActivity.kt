package com.wiserock.heruri

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wiserock.heruri.api.Api
import com.wiserock.heruri.api.Value
import com.wiserock.heruri.model.SignIn
import com.wiserock.heruri.utils.MyApp
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var username: String
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Api.update(
            Value.BASE_URL
        )
        setContentView(R.layout.activity_login)
        activity_login_button.setOnClickListener(setOnClickLoginButtonListener())
    }

    private fun setOnClickLoginButtonListener(): View.OnClickListener? {
        return View.OnClickListener {
            username = activity_login_password.text.toString()
            password = activity_login_username.text.toString()
            println("username = $username")
            println("password = $password")
            Api.auth.signIn(SignIn(username, password)).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("t.message = ${t.message}")
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    MyApp.debugResponse(response)
                    println("XSRF-TOKEN= ${response.headers().get("XSRF-TOKEN")}")
                }
            })
        }
    }
}
