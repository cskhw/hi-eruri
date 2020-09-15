package com.wiserock.heruri.utils

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.wiserock.template.model.user.UserEntity
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Response

object MyApp : Application() {
    var user: UserEntity? = null
    var isSigned: Boolean? = null
    lateinit var html: Document
    lateinit var index: Connection.Response
    lateinit var cookies: Map<String, String>
    var homeworkIds: MutableLiveData<HashSet<Int>> = MutableLiveData()
    fun getResponseWithUrl(
        url: String,
        method: Connection.Method,
        formData: HashMap<String, String>? = hashMapOf()
    ): Connection.Response? {
        return Jsoup.connect(url)
            .data(formData)
            .cookies(cookies)
            .method(method)
            .userAgent("Android")
            .execute()
    }

    fun <T> debugResponse(response: Response<T>) {
        println("\n================Start Response Debug==================")
        println("response.body() = ${response.body()}")
        println("response.code() = ${response.code()}")
        println("response.message() = ${response.message()}")
        println("response.errorBody() = ${response.errorBody()}")
        println("response.headers() = ${response.headers()}")
        println("response.raw() = ${response.raw()}")
        println("================End Response Debug==================")
    }
}