package com.ufscar.queimadas_front.data.network.request

data class RegisterForm(
    val username: String,
    val email: String,
    val role: List<RequestRole> = listOf(),
    val password: String
) {
    constructor(email: String, password: String) : this(email, email, listOf(), password)
}
