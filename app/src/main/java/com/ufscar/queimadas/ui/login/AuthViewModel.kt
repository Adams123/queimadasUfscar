package com.ufscar.queimadas.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.ufscar.queimadas.rest.repositories.UserRepository

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            authListener?.onFailure("Usuário e senha n pode ser vazio mano, pqp tu é burro?")
            return
        }

        val createdResponse = UserRepository().userCreate(email!!, password!!)
        authListener?.onSuccess(createdResponse)
    }

}