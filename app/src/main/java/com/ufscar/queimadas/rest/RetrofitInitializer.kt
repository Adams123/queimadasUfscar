package com.ufscar.queimadas.rest

import com.google.gson.GsonBuilder
import com.ufscar.queimadas.rest.endpoints.APILogin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInitializer {
    private lateinit var retrofit: Retrofit

    fun loginService(): APILogin {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gsonBuilder = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://187.10.31.216:8666/")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(httpClient.build())
            .build()
        return retrofit.create(APILogin::class.java)
    }

}
