package com.ufscar.queimadas_front.data.network

import android.content.Context
import androidx.lifecycle.asLiveData
import com.ufscar.queimadas_front.BuildConfig
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.utils.Constants.Companion.BASE_URL
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val preferences: UserPreferences) {

    fun<Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

/*    private fun buildTokenApi(): TokenRefreshApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TokenRefreshApi::class.java)
    }*/

    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                    val token = preferences.authToken.asLiveData().value
                    if(token?.isNotBlank()==true)
                        it.addHeader("Authorization", "Bearer $token")
                }.build())
            }.also { client ->
                authenticator?.let { client.authenticator(it) }
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}