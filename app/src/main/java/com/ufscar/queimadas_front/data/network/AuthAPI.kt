package com.ufscar.queimadas_front.data.network

import com.ufscar.queimadas_front.data.network.request.LoginForm
import com.ufscar.queimadas_front.data.network.request.RegisterForm
import com.ufscar.queimadas_front.data.network.response.LoginResponse
import com.ufscar.queimadas_front.data.network.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI : BaseApi{

    @POST("api/auth/signin")
    suspend fun login(
        @Body loginForm: LoginForm
    ) : LoginResponse

    @POST("api/auth/signup")
    suspend fun register(
        @Body registerForm: RegisterForm
    ) : SignupResponse

}