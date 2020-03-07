package com.ufscar.queimadas.model

data class UserBearerToken(
    var user: User,
    var token: String?
)