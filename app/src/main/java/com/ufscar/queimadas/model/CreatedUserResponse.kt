package com.ufscar.queimadas.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CreatedUserResponse(
    @SerializedName("userId")
    val userId: UUID,
    @SerializedName("token")
    val token: String,
    var statusResponse: StatusResponse?
) {
    class StatusResponse(
        val status: Int,
        var errorMessage: String? = null
    )
}