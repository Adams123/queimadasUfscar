package com.ufscar.queimadas.rest.endpoints

import com.ufscar.queimadas.model.User
import com.ufscar.queimadas.utils.Constants.GET_PATH
import com.ufscar.queimadas.utils.Constants.ID
import com.ufscar.queimadas.utils.Constants.LOGIN_PATH
import com.ufscar.queimadas.utils.Constants.PASSWORD
import com.ufscar.queimadas.utils.Constants.PUBLIC_PATH
import com.ufscar.queimadas.utils.Constants.REGISTRATION_PATH
import com.ufscar.queimadas.utils.Constants.USERNAME
import com.ufscar.queimadas.utils.Constants.USER_PATH
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface APILogin {

    @FormUrlEncoded
    @POST("$USER_PATH/$PUBLIC_PATH/$REGISTRATION_PATH")
    fun createUserBody(
        @Query(USERNAME) username: String,
        @Query(PASSWORD) password: String
    ): Call<ResponseBody>

    @GET("$USER_PATH/$GET_PATH")
    fun findUser(@Query(ID) id: UUID): Call<User>

    @DELETE(USER_PATH)
    fun deleteUser(@Query(ID) id: UUID): Call<String>

    @FormUrlEncoded
    @POST("$USER_PATH/$PUBLIC_PATH/$LOGIN_PATH")
    fun login(@Field(USERNAME) username: String, @Field(PASSWORD) password: String): Call<UUID>

    @GET("actuator/health")
    fun healthCheck(): Call<ResponseBody>
}