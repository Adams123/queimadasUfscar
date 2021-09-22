package com.ufscar.queimadas_front.data.repository

import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.AuthAPI
import com.ufscar.queimadas_front.data.network.request.LoginForm
import com.ufscar.queimadas_front.data.network.request.RegisterForm
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthAPI,
    private val preferences: UserPreferences
) : BaseRepository(api){

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(LoginForm(username, password))
    }

    suspend fun signup(
        email: String,
        password: String
    ) = safeApiCall {
        api.register(RegisterForm(email, password))
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    suspend fun saveUserId(userId: String) {
        preferences.saveUserId(userId)
    }

}