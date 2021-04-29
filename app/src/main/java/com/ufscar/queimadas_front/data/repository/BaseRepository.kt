package com.ufscar.queimadas_front.data.repository

import com.ufscar.queimadas_front.data.network.BaseApi
import com.ufscar.queimadas_front.data.network.SafeApiCall

abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {
    suspend fun logout() = safeApiCall { api.logout() }
}