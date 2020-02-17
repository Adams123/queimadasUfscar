package com.ufscar.queimadas.service

import com.ufscar.queimadas.model.User
import com.ufscar.queimadas.model.UserTest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("/user/create")
    fun createUser(@Query("username") username: String, @Query("password") password: String): Call<User>

    @GET("/posts")
    fun testRest(): Call<List<UserTest>>
}