package com.ufscar.queimadas.rest

import com.ufscar.queimadas.service.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.15.12:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun loginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }

}
