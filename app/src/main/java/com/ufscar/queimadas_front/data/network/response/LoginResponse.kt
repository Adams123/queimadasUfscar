package com.ufscar.queimadas_front.data.network.response

data class LoginResponse(
    val email: String,
    val id: String,
    val roles: List<ResponseRole>,
    val token: String,
    val type: String,
    val username: String
)