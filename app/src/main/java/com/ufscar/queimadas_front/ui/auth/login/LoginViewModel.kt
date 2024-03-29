package com.ufscar.queimadas_front.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.data.network.response.LoginResponse
import com.ufscar.queimadas_front.data.repository.AuthRepository
import com.ufscar.queimadas_front.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>> get() = _loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token: String)= repository.saveAuthToken(token)
    suspend fun saveUserId(userId: String)= repository.saveUserId(userId)

}