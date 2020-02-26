package com.ufscar.queimadas.rest.endpoints

import com.ufscar.queimadas.model.CreatedUserResponse
import com.ufscar.queimadas.model.User
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface APILogin {
    @POST("public/user/registration")
    fun createUser(@Query("username") username: String, @Query("password") password: String): Call<CreatedUserResponse>

    @GET("user/find")
    fun findUser(@Query("id") id: UUID): Call<User>

    @DELETE("user")
    fun deleteUser(@Query("id") id: UUID): Call<String>

    @POST("public/user/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<UUID>
}