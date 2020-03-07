package com.ufscar.queimadas.utils

object Constants {
    private const val BASE_URL: String = "http://187.10.31.216"
    private const val BASE_PORT: String = "8666"
    const val BASE_URL_PORT = "$BASE_URL:$BASE_PORT"

    /* API CONSTANTS */
    const val PUBLIC_PATH = "public"
    const val USER_PATH = "user"

    const val LOGIN_PATH = "login"
    const val REGISTRATION_PATH = "registration"

    const val GET_PATH = "find"

    /* PARAM CONSTANTS */
    const val ID = "id"
    const val USERNAME = "username"
    const val PASSWORD = "password"
}