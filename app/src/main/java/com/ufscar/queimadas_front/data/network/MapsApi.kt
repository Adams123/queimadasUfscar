package com.ufscar.queimadas_front.data.network

import com.ufscar.queimadas_front.data.network.request.LocationCreationForm
import com.ufscar.queimadas_front.data.network.response.LocationCompleteResponse
import com.ufscar.queimadas_front.data.network.response.LocationResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface MapsApi : BaseApi {

    @POST("api/location")
    @Multipart
    suspend fun create(
        @Part("locationDTO") creationForm: LocationCreationForm,
        @Part("image") filePart: MultipartBody.Part,
        @Part("userId") userId: String
    ) : String

    @POST("api/location")
    @Multipart
    suspend fun create(
        @Part("locationDTO") creationForm: LocationCreationForm,
        @Part("userId") userId: String
    ) : String

    @GET("api/location/pendings")
    suspend fun findPendings() : List<LocationResponse>

    @PUT("api/location/sent")
    suspend fun updateSent(locations: List<String>) : List<String>

    @GET("api/location/locationInfo")
    suspend fun getLocationInformation() : List<LocationCompleteResponse>

}