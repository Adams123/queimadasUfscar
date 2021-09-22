package com.ufscar.queimadas_front.ui.firelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.data.network.response.LocationCompleteResponse
import com.ufscar.queimadas_front.data.repository.MapsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FireListViewModel @Inject constructor(
    private val repository: MapsRepository
) : ViewModel() {

    private val _locationsResponse: MutableLiveData<Resource<List<LocationCompleteResponse>>> = MutableLiveData()
    val locationsResponse : LiveData<Resource<List<LocationCompleteResponse>>> get() = _locationsResponse

    fun fetchLocations() = viewModelScope.launch {
        _locationsResponse.value = Resource.Loading
        _locationsResponse.value = repository.findLocations()
    }

}