package com.wiserock.heruri.api.service

import com.wiserock.heruri.model.SignIn
import retrofit2.Call
import retrofit2.http.*

interface Auth {
    
    @POST("login/index.php")
    fun signIn(
        @Body signIn: SignIn
    ): Call<Void>
}