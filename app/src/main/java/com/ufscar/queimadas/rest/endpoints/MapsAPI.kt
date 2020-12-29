package com.ufscar.queimadas.rest.endpoints

import com.google.android.gms.maps.model.LatLng
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MapsAPI {

    @FormUrlEncoded
    @POST("/maps")
    fun addPoint(@Field("POINT") point: LatLng): Call<ResponseBody>
}