package com.ufscar.queimadas.ui

import androidx.lifecycle.LiveData

interface Listener<T> {
    fun onStarted()
    fun onFailure(message: String)
    fun onSuccess(genericResponse: LiveData<T>)
}