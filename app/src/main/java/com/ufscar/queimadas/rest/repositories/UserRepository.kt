package com.ufscar.queimadas.rest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.model.StatusResponse
import com.ufscar.queimadas.rest.RetrofitInitializer
import com.ufscar.queimadas.rest.endpoints.UserAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserRepository {

    private val userApi: UserAPI = RetrofitInitializer().userService()
    private val mapper = GsonBuilder().setPrettyPrinting().create()

    fun userCreate(username: String, password: String): LiveData<UserResponse> {
        val userCreatedResponse = MutableLiveData<UserResponse>()

        userApi.createUserBody(username, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                userCreatedResponse.value?.statusResponse?.errorMessage = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    userCreatedResponse.value =
                        mapper.fromJson(response.body()?.string(), UserResponse::class.java)

                } else {
                    userCreatedResponse.value?.statusResponse = StatusResponse(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            }

        })

        return userCreatedResponse
    }

    fun userLogin(username: String, password: String): LiveData<UserResponse> {
        val loginResponse = MutableLiveData<UserResponse>()

        userApi.login(username, password).enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value?.statusResponse?.errorMessage = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    loginResponse.value = mapper.fromJson(response.body()?.string(), UserResponse::class.java)
                } else {
                    loginResponse.value?.statusResponse = StatusResponse(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            }

        })

        return loginResponse
    }
}