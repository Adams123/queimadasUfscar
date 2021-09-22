package com.ufscar.queimadas_front.ui.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.data.network.response.SignupResponse
import com.ufscar.queimadas_front.data.repository.AuthRepository
import com.ufscar.queimadas_front.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _registrationResponse : MutableLiveData<Resource<SignupResponse>> = MutableLiveData()
    val registrationResponse: LiveData<Resource<SignupResponse>> get() = _registrationResponse

    fun signup(email: String, password: String) = viewModelScope.launch {
        _registrationResponse.value = Resource.Loading
        _registrationResponse.value = repository.signup(email, password)
    }

}