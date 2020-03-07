package com.ufscar.queimadas.ui.login

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}