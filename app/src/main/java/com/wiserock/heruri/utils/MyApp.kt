package com.wiserock.template.utils

import android.app.Application
import com.wiserock.template.model.user.UserEntity
import retrofit2.Response

class MyApp : Application() {
    var user: UserEntity? = null
    
    fun <T> debugResponse(response: Response<T>) {
        println("response.body() = ${response.body()}")
        println("response.code() = ${response.code()}")
        println("response.message() = ${response.message()}")
        println("response.errorBody() = ${response.errorBody()}")
        println("response.headers() = ${response.headers()}")
    }
}