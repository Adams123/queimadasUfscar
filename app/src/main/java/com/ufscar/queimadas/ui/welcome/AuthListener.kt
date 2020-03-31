package com.ufscar.queimadas.ui.welcome

import androidx.lifecycle.LiveData
import com.ufscar.queimadas.model.CreatedUserResponse

interface AuthListener {
    fun onStarted()
    fun onSuccess(createdResponse: LiveData<CreatedUserResponse>)
    fun onFailure(message: String)
}