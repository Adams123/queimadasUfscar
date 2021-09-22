package com.ufscar.queimadas_front.data.network.response

import java.util.*

data class LocationResponse (
    val id: UUID,
    val latitude: Double,
    val longitude: Double,
    val image: FileDB? = null
)