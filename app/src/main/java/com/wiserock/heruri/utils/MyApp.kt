package com.wiserock.heruri.utils

import android.app.Application
import com.wiserock.template.model.user.UserEntity
import org.jsoup.nodes.Document
import retrofit2.Response

object MyApp : Application() {
    var user: UserEntity? = null
    var isSigned: Boolean? = null
    lateinit var html: Document

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