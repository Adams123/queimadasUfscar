package com.ufscar.queimadas_front.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.data.network.response.LoginResponse
import com.ufscar.queimadas_front.data.repository.UserRepository
import com.ufscar.queimadas_front.ui.base.BaseViewModel

class UserViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {
    private val _response : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val response: LiveData<Resource<LoginResponse>> get() = _response
}