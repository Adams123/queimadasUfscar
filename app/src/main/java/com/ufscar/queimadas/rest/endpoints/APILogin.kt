package com.ufscar.queimadas.rest.endpoints

import com.ufscar.queimadas.model.CreatedUserResponse
import com.ufscar.queimadas.model.User
import com.ufscar.queimadas.utils.Constants.GET_PATH
import com.ufscar.queimadas.utils.Constants.ID
import com.ufscar.queimadas.utils.Constants.LOGIN_PATH
import com.ufscar.queimadas.utils.Constants.PASSWORD
import com.ufscar.queimadas.utils.Constants.PUBLIC_PATH
import com.ufscar.queimadas.utils.Constants.REGISTRATION_PATH
import com.ufscar.queimadas.utils.Constants.USERNAME
import com.ufscar.queimadas.utils.Constants.USER_PATH
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface APILogin {
    @POST("$PUBLIC_PATH/$USER_PATH/$REGISTRATION_PATH")
    fun createUser(@Query(USERNAME) username: String, @Query(PASSWORD) password: String): Call<CreatedUserResponse>

    @GET("$USER_PATH/$GET_PATH")
    fun findUser(@Query(ID) id: UUID): Call<User>

    @DELETE(USER_PATH)
    fun deleteUser(@Query(ID) id: UUID): Call<String>

    @POST("$PUBLIC_PATH/$USER_PATH/$LOGIN_PATH")
    fun login(@Query(USERNAME) username: String, @Query(PASSWORD) password: String): Call<UUID>

    @POST("$PUBLIC_PATH/$USER_PATH/$REGISTRATION_PATH")
    suspend fun createUserSync(@Query(USERNAME) username: String, @Query(PASSWORD) password: String): CreatedUserResponse
}