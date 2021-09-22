package com.ufscar.queimadas_front.data.network.response

data class LocationCompleteResponse(
    val latitude: Double,
    val longitude: Double,
    val detectionDate: String,
    val username: String,
    val userEmail: String,
    var locationName: String
)
