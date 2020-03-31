package com.ufscar.queimadas.rest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.ufscar.queimadas.model.CreatedUserResponse
import com.ufscar.queimadas.rest.RetrofitInitializer
import com.ufscar.queimadas.rest.endpoints.APILogin
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val loginApi: APILogin = RetrofitInitializer().loginService()
    private val mapper = GsonBuilder().setPrettyPrinting().create()

    fun userCreate(username: String, password: String): LiveData<CreatedUserResponse> {
        val loginResponse = MutableLiveData<CreatedUserResponse>()

        loginApi.createUserBody(username, password).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value?.statusResponse?.errorMessage = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    loginResponse.value =
                        mapper.fromJson(response.body()?.string(), CreatedUserResponse::class.java)
                } else {
                    loginResponse.value?.statusResponse = CreatedUserResponse.StatusResponse(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            }

        })

        return loginResponse
    }

    fun testCall(): LiveData<String> {
        val testResponse = MutableLiveData<String>()

        loginApi.healthCheck().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                testResponse.value = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                testResponse.value = response.body().toString()
            }
        })

        return testResponse
    }
}