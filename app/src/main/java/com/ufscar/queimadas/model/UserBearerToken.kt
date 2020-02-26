package com.ufscar.queimadas.model

import java.util.*

data class UserBearerToken(
    val user: User,
    val token: UUID
)