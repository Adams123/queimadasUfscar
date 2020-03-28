package com.ufscar.queimadas.model

open class BaseResponse(
    val status: Int,
    var errorMessage: String? = null
)
