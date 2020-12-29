package com.ufscar.queimadas.rest

import com.google.gson.GsonBuilder
import com.ufscar.queimadas.rest.endpoints.MapsAPI
import com.ufscar.queimadas.rest.endpoints.UserAPI
import com.ufscar.queimadas.utils.Constants.BASE_URL_PORT
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy


class RetrofitInitializer {
    val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val cookieJar = JavaNetCookieJar(cookieManager)
        val httpClient = OkHttpClient.Builder()
            .cookieJar(cookieJar)
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .header("Accept-Encoding", "gzip, deflate, br")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }


        val gsonBuilder = GsonBuilder().setLenient().create()

        Retrofit.Builder()
            .baseUrl(BASE_URL_PORT)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .client(httpClient.build())
            .build()
    }

    fun userService(): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }

    fun mapsService(): MapsAPI {
        return retrofit.create(MapsAPI::class.java)
    }

}
