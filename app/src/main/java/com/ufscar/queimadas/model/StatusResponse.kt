package com.ufscar.queimadas.model

data class StatusResponse(
    val status: Int,
    var errorMessage: String? = null
)
