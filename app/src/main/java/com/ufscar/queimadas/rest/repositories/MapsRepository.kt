package com.ufscar.queimadas.rest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import com.ufscar.queimadas.model.PointResponse
import com.ufscar.queimadas.model.StatusResponse
import com.ufscar.queimadas.model.UserResponse
import com.ufscar.queimadas.rest.RetrofitInitializer
import com.ufscar.queimadas.rest.endpoints.MapsAPI
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsRepository {

    private val mapsApi: MapsAPI = RetrofitInitializer().mapsService()
    private val mapper = GsonBuilder().setPrettyPrinting().create()

    fun addPoint(point: LatLng): LiveData<PointResponse> {
        val mapsCreatedResponse = MutableLiveData<PointResponse>()

        mapsApi.addPoint(point).enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mapsCreatedResponse.value?.statusResponse?.errorMessage = t.message
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful) {
                    mapsCreatedResponse.value =
                        mapper.fromJson(response.body()?.string(), PointResponse::class.java)
                } else {
                    mapsCreatedResponse.value?.statusResponse = StatusResponse(
                        response.code(),
                        response.errorBody()?.string()
                    )
                }
            }

        })
        return mapsCreatedResponse
    }

}