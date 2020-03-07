package com.ufscar.queimadas.rest

import com.google.gson.GsonBuilder
import com.ufscar.queimadas.rest.endpoints.APILogin
import com.ufscar.queimadas.utils.Constants.BASE_URL_PORT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInitializer {
    val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gsonBuilder = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL_PORT)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(httpClient.build())
            .build()
    }

    fun loginService(): APILogin {
        return retrofit.create(APILogin::class.java)
    }

}
