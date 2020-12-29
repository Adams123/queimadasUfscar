package com.ufscar.queimadas.rest

import android.content.Context
import com.ufscar.queimadas.sharedPrefs.SharedPrefsManager
import kotlinx.coroutines.newFixedThreadPoolContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor(context: Context) : Interceptor{

    var prefsManager : SharedPrefsManager = SharedPrefsManager.getInstance(context)


    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if(request.header("No-Authentication")==null) {
            val authToken = prefsManager.fetchAuthToken
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
        }

        return chain.proceed(request)
    }
}