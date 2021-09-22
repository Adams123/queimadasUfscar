package com.ufscar.queimadas_front.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.data.network.response.LocationResponse
import com.ufscar.queimadas_front.data.repository.MapsRepository
import com.ufscar.queimadas_front.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: MapsRepository
) : BaseViewModel(repository) {

    private val _creationResponse: MutableLiveData<Resource<String>> = MutableLiveData()
    val creationResponse: LiveData<Resource<String>> get() = _creationResponse

    private val _pendingsResponse: MutableLiveData<Resource<List<LocationResponse>>> = MutableLiveData()
    val pendingsResponse : LiveData<Resource<List<LocationResponse>>> get() = _pendingsResponse

    private val _updateResponse: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    val updateResponse : LiveData<Resource<List<String>>> get() = _updateResponse

    fun create(latitude: Double, longitude: Double, file: MultipartBody.Part) = viewModelScope.launch {
        _creationResponse.value = Resource.Loading
        _creationResponse.value = repository.create(latitude, longitude, file)
    }

    fun create(latitude: Double, longitude: Double) = viewModelScope.launch {
        _creationResponse.value = Resource.Loading
        _creationResponse.value = repository.create(latitude, longitude)
    }

    fun fetchPendings() = viewModelScope.launch {
        _pendingsResponse.value = Resource.Loading
        _pendingsResponse.value = repository.findPendings()
    }

    fun updateSent(ids: List<String>) = viewModelScope.launch {
       _updateResponse.value = Resource.Loading
       _updateResponse.value = repository.updateLocations(ids)
    }
}