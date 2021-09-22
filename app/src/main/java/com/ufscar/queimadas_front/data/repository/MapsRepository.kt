package com.ufscar.queimadas_front.data.repository

import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.MapsApi
import com.ufscar.queimadas_front.data.network.request.LocationCreationForm
import com.ufscar.queimadas_front.utils.getLogger
import kotlinx.coroutines.flow.first
import okhttp3.MultipartBody
import javax.inject.Inject

class MapsRepository @Inject constructor(
    private val api: MapsApi,
    private val preferences: UserPreferences
    ) : BaseRepository(api) {

    suspend fun create(latitude: Double, longitude: Double, file: MultipartBody.Part) = safeApiCall {
        getLogger(MapsRepository::class.java).info(preferences.userId.first())
        api.create(LocationCreationForm(latitude, longitude), file, preferences.userId.first()!!)
    }

    suspend fun create(latitude: Double, longitude: Double) = safeApiCall {
        getLogger(MapsRepository::class.java).info(preferences.userId.first())
        api.create(LocationCreationForm(latitude, longitude), preferences.userId.first()!!)
    }

    suspend fun findPendings() = safeApiCall {
        api.findPendings()
    }

    suspend fun updateLocations(ids: List<String>) = safeApiCall {
        api.updateSent(ids)
    }

    suspend fun findLocations() = safeApiCall {
        api.getLocationInformation()
    }
}